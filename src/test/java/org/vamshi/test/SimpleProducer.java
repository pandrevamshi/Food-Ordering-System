package org.vamshi.test;

import java.util.Properties;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

    public class SimpleProducer {

        public static void main(String[] args) throws Exception{

            String topicName = "order_details";

            Properties props = new Properties();

            props.put("bootstrap.servers", "localhost:9092");
            props.put("acks", "all");
            props.put("retries", 0);
            props.put("batch.size", 16384);
            props.put("linger.ms", 1);

            props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");

            props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

            Producer<String, String> producer = new KafkaProducer <String, String>(props);
            String payload = "{\"name\":\"pandre\"}";
                producer.send(new ProducerRecord<>(topicName, payload));
            producer.close();
        }
    }
