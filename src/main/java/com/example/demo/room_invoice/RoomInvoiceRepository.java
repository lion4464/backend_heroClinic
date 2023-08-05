package com.example.demo.room_invoice;

import com.example.demo.generic.JpaGenericRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface RoomInvoiceRepository extends JpaGenericRepository<RoomInvoiceEntity, UUID> {

}
