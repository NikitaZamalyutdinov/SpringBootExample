package org.ibs.middleware;

import org.ibs.domain.entity.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
public class PersonMessageProducer extends KafkaProducer<Person> {

    static final Logger LOGGER = LoggerFactory.getLogger(PersonMessageProducer.class);

    @Value("${com.ibs.kafka.topic.person}")
    private String personTopic;

    @Override
    public void sendMessage(String prefix, Person entity) {
        ListenableFuture<SendResult<String, Person>> future = kafkaTemplate.send(personTopic, prefix, entity);
        future.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onFailure(Throwable ex) {
                LOGGER.warn(ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Person> result) {
                String logMessage = String.format("Added message '%s' to topic '%s'.",
                        result.getProducerRecord().key() + ":" + result.getProducerRecord().value(),
                        personTopic);
                LOGGER.debug(logMessage);
            }
        });
    }

    void sendAddedPerson(Person person) {
        sendMessage("Added person", person);
    }

    void sendDeletedPerson(Person person) {
        sendMessage("Deleted person", person);
    }

    void sendUpdatedPerson(Person person) {
        sendMessage("Updated person", person);
    }
}
