package com.example.rabbitmq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 应用监控器，运行指定时间停止应用的运行
 * Author: 王俊超
 * Date: 2017-06-09 07:37
 * All Rights Reserved !!!
 */
public class RabbitAmqpTutorialsRunner implements CommandLineRunner {

    @Value("${tutorial.client.duration:10000}")
    private int duration;

    @Autowired
    private ConfigurableApplicationContext ctx;

    @Override
    public void run(String... arg0) throws Exception {
        System.out.println("Ready ... running for " + duration + "ms");
        Thread.sleep(duration);
        ctx.close();
    }
}
