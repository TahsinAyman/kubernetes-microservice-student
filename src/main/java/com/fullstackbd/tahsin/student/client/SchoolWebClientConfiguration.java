package com.fullstackbd.tahsin.student.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class SchoolWebClientConfiguration {
    @Bean
    public WebClient schoolWebClient() {
        return WebClient
                .builder()
                .baseUrl("http://school-service:8080")
                .build();
    }
    @Bean
    public SchoolClient schoolClient() {
        HttpServiceProxyFactory httpServiceProxyFactory =
                HttpServiceProxyFactory
                        .builder(WebClientAdapter.forClient(this.schoolWebClient()))
                        .build();
        return httpServiceProxyFactory.createClient(SchoolClient.class);
    }
}
