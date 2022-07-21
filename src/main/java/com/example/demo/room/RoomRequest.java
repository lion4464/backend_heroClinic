package com.example.demo.room;

import com.example.demo.generic.DataStatusEnum;
import com.example.demo.room_type.RoomTypeDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RoomRequest {
    private UUID id;

    @NotBlank(message = "name is required")
    private String name;
    private Integer capacity;
    private Float protcent;
    private Long closedDate;
    private UUID roomTypeId;

    private UUID workerId;
    private DataStatusEnum status;


    public RoomRequest(UUID id,UUID roomTypeId,DataStatusEnum status,UUID workerId) {
        this.id=id;
        this.roomTypeId=roomTypeId;
        this.status = status;
        this.workerId=workerId;
    }




    public RoomRequest(UUID id, String name,
                       Integer capacity, Float protcent,
                       DataStatusEnum status, Long closeDate,
                       UUID roomTypeId) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.protcent = protcent;
        this.closedDate = closeDate;
        this.roomTypeId = roomTypeId;
        this.status = status;
    }
}
