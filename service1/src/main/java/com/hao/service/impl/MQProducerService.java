package com.hao.service.impl;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MQProducerService {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 消息发送者
     *
     * @param topic
     * @param tags
     * @param keys
     * @param body
     * @return
     * @throws InterruptedException
     * @throws RemotingException
     * @throws MQClientException
     * @throws MQBrokerException
     */
    public SendResult producer(String topic, String tags, String keys, String body) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        Message msg = new Message(topic, tags, keys, body.getBytes());
        return rocketMQTemplate.getProducer().send(msg);
    }

}