package com.example.demo.cashier;

import com.example.demo.generic.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CashierForRoomInvoice {
    private UUID id;
    private PaymentType paymentType;
    private Float sum;
    protected UUID createdBy;
    private Long createdDate;
    private Long modifiedDate;
}
