package com.fullstackbd.tahsin.student.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fullstackbd.tahsin.common.events.StudentSaveFailedEvent;
import com.fullstackbd.tahsin.common.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class StudentSaveFailedConsumer implements ConsumerService {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private StudentService service;
    @Override
    @KafkaListener(topics = "student-save-failed-event")
    public void consume(String event) {
        try {
            StudentSaveFailedEvent studentSaveFailedEvent = objectMapper.readValue(event, StudentSaveFailedEvent.class);
            service.deleteById(studentSaveFailedEvent.getId());
        } catch (JsonProcessingException e) {
            throw new RuntimeException("JSON DECODE ERROR");
        }
    }
}
