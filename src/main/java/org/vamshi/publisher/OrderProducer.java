package org.vamshi.publisher;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.vamshi.config.OrderConfig;
import org.vamshi.dto.OrderObject;
import org.vamshi.utils.Utils;

import java.util.Properties;
@Slf4j
public class OrderProducer implements Processor<byte[], OrderObject> {
    ProcessorContext context;
    public OrderProducer(String name) {
    }

    @Override
    public void init(ProcessorContext processorContext) {
        this.context = processorContext;
    }

    @Override
    public void process(byte[] key, OrderObject orderObject) {
        try {
            Properties producerProps = Utils.getOrderProducerProperties();
            //TODO
//            Producer<byte[], OrderObject> producer = new KafkaProducer<>(producerProps);
//            producer.send(new ProducerRecord<>(OrderConfig.getInstance().getDestinationKafkaTopic(), key, orderObject));
        } catch (Exception e) {
            log.error("Failed to send events to Sink topic: ", e.getStackTrace());
        }

    }

    @Override
    public void close() {

    }
}
