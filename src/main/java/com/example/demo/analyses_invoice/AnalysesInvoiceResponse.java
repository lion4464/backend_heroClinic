package com.example.demo.analyses_invoice;


import com.example.demo.analyses.AnalysesResponse;
import com.example.demo.department.DepartmentDto;
import com.example.demo.generic.PaymentType;

import com.example.demo.patients.PatientDTO;
import com.example.demo.workers.WorkerFullDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnalysesInvoiceResponse {
    private UUID id;

    private Float paidAmount;
    private PatientDTO patient;
    private AnalysesResponse analyse;
    protected UUID createdBy;
    private Long modifiedDate;
    private Long createdDate;
    private PaymentType paymentType;

    public AnalysesInvoiceResponse(UUID id,
                                   PatientDTO patient,
                                   AnalysesResponse analyse,
                                   Float paidAmount,
                                   PaymentType paymentType,
                                   UUID createdBy,
                                   Long modifiedDate,
                                   Long createdDate) {
    this.id=id;
        this.paidAmount=paidAmount;
        this.patient=patient;
        this.analyse=analyse;
        this.createdBy=createdBy;
        this.modifiedDate=modifiedDate;
        this.createdDate=createdDate;
        this.paymentType=paymentType;

    }



}
