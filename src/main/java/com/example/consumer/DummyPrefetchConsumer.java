package com.example.consumer;

import com.example.config.RabbitMQConfig;
import com.example.entity.DummyMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
//@Component
public class DummyPrefetchConsumer {
	@RabbitListener(queues = RabbitMQConfig.DUMMY_QUEUE)
	public void listenDummy(DummyMessage message) throws InterruptedException {
		log.info("Message is {}", message);
		TimeUnit.MILLISECONDS.sleep(1000);
	}
}
