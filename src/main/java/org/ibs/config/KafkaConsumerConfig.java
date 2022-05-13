package org.ibs.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.ByteArrayDeserializer;
import org.apache.kafka.common.serialization.BytesDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.ibs.domain.entity.Person;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.*;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.backoff.FixedBackOff;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Component
@Slf4j
@Profile("dev") // Testing consumer locally
public class KafkaConsumerConfig {

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Bean
    public ConsumerFactory<String, Person> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "1");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        props.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, StringDeserializer.class);
        props.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "org.ibs.domain.entity");
        props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, BytesDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(props);
    }

    /// Configure listeners with filters

    private ConcurrentKafkaListenerContainerFactory<String, Person> getBaseListenerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Person> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());

        // Log failed records.
        factory.setCommonErrorHandler(new CommonLoggingErrorHandler());
        return factory;
    }

    @Bean("kafkaListenerPersonAddedFactory")
    public ConcurrentKafkaListenerContainerFactory<String, Person> kafkaListenerPersonAddedFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Person> factory = getBaseListenerFactory();
        factory.setRecordFilterStrategy(r -> r.key().startsWith("Added person"));
        return factory;
    }

    @Bean("kafkaListenerPersonUpdatedFactory")
    public ConcurrentKafkaListenerContainerFactory<String, Person> kafkaListenerPersonUpdatedFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Person> factory = getBaseListenerFactory();
        factory.setRecordFilterStrategy(r -> r.key().startsWith("Updated person"));
        return factory;
    }

    @Bean("kafkaListenerPersonDeletedFactory")
    public ConcurrentKafkaListenerContainerFactory<String, Person> kafkaListenerPersonDeletedFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Person> factory = getBaseListenerFactory();
        factory.setRecordFilterStrategy(r -> r.key().startsWith("Deleted person"));
        return factory;
    }
}
