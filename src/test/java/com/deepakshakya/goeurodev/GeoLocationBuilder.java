package com.deepakshakya.goeurodev;

import java.util.ArrayList;
import java.util.List;

import com.deepakshakya.goeurodev.json.GeoLocation;

public class GeoLocationBuilder {

    private GeoLocation location;

    public GeoLocationBuilder(final float latitude, final float longitude) {
        location = new GeoLocation();
        location.setLatitude(latitude);
        location.setLongitude(longitude);
    }

    public GeoLocationBuilder setLatitude(final float latitude) {
        location.setLatitude(latitude);
        return this;
    }

    public GeoLocationBuilder setLongitude(final float longitude) {
        location.setLongitude(longitude);
        return this;
    }

    public GeoLocation build() {
        return location;
    }

    public List<GeoLocation> buildList(final int count) {

        List<GeoLocation> locations = new ArrayList<>(count);
        for (int i = 1; i <= count; i++) {
            locations.add(new GeoLocationBuilder(i, i).build());
        }

        return locations;
    }
}
