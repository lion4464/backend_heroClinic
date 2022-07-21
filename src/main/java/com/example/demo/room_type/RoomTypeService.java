package com.example.demo.room_type;

import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.salary_type.SalaryTypeRequest;
import com.example.demo.user.UserEntity;

import java.util.List;
import java.util.UUID;

public interface RoomTypeService {
    RoomTypeEntity save(RoomTypeDTO request,UserEntity user);
    RoomTypeEntity get(UUID id) throws DataNotFoundException;
    String delete(UUID id);
    List<RoomTypeEntity> all(UserEntity user);
    RoomTypeEntity update(RoomTypeDTO obj,UserEntity user);

}
