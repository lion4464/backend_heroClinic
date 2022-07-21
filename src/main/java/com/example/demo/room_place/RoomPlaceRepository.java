package com.example.demo.room_place;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RoomPlaceRepository extends JpaRepository<RoomPlaceEntity, UUID> {

    List<RoomPlaceEntity> getAllByRoomIdAndCompanyId(UUID roomId, UUID companyId);

    List<RoomPlaceEntity> getAllByClosedDateAndRoomIdAndCompanyId(Long closeDate, UUID roomId, UUID companyId);
}
