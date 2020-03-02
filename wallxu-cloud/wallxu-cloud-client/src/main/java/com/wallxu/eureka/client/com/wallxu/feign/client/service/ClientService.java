package com.wallxu.eureka.client.com.wallxu.feign.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "EUREKA-CLIENT")
public interface ClientService {

    @RequestMapping(value = "/client/helloWorld")
    public String helloWorld();

}
