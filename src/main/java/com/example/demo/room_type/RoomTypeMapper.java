package com.example.demo.room_type;

import com.example.demo.room_type.RoomTypeDTO;
import com.example.demo.room_type.RoomTypeEntity;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring")
public interface RoomTypeMapper {
    RoomTypeDTO toDto(RoomTypeEntity entity);
    RoomTypeEntity fromDto(RoomTypeDTO request);
    List<RoomTypeDTO> fromPageEntity(List<RoomTypeEntity> entityList);
}
