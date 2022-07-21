package com.example.demo.room_place;

import com.example.demo.room.RoomResponse;
import com.example.demo.workers.WorkerFullDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomPlaceResponce {
    private UUID id;

    private Float payAmount;

    private Integer placeNumber;

    private Float protcent;

    private Long closedDate;

    private Long createdDate;

    private Long modifiedDate;
}
