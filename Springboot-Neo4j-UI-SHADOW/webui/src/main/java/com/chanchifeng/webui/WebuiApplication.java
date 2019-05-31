package com.chanchifeng.webui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.chanchifeng")
public class WebuiApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebuiApplication.class, args);
    }
}

