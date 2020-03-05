package com.wallxu.seckill.mq;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wallxu.common.utils.FastJsonUtil;
import com.wallxu.seckill.dao.domain.TbMiaoshaOrder;
import com.wallxu.seckill.rpc.api.MiaoshaOrderService;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;

/**
 * Created by Administrator on 2018/8/6.
 */
@Service
public class MyMessageListener {

    @Reference
    private MiaoshaOrderService miaoshaOrderService;

    @JmsListener(destination = "miaosha")
    public void onMessage(ActiveMQTextMessage msg){

        //商品秒杀处理中。。。
        System.out.println("商品秒杀处理中。。。");
        try {
            TbMiaoshaOrder tbMiaoshaOrder =FastJsonUtil.parseToClass(msg.getText(), TbMiaoshaOrder.class);
            miaoshaOrderService.MqMiaosha(tbMiaoshaOrder);
        } catch (JMSException e) {
            e.printStackTrace();
        }


    }
}
