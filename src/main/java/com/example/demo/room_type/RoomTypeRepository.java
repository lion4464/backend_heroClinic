package com.example.demo.room_type;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RoomTypeRepository extends JpaRepository<RoomTypeEntity, UUID> {
    List<RoomTypeEntity> findAllByCompanyId(UUID companyId);
}
