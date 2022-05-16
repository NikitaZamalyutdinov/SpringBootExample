package org.ibs.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.ibs.domain.NewPersonRepository;
import org.ibs.domain.entity.NewPerson;
import org.ibs.domain.entity.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
@EnableKafka
@Profile("dev")
public class PersonMessageConsumer {

    @Autowired
    public NewPersonRepository newPersonRepository;

    static final Logger LOGGER = LoggerFactory.getLogger(PersonMessageConsumer.class);

    private void logMessage(String key, Person person) {
        String message = String.format("%s:%s", key, person.toString());
        LOGGER.info("Listener received message: " + message);
    }

    @KafkaListener(topics = "employee", filter = "filterPersonAdded", groupId = "1")
    public void listenPersonAdded(ConsumerRecord<String, Person> record) {
        logMessage(record.key(), record.value());
        newPersonRepository.save(NewPerson.fromPerson(record.value(), Calendar.getInstance()));
    }

    @KafkaListener(topics = "employee", filter = "filterPersonUpdated", groupId = "1")
    public void listenPersonUpdated(ConsumerRecord<String, Person> record) {
        logMessage(record.key(), record.value());
    }

    @KafkaListener(topics = "employee", filter = "filterPersonDeleted", groupId = "1")
    public void listenPersonDeleted(ConsumerRecord<String, Person> record) {
        logMessage(record.key(), record.value());
    }
}
