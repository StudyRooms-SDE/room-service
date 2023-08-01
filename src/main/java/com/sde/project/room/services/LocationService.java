package com.sde.project.room.services;

import com.sde.project.room.models.LocationApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public class LocationService {

    @Value("${geo.api.key}")
    private String key;

    public LocationApiResponse getLocation(String description) {
        String formattedDescription = description.replaceAll(" ", "%");
        String url = String.format("https://eu1.locationiq.com/v1/search?key=%s&q=%s&format=json", key, formattedDescription);
        RestTemplate restTemplate = new RestTemplate();
        return Objects.requireNonNull(restTemplate.getForObject(url, LocationApiResponse[].class))[0];
    }
}
