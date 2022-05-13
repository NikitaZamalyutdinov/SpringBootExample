package org.ibs.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.ibs.domain.entity.Person;
import org.ibs.middleware.PersonMessageProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@EnableKafka
@Profile("dev")
public class PersonMessageConsumer {

    static final Logger LOGGER = LoggerFactory.getLogger(PersonMessageConsumer.class);

    private void logMessage(String key, Person person) {
        String message = String.format("%s:%s", key, person.toString());
        LOGGER.info("Listener received message: " + message);
    }

    @KafkaListener(topics = "employee", containerFactory = "kafkaListenerPersonAddedFactory")
    public void listenPersonAdded(ConsumerRecord<String, Person> record) {
        logMessage(record.key(), record.value());
    }

    @KafkaListener(topics = "employee", containerFactory = "kafkaListenerPersonUpdatedFactory")
    public void listenPersonUpdated(ConsumerRecord<String, Person> record) {
        logMessage(record.key(), record.value());
    }

    @KafkaListener(topics = "employee", containerFactory = "kafkaListenerPersonDeletedFactory")
    public void listenPersonDeleted(ConsumerRecord<String, Person> record) {
        logMessage(record.key(), record.value());
    }
}
