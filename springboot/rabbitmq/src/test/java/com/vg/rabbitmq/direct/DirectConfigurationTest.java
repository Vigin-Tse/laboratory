package com.vg.rabbitmq.direct;

import com.vg.rabbitmq.direct.demo.provider.DirectProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Description
 * @Author xieweij
 * @create 2020/5/4 11:02
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DirectConfigurationTest {

    @Autowired
    private DirectProvider directProvider;

    @Test
    public void directSenderTest(){
        directProvider.sendDirectMessage(100);
    }
}