package com.deepakshakya.goeurodev;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import com.deepakshakya.goeurodev.json.City;

public class CityReaderTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private CityReader reader;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getWithNullCity() {
        reader.get(null);
    }

    @Test
    public void getWithValidCity() {

        int count = 3;

        List<City> expectedCities = new CityBuilder().buildList(count);

        when(restTemplate.getForObject(anyString(), anyObject()))
                .thenReturn(expectedCities.toArray(new City[expectedCities.size()]));

        List<City> cities = reader.get("Berlin");

        assertNotNull(cities);
        assertThat(cities, hasSize(count));

        for (int i = 1; i <= count; i++) {
            City city = cities.get(i - 1);

            assertNotNull(city);
            assertThat(city.getId(), is(i));
            assertThat(city.getName(), is(CityBuilder.CITY_NAME_PREFIX + i));
            assertThat(city.getType(), is(CityBuilder.CITY_TYPE_PREFIX + i));
            assertNotNull(city.getGeolocation());
            assertThat(city.getGeolocation().getLatitude(), is((float) i));
            assertThat(city.getGeolocation().getLongitude(), is((float) i));
        }
    }

    @Test(expected = IOException.class)
    public void getWithValidCityException() {

        when(restTemplate.getForObject(anyString(), anyObject())).thenThrow(IOException.class);

        reader.get("Berlin");
    }
}
