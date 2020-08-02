package com.toyproject.resourcemanagement.service;

import com.toyproject.resourcemanagement.model.Server;
import com.toyproject.resourcemanagement.repository.ServerRepository;
import com.toyproject.resourcemanagement.thread.ActivationTimer;
import com.toyproject.resourcemanagement.thread.AllocationThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

@Service
public class CloudServerProvider {
    private static final Logger log = LoggerFactory.getLogger(CloudServerProvider.class);
    public int numberOfAllocatedServers=0;

    @Autowired
    ServerRepository serverRepository;

    private Map<Integer,Integer> waitingServers=new HashMap<>();

    public synchronized Server getIdOfAvailable(int size){
        List<Server> servers=new ArrayList<>();
        serverRepository.findAll().forEach(servers::add);
        Collections.sort(servers);
        boolean flag=false;
        Server targetServer=null;
        for(Server server:servers){
            if((!waitingServers.containsKey(server.getServerId())&&server.getFreeSize()>=size)||(waitingServers.containsKey(server.getServerId())&&(100-waitingServers.get(server.getServerId()))>=size)){
                if(waitingServers.get(server.getServerId())!=null){
                    waitingServers.put(server.getServerId(),waitingServers.get(server.getServerId())+size);
                }
                targetServer=server;
                flag=true;
                break;
            }
        }
        if(flag){
            return targetServer;
        }else{
            return null;
        }
    }

    public void allocateServer(int size){
        Server targetServer=getIdOfAvailable(size);
        AllocationThread allocationThread;
        if(targetServer!=null) {
            allocationThread=new AllocationThread(serverRepository,targetServer.getServerId(),size);
            allocationThread.start();
        }else{
            int spunServerId=spinServer();
            waitingServers.put(spunServerId,size);
            allocationThread=new AllocationThread(serverRepository,spunServerId,size);
            allocationThread.start();
        }
    }


    public int spinServer(){
        Server server= new Server(++numberOfAllocatedServers,0,100,false);
        ActivationTimer activationTimer=new ActivationTimer(serverRepository,waitingServers,server.getServerId());
        serverRepository.save(server);
        activationTimer.start();
        return server.getServerId();
    }
}
