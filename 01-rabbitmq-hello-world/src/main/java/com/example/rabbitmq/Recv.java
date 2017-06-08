package com.example.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消息接收类
 *
 * Author: 王俊超
 * Date: 2017-06-08 08:02
 * All Rights Reserved !!!
 */
public class Recv {
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, TimeoutException {
        // [1] 创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        // [2] 设置连接的主机，其他的属性比如端口，用户名，密码都使用了默认设置
        factory.setHost("localhost");
        // [3] 创建连接对象
        Connection connection = factory.newConnection();
        // [4] 创建消息通道
        Channel channel = connection.createChannel();
        // [5] 声明一个需要使用的消息队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        System.out.println(" [*] Wait For message. To exit press CTRL+C");

        // [6] 创建一个消息消费者对象
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                    AMQP.BasicProperties properties, byte[] body) throws IOException {
                // 实现消息消费动作
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received '" + message + "'");
            }
        };

        // [7] 向消息通道中添加添加消费者
        channel.basicConsume(QUEUE_NAME, true, consumer);

        // [8] 在此处消息通道和连接都没有关闭，可以一直接收消息

    }
}
