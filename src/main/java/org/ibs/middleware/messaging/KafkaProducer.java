package org.ibs.middleware.messaging;

import org.ibs.domain.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

abstract class KafkaProducer<T> {

    @Autowired
    protected KafkaTemplate<String, Person> kafkaTemplate;

    abstract void sendMessage(String prefix, T entity);
}
