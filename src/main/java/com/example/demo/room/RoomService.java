package com.example.demo.room;

import com.example.demo.company.CompanyEntity;
import com.example.demo.exceptions.NonUniqueResultException;
import com.example.demo.generic.DataStatusEnum;
import com.example.demo.room.RoomEntity;
import com.example.demo.room.RoomRequest;
import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.room_place.RoomPlaceEntity;
import com.example.demo.room_type.RoomTypeEntity;
import com.example.demo.user.UserEntity;

import java.util.List;
import java.util.UUID;

public interface RoomService {
    RoomEntity save(UserEntity user,RoomRequest request) throws NonUniqueResultException;
    RoomEntity get(UserEntity user,UUID id) throws DataNotFoundException;
    String delete(UUID id);
    List<RoomEntity> all(UserEntity user,DataStatusEnum status);

    RoomEntity update(RoomRequest obj,UserEntity user);

    void changedSumIfRoomTypeSumChanged(UUID roomId, UUID roomType,UUID workerId,UserEntity user);

    List<RoomEntity> getByRoomTypeId(UserEntity user, UUID roomTypeId);
}
