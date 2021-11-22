package com.fabrick.exercise.configurations;

import com.fabrick.exercise.interceptors.HttpHeaderInterceptor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Configuration
public class FabrickConfiguration {

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(Collections.singletonList(httpHeaderInterceptor()));
        return restTemplate;
    }

    @Bean
    public HttpHeaderInterceptor httpHeaderInterceptor() {
        return new HttpHeaderInterceptor();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

}
