package com.rusinek.javamessaging.sender;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rusinek.javamessaging.config.JmsConfig;
import com.rusinek.javamessaging.model.HelloWorldMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.UUID;

/**
 * Created by Adrian Rusinek on 06.04.2020
 **/
@RequiredArgsConstructor
@Component
public class HelloSender {

    private final JmsTemplate jmsTemplate;
    private final ObjectMapper objectMapper;

    @Scheduled(fixedRate = 2000)
    public void sendMessage() {
        HelloWorldMessage message = HelloWorldMessage
                .builder()
                .id(UUID.randomUUID())
                .message("Doberek ")
                .build();

        jmsTemplate.convertAndSend(JmsConfig.MY_QUEUE, message);

    }

    @Scheduled(fixedRate = 2000)
    public void sendAndReceiveMessage() throws JMSException {
        System.out.println("I'm sending message");

        HelloWorldMessage message = HelloWorldMessage
                .builder()
                .id(UUID.randomUUID())
                .message("Hello ")
                .build();

      Message receivedMessage =  jmsTemplate.sendAndReceive(JmsConfig.MY_SEND_RECEIVE_QUEUE, (Session session) -> {
            Message helloMessage = null;
            try {
                helloMessage = session.createTextMessage(objectMapper.writeValueAsString(message));
                helloMessage.setStringProperty("_type", "com.rusinek.javamessaging.model.HelloWorldMessage");

                System.out.println("Sending hello.");

                return helloMessage;
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            throw new JMSException("wops");
        });
        System.out.println(receivedMessage.getBody(String.class));
    }
}
