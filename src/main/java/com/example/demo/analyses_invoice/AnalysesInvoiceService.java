package com.example.demo.analyses_invoice;


import com.example.demo.analyses.AnalysesEntity;
import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.exceptions.StatusInactiveException;
import com.example.demo.generic.JpaGenericService;
import com.example.demo.user.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.UUID;
import java.util.Date;

public interface AnalysesInvoiceService extends JpaGenericService<AnalysesInvoiceEntity, UUID> {
    AnalysesInvoiceEntity updateAnalysesInvoice(AnalysesInvoiceRequest obj) throws DataNotFoundException, StatusInactiveException;
    AnalysesInvoiceEntity get(UUID id) throws DataNotFoundException;
    AnalysesInvoiceEntity saveAnalysesInvoice(AnalysesInvoiceRequest obj) throws DataNotFoundException, StatusInactiveException;
    List<AnalysesInvoiceEntity> saveAll(List<AnalysesInvoiceRequest> objList) throws DataNotFoundException, StatusInactiveException;
    Page<AnalysesInvoiceEntity> all(Specification<AnalysesInvoiceEntity> spec, Pageable page);
    List<AnalyseInvoiseCounterDto> getCountInvoicesBetweenDates(UserEntity user,Date time1, Date time2) throws DataNotFoundException;
}
