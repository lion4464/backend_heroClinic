package com.example.demo.cashier;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface CashierRepository extends JpaRepository<CashierEntity, UUID>, JpaSpecificationExecutor<CashierEntity> {
   @Query(value = "SELECT sum(c.sum) AS payment FROM cashier c WHERE c.patient_id=:patientId and c.company_id=:companyId",nativeQuery = true)
    Float summAllPaymentsByPatientAndCompanyId(@Param("patientId") UUID patientId,@Param("companyId") UUID companyId);

    List<CashierEntity> findAllByCompanyId(UUID companyId);
}
