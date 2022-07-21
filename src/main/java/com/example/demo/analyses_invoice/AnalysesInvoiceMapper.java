package com.example.demo.analyses_invoice;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")

public interface AnalysesInvoiceMapper {
    AnalysesInvoiceResponse toDto(AnalysesInvoiceEntity patientEntity);
    AnalysesInvoiceEntity fromDto(AnalysesInvoiceResponse patientDTO);
    List<AnalysesInvoiceResponse> fromPageEntity(Page<AnalysesInvoiceEntity> page);

    List<AnalysesInvoiceResponse> fromEntityList(List<AnalysesInvoiceEntity> saveAll);

}
