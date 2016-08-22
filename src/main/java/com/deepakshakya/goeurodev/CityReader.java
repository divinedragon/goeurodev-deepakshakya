package com.deepakshakya.goeurodev;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import com.deepakshakya.goeurodev.json.City;

@Component
public class CityReader {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.url}")
    private String apiUrl;

    public List<City> get(final String cityStr) {

        Assert.hasLength(cityStr);

        return Arrays.asList(restTemplate.getForObject(apiUrl + cityStr, City[].class));
    }
}
