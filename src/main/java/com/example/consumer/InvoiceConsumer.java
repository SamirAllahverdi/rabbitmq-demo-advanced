package com.example.consumer;

import com.example.config.RabbitMQConfig;
import com.example.entity.InvoiceCancelledMessage;
import com.example.entity.InvoiceCreatedMessage;
import com.example.entity.InvoicePaidMessage;
import com.example.entity.PaymentCancelStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
//@Component
@RabbitListener(queues = RabbitMQConfig.INVOICE_QUEUE)
public class InvoiceConsumer {

    private final String EXCHANGE_WITH_ROUTING_KEY = RabbitMQConfig.INVOICE_CANCEL_EXCHANGE + "/" + RabbitMQConfig.FANOUT_EXCHANGE_ROUTING_KEY;

    @RabbitHandler
    public void handleInvoiceCreated(InvoiceCreatedMessage message) {
        log.info("Invoice created : {}", message);
    }

    @RabbitHandler
    public void handleInvoicePaid(InvoicePaidMessage message) {
        log.info("Invoice paid : {}", message);
    }

    @RabbitHandler(isDefault = true)
    public void handleDefault(Object message) {
        log.warn("Handling default : {}", message);
    }

    @RabbitHandler
    @SendTo(EXCHANGE_WITH_ROUTING_KEY)
    public PaymentCancelStatus handleInvoiceCancelled(InvoiceCancelledMessage message) {
        log.info("Invoice canceled : {}", message);
        var randomStatus = ThreadLocalRandom.current().nextBoolean();
        return new PaymentCancelStatus(randomStatus, LocalDate.now(), message.getInvoiceNumber());
    }

}
