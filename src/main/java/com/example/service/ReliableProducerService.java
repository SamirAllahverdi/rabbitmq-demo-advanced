package com.example.service;

import com.example.config.RabbitMQConfig;
import com.example.entity.DummyMessage;
import com.example.entity.InvoiceCancelledMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReliableProducerService {


    private final RabbitTemplate rabbitTemplate;

    @PostConstruct
    private void registerCallback() {
        rabbitTemplate.setConfirmCallback((correlation, ack, reason) -> {
            if (correlation == null) {
                return;
            }
            if (ack) {
                log.info("Message with correlation {} published", correlation.getId());
            } else {
                log.warn("Invalid exchange for message with correlation {}", correlation.getId());
            }
        });

        rabbitTemplate.setReturnsCallback(returned -> {
            log.info("return callback");
            if (returned.getReplyText() != null && returned.getReplyText().equalsIgnoreCase("NO_ROUTE")) {
                log.info("Invalid routing key for message is {}", returned.getMessage());
            }
        });
    }

    public void sendDummyWithInvalidRoutingKey(DummyMessage message) {
        var correlationData = new CorrelationData(Integer.toString(message.getPublishOrder()));
        rabbitTemplate.convertAndSend(RabbitMQConfig.DUMMY_EXCHANGE_2, RabbitMQConfig.INVALID_ROUTING_KEY, message, correlationData);
    }

    public void sendDummyToInvalidExchange(DummyMessage message) {
        var correlationData = new CorrelationData(Integer.toString(message.getPublishOrder()));
        rabbitTemplate.convertAndSend(RabbitMQConfig.NON_EXISTS_EXCHANGE, RabbitMQConfig.FANOUT_EXCHANGE_ROUTING_KEY, message, correlationData);
    }



}
