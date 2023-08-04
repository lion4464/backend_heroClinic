package com.example.demo.workers;

import com.example.demo.analyses.AnalysesEntity;
import com.example.demo.generic.DataStatusEnum;
import com.example.demo.generic.JpaGenericRepository;
import com.example.demo.generic.JpaGenericService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface WorkerRepository extends JpaGenericRepository<WorkersEntity, UUID>{
    List<WorkersEntity> findAllByStatusAndCompanyIdAndDeleted(DataStatusEnum inactive, UUID companyId,Boolean deleted);
}
