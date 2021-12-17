package com.vg.rabbitmq.direct.demo.consumer;

import com.alibaba.fastjson.JSON;
import com.vg.rabbitmq.direct.demo.configuration.DirectConfiguration;
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
@RabbitListener(queues = DirectConfiguration.DEMO_DIRECT_QUEUE)
public class DirectConsumer {

    @RabbitHandler
    public void getMessage(Map messages) {
        System.out.println(JSON.toJSON(messages));
        try {
            Thread.sleep(10000L);
            System.out.println(Thread.currentThread().getName() + ",休眠结束");
//            System.out.println(1/0);
        }catch (Exception e){
            System.out.println("异常：" + messages.get("id"));
        }

    }
}
