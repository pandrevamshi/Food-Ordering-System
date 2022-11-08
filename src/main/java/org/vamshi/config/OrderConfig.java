package org.vamshi.config;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.vamshi.utils.CommonConstants;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

@Getter
@Slf4j
public class OrderConfig {

    static OrderConfig instance;
    String sourceKafkaTopic;
    String destinationKafkaTopic;
    String sourceBrokers;
    String destinationBrokers;
    String applicationId;
    String clientId;
    String offset;

    public static OrderConfig getInstance(){
        if(instance==null){
            instance = new OrderConfig();
        }
        return instance;
    }

    private OrderConfig() {
        try {
            InputStream input = new FileInputStream(CommonConstants.CONFIG_PATH);
            Properties props = new Properties();
            props.load(input);

            this.sourceBrokers = props.getProperty("source.kafka.brokers");
            this.sourceKafkaTopic = props.getProperty("source.kafka.topic");
            this.destinationKafkaTopic = props.getProperty("sink.kafka.topic");
            this.destinationBrokers = props.getProperty("sink.kafka.brokers");
            this.applicationId = props.getProperty("application.id");
            this.clientId = props.getProperty("producer.client.id");
            this.offset = props.getProperty("offset");

        } catch (Exception e) {
            log.info("Error while fetching the properties from source");
        }
    }




}
