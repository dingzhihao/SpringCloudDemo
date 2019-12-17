package com.hao.service.impl;

import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * 消息监听者
 */
@Service
@RocketMQMessageListener(consumerGroup = "${rocketmq.producer.group}", topic = "testMQ", messageModel = MessageModel.CLUSTERING)
public class MQConsumerService implements RocketMQListener<MessageExt> {

    @Override
    public void onMessage(MessageExt message) {
        try {
            String tags = message.getTags();
            String keys = message.getKeys();
            String body = new String(message.getBody(), "UTF-8");
            System.out.println("<< Tags：" + tags + " keys：" + keys + " Body：" + body);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}