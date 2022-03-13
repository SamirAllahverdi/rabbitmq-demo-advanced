package com.example.consumer;

import com.example.config.RabbitMQConfig;
import com.example.entity.DummyMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
//@Component
public class SingleActiveConsumer {

	@RabbitListener(queues = RabbitMQConfig.SINGLE_QUEUE)
	public void listenSingleActiveConsumer(DummyMessage message) throws InterruptedException {
		log.info("First Consuming {}", message);
		TimeUnit.SECONDS.sleep(1);
	}

	@RabbitListener(queues = RabbitMQConfig.SINGLE_QUEUE)
	public void listen(DummyMessage message) throws InterruptedException {
		log.info("SecondConsuming {}", message);
		TimeUnit.SECONDS.sleep(1);
	}

}
