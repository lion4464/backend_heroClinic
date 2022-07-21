package com.example.demo.patient_dept;

import com.example.demo.generic.PaymentType;
import com.example.demo.patients.PatientDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDeptRequest {
    private UUID id;
    @NotNull(message = "sum must be filled because it is reuired!!!")
    private Float sum;
    private UUID patientId;
    private PaymentType paymentType;


}
