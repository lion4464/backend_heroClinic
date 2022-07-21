package com.example.demo.patient_dept;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface PatientDeptRepository extends JpaRepository<PatientDeptEntity, UUID>, JpaSpecificationExecutor<PatientDeptEntity> {
    @Query(value = "SELECT sum(p.sum) AS payment FROM patient_dept p WHERE p.patient_id =:patientId and p.company_id=:companyId",nativeQuery = true)
    Float summAllPaymentsByPatient(@Param("patientId") UUID patientId, @Param("companyId") UUID companyId);
}
