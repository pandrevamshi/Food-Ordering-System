package org.vamshi.publisher;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.vamshi.utils.CommonConstants;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
@Slf4j
public class ErrorProducer {
    String errorBrokers;
    String errorTopic;
    String clientId;
    static ErrorProducer instance;

    ErrorProducer(){
        try {
            InputStream input = new FileInputStream(CommonConstants.ERROR_CONFIG_PATH);
            Properties props = new Properties();
            props.load(input);
            this.errorBrokers = props.getProperty("error.kafka.brokers");
            this.errorTopic = props.getProperty("error.kafka.topic");
            this.clientId = props.getProperty("error.client.id");

        } catch (Exception e) {
            log.info("Error while fetching the error properties from config file");
        }
    }
    public static ErrorProducer getInstance(){
        if(instance==null){
            instance = new ErrorProducer();
        }
        return instance;
    }
    public void sendToErrorTopic(String data, String type) {
        try {

            Properties producerProps = getErrorProducerProperties();
            Producer<String, String> producer = new KafkaProducer<>(producerProps);
            producer.send(new ProducerRecord<>(errorTopic, type, data));

        } catch (Exception e) {
            log.error("Failed to send events to Error topic: ", e.getStackTrace());
        }

    }

    public Properties getErrorProducerProperties(){
        Properties producerProperties = new Properties();
        producerProperties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, this.errorBrokers);
        producerProperties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getCanonicalName());
        producerProperties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getCanonicalName());
        producerProperties.setProperty(ProducerConfig.CLIENT_ID_CONFIG, this.clientId);

        return producerProperties;
    }

}
