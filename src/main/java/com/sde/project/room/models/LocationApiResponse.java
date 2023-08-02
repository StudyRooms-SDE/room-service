package com.sde.project.room.models;

import java.util.Map;

public record LocationApiResponse(
        String place_id,
        String licence,
        String osm_type,
        String osm_id,
        String[] boundingbox,
        String lat,
        String lon,
        String display_name,
        String class_,
        String type,
        Double importance,
        String icon,
        Address address
) {}
