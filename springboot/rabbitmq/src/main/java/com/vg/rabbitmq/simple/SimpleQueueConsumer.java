package com.vg.rabbitmq.simple;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RabbitListener(queues = SimpleQueueConfig.SIMPLE_QUEUE)
public class SimpleQueueConsumer {

    @RabbitHandler
    public void getMessage(SimpleDto data){
        log.info("simple-queue消费者：data = {}", JSONObject.toJSONString(data));

        int a = 1/0;
    }
}
