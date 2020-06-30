package com.toyproject.resourcemanagement.controller;

import com.toyproject.resourcemanagement.model.Server;
import com.toyproject.resourcemanagement.service.ServerService;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/CloudService",method=RequestMethod.GET)
public class ServerController {
    private static final Logger log = LoggerFactory.getLogger(ServerController.class);

    @Autowired
    ServerService serverService;
    //
    @RequestMapping(path = "/api",method=RequestMethod.GET)
    public ResponseEntity<String> api(){
        return new ResponseEntity<String>("Welcome to the cloud service api",HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/api/servers")
    public ResponseEntity<List<Server>> getServers() {
        log.info("getServers");
        try {
            List<Server> serverList = serverService.getServers();
            return new ResponseEntity<List<Server>>(serverList,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<List<Server>>(new ArrayList<Server>(),HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/api/servers/{id}")
    public ResponseEntity<Server> getServer(@PathVariable int id) {
        log.info("getServer");
        try {
            Server server= serverService.getServer(id);
            if(server!=null) {
                return new ResponseEntity<Server>(server, HttpStatus.OK);
            }else{
                return new ResponseEntity<Server>(new Server(),HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<Server>(new Server(),HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/api/servers/allocate")
    public ResponseEntity<String> allocateServer(@NotEmpty @RequestParam int size) {
        log.info("allocateServer");
        String responeBody=serverService.allocateServer(size);
        return new ResponseEntity<String>(responeBody,HttpStatus.OK);
    }
}
