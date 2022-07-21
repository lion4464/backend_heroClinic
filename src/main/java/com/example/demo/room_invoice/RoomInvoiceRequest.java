package com.example.demo.room_invoice;

import com.example.demo.generic.PaymentType;
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
public class RoomInvoiceRequest {
    private UUID id;

    @NotNull(message = "Patient is required!!!")
    private UUID patientId;

    private UUID cashierId;

    @NotNull(message = "Worker is required!!!")
    private UUID workerId;

    @NotNull(message = "Room Place is required!!!")
    private UUID roomPlaceId;

    private Integer discount;

    @NotNull(message = "Type is required!!!")
    private PaymentType paymentType;

    private Integer delay;

}
