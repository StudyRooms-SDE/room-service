package com.sde.project.room.models;

import jakarta.persistence.*;

import java.util.UUID;

@Table(name = "room")
@Entity(name = "room")
public class Room {
    @Id
    private UUID id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name="building", nullable = false)
    private String building;
    @Column(name = "description", nullable = true)
    private String description;

    public Room() {}

    public Room(UUID id, String name, String building, String description) {
        this.id = id;
        this.name = name;
        this.building = building;
        this.description = description;
    }
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }
}
