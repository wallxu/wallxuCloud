package com.wallxu.seckill;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by Administrator on 2018/7/16.
 */

@SpringBootApplication
@EnableTransactionManagement
public class SeckillServiceApplication {

    private static final Logger logger = LoggerFactory.getLogger(SeckillServiceApplication.class);

    public static void main(String[] args) {

        ConfigurableApplicationContext run = SpringApplication.run(SeckillServiceApplication.class, args);
        logger.info("项目启动 =============Service=================");
//        GoodsService tbGoodsMapper = run.getBean(GoodsService.class);
//        TbGoods tbGoods = new TbGoods();
//        tbGoods.setId(1126l);
//        tbGoods.setGoodsDetail("444");
//
//        tbGoodsMapper.add(tbGoods);
//        System.out.println(tbGoodsMapper.findOne(253l));

    }
}