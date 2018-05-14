package com.linxxjie.activemq.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class AppProducer {
    private static final String url = "tcp://192.168.209.1:61616";//61616 activemq默认端口
    private static final String topicName = "topic-test";
    public static void main(String[] args) throws JMSException {
        //创建ConnectionFactory
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        //创建连接
        Connection connection = connectionFactory.createConnection();
        //启动连接
        connection.start();
        //创建会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建一个目标
        Destination destination = session.createTopic(topicName);
        //创建一个生产者
        MessageProducer producer = session.createProducer(destination);
        for (int i=0; i<100; i++){
            //创建消息
            TextMessage textMessage = session.createTextMessage("test"+i);
            //发布消息
            producer.send(textMessage);

            System.out.println("发送消息"+ textMessage.getText());
        }
        //关闭连接
        connection.close();

    }
}
