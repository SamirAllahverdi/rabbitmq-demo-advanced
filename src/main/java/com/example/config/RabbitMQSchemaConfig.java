package com.example.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQSchemaConfig {


    @Bean
    public Declarables rabbitmqSchema() {
        FanoutExchange fanoutExchange = new FanoutExchange(RabbitMQConfig.ANOTHER_DUMMY_EXCHANGE, true, false);
        Queue queue = new Queue(RabbitMQConfig.ANOTHER_DUMMY_QUEUE);
        Binding binding = BindingBuilder.bind(queue).to(fanoutExchange);
        return new Declarables(fanoutExchange, queue, binding);
    }
}
