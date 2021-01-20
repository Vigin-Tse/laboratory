package com.vg.rabbitmq.fanout.demo.consumer;

import com.alibaba.fastjson.JSON;
import com.vg.rabbitmq.fanout.demo.configuration.FanoutConfiguration;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Description
 * @Author xieweij
 * @create 2020/5/4 15:17
 */
@Component
@RabbitListener(queues = FanoutConfiguration.FANOUT_C_QUEUE)
public class FanoutCConsumer {

    @RabbitHandler

    public void getMessage(Map messages){
        System.out.println("C:");
        System.out.println(JSON.toJSON(messages));
    }
}
