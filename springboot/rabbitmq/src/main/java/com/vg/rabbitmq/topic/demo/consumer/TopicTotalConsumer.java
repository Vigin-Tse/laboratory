package com.vg.rabbitmq.topic.demo.consumer;

import com.alibaba.fastjson.JSON;
import com.vg.rabbitmq.topic.demo.configuration.TopicConfiguration;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Description
 * @Author xieweij
 * @create 2020/5/4 14:15
 */
@Component
@RabbitListener(queues = TopicConfiguration.TOPIC_WOMAN_QUEUE)
public class TopicTotalConsumer {

    @RabbitHandler
    public void getMessage(Map messages) {
        System.out.println("woman-consumer" + JSON.toJSON(messages));
    }
}
