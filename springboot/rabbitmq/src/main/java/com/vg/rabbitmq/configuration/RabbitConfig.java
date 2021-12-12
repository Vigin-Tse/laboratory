package com.vg.rabbitmq.configuration;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * @Author xieweij
 * @create 2020/5/4 15:51
 */
//@Configuration
public class RabbitConfig {

    @Bean
    public RabbitTemplate createRabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        //设置开启Mandatory,才能触发回调函数,无论消息推送结果怎么样都强制调用回调函数
        rabbitTemplate.setMandatory(true);

        /**
         * 下面这两种回调函数都是在什么情况会触发呢？
         *
         * 先从总体的情况分析，推送消息存在四种情况：
         *
         * ①消息推送到server，但是在server里找不到交换机     ①这种情况触发的是 ConfirmCallback 回调函数（ack:false）。
         * ②消息推送到server，找到交换机了，但是没找到队列    ②这种情况触发的是 ConfirmCallback(ack:ture)和 RetrunCallback(响应码：312，回应信息：NO_ROUTE)两个回调函数。
         * ③消息推送到sever，交换机和队列啥都没找到          ③这种情况触发的是 ConfirmCallback（ack:false） 回调函数。
         * ④消息推送成功                                 ④这种情况触发的是 ConfirmCallback(ack:ture) 回调函数
         */

        //主要是用来判断消息是否有正确到达交换机，如果有，那么就 ack 就返回 true；如果没有，则是 false。
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            /**
             *  producer -> Exchange
             *  ConfirmCallback只确认消息是否到达exchange
             *        以实现方法confirm中ack属性为标准，true到达
             *  config : 需要开启rabbitmq得ack    publisher-confirm-type
             */
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                System.out.println("ConfirmCallback:     "+"相关数据："+correlationData);
                System.out.println("ConfirmCallback:     "+"确认情况："+ack);
                System.out.println("ConfirmCallback:     "+"原因："+cause);
            }
        });

        //如果你的消息已经正确到达交换机，但是后续处理出错了，那么就会回调 return，并且把信息送回给你（前提是需要设置了 Mandatory，不设置那么就丢弃）；
        // 如果消息没有到达交换机，那么不会调用 return 的东西。
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            /**
             *  Exchange -> Queue
             *  ReturnCallback消息没有正确到达队列时触发回调，如果正确到达队列不执行
             *  config : 需要开启rabbitmq发送失败回退    publisher-returns    或rabbitTemplate.setMandatory(true);设置为true
             */
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                System.out.println("ReturnCallback:     "+"消息："+message);
                System.out.println("ReturnCallback:     "+"回应码："+replyCode);
                System.out.println("ReturnCallback:     "+"回应信息："+replyText);
                System.out.println("ReturnCallback:     "+"交换机："+exchange);
                System.out.println("ReturnCallback:     "+"路由键："+routingKey);
            }
        }

                /**
                 *
                 * queue -> consumer
                 * 默认:
                 *     客户端接收到即确认
                 * 手动确认：
                 *     配置中开启消费ack确认
                 *      listener:
                 *           simple:
                 *             acknowledge-mode: manual #消费者手动确认消息
                 */

        );

        return rabbitTemplate;
    }
}
