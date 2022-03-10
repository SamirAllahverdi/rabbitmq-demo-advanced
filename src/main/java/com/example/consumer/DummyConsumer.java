package com.example.consumer;

import com.example.config.RabbitMQConfig;
import com.example.entity.DummyMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DummyConsumer {

	@RabbitListener(queues = RabbitMQConfig.DUMMY_QUEUE)
	public void listenDummy(DummyMessage message) {
		log.info("Message is {}", message);
	}
	
}
