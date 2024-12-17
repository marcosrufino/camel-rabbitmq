package com.poc.camelspring;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//@EnableRabbit
@SpringBootApplication
public class CamelSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(CamelSpringApplication.class, args);
    }

}
