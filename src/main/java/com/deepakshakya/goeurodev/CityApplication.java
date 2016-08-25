package com.deepakshakya.goeurodev;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.deepakshakya.goeurodev.json.City;

@SpringBootApplication
public class CityApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(CityApplication.class);

    @Autowired
    private CityReader reader;

    @Autowired
    private CityWriter writer;

    @Value("${default.city}")
    private String defaultCity;

    public static void main(String[] args) {
        SpringApplication.run(CityApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        String city;
        if (args == null || args.length == 0 || args[0].isEmpty()) {
            log.warn("No city provided from command line. Using default - " + defaultCity);
            city = defaultCity;
        } else {
            city = args[0].trim();
        }

        log.info("Querying for City - " + city);

        List<City> cities = reader.get(city);
        writer.write(cities);
    }
}
