package com.wallxu.eureka.server.com.wallxu.eureka.server.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/client")
public class HelloController {

    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value = "/hello")
    public String hello(@RequestParam(name = "name") String name){
        //服务列表
        List<String> services = client.getServices();

        for (String service : services) {
            System.out.println(service);
        }
        return "Hello " + name;
    }

    @RequestMapping(value = "/helloWorld")
    public String helloWorld(){
        //服务列表
        return "helloWorld 8011";
    }
}
