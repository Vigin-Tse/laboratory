package com.vg.study.rabbitmq.direct.demo.provider;

import com.vg.study.rabbitmq.direct.demo.configuration.DirectConfiguration;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author xieweij
 * @create 2020/5/4 10:38
 */
@Service
public class DirectProvider {

    /**
     * 使用RabbitTemplate,这提供了接收/发送等等方法
     */
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendDirectMessage(int sendTimes) {
        for (int i = 1; i <= sendTimes; i++) {
            String messageId = String.valueOf(String.valueOf(i));
            String messageData = "我是消息-";
            String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            Map<String, Object> message = new HashMap<>(1);
            message.put("id", messageId);
            message.put("data", messageData + i);
            message.put("time", createTime);

            rabbitTemplate.convertAndSend(DirectConfiguration.DEMO_DIRECT_EXCHANGE, DirectConfiguration.DIRECT_ROUTINGKEY, message);
        }
    }
}
