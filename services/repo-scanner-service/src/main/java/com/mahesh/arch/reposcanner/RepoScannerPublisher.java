package com.mahesh.arch.reposcanner;

import com.mahesh.arch.shared.ServiceDiscord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class RepoScannerPublisher {
    private final KafkaTemplate<String,Object> kafkaTemplate;


    public RepoScannerPublisher(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(String service,String repo){
        kafkaTemplate.send("architecture.service.discovered",new ServiceDiscord(service,repo));
    }
}
