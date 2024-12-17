package com.poc.camelspring.rabbimq.fanout;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class HeadersRabbitMQRoute extends RouteBuilder {


        @Override
        public void configure() throws Exception {

            from("timer://foo?fixedRate=true&period=3000")
                    .routeId("headerPublisher")
                    .log("Enviando mensagem com header: ${header.routingKey}")
                    .setBody(constant("Mensagem com headers"))
                    .setHeader("routingKey", constant("my.route.key.headers"))  // Definindo o header para a chave de roteamento
                    .to("spring-rabbitmq:headers.poc?exchangeType=headers&queues=my.queue.headers&autoDeclareProducer=true");

        }
}