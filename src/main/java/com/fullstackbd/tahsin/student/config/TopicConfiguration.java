package com.fullstackbd.tahsin.student.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class TopicConfiguration {

    @Bean
    public NewTopic studentSaveFailedTopic() {
        return TopicBuilder
                .name("student-save-failed-event")
                .build();
    }


}
