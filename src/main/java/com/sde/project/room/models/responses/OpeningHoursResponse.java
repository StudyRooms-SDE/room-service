package com.sde.project.room.models.responses;

import com.sde.project.room.models.tables.OpeningHours;
import com.sde.project.room.models.tables.Room;
import com.sde.project.room.models.utils.TimeSlot;

import java.util.List;
import java.util.stream.Collectors;

public class OpeningHoursResponse {
    private RoomResponse room;
    private List<TimeSlot> openingHours;

    public OpeningHoursResponse() {
    }

    public OpeningHoursResponse(RoomResponse room, List<TimeSlot> openingHours) {
        this.room = room;
        this.openingHours = openingHours;
    }

    public OpeningHoursResponse build(Room room, List<OpeningHours> openingHours) {
        RoomResponse roomResponse = new RoomResponse().build(room);
        List<TimeSlot> timeSlots = openingHours.stream()
                .map(hours -> new TimeSlot().build(hours))
                .collect(Collectors.toList());
        return new OpeningHoursResponse(roomResponse, timeSlots);

    }

    public RoomResponse room() {
        return room;
    }

    public RoomResponse getRoom() {
        return room;
    }

    public void setRoom(RoomResponse room) {
        this.room = room;
    }

    public List<TimeSlot> getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(List<TimeSlot> openingHours) {
        this.openingHours = openingHours;
    }
}
