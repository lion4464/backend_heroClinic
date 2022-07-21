package com.example.demo.history_room_amount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface HistoryRoomRepository extends JpaRepository<HistoryRoomEntity, UUID> {
    List<HistoryRoomEntity> findAllByRoomAndCompanyId(UUID id, UUID companyId);
}
