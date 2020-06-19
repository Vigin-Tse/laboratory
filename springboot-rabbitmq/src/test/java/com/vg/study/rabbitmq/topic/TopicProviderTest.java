package com.vg.study.rabbitmq.topic;

import com.vg.study.rabbitmq.topic.demo.provider.TopicProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Description
 * @Author xieweij
 * @create 2020/5/4 14:16
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TopicProviderTest {

    @Autowired
    private TopicProvider topicProvider;

    @Test
    public void testSendTopicMessage1(){
        this.topicProvider.sendTopicMessage1();
    }

    @Test
    public void testSendTopicMessage2(){
        this.topicProvider.sendTopicMessage2();
    }
}