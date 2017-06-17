package com.example.rabbitmq;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
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

    @Bean
    public CommandLineRunner tutorial() {
        return new RabbitAmqpTutorialsRunner();
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(RabbitAmqpTutorialsApplication.class, args);
    }
}
