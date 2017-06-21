package com.example.rabbitmq.tut2;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * 消息发送者
 *
 * Author: 王俊超
 * Date: 2017-06-17 10:31
 * All Rights Reserved !!!
 */
public class Tut2Sender {

    /** 消息模板对象 */
    @Autowired
    private RabbitTemplate template;

    /** 消息队列 */
    @Autowired
    private Queue queue;

    private int dots = 0;
    private int count = 0;

    /**
     * 消息发送方法，初始延迟0.5秒，之后每1秒发送一个消息
     */
    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send() {
        StringBuilder builder = new StringBuilder("Hello");
        if (dots++ == 3) {
            dots = 1;
        }

        for (int i = 0; i < dots; i++) {
            builder.append('.');
        }

        builder.append(Integer.toString(++count));
        String message = builder.toString();
        template.convertAndSend(queue.getName(), message);
        System.out.println( " [x] Sent '" + message + "'");
    }
}
