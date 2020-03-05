package com.wallxu.seckill.mq;

import com.wallxu.common.utils.FastJsonUtil;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;

/**
 * Created by Administrator on 2018/8/6.
 */
@Service
public class MyMessageProducer {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    public void sendMsg(Object o){
        Destination destination = new ActiveMQQueue("miaosha");
        jmsMessagingTemplate.convertAndSend(destination, FastJsonUtil.parseToJSON(o));
    }
}
