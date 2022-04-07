package com.msusuario.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTesmpleteConfig {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();

    }

}
