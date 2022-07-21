package com.example.demo.review_invoice;

import com.example.demo.department.DepartmentDto;
import com.example.demo.department.Departmentmapper;
import com.example.demo.generic.Convertor;
import com.example.demo.patients.PatientConvertor;
import com.example.demo.patients.PatientDTO;
import com.example.demo.workers.WorkerFullDTO;
import com.example.demo.workers.WorkerMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.function.Function;

@Component
public class ReviewInvoiceConvertor extends Convertor<ReviewInvoiceDTO,ReviewInvoiceEntity> {
    public ReviewInvoiceConvertor(PatientConvertor patientConvertor, WorkerMapper workerMapper, Departmentmapper departmentmapper) {
        super(reviewInvoiceDTO -> new ReviewInvoiceEntity(),reviewInvoiceEntity -> new ReviewInvoiceDTO(
                reviewInvoiceEntity.getId(),
                patientConvertor.convertFromEntity(reviewInvoiceEntity.getPatient()),
                workerMapper.toDto(reviewInvoiceEntity.getWorker()),
                reviewInvoiceEntity.getPaidAmount(),
                reviewInvoiceEntity.getPaymentType(),
                departmentmapper.toDto(reviewInvoiceEntity.getDepartment()),
                reviewInvoiceEntity.getCreatedBy(),
                reviewInvoiceEntity.getModifiedDate(),
                reviewInvoiceEntity.getCreatedDate()
        ));
    }
}
