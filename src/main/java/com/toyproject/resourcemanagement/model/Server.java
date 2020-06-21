package com.toyproject.resourcemanagement.model;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class Server implements Comparable<Server> {
    @Id
    private int serverId;
    private int allocatedSize;
    private int freeSize;
    private boolean isActive;
    private String creationDate;

    public Server() {
        Date date=new Date(System.currentTimeMillis());
        this.creationDate=date.toString();
    }

    public Server(int serverId, int allocatedSize, int freeSize, boolean isActive) {
        Date date=new Date(System.currentTimeMillis());
        this.serverId = serverId;
        this.allocatedSize = allocatedSize;
        this.freeSize = freeSize;
        this.isActive = isActive;
        this.creationDate=date.toString();
    }

    public int getServerId() {
        return serverId;
    }

    public void setServerId(int serverId) {
        this.serverId = serverId;
    }

    public int getAllocatedSize() {
        return allocatedSize;
    }

    public void setAllocatedSize(int allocatedSize) {
        this.allocatedSize = allocatedSize;
    }

    public int getFreeSize() {
        return freeSize;
    }

    public void setFreeSize(int freeSize) {
        this.freeSize = freeSize;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isActive() {
        return isActive;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public int compareTo(Server o) {
        return serverId>o.getServerId()?1:serverId<o.getServerId()?-1:0;
    }
}
