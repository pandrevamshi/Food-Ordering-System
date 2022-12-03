package org.vamshi.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.vamshi.dto.OrderObject;
import org.vamshi.publisher.ErrorProducer;
@Slf4j
public class OrderMapper implements Processor<byte[], byte[]> {

    private ProcessorContext context;

    public OrderMapper(String name) {
    }

    @Override
    public void init(ProcessorContext processorContext) {
        this.context = processorContext;
    }

    public void process(byte[] key, byte[] payload) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            String event  = new String(payload);
            OrderObject orderObject = objectMapper.readValue(event, OrderObject.class);
//            validate(orderObject);
            context.forward(key, orderObject);

        } catch (JsonProcessingException e) {
            ErrorProducer.getInstance().sendToErrorTopic(payload.toString(), "parseError");
//            throw new RuntimeException(e);
            log.info("Error in processing the payload\n");
        }
    }

    @Override
    public void close() {

    }

}
