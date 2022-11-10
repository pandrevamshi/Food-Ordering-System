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
public class ConfirmedStream {
    public static void main(String[] args) {
        ConfirmedStream orderStream = new ConfirmedStream();
        log.info("Stream about to start...");
        orderStream.startStream(Utils.getStreamProperties());
    }

    public void startStream(Properties streamConfiguration){
        Topology builder = new Topology();

    }
}