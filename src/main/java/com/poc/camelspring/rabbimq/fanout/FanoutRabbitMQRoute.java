package com.poc.camelspring.rabbimq.fanout;

import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class FanoutRabbitMQRoute extends RouteBuilder {


        @Override
        public void configure() throws Exception {

            from("timer://foo?fixedRate=true&period=3000")
                    .routeId("fanoutTime")
                    .log("Time fonout")
                    .setBody(constant("fanout-message"))
                    .to("direct:sendToRabbitMQFanout");


            // Segunda rota: Envia a mensagem para o RabbitMQ usando fanout
            from("direct:sendToRabbitMQFanout")
                    .description("Publishes data to RabbitMQ using fanout exchange")
                    .setExchangePattern(ExchangePattern.InOnly)
                    .to("spring-rabbitmq:fanout.poc?exchangeType=fanout&queues=my-queue-1&autoDeclareProducer=true")

            ;
        }
}