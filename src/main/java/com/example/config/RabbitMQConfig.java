package com.example.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String DUMMY_EXCHANGE = "x.dummy";
    public static final String DUMMY_QUEUE = "q.dummy";

    public static final String INVOICE_QUEUE = "q.invoice";
    public static final String INVOICE_EXCHANGE = "x.invoice";
    
    public static final String SINGLE_QUEUE = "q.single";
    public static final String SINGLE_EXCHANGE = "x.single";

    public static final String NON_EXISTS_EXCHANGE = "x.non-exists-exchange";
    public static final String DUMMY_EXCHANGE_2 = "x.dummy2";
    public static final String INVOICE_CANCEL_EXCHANGE = "x.invoice.cancel";

    public static final String FANOUT_EXCHANGE_ROUTING_KEY = "";
    public static final String INVALID_ROUTING_KEY = "invalid-routing-key";

    @Bean
    public RabbitListenerContainerFactory<SimpleMessageListenerContainer> prefetchOneContainerFactory(
            SimpleRabbitListenerContainerFactoryConfigurer configurer, ConnectionFactory connectionFactory) {
        var factory = new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        factory.setPrefetchCount(1);

        return factory;
    }


    @Bean
    public ObjectMapper objectMapper() {
        return JsonMapper.builder().findAndAddModules().build();
    }

    @Bean
    public Jackson2JsonMessageConverter converter(ObjectMapper objectMapper) {
        return new Jackson2JsonMessageConverter(objectMapper);
    }


}
