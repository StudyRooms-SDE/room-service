package com.sde.project.room.models.responses;

public record RoomDetailsResponse (String id, String name, String building, String description, Double latitude, Double longitude, String address) {
}
