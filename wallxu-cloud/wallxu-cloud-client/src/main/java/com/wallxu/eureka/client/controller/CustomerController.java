package com.wallxu.eureka.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {

    @Bean
    @LoadBalanced
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/helloCustomerOne")
    public String helloCustomerOne(){
        return restTemplate.getForEntity("http://EUREKA-CLIENT01/client/helloWorld", String.class, "8885").getBody();
    }

    @RequestMapping(value = "/helloCustomerTwo")
    public String helloCustomerTwo(){

        return restTemplate.getForEntity("http://EUREKA-CLIENT/client/helloWorld", String.class, "8885").getBody();
    }
}
