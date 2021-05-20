package com.nqminhuit.voucherShared.configs;

import static org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG;
import static org.junit.Assert.assertEquals;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.Test;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

public class CentralKafkaConfigTest {

    @Test
    public void jsonConsumerConfigs() {
        var props = CentralKafkaConsumerConfig.jsonConsumerConfigs("localhost:8080");
        assertEquals("localhost:8080", props.get(BOOTSTRAP_SERVERS_CONFIG));
        assertEquals(StringDeserializer.class, props.get(KEY_DESERIALIZER_CLASS_CONFIG));
        assertEquals(JsonDeserializer.class, props.get(VALUE_DESERIALIZER_CLASS_CONFIG));
    }

    @Test
    public void jsonProducerConfigs() {
        var props = CentralKafkaProducerConfig.jsonProducerConfigs("localhost:8901");
        assertEquals("localhost:8901", props.get(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG));
        assertEquals(StringSerializer.class, props.get(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG));
        assertEquals(JsonSerializer.class, props.get(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG));
    }

    @Test
    public void stringProducerConfigs() {
        var props = CentralKafkaProducerConfig.stringProducerConfigs("localhost:9012");
        assertEquals("localhost:9012", props.get(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG));
        assertEquals(StringSerializer.class, props.get(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG));
        assertEquals(StringSerializer.class, props.get(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG));
    }
}
