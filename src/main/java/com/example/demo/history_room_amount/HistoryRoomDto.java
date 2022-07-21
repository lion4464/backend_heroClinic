package com.example.demo.history_room_amount;

import com.example.demo.room.RoomResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoryRoomDto {
    private UUID id;
    private Float amount;
    private RoomResponse room;

}
