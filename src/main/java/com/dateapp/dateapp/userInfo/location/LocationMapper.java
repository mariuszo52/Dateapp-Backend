package com.dateapp.dateapp.userInfo.location;

public class LocationMapper {
    public static Location map(LocationDto locationDto) {
        Location location = new Location();
        location.setId(locationDto.getId());
        location.setCountry(locationDto.getCountry());
        location.setName(locationDto.getName());
        location.setLatitude(locationDto.getLatitude());
        location.setLongitude(locationDto.getLongitude());
        return location;

    }

    public static LocationDto map(Location location) {
        LocationDto locationDto = new LocationDto();
        locationDto.setId(location.getId());
        locationDto.setName(location.getName());
        locationDto.setCountry(location.getCountry());
        locationDto.setLatitude(location.getLatitude());
        locationDto.setLongitude(location.getLongitude());
        return locationDto;

    }
}
