package com.toyproject.resourcemanagement.controller;

import com.toyproject.resourcemanagement.model.Server;
import com.toyproject.resourcemanagement.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
public class ServerController {
    private static final Logger log = LoggerFactory.getLogger(ServerService.class);

    @Autowired
    ServerService serverService;

    @RequestMapping(path = "/servers", method = RequestMethod.GET)
    public ResponseEntity<String> getServer(@RequestParam int size) {
        log.info("controller");
        serverService.allocateServer(size);
        return new ResponseEntity<String>("A server with size: "+size+" was allocated",HttpStatus.OK);
    }

}
