package com.deepakshakya.goeurodev;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.deepakshakya.goeurodev.json.City;

@Component
public class CityWriter {

    @Autowired
    private BufferedWriter writer;

    public void write(final List<City> cities) throws IOException {

        Assert.notNull(cities);

        for (City city : cities) {
            writer.write(getRow(city));
        }

        writer.close();
    }

    private String getRow(final City city) {

        Assert.notNull(city);

        // @formatter:off
        return city.getId() + ","
             + city.getName() + ","
             + city.getType() + ","
             + city.getGeolocation().getLatitude() + ","
             + city.getGeolocation().getLongitude()
             + "\n"
        ;
        // @formatter:on
    }

    public void setWriter(final BufferedWriter writer) {
        this.writer = writer;
    }
}
