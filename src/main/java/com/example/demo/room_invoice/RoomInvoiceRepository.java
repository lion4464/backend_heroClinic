package com.example.demo.room_invoice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface RoomInvoiceRepository extends JpaRepository<RoomInvoiceEntity, UUID> , JpaSpecificationExecutor<RoomInvoiceEntity> {

}
