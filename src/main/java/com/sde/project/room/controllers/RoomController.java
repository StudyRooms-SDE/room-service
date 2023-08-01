package com.sde.project.room.controllers;

import com.sde.project.room.models.LocationApiResponse;
import com.sde.project.room.models.Room;
import com.sde.project.room.models.RoomDetailsResponse;
import com.sde.project.room.models.RoomResponse;
import com.sde.project.room.services.LocationService;
import com.sde.project.room.services.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/v1")
public class RoomController {

    private final RoomService roomService;
    private LocationService locationService;

    public RoomController(RoomService roomService, LocationService locationService) {
        this.roomService = roomService;
        this.locationService = locationService;
    }

    @GetMapping(path = "/rooms")
    @ResponseStatus(HttpStatus.OK)
    public List<RoomResponse> getRooms() {
        return roomService.getRooms()
                .stream()
                .map(room -> new RoomResponse(room.getId().toString(), room.getName(), room.getBuilding(), room.getDescription()))
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/rooms/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RoomDetailsResponse getRoomById(@PathVariable UUID id) {
        Room room = roomService.getRoomById(id);
        LocationApiResponse locationApiResponse = locationService.getLocation(room.getDescription());

        return new RoomDetailsResponse(room.getId().toString(),
                room.getName(),
                room.getBuilding(),
                room.getDescription(),
                Double.valueOf(locationApiResponse.lat()),
                Double.valueOf(locationApiResponse.lon()),
                locationApiResponse.display_name());
    }
}
