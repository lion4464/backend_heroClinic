package com.example.demo.room;

import com.example.demo.room.RoomEntity;
import com.example.demo.analyses.AnalysesRequest;
import com.example.demo.room.RoomResponse;
import com.example.demo.room_place.RoomPlaceEntity;
import com.example.demo.room_place.RoomPlaceResponce;
import com.example.demo.room_place.RoomPlaceService;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoomMapper {
    RoomResponse toDto(RoomEntity analyse);
    RoomEntity fromDto(AnalysesRequest analyseRequest);
    List<RoomResponse> fromPageEntity(List<RoomEntity> entityList);

    List<RoomPlaceResponce> fromListEntity(List<RoomPlaceEntity> allRoom);
}
