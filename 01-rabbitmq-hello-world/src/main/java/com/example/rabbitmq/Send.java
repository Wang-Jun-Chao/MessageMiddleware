package com.example.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消息发送类
 *
 * Author: 王俊超
 * Date: 2017-06-08 07:56
 * All Rights Reserved !!!
 */
public class Send {
    // 消息通道的名称
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

        String message = "hello world";

        // [6] 使用指定的消息列队，向消息通中发送消息
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());

        System.out.println(" [x] Sent '" + message + "'");

        // [7] 关闭消息消息通道和连接
        channel.close();
        connection.close();

    }
}
