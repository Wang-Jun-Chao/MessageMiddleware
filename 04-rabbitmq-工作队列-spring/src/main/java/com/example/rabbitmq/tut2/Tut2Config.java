package com.example.rabbitmq.tut2;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * RabbitMQ消息配置
 * Author: 王俊超
 * Date: 2017-06-17 10:26
 * All Rights Reserved !!!
 */
@Profile({"tut2", "work-queues"})
@Configuration
public class Tut2Config {
    /**
     * 创建一个消息队列
     * @return
     */
    @Bean
    public Queue hello() {
        return new Queue("hello");
    }

    /**
     * 定一个接收配置对象，定义两个接收者
     */
    @Profile("receiver")
    private static class ReceiverConfig {

        @Bean
        public Tut2Receiver receiver1() {
            return new Tut2Receiver(1);
        }

        @Bean
        public Tut2Receiver receiver2() {
            return new Tut2Receiver(2);
        }
    }

    /**
     * 定义一个消息发送对象
     *
     * @return
     */
    @Profile("sender")
    @Bean
    public Tut2Sender sender() {
        return  new Tut2Sender();
    }
}
