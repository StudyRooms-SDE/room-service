package com.sde.project.room.repositories;

import com.sde.project.room.models.tables.OpeningHours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OpeningHoursRepository extends JpaRepository<OpeningHours, UUID> {
    List<OpeningHours> findAllByRoomId(UUID id);

}