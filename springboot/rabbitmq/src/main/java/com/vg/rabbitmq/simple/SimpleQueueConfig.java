package com.vg.rabbitmq.simple;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SimpleQueueConfig {

    public static final String SIMPLE_QUEUE = "simple-queue";

    @Bean
    public Queue simpleQueue(){
        return new Queue(SIMPLE_QUEUE);
    }
}
