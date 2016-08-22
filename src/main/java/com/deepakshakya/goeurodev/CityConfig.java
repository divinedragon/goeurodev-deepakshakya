package com.deepakshakya.goeurodev;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CityConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /*
     * @Bean public CityReader cityReader() { return new CityReader(); }
     * 
     * @Bean public CityWriter cityWriter() { return new CityWriter(); }
     */
}
