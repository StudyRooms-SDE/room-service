package com.sde.project.room.models;

public record Address(
        String name,
        String house_number,
        String road,
        String neighbourhood,
        String suburb,
        String city,
        String county,
        String state,
        String postcode,
        String country,
        String country_code
) {
}

