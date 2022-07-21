package com.example.demo.patient_dept;

import com.example.demo.generic.PaymentType;
import com.example.demo.patients.PatientDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PatientDeptDto {
    private UUID id;
    private PaymentType paymentType;
    private Float sum;
    private PatientDTO patient;
    protected UUID createdBy;
    private Long createdDate;
    private Long modifiedDate;

}
