package org.ibs.kafka.consumer;

import org.ibs.middleware.PersonMessageProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@EnableKafka
public class PersonMessageConsumer {

    static final Logger LOGGER = LoggerFactory.getLogger(PersonMessageConsumer.class);

    @KafkaListener(topics = "employee", containerFactory = "kafkaListenerPersonAddedFactory")
    public void listenPersonAdded(String message) {
        LOGGER.info("Listener received message: " + message);
    }

    @KafkaListener(topics = "employee", containerFactory = "kafkaListenerPersonUpdatedFactory")
    public void listenPersonUpdated(String message) {
        LOGGER.info("Listener received message: " + message);
    }

    @KafkaListener(topics = "employee", containerFactory = "kafkaListenerPersonDeletedFactory")
    public void listenPersonDeleted(String message) {
        LOGGER.info("Listener received message: " + message);
    }
}
