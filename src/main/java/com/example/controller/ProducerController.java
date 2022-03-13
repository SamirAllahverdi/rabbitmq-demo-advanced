package com.example.controller;

import com.example.entity.*;
import com.example.service.ProducerService;
import com.example.service.ReliableProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequiredArgsConstructor
public class ProducerController {

    public static final String DUMMY_CONTENT = "dummyContent";
    private final ProducerService producerService;
    private final ReliableProducerService reliableProducerService;


    @GetMapping
    public String testDummyConsumer() {
        producerService.sendDummyMessage(new DummyMessage(DUMMY_CONTENT, 1));
        return "OK";
    }

    @GetMapping("/scheduler")
    public String testRabbitMQScheduler(int count) {
        for (int a = 0; a < count; a++) {
            producerService.sendDummyMessage(new DummyMessage(DUMMY_CONTENT, a));
        }
        return "OK";
    }


    @GetMapping("/invoice")
    public String testInvoice() {

        var createdMessage = new InvoiceCreatedMessage();
        createdMessage.setInvoiceNumber("I_101");
        createdMessage.setCreatedDate(LocalDate.now());
        createdMessage.setAmount(102.2);
        createdMessage.setCurrency("AZN");

        producerService.sendInvoice(createdMessage);

        var paidMessage = new InvoicePaidMessage();
        paidMessage.setInvoiceNumber("I_102");
        paidMessage.setPaidDate(LocalDate.now());
        paidMessage.setPaymentNumber("41234123");

        producerService.sendInvoice(paidMessage);

        var cancelledMessage = new InvoiceCancelledMessage();
        cancelledMessage.setInvoiceNumber("I_100");
        cancelledMessage.setCancelDate(LocalDate.now());
        cancelledMessage.setReason("CVV is wrong");

        producerService.sendInvoice(cancelledMessage);

        return "OK";
    }

    @GetMapping("/single-active")
    public String testSingleActive() {
        var message = new DummyMessage(DUMMY_CONTENT, ThreadLocalRandom.current().nextInt(100));
        producerService.sendToSingleActiveConsumer(message);
        return "OK";
    }


    @GetMapping("/reliable-producer")
    public String testReliableProduce() {
        var message = new DummyMessage(DUMMY_CONTENT, ThreadLocalRandom.current().nextInt(100));
        reliableProducerService.sendDummyToInvalidExchange(message);

        var message2 = new DummyMessage(DUMMY_CONTENT, ThreadLocalRandom.current().nextInt(100));
        reliableProducerService.sendDummyWithInvalidRoutingKey(message2);
        return "OK";
    }

    @GetMapping("/request-reply")
    public String testRequestReply() {

        var cancelledMessage = new InvoiceCancelledMessage();
        cancelledMessage.setInvoiceNumber("I_100");
        cancelledMessage.setCancelDate(LocalDate.now());
        cancelledMessage.setReason("CVV is wrong");

        producerService.sendToRequestReply(cancelledMessage);
        return "OK";
    }
}
