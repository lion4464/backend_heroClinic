package com.example.demo.worker_expense;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface WorkerExpenseRepository extends JpaRepository<WorkerExpenseEntity, UUID> {
    List<WorkerExpenseEntity> findAllByCompanyId(UUID companyId);
}
