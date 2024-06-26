package com.chen.notification;

import com.alibaba.fastjson.JSON;
import com.chen.notification.endpoints.NotificationServerEndpoint;
import com.chen.notification.entities.Notification;
import com.chen.notification.entities.SingleMessage;
import com.chen.notification.service.SendNotificationService;
import com.chen.notification.utils.ConfigUtil;
import jakarta.annotation.PostConstruct;
import org.apache.rocketmq.client.apis.ClientConfiguration;
import org.apache.rocketmq.client.apis.ClientException;
import org.apache.rocketmq.client.apis.ClientServiceProvider;
import org.apache.rocketmq.client.apis.consumer.ConsumeResult;
import org.apache.rocketmq.client.apis.consumer.FilterExpression;
import org.apache.rocketmq.client.apis.consumer.FilterExpressionType;
import org.apache.rocketmq.client.apis.consumer.PushConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;

@Component
public class AutoRunner {

    private static final Logger logger = LoggerFactory.getLogger(AutoRunner.class);

    @Autowired
    NotificationServerEndpoint service;

    @PostConstruct
    public void startListening() throws InterruptedException, ClientException {
        final ClientServiceProvider provider = ClientServiceProvider.loadService();
        String endpoints = "192.168.23.129:8081";
        ClientConfiguration clientConfiguration = ClientConfiguration.newBuilder()
                .setEndpoints(endpoints)
                .build();
        String tag = ConfigUtil.getTag();
        FilterExpression filterExpression = new FilterExpression(tag, FilterExpressionType.TAG);
        String consumerGroup = "notificationGroup";
        String topic = "notificationTopic";
        PushConsumer pushConsumer = provider.newPushConsumerBuilder()
                .setClientConfiguration(clientConfiguration)
                .setConsumerGroup(consumerGroup)
                .setSubscriptionExpressions(Collections.singletonMap(topic, filterExpression))
                .setMessageListener(messageView -> {
                    ByteBuffer body = messageView.getBody();

                    String s = StandardCharsets.UTF_8.decode(body).toString();

                    System.out.println(s);

                    logger.info("Consume message successfully, messageContent={}", s);
                    SingleMessage singleMessage = JSON.parseObject(s, SingleMessage.class);
                    try {
                        service.sendMessage(singleMessage.getReceiverId(), singleMessage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return ConsumeResult.SUCCESS;
                })
                .build();

    }

}
