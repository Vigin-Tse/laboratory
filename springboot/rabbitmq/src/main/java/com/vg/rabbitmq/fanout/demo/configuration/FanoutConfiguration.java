package com.vg.rabbitmq.fanout.demo.configuration;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * 扇型交换机，这个交换机没有路由键概念，就算你绑了路由键也是无视的。 这个交换机在接收到消息后，会直接转发到绑定到它上面的所有队列。
 *
 * @Author xieweij
 * @create 2020/5/4 10:09
 */
@Configuration
public class FanoutConfiguration {

    public static final String FANOUT_A_QUEUE = "FanoutAQueue";
    public static final String FANOUT_B_QUEUE = "FanoutBQueue";
    public static final String FANOUT_C_QUEUE = "FanoutCQueue";

    public static final String FANOUT_DEMO_EXCHANGE = "FanoutDemoExchange";

    /**
     * 创建三个队列 ：fanout.A   fanout.B  fanout.C
     * 将三个队列都绑定在交换机 fanoutExchange 上
     * 因为是扇型交换机, 路由键无需配置,配置也不起作用
     * @return
     */

    @Bean
    public Queue aQueue(){
        return new Queue(FanoutConfiguration.FANOUT_A_QUEUE, true);
    }

    @Bean
    public Queue bQueue(){
        return new Queue(FanoutConfiguration.FANOUT_B_QUEUE, true);
    }


    @Bean
    public Queue cQueue(){
        return new Queue(FanoutConfiguration.FANOUT_C_QUEUE, true);
    }

    /**
     * 创建交换机
     * @return
     */
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange(FanoutConfiguration.FANOUT_DEMO_EXCHANGE, true, false);
    }

    /**
     * 绑定  将队列和交换机绑定, 并设置用于匹配键
     * @return
     */

    @Bean
    public Binding bindingA(){
        return BindingBuilder.bind(aQueue()).to(fanoutExchange());
    }

    @Bean
    public Binding bindingB(){
        return BindingBuilder.bind(bQueue()).to(fanoutExchange());
    }

    @Bean
    public Binding bindingC(){
        return BindingBuilder.bind(cQueue()).to(fanoutExchange());
    }
}
