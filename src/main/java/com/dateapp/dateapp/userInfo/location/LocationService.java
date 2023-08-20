package com.dateapp.dateapp.userInfo.location;

import org.apache.lucene.util.SloppyMath;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.apache.lucene.util.SloppyMath.haversinMeters;
import static org.apache.lucene.util.SloppyMath.haversinSortKey;

@Service
public class LocationService {
    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Optional<LocationDto> save(LocationDto locationDto){
        Location savedLocation = locationRepository.save(LocationMapper.map(locationDto));
        return Optional.of(LocationMapper.map(savedLocation));

    }

    public double calculateDistanceInMetersBetweenUsers(
            double user1Latitude, double user1Longitude,
            double user2Latitude, double user2Longitude){
        double sortKey = haversinSortKey(user1Latitude, user1Longitude, user2Latitude, user2Longitude);
        return haversinMeters(sortKey);

    }
}
