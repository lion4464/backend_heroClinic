package com.example.demo.room_type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RoomTypeDTO {
    private UUID id;
    private String name;
    private boolean deleted;
    @NotNull(message = "payment_amount_not_null")
    @Min(value = 0,message = "must_be_greater_han_or_equal_to_0")
    private Float paymentAmount;
}
