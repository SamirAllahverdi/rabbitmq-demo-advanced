package com.example.service;

import com.example.config.RabbitMQConfig;
import com.example.entity.DummyMessage;
import com.example.entity.Invoice;
import com.example.entity.InvoiceCancelledMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProducerService {

    private final RabbitTemplate rabbitTemplate;

    public void sendDummyMessage(DummyMessage message) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.DUMMY_EXCHANGE, RabbitMQConfig.FANOUT_EXCHANGE_ROUTING_KEY, message);
    }

    public void sendInvoice(Invoice message) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.INVOICE_EXCHANGE, RabbitMQConfig.FANOUT_EXCHANGE_ROUTING_KEY, message);
    }

    public void sendToSingleActiveConsumer(DummyMessage message) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.SINGLE_EXCHANGE, RabbitMQConfig.FANOUT_EXCHANGE_ROUTING_KEY, message);
    }

    public void sendToRequestReply(InvoiceCancelledMessage cancelledMessage) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.INVOICE_EXCHANGE, RabbitMQConfig.FANOUT_EXCHANGE_ROUTING_KEY, cancelledMessage);
    }
}
