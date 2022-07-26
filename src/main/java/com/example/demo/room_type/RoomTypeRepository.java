package com.example.demo.room_type;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoomTypeRepository extends JpaRepository<RoomTypeEntity, UUID> {
    List<RoomTypeEntity> findAllByCompanyId(UUID companyId);

    Optional<RoomTypeEntity> findByIdAndDeleted(UUID id, boolean deleted);

    List<RoomTypeEntity> findAllByCompanyIdAndDeleted(UUID companyId, boolean deleted);
}
