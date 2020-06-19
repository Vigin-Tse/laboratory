package com.vg.study.rabbitmq.topic.demo.configuration;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * 主题交换机，这个交换机其实跟直连交换机流程差不多，但是它的特点就是在它的路由键和绑定键之间是有规则的。
 * 简单地介绍下规则：
 *
 * *  (星号) 用来表示一个单词 (必须出现的)
 * #  (井号) 用来表示任意数量（零个或多个）单词
 * 通配的绑定键是跟队列进行绑定的，举个小例子
 * 队列Q1 绑定键为 *.TT.*          队列Q2绑定键为  TT.#
 * 如果一条消息携带的路由键为 A.TT.B，那么队列Q1将会收到；
 * 如果一条消息携带的路由键为TT.AA.BB，那么队列Q2将会收到；
 *
 * 主题交换机是非常强大的，为啥这么膨胀？
 * 当一个队列的绑定键为 "#"（井号） 的时候，这个队列将会无视消息的路由键，接收所有的消息。
 * 当 * (星号) 和 # (井号) 这两个特殊字符都未在绑定键中出现的时候，此时主题交换机就拥有的直连交换机的行为。
 * 所以主题交换机也就实现了扇形交换机的功能，和直连交换机的功能。
 *
 * @Author xieweij
 * @create 2020/5/4 10:09
 */
@Configuration
public class TopicConfiguration {

    public static final String TOPIC_MAN_QUEUE = "TopicManQueue";
    public static final String TOPIC_WOMAN_QUEUE = "TopicWomanQueue";

    public static final String TOPIC_EXCHANGE = "DemoTopicExchange";

    public static final String TOPIC_MAN_BINDINGKEY = "topic.man";
    public static final String TOPIC_WOMAN_BINDINGKEY = "topic.woman";
    public static final String TOPIC_TOTAL_BINDINGKEY = "topic.#";

    /**
     * 创建队列 man
     * @return
     */
    @Bean
    public Queue firstQueue(){
        return new Queue(TopicConfiguration.TOPIC_MAN_QUEUE, true);
    }

    /**
     * 创建队列 woman
     * @return
     */
    @Bean
    public Queue secondQueue(){
        return new Queue(TopicConfiguration.TOPIC_WOMAN_QUEUE, true);
    }

    /**
     * 创建交换机
     * @return
     */
    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(TopicConfiguration.TOPIC_EXCHANGE, true, false);
    }

    /**
     * 绑定  而且绑定的键值为topic.man，这样只要是消息携带的路由键是topic.man,才会分发到该队列
     * @return
     */
    @Bean
    public Binding bindingMan(){
        return BindingBuilder.bind(firstQueue()).to(exchange()).with(TOPIC_MAN_BINDINGKEY);
    }

    /**
     * 绑定  将secondQueue和topicExchange绑定,而且绑定的键值为用上通配路由键规则topic.#
     * 这样只要是消息携带的路由键是以topic.开头,都会分发到该队列
     * @return
     */
    @Bean
    public Binding bindingWoman(){
        return BindingBuilder.bind(secondQueue()).to(exchange()).with(TOPIC_TOTAL_BINDINGKEY);
    }

}
