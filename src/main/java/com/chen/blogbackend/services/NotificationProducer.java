package com.chen.blogbackend.services;

import com.alibaba.fastjson2.JSON;
import com.chen.blogbackend.entities.Notification;
import com.chen.blogbackend.entities.SingleMessage;
import org.apache.rocketmq.client.apis.ClientException;
import org.apache.rocketmq.client.apis.message.Message;
import org.apache.rocketmq.client.apis.message.MessageBuilder;
import org.apache.rocketmq.client.apis.message.MessageId;
import org.apache.rocketmq.client.apis.producer.Producer;
import org.apache.rocketmq.client.apis.producer.SendReceipt;
import org.apache.rocketmq.client.java.message.MessageBuilderImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationProducer {
    private static final Logger logger = LoggerFactory.getLogger(NotificationProducer.class);

    @Autowired
    Producer producer;

    public void sendNotification(SingleMessage message ) throws ClientException {
        int partitions = 0;
        MessageBuilder builder = new MessageBuilderImpl();
        int i = message.getUserId().hashCode();
        Message messageToSend = builder.setTopic("notificationTopic").setKeys().setTag("1")
                .setBody(JSON.toJSONBytes(message)).build();

        SendReceipt send = producer.send(messageToSend);
        MessageId messageId = send.getMessageId();
        System.out.println("message Id" + messageId);
    }






}
