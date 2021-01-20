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
 * @create 2020/5/4 11:19
 */
@Component
@RabbitListener(queues = TopicConfiguration.TOPIC_MAN_QUEUE)
public class TopicManConsumer {

    @RabbitHandler
    public void getMessage(Map messages) {
        System.out.println("Man-consumer" + JSON.toJSON(messages));
    }
}
