package com.chanchifeng.web1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.chanchifeng")
public class Web1Application {
    public static void main(String[] args) {
        SpringApplication.run(Web1Application.class, args);
    }
}
