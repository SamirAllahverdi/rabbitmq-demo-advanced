package com.example.service;

import com.example.config.RabbitMQConfig;
import com.example.entity.DummyMessage;
import com.example.entity.Invoice;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProducerService {

    public static final String FANOUT_EXCHANGE_ROUTING_KEY = "";
    private final RabbitTemplate rabbitTemplate;

    public void sendDummyMessage(DummyMessage message) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.DUMMY_EXCHANGE, FANOUT_EXCHANGE_ROUTING_KEY, message);
    }

    public void sendInvoice(Invoice message) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.INVOICE_EXCHANGE, FANOUT_EXCHANGE_ROUTING_KEY, message);
    }
}
