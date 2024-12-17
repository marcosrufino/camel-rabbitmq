package com.poc.camelspring;

import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
public class CamelRoute extends RouteBuilder {


        @Override
        public void configure() throws Exception {

          /*  from("netty-http:{{server.host}}:{{server.port}}{{server.base-uri}}")
                    .routeId("InventoryPublishRoute")
                    .description("Receives inventory data in JSON and publishes it to RabbitMQ")
                    .unmarshal().json(JsonLibrary.Jackson, Object.class) // Ensures the body is treated as a JSON object
                    .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(201))
                    .multicast()
                    .aggregate(constant(true), new MyAggregationStrategy())
                    .completionTimeout(3000)
                    .completionSize(20)
                    .marshal().json()
                    .log("")
                    .log("Aggregated result as JSON Array: ${body} | Processed by thread: ${threadName}") // Log with thread name
                    .to("direct:sendToRabbitMQ")
            ;


            // Segunda rota: Envia a mensagem para o RabbitMQ sem esperar resposta
            from("direct:sendToRabbitMQ")
                    .routeId("SendToRabbitMQRoute")
                    .description("Publishes aggregated inventory data to RabbitMQ")
                    .setExchangePattern(ExchangePattern.InOnly)
                    .setHeader("rabbitmq.ROUTING_KEY", constant("{{inventory.rabbitmq.routing-key}}")) // Set routing key
                    .to("spring-rabbitmq:{{inventory.rabbitmq.exchange}}?queues={{inventory.rabbitmq.queue}}&autoDeclareProducer=true&routingKey={{inventory.rabbitmq.routing-key}}")
            ;*/

            from("timer://foo?fixedRate=true&period=3000")
                    .routeId("topicPublisher")
                    .log("${body}")
                    .to("file:outbox");
        }
}