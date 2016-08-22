package com.deepakshakya.goeurodev;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.Assert;

import com.deepakshakya.goeurodev.json.City;

@SpringBootApplication
public class CityApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(CityApplication.class);

    @Autowired
    private CityReader reader;

    @Autowired
    private CityWriter writer;

    public static void main(String[] args) {
        SpringApplication.run(CityApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Assert.notEmpty(args);
        // Assert.hasLength(args[0]);

        List<City> cities = reader.get("Berlin");
        writer.write(cities);
    }
}
