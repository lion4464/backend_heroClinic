package com.example.demo.room_type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RoomTypeDTO {
    private UUID id;
    private String name;
    @NotNull(message = "paymentAmount_not_null")
    private Float paymentAmount;
}
