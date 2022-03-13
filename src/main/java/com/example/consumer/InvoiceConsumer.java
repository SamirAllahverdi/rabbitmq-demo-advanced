package com.example.consumer;

import com.example.config.RabbitMQConfig;
import com.example.entity.InvoiceCreatedMessage;
import com.example.entity.InvoicePaidMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
//@Component
@RabbitListener(queues = RabbitMQConfig.INVOICE_QUEUE)
public class InvoiceConsumer {


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

}
