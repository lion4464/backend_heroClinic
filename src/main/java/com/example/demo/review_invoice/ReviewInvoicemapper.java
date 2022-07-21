package com.example.demo.review_invoice;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewInvoicemapper {
    ReviewInvoiceDTO toDto(ReviewInvoiceEntity patientEntity);
    ReviewInvoiceEntity fromDto(ReviewInvoiceDTO patientDTO);
    List<ReviewInvoiceDTO> fromPageEntity(Page<ReviewInvoiceEntity> page);
}
