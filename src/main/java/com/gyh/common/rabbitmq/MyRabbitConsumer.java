package com.gyh.common.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author gyh
 * @Date 2021/2/25 18:30
 */

@Component
public class MyRabbitConsumer {

    @RabbitListener(queues = "test.queue")
    public void processMessage(byte[] content) {
        System.out.println("监听到消息: " + new String(content) + "\t 现在时间=" + new Date());

    }

}
