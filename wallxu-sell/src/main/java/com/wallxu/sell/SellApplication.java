package com.wallxu.sell;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SellApplication {

    private static final Logger logger = LoggerFactory.getLogger(SellApplication.class);


    public static void main(String[] args) {

        SpringApplication.run(SellApplication.class, args);
        logger.info("项目启动 ================sell==============");
    }
}
