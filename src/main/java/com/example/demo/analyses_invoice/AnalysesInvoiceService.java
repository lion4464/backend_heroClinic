package com.example.demo.analyses_invoice;


import com.example.demo.analyses.AnalysesEntity;
import com.example.demo.generic.JpaGenericService;
import com.example.demo.user.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.UUID;
import java.util.Date;

public interface AnalysesInvoiceService extends JpaGenericService<AnalysesEntity, UUID> {
    AnalysesInvoiceEntity update(AnalysesInvoiceRequest obj);
    String delete(UUID id);
    AnalysesInvoiceEntity get(UUID id);
    AnalysesInvoiceEntity save(AnalysesInvoiceRequest obj);
    List<AnalysesInvoiceEntity> saveAll(List<AnalysesInvoiceRequest> objList);
    Page<AnalysesInvoiceEntity> all(Specification<AnalysesInvoiceEntity> spec, Pageable page);
    List<AnalyseInvoiseCounterDto> getCountInvoicesBetweenDates(UserEntity user,Date time1, Date time2);
}
