package com.example.controller;

import com.example.entity.*;
import com.example.service.ProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
public class ProducerController {

    public static final String DUMMY_CONTENT = "dummyContent";
    private final ProducerService producerService;

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


}
