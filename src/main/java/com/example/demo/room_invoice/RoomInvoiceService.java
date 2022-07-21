package com.example.demo.room_invoice;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.UUID;

public interface RoomInvoiceService {
    RoomInvoiceEntity update(RoomInvoiceRequest obj) throws IllegalArgumentException;
    String delete(UUID id);
    RoomInvoiceEntity get(UUID id);
    RoomInvoiceEntity save(RoomInvoiceRequest obj);

    Page<RoomInvoiceEntity> all(Specification<RoomInvoiceEntity> spec, Pageable page);
}
