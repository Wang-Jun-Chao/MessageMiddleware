package com.example.rabbitmq;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * Author: 王俊超
 * Date: 2017-06-17 20:35
 * All Rights Reserved !!!
 */
public class EmitLogTopic {
    private static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] argv) throws Exception {
        Connection connection = null;
        Channel channel = null;

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        connection = factory.newConnection();
        channel = connection.createChannel();
        // 创建一个主题通道
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
        String routingKey = getRouting(argv);
        String message = getMessage(argv);
        // 使用路由向通道中发送消息
        channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes("UTF-8"));
        System.out.println(" [x] Sent '" + routingKey + "':'" + message + "'");
        connection.close();
    }

    /**
     * 获取中路由字符串
     * @param strings
     * @return
     */
    private static String getRouting(String[] strings) {
        if (strings.length < 1) {
            return "anonymous.info";
        }
        return strings[0];
    }

    /**
     * 获取消息
     *
     * @param strings
     * @return
     */
    private static String getMessage(String[] strings) {
        if (strings.length < 2) {
            return "Hello World!";
        }
        return joinStrings(strings, " ", 1);
    }

    private static String joinStrings(String[] strings, String delimiter, int startIndex) {
        int length = strings.length;
        if (length == 0) {
            return "";
        }
        if (length < startIndex) {
            return "";
        }
        StringBuilder words = new StringBuilder(strings[startIndex]);
        for (int i = startIndex + 1; i < length; i++) {
            words.append(delimiter).append(strings[i]);
        }
        return words.toString();
    }
}
