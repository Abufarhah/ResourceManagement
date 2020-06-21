package com.toyproject.resourcemanagement.thead;
import com.toyproject.resourcemanagement.model.Server;
import com.toyproject.resourcemanagement.repository.ServerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class ActivationTimer implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(ActivationTimer.class);
    Thread thread;
    ServerRepository serverRepository;
    int serverId;

    public ActivationTimer(ServerRepository serverRepository, int serverId) {
        this.serverRepository = serverRepository;
        this.serverId = serverId;
    }

    public ServerRepository getServerRepository() {
        return serverRepository;
    }

    public void setServerRepository(ServerRepository serverRepository) {
        this.serverRepository = serverRepository;
    }

    public int getServerId() {
        return serverId;
    }

    public void setServerId(int serverId) {
        this.serverId = serverId;
    }

    @Override
    public void run() {
        log.info("running activation thread of server: "+serverId);
        Server server=serverRepository.findOne(serverId);
        server.setActive(true);
        try {
            Thread.sleep(20000);
            server.setActive(true);
            serverRepository.save(server);
        } catch (InterruptedException e) {
            log.info(e.getMessage());
        }
    }

    public void start(){
        log.info("Starting activation timer for server with id: "+serverId);
        if(thread==null) {
            thread = new Thread(this, "timer of server " + serverId);
        }
        thread.start();
    }
}
