package com.example.rabbitmq.spring.tut1;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

/**
 * 消息接收者类，使用@RabbitListener指定监听的消息通道
 *
 * Author: 王俊超
 * Date: 2017-06-09 07:32
 * All Rights Reserved !!!
 */
@RabbitListener(queues = "hello")
public class Tut1Receiver {
    /**
     * 消息处理方法，使用@RabbitHandler进行标记
     * @param in
     */
    @RabbitHandler
    public void receive(String in) {
        System.out.println(" [x] Received '" + in + "'");
    }
}
