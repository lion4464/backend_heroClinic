package com.example.demo.cashier;

import com.example.demo.generic.PaymentType;
import com.example.demo.patients.PatientDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CashierRequest {
    private UUID id;
    @NotNull(message = "sum_not_null")
    @Min(value = 0,message = "must_be_greater_han_or_equal_to_0")
    private Float sum;
    @NotNull(message = "patient_id_must_be_filled_because_it_is_required")
    private UUID patientId;
    private PaymentType paymentType;

}
