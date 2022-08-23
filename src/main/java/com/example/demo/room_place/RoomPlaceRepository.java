package com.example.demo.room_place;

import com.example.demo.room.RoomEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoomPlaceRepository extends JpaRepository<RoomPlaceEntity, UUID> {

    @EntityGraph(value = "RoomPlaceEntity.graph_1")
    Optional<RoomPlaceEntity> findById(UUID id);

    List<RoomPlaceEntity> getAllByRoomIdAndCompanyId(UUID roomId, UUID companyId);

    List<RoomPlaceEntity> getAllByClosedDateAndRoomIdAndCompanyId(Long closeDate, UUID roomId, UUID companyId);
}
