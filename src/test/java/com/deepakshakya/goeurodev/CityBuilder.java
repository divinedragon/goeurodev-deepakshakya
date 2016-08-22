package com.deepakshakya.goeurodev;

import java.util.ArrayList;
import java.util.List;

import com.deepakshakya.goeurodev.json.City;

public class CityBuilder {

    public static final String CITY_NAME_PREFIX = "CITY";

    public static final String CITY_TYPE_PREFIX = "CITY_TYPE";

    private City city;

    public CityBuilder() {
        city = new City();
    }

    public CityBuilder setId(final int id) {
        city.setId(id);
        return this;
    }

    public CityBuilder setName(final String name) {
        city.setName(name);
        return this;
    }

    public CityBuilder setType(final String type) {
        city.setType(type);
        return this;
    }

    public CityBuilder setGeoLocation(final float latitude, final float longitude) {
        city.setGeolocation(new GeoLocationBuilder(latitude, longitude).build());
        return this;
    }

    public City build() {
        return city;
    }

    public List<City> buildList(final int count) {

        List<City> cities = new ArrayList<>(count);

        for (int i = 1; i <= count; i++) {
            // @formatter:off
            cities.add(
                    new CityBuilder()
                            .setId(i)
                            .setName(CITY_NAME_PREFIX + i)
                            .setType(CITY_TYPE_PREFIX + i)
                            .setGeoLocation(i, i)
                            .build()
                        
            );
            // @formatter:on
        }

        return cities;
    }
}
