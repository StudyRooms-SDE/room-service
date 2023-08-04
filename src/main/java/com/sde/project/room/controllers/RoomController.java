package com.sde.project.room.controllers;

import com.sde.project.room.models.responses.LocationApiResponse;
import com.sde.project.room.models.responses.OpeningHoursResponse;
import com.sde.project.room.models.responses.RoomDetailsResponse;
import com.sde.project.room.models.responses.RoomResponse;
import com.sde.project.room.models.tables.OpeningHours;
import com.sde.project.room.models.tables.Room;
import com.sde.project.room.services.LocationService;
import com.sde.project.room.services.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
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

    @GetMapping(path = "/rooms/opening_hours")
    @ResponseStatus(HttpStatus.OK)
    public List<OpeningHoursResponse> getOpeningHours() {
        List<OpeningHours> openingHours = roomService.getOpeningHours();

        // Group the opening hours by study room
        Map<Room, List<OpeningHours>> map = openingHours.stream().collect(Collectors.groupingBy(OpeningHours::getRoom));

        return map.entrySet().stream()
                .map(entry -> new OpeningHoursResponse().build(entry.getKey(), entry.getValue()))
                .toList();
    }

    @GetMapping(path = "/rooms/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RoomResponse getRoomById(@PathVariable UUID id) {
        Room room = roomService.getRoomById(id);
        return new RoomResponse(room.getId().toString(), room.getName(), room.getBuilding(), room.getDescription());
    }

    @GetMapping(path = "/rooms/{id}/details")
    @ResponseStatus(HttpStatus.OK)
    public RoomDetailsResponse getRoomDetailsById(@PathVariable UUID id) {
        Room room = roomService.getRoomById(id);
        LocationApiResponse locationApiResponse = locationService.getLocation(room.getBuilding());

        return new RoomDetailsResponse(
                room.getId().toString(),
                room.getName(),
                room.getBuilding(),
                room.getDescription(),
                Double.valueOf(locationApiResponse.lat()),
                Double.valueOf(locationApiResponse.lon()),
                locationApiResponse.address().road() + " " + locationApiResponse.address().house_number() + ", " + locationApiResponse.address().city());
    }

    @GetMapping(path = "/rooms/{id}/opening_hours")
    @ResponseStatus(HttpStatus.OK)
    public OpeningHoursResponse getRoomOpeningHours(@PathVariable UUID id) {
        List<OpeningHours> openingHours = roomService.getRoomOpeningHours(id);
        Room room = roomService.getRoomById(id);
        return new OpeningHoursResponse().build(room, openingHours);
    }
}
