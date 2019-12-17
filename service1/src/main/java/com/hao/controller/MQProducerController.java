package com.hao.controller;

import com.hao.service.impl.MQProducerService;
import com.hao.util.SnowflakeIdWorker;
import org.apache.rocketmq.client.producer.SendResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("MQTest")
public class MQProducerController {

    @Autowired
    private MQProducerService mqProducerService;

    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

    @RequestMapping(value = "producer", method = {RequestMethod.GET})
    public SendResult producer() {
        SendResult sendResult = null;
        try {
            String keys = Long.toString(snowflakeIdWorker.nextId());
            sendResult = mqProducerService.producer("testMQ", "tag1", keys, "Hello RocketMQ!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendResult;
    }

}