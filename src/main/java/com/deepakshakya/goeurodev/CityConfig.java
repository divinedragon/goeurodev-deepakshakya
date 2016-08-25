package com.deepakshakya.goeurodev;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CityConfig {

    @Value("${csvfile.path}")
    private String filePath;

    @Value("${csvfile.header}")
    private String header;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean(destroyMethod = "close")
    public BufferedWriter writer() throws Exception {

        File file = new File(filePath);
        file.delete();

        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

        writer.write(header + "\n");

        return writer;
    }
}
