package com.example.demo.review_invoice;

import com.example.demo.department.DepartmentDto;
import com.example.demo.generic.PaymentType;
import com.example.demo.patients.PatientDTO;
import com.example.demo.workers.WorkerFullDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ReviewInvoiceDTO {
    private UUID id;
    private PatientDTO patient;
    private WorkerFullDTO worker;
    private Float paidAmount;
    private PaymentType paymentType;
    private DepartmentDto department;
    protected UUID createdBy;
    private Long modifiedDate;
    private Long createdDate;

}
