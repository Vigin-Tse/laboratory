package com.vg.rabbitmq.controller;

import com.vg.rabbitmq.direct.demo.provider.DirectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sender")
public class MessageSenderController {

    @Autowired
    private DirectProvider directProvider;

    /**
     * 模拟发送100条信息到 直连交换机
     */
    @GetMapping("/dircet")
    public void directSender(){
        directProvider.sendDirectMessage(100);
    }

}
