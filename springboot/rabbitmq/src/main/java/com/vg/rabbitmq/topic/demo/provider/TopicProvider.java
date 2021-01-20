package com.vg.rabbitmq.topic.demo.provider;

import com.vg.rabbitmq.topic.demo.configuration.TopicConfiguration;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Description
 * @Author xieweij
 * @create 2020/5/4 10:38
 */
@RestController
public class TopicProvider {

    /**
     * 使用RabbitTemplate,这提供了接收/发送等等方法
     */
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("/topic/mess1")
    public void sendTopicMessage1() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message: M A N ";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> manMap = new HashMap<>(1);
        manMap.put("messageId", messageId);
        manMap.put("messageData", messageData);
        manMap.put("createTime", createTime);
        rabbitTemplate.convertAndSend(TopicConfiguration.TOPIC_EXCHANGE, TopicConfiguration.TOPIC_MAN_BINDINGKEY, manMap);
    }

    @RequestMapping("/topic/mess2")
    public void sendTopicMessage2() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message: woman is all ";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> womanMap = new HashMap<>(1);
        womanMap.put("messageId", messageId);
        womanMap.put("messageData", messageData);
        womanMap.put("createTime", createTime);
        rabbitTemplate.convertAndSend(TopicConfiguration.TOPIC_EXCHANGE, TopicConfiguration.TOPIC_WOMAN_BINDINGKEY, womanMap);
    }

}
