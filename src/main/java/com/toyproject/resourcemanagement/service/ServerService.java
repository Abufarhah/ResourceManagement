package com.toyproject.resourcemanagement.service;

import com.toyproject.resourcemanagement.model.Server;
import com.toyproject.resourcemanagement.repository.ServerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServerService {
    private static final Logger log = LoggerFactory.getLogger(ServerService.class);

    @Autowired
    ServerRepository serverRepository;

    @Autowired
    CloudServerProvider cloudServerProvider;

    public String allocateServer(int size) {
        log.info("Allocate server with size: " + size);
        if(size>100) return "Maximum server size is 100 Giga";
        if(size<=0) return "Minimum server size is 1 Giga";
        cloudServerProvider.allocateServer(size);
        return "A server with size: "+size+" was allocated";
    }
}
