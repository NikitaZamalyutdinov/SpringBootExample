package org.ibs.middleware;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

abstract class KafkaProducer<T> {

    @Autowired
    protected KafkaTemplate<String, String> kafkaTemplate;

    abstract void sendMessage(String prefix, T entity);
}
