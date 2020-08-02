package com.toyproject.resourcemanagement.thread;

import com.toyproject.resourcemanagement.model.Server;
import com.toyproject.resourcemanagement.repository.ServerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AllocationThread implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(AllocationThread.class);
    Thread thread;
    ServerRepository serverRepository;
    int serverId;
    int size;

    public AllocationThread(ServerRepository serverRepository, int serverId, int size) {
        this.serverRepository = serverRepository;
        this.serverId = serverId;
        this.size = size;
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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public void run() {
        log.info("running allocation thread of server: " + serverId);
        Server server = serverRepository.findOne(serverId);
        try {
            while (!server.isActive()) {
                server = serverRepository.findOne(serverId);
            }
            synchronized (serverRepository) {
                server = serverRepository.findOne(serverId);
                server.setAllocatedSize(server.getAllocatedSize() + size);
                server.setFreeSize(server.getFreeSize() - size);
                serverRepository.save(server);
            }
            log.info("Allocated successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() {
        log.info("Starting allocation thread for server with id: " + serverId);
        if (thread == null) {
            thread = new Thread(this, "allocator of server " + serverId);
        }
        thread.start();
    }
}
