package com.laura.portal.portal.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Configuracion implements WebMvcConfigurer {
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
