package com.example.rabbitmq.tut6;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Author: 王俊超
 * Date: 2017-06-17 22:15
 * All Rights Reserved !!!
 */
public class Tut6Client {
    @Autowired
    private RabbitTemplate template;

    @Autowired
    private DirectExchange exchange;

    int start = 0;

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send() {
        System.out.println(" [x] Requesting fib(" + start + ")");
        // 设置发送并且接收
        Integer response = (Integer) template.convertSendAndReceive(exchange.getName(), "rpc", start++);
        System.out.println(" [.] Got '" + response + "'");
    }
}
