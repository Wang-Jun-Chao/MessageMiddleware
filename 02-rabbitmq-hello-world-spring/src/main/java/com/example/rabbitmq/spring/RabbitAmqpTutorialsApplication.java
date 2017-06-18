package com.example.rabbitmq.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 应用入口类
 *
 * Author: 王俊超
 * Date: 2017-06-09 07:34
 * All Rights Reserved !!!
 */
@SpringBootApplication
@EnableScheduling
public class RabbitAmqpTutorialsApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(RabbitAmqpTutorialsApplication.class, args);
    }
}
