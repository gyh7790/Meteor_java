package com.gyh.common.rabbitmq;

import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author gyh
 * @Date 2021/2/25 18:58
 */
@Component
public class MyRabbitProducer {


    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String context = "hello " + new Date();
        System.out.println("Sender : " + context);
        MessageProperties properties = new MessageProperties();
        properties.setExpiration("5000");
        Message msgobj = new Message(context.getBytes(),properties);

        rabbitTemplate.convertAndSend("delay.queue", msgobj);
    }

}
