package com.example.demo.analyses;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface AnalysesRepository extends JpaRepository<AnalysesEntity, UUID> {
    List<AnalysesEntity> findAllByCompanyId(UUID companyId);
}
