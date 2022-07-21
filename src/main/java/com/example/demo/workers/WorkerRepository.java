package com.example.demo.workers;

import com.example.demo.generic.DataStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface WorkerRepository extends JpaRepository<WorkersEntity, UUID> {
    List<WorkersEntity> findAllByStatusAndCompanyId(DataStatusEnum inactive, UUID companyId);
}
