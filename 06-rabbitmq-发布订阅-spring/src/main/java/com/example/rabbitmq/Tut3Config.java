package com.example.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * 配置文件
 * Author: 王俊超
 * Date: 2017-06-17 11:24
 * All Rights Reserved !!!
 */
@Profile({"tut3", "pub-sub", "publish-subscribe"})
@Configuration
public class Tut3Config {
    /**
     * 扇出交换器
     *
     * @return
     */
    @Bean
    public FanoutExchange fanout() {
        return new FanoutExchange("tut.fanout");
    }

    /**
     * 消息接收者配置类
     */
    @Profile("receiver")
    private static class ReceiverConfig {
        /**
         * 匿名队列
         *
         * @return
         */
        @Bean
        public Queue autoDeleteQueue1() {
            return new AnonymousQueue();
        }

        @Bean
        public Queue autoDeleteQueue2() {
            return new AnonymousQueue();
        }

        /**
         * 将匿名队列绑定到扇出交换器上
         *
         * @param fanout
         * @param autoDeleteQueue1
         * @return
         */
        public Binding binding1(FanoutExchange fanout, Queue autoDeleteQueue1) {
            return BindingBuilder.bind(autoDeleteQueue1).to(fanout);
        }

        @Bean
        public Binding binding2(FanoutExchange fanout, Queue autoDeleteQueue2) {
            return BindingBuilder.bind(autoDeleteQueue2).to(fanout);
        }

        @Bean
        public Tut3Receiver receiver() {
            return new Tut3Receiver();
        }
    }

    /**
     * 消息接收者
     * @return
     */
    @Profile("sender")
    @Bean
    public Tut3Sender sender() {
        return new Tut3Sender();
    }
}
