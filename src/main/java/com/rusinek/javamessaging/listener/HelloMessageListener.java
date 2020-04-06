package com.rusinek.javamessaging.listener;

import com.rusinek.javamessaging.config.JmsConfig;
import com.rusinek.javamessaging.model.HelloWorldMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.jms.Message;

/**
 * Created by Adrian Rusinek on 06.04.2020
 **/
@Component
public class HelloMessageListener {

    @JmsListener(destination = JmsConfig.MY_QUEUE)
    public void listen(@Payload HelloWorldMessage helloWorldMessage,
                       @Headers MessageHeaders headers, Message message) {

        System.out.println("I got message!");

        System.out.println(helloWorldMessage);
    }
}
