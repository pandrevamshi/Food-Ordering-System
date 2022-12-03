package org.vamshi.streams;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.Topology;
import org.vamshi.config.OrderConfig;
import org.vamshi.mapper.OrderMapper;
import org.vamshi.publisher.OrderProducer;
import org.vamshi.utils.CommonConstants;
import org.vamshi.utils.Utils;

import java.util.Properties;
@Slf4j
public class OrderStream {
    public static void main(String[] args) {
        OrderStream orderStream = new OrderStream();
        log.info("Stream about to start...");
        orderStream.startStream(Utils.getStreamProperties());
    }

    public void startStream(Properties streamConfiguration){
        Topology builder = new Topology();
        builder.addSource(CommonConstants.STREAM_NAME, OrderConfig.getInstance().getSourceKafkaTopic())
                .addProcessor(CommonConstants.MAPPER_NAME, () -> new OrderMapper(this.getClass().getName()), CommonConstants.STREAM_NAME)
                .addProcessor(CommonConstants.PUBLISHER, () -> new OrderProducer(this.getClass().getName()), CommonConstants.MAPPER_NAME);
        KafkaStreams streams = new KafkaStreams(builder, streamConfiguration);
        streams.setUncaughtExceptionHandler((Thread thread, Throwable throwable)->log.error("Uncaught Exception {}", throwable.getStackTrace()));
        streams.cleanUp();
        streams.start();
        Runtime.getRuntime().addShutdownHook(new Thread("shutdown") {
            @Override
            public void run() {
                streams.close();
            }
        });
    }

}