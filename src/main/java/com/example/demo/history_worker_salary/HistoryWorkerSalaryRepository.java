package com.example.demo.history_worker_salary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface HistoryWorkerSalaryRepository extends JpaRepository<HistoryWorkerSalary, UUID> {
    List<HistoryWorkerSalary> findByWorkerId(UUID id);
}
