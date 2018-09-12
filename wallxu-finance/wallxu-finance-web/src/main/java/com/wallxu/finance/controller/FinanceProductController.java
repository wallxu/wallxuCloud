package com.wallxu.finance.controller;

import com.wallxu.common.utils.RedisUtil;
import com.wallxu.finance.dao.entity.FinanceProduct;
import com.wallxu.finance.rpc.api.FinanceProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Administrator on 2018/8/14.
 */
@RestController
@RequestMapping(value = "/product")
public class FinanceProductController {
    static Logger logger = LoggerFactory.getLogger(FinanceProductController.class);
    static AtomicInteger i = new AtomicInteger(0);
    static Long count = 90l;

    @Autowired
    private FinanceProductService financeProductService;


    @PostMapping(value = "/desc")
    public String desc(){
        try {

            Jedis jedis = RedisUtil.getJedis();
            if (null == jedis) {
                return null;
            }

            jedis.watch("shangping1");
            Long hlen = jedis.hlen("shangping1");

            if (hlen < count) {
                System.out.println("hlen" + hlen);

                Transaction tx = jedis.multi();
                //有库存，
                tx.hset("shangping1", "user_id_"+ i.incrementAndGet(), "666");
                List<Object> exec = tx.exec();

                jedis.close();
                System.out.println(i.get());
                return "成功";

            }else{
                return "over";
            }
        }catch (Exception e){
            e.printStackTrace();
            return  "";
        }
    }


    @PostMapping(value = "/add")
    public void add(@RequestBody FinanceProduct financeProduct){
        financeProductService.addOrUpdate(financeProduct);
    }

    @GetMapping(value = "/findOne")
    public FinanceProduct findOne(@PathVariable String id){
        return financeProductService.findOne(id);
    }

    //条件查询
    @GetMapping(value = "/query")
    public Page<FinanceProduct> query(String ids,
                                      Long maxRewardRate, Long minRewardRate, String status,
                                      @RequestParam(defaultValue = "10") Integer pageSize,
                                      @RequestParam(defaultValue = "0") Integer currentPage ){

        List<String> idList = null, statusList = null;

        //id 筛选
        if(!StringUtils.isEmpty(ids) && ids.contains(",")){
            idList = Arrays.asList(ids.split(","));
        }
        //status状态筛选
        if (!StringUtils.isEmpty(status) && status.contains(",")) {
            statusList = Arrays.asList(status.split(","));
        }
        //分页信息
        Pageable pageable = PageRequest.of(currentPage, pageSize);
        return financeProductService.query(idList, maxRewardRate, minRewardRate, statusList, pageable);
    }
}
