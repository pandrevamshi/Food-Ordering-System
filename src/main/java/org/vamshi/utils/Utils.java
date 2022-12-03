package org.vamshi.utils;

import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.streams.StreamsConfig;
import org.vamshi.config.OrderConfig;

public class Utils {


    public static Properties getStreamProperties() {

        Properties streamProperties = new Properties();
        streamProperties.setProperty(StreamsConfig.APPLICATION_ID_CONFIG, OrderConfig.getInstance().getApplicationId());
        streamProperties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, OrderConfig.getInstance().getSourceBrokers());
        streamProperties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getCanonicalName());
        streamProperties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getCanonicalName());
        streamProperties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, OrderConfig.getInstance().getOffset());
        streamProperties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
//        streamProperties.setProperty("state.dir", "/test");
        return streamProperties;
    }

    public static Properties getOrderProducerProperties() {

        Properties producerProperties = new Properties();
        producerProperties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, OrderConfig.getInstance().getDestinationBrokers());
        producerProperties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getCanonicalName());
        producerProperties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getCanonicalName());
        producerProperties.setProperty(ProducerConfig.CLIENT_ID_CONFIG, OrderConfig.getInstance().getClientId());

        return producerProperties;
    }



}
