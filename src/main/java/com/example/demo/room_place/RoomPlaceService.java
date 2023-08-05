package com.example.demo.room_place;

import com.example.demo.analyses.AnalysesEntity;
import com.example.demo.company.CompanyEntity;
import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.generic.JpaGenericService;
import com.example.demo.room.RoomEntity;
import com.example.demo.user.UserEntity;

import java.util.List;
import java.util.UUID;

public interface RoomPlaceService   extends JpaGenericService<RoomPlaceEntity, UUID> {
    void saveAll(List<RoomPlaceEntity> roomPlaceEntity);

    List<RoomPlaceEntity> getAllPlaces(CompanyEntity company, UUID roomId);

    RoomPlaceEntity get(UUID id) throws DataNotFoundException;
    List<RoomPlaceEntity> getAllPlaceFree(UUID roomID,UUID companyId) throws DataNotFoundException;

    List<RoomPlaceEntity> findAllById(UUID id);

    void updateCloseDate(UUID roomid, Long closeDate) throws DataNotFoundException;
}
