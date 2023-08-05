package com.example.demo.analyses;

import com.example.demo.generic.JpaGenericRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface AnalysesRepository extends JpaGenericRepository<AnalysesEntity, UUID> {
    List<AnalysesEntity> findAllByCompanyId(UUID companyId);
}
