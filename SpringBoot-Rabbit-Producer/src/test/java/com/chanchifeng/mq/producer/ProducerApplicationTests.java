package com.chanchifeng.mq.producer;

import com.chanchifeng.mq.producer.entity.Order;
import com.chanchifeng.mq.producer.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProducerApplicationTests {

    @Autowired
    private OrderService orderService;

    @Test
    public void contextLoads() {
        Order order = new Order();
        order.setId(20190619);
        order.setName("测试订单2019-06-19");
        order.setMessageId(System.currentTimeMillis()+"$"+ UUID.randomUUID().toString());
        try {
            orderService.sendOrder(order);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
