package com.example.consumer;

import com.example.config.RabbitMQConfig;
import com.example.entity.DummyMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
//@Component
@RequiredArgsConstructor
public class MultiplePrefetchConsumer {

    @RabbitListener(queues = RabbitMQConfig.DUMMY_QUEUE, containerFactory = "prefetchOneContainerFactory")
    public void listenTransaction(DummyMessage message) throws InterruptedException {
        log.info("Message is {}", message);
        TimeUnit.MILLISECONDS.sleep(100);
    }

}
