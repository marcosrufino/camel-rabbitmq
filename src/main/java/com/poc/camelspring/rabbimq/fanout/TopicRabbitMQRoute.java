package com.poc.camelspring.rabbimq.fanout;

import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class TopicRabbitMQRoute extends RouteBuilder {


        @Override
        public void configure() throws Exception {

            from("timer://foo?fixedRate=true&period=3000")
                    .routeId("topicPublisher")
                    .log("Enviando mensagem com routing key: my.queue.example")
                    .setBody(constant("Mensagem com coringas"))
                    .to("spring-rabbitmq:topic.poc?exchangeType=topic&queues=my-queue-1&autoDeclareProducer=true&routingKey=my.queue.example");

            /*from("spring-rabbitmq:topic.poc?exchangeType=topic&queues=my.queue.example")
                    .routeId("consumerQueueExample")
                    .log("Fila my.queue.example recebeu: ${body}");
            from("spring-rabbitmq:topic.poc?exchangeType=topic&queues=my.queue.example2")
                    .routeId("consumerQueueExample2")
                    .log("Fila my.queue.example2 recebeu: ${body}");*/
        }
}