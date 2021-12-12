package com.vg.rabbitmq.simple;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SimpleQueueTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void simpleQueueProvider(){

        SimpleDto data = new SimpleDto();
        data.setId(1);
        data.setName("Jody");

        rabbitTemplate.convertAndSend(SimpleQueueConfig.SIMPLE_QUEUE, data);
    }
}
