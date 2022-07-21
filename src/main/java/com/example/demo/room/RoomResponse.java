package com.example.demo.room;

import com.example.demo.generic.DataStatusEnum;
import com.example.demo.room_type.RoomTypeDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RoomResponse {
    private UUID id;
    private String name;
    private Integer capacity;
    private Float protcent;
    private DataStatusEnum status;
    private RoomTypeDTO roomType;
    private Long createdDate;
    private Long modifiedDate;
    private Integer freePlace;


}
