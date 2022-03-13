package com.example.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RabbitMQScheduler {

	private final RabbitListenerEndpointRegistry registry;
	
	@Scheduled(cron = "0 38 12 * * *")
	public void stopAll() {
		registry.getListenerContainers().forEach(c -> {
			log.info("Stopping listener container {}", c);
			c.stop();
		});
	}

	@Scheduled(cron = "0 40 12 * * *")
	public void startAll() {
		registry.getListenerContainers().forEach(c -> {
			log.info("Starting listener container {}", c);
			c.start();
		});
	}

}
