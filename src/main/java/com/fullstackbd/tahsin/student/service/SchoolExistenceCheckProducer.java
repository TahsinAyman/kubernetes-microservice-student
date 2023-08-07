package com.fullstackbd.tahsin.student.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fullstackbd.tahsin.common.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class SchoolExistenceCheckProducer implements ProducerService {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public <T> void produce(T event) {
        try {
            String body = objectMapper.writeValueAsString(event);
            kafkaTemplate.send("school-existence-check-event", body);
        } catch (Exception e) {
            throw new RuntimeException("JSON DECODE ERROR");
        }
    }
}
