package com.example.demo.patients;

import com.example.demo.generic.JpaGenericRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface PatientRepo extends JpaGenericRepository<PatientEntity, UUID>, JpaSpecificationExecutor<PatientEntity> {

}
