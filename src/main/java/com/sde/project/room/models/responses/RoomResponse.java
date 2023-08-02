package com.sde.project.room.models.responses;

import com.sde.project.room.models.tables.Room;

public class RoomResponse {
    private String id;
    private String name;
    private String building;
    private String description;

    public RoomResponse(){}
    public RoomResponse(String id, String name, String building, String description) {
        this.id = id;
        this.name = name;
        this.building = building;
        this.description = description;
    }

    public RoomResponse build(Room room) {
        RoomResponse response = new RoomResponse(
                room.getId().toString(),
                room.getName(),
                room.getBuilding(),
                room.getDescription()
        );
        return response;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
