package com.example.demo.history_anaylse_amount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HistoryAnalyseRepository extends JpaRepository<HistoryAnalysesEntity, UUID> {
}
