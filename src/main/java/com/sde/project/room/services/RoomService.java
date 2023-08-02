package com.sde.project.room.services;

import com.sde.project.room.models.tables.OpeningHours;
import com.sde.project.room.models.tables.Room;
import com.sde.project.room.repositories.OpeningHoursRepository;
import com.sde.project.room.repositories.RoomRepository;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RoomService {
    private final RoomRepository roomRepository;

    private final OpeningHoursRepository hoursRepository;
    public RoomService(RoomRepository roomRepository, OpeningHoursRepository hoursRepository) {
        this.roomRepository = roomRepository;
        this.hoursRepository = hoursRepository;
    }

    public List<Room> getRooms() {
        return roomRepository.findAll();
    }

    public Room getRoomById(UUID id) {
        Room room = roomRepository.findById(id).orElseThrow(() -> {
            throw new DataRetrievalFailureException("Room not found");});

        return room;
    }

    public List<OpeningHours> getOpeningHours() {
        return hoursRepository.findAll();
    }

    public List<OpeningHours> getRoomOpeningHours(UUID id) {
        return hoursRepository.findAllByRoomId(id);
    }

}
