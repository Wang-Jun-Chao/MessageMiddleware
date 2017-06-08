package com.example.rabbitmq.spring;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * 消息连接配置
 * Author: 王俊超
 * Date: 2017-06-09 07:30
 * All Rights Reserved !!!
 */
@Profile({"tut1", "hello-world"})
@Configuration
public class Tut1Config {
    /**
     * 创建一个消息列队
     * @return
     */
    @Bean
    public Queue hello() {
        return new Queue("hello");
    }

    /**
     * 创建消息接收者
     * @return
     */
    @Profile("receiver")
    @Bean
    public Tut1Receiver receiver() {
        return new Tut1Receiver();
    }

    /**
     * 创建消息发送者
     *
     * @return
     */
    @Profile("sender")
    @Bean
    public Tut1Sender sender() {
        return new Tut1Sender();
    }
}
