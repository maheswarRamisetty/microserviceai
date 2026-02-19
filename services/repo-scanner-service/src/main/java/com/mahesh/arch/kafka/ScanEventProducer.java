package com.mahesh.arch.kafka;

import com.mahesh.arch.model.ScanEvent;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class ScanEventProducer {
    private final KafkaTemplate<String, ScanEvent> kafkaTemplate;

    public ScanEventProducer(@Qualifier("scanEventKafkaTemplate") KafkaTemplate<String, ScanEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(ScanEvent event){
        kafkaTemplate.send("repo-scan-event", event);
    }
}

