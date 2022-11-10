package org.vamshi.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.vamshi.dto.OrderObject;
import org.vamshi.publisher.ErrorProducer;

public class OrderMapper implements Processor<byte[], Object> {

    private ProcessorContext context;

    public OrderMapper(String name) {
    }

    @Override
    public void init(ProcessorContext processorContext) {
        this.context = processorContext;
    }

    @Override
    public void process(byte[] key, Object payload) {
        try{
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            OrderObject orderObject = mapper.readValue(payload.toString(), OrderObject.class);

//            validate(orderObject);
            context.forward(key, orderObject);

        } catch (JsonProcessingException e) {
            ErrorProducer.getInstance().sendToErrorTopic(payload.toString(), "parseError");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {

    }

}
