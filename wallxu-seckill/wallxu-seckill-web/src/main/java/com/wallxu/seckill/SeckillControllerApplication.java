package com.wallxu.seckill;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SeckillControllerApplication {

    private static final Logger logger = LoggerFactory.getLogger(SeckillControllerApplication.class);

    public static void main(String[] args) {

        SpringApplication.run(SeckillControllerApplication.class, args);
        logger.info("项目启动 ================Controller==============");

    }
}