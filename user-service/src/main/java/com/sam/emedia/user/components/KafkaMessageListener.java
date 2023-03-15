package com.sam.emedia.user.components;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaMessageListener {
    @KafkaListener(topics = "msg_prdct", id = "kafkaMsgId")
    public void handlerNotification(String msg) {
        log.info("Received message from product service: {}", msg);
    }
}
