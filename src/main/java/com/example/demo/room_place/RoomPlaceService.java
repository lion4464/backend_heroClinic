package com.example.demo.room_place;

import com.example.demo.company.CompanyEntity;
import com.example.demo.room.RoomEntity;
import com.example.demo.user.UserEntity;

import java.util.List;
import java.util.UUID;

public interface RoomPlaceService {
    void saveAll(List<RoomPlaceEntity> roomPlaceEntity);

    List<RoomPlaceEntity> getAllPlaces(CompanyEntity company, UUID roomId);

    RoomPlaceEntity get(UUID id);
    List<RoomPlaceEntity> getAllPlaceFree(UUID roomID,UUID companyId);

    List<RoomPlaceEntity> findAllById(UUID id);

    void updateCloseDate(UUID roomid, Long closeDate);
}
