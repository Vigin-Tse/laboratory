package com.vg.study.rabbitmq.direct.demo.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description 直连型交换机，根据消息携带的路由键将消息投递给对应队列。
 * 大致流程，有一个队列绑定到一个直连交换机上，同时赋予一个路由键 routing key 。
 * 然后当一个消息携带着路由值为X，这个消息通过生产者发送给交换机时，交换机就会根据这个路由值X去寻找绑定值也是X的队列。
 * @Author xieweij
 * @create 2020/5/4 10:09
 */
@Configuration
public class DirectConfiguration {

    public static final String DEMO_DIRECT_QUEUE = "DemoDirectQueue";
    public static final String DEMO_DIRECT_EXCHANGE = "DomoDirectExchange";
    public static final String DIRECT_ROUTINGKEY = "direct-routingKey";

    /**
     * 创建一个队列
     *
     * @return
     */
    @Bean
    public Queue directQueue() {
        // durable:是否持久化,默认是false,持久化队列：会被存储在磁盘上，当消息代理重启时仍然存在，暂存队列：当前连接有效
        // exclusive:默认也是false，只能被当前创建的连接使用，而且当连接关闭后队列即被删除。此参考优先级高于durable
        // autoDelete:是否自动删除，当没有生产者或者消费者使用此队列，该队列会自动删除。
        return new Queue(DirectConfiguration.DEMO_DIRECT_QUEUE, true);
    }

    /**
     * 创建交换机
     *
     * @return
     */
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(DirectConfiguration.DEMO_DIRECT_EXCHANGE, true, false);
    }

    /**
     * 绑定  将队列和交换机绑定, 并设置用于匹配键
     *
     * @return
     */
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(directQueue()).to(directExchange()).with(DirectConfiguration.DIRECT_ROUTINGKEY);
    }
}
