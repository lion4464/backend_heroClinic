package com.example.demo.analyses_invoice;

import com.example.demo.analyses.AnalysesMapper;
import com.example.demo.generic.Convertor;
import com.example.demo.patients.PatientConvertor;
import org.springframework.stereotype.Component;

@Component
public class AnalyseInvoiceConvertor extends Convertor<AnalysesInvoiceResponse, AnalysesInvoiceEntity> {
    public AnalyseInvoiceConvertor(PatientConvertor patientConvertor, AnalysesMapper analysesMapper) {
        super(analyseInvoiceDTO -> new AnalysesInvoiceEntity(),reviewInvoiceEntity -> new AnalysesInvoiceResponse(
                reviewInvoiceEntity.getId(),
                patientConvertor.convertFromEntity(reviewInvoiceEntity.getPatient()),
                analysesMapper.toDto(reviewInvoiceEntity.getAnalyse()),
                reviewInvoiceEntity.getPaidAmount(),
                reviewInvoiceEntity.getPaymentType(),
                reviewInvoiceEntity.getCreatedBy(),
                reviewInvoiceEntity.getModifiedDate(),
                reviewInvoiceEntity.getCreatedDate()
        ));
    }
}
