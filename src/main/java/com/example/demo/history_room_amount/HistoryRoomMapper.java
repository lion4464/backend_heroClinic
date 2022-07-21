package com.example.demo.history_room_amount;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HistoryRoomMapper {
    List<HistoryRoomDto> fromPageEntity(List<HistoryRoomEntity> entityList);
}
