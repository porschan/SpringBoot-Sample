package com.chanchifeng.mq.producer.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Order implements Serializable {

    private int id;
    private String name;
    private String messageId;

}
