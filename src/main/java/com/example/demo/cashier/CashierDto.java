package com.example.demo.cashier;

import com.example.demo.generic.PaymentType;
import com.example.demo.patients.PatientDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.UUID;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CashierDto {
    private UUID id;
    private PaymentType paymentType;
    private Float sum;
    private PatientDTO patient;
    protected UUID createdBy;
    private Long createdDate;
    private Long modifiedDate;

}
