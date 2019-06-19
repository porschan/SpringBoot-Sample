package com.chanchifeng.mq.producer.consumer;

import com.chanchifeng.mq.producer.entity.Order;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class OrderReceiver {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "order-queue",durable = "true"),
            exchange = @Exchange(name="order-exchange",durable = "true",type = "topic"),
            key = "order.*"
        )
    )
    @RabbitHandler
    public void onOrderMessage(@Payload Order order
            ,@Headers Map<String,Object> headers
            ,Channel channer) throws IOException {
        //消费者操作

        System.out.println("start   收到消息");

//        System.out.println("订单ID:" + order.getId());
        System.out.println("订单Name:" + order);

        //手动签收

        Long deliveryTag = (Long)headers.get(AmqpHeaders.DELIVERY_TAG);
        //ACK
        channer.basicAck(deliveryTag,false);

    }

}
