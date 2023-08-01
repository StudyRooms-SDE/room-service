package com.sde.project.room.models;

public record RoomDetailsResponse (String id, String name, String building, String description, Double latitude, Double longitude, String address) {
}
