package com.sde.project.room.services;

import com.sde.project.room.models.LocationApiResponse;
import com.sde.project.room.models.Room;
import com.sde.project.room.repositories.RoomRepository;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RoomService {
    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> getRooms() {
        return roomRepository.findAll();
    }

    public Room getRoomById(UUID id) {
        Room room = roomRepository.findById(id).orElseThrow(() -> {
            throw new DataRetrievalFailureException("Room not found");});
        return room;
    }
}
