package com.example.demo.room_invoice;


import com.example.demo.analyses.AnalysesEntity;
import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.exceptions.StatusInactiveException;
import com.example.demo.generic.JpaGenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.UUID;

public interface RoomInvoiceService   extends JpaGenericService<RoomInvoiceEntity, UUID> {
    RoomInvoiceEntity updateRoomInvoiceE(RoomInvoiceRequest obj) throws IllegalArgumentException, DataNotFoundException, StatusInactiveException;

    RoomInvoiceEntity get(UUID id) throws DataNotFoundException;
    RoomInvoiceEntity saveRoomInvoiceE(RoomInvoiceRequest obj) throws DataNotFoundException, StatusInactiveException;

    Page<RoomInvoiceEntity> all(Specification<RoomInvoiceEntity> spec, Pageable page);
}
