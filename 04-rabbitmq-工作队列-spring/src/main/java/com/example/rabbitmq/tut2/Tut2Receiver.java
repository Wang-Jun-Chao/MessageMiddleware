package com.example.rabbitmq.tut2;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;

/**
 * 消息接收者对象，并指定接收的消息队列
 * <p>
 * Author: 王俊超
 * Date: 2017-06-17 10:30
 * All Rights Reserved !!!
 */
@RabbitListener(queues = "hello")
public class Tut2Receiver {

    private final int instance;


    public Tut2Receiver(int i) {
        this.instance = i;
    }

    /**
     * 指定消息接收后的处理方法，因为发送的消息是string类型，所以接收方法的入参也是string类型
     *
     * @param in
     * @throws InterruptedException
     */
    @RabbitHandler
    public void receive(String in) throws InterruptedException {
        StopWatch watch = new StopWatch();
        watch.start();
        System.out.println("instance " + this.instance + " [x] Received '" + in + "'");
        doWork(in);
        System.out.println("instance " + this.instance + " [x] Done in " + watch.getTotalTimeSeconds() + "s");
    }

    private void doWork(String in) throws InterruptedException {
        for (char ch : in.toCharArray()) {
            if (ch == '.') {
                Thread.sleep(1000);
            }
        }
    }
}
