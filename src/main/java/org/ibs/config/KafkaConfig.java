package org.ibs.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Component
public class KafkaConfig {

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Bean
    public NewTopic newTopic(@Value("${com.ibs.kafka.topic.person}") String topicName) {
        return new NewTopic(topicName, 1, (short) 1);
    }

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapAddress);
        configProps.put(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);
        configProps.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapAddress);
        props.put(
                ConsumerConfig.GROUP_ID_CONFIG,
                "1");
        props.put(
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class);
        props.put(
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(props);
    }

    /// Configure listeners with filters

    private ConcurrentKafkaListenerContainerFactory<String, String> getBaseListenerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    @Bean("kafkaListenerPersonAddedFactory")
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerPersonAddedFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = getBaseListenerFactory();
        factory.setRecordFilterStrategy(r -> r.value().startsWith("Added person"));
        return factory;
    }

    @Bean("kafkaListenerPersonUpdatedFactory")
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerPersonUpdatedFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = getBaseListenerFactory();
        factory.setRecordFilterStrategy(r -> r.value().startsWith("Updated person"));
        return factory;
    }

    @Bean("kafkaListenerPersonDeletedFactory")
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerPersonDeletedFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = getBaseListenerFactory();
        factory.setRecordFilterStrategy(r -> r.value().startsWith("Deleted person"));
        return factory;
    }
}
