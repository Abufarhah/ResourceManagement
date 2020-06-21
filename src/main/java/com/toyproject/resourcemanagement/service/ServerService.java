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

    public void allocateServer(int size) {
        log.info("Allocate server with size: " + size);
        cloudServerProvider.allocateServer(size);
    }
}
