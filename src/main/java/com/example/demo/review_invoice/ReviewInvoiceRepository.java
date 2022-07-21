package com.example.demo.review_invoice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReviewInvoiceRepository extends JpaRepository<ReviewInvoiceEntity, UUID>, JpaSpecificationExecutor<ReviewInvoiceEntity> {

    @Query(value = "select r.workerId as workerId, count(r.patientId) as count from " +
            "ReviewInvoiceEntity r where r.createdDate between :from and :to and r.companyId =:companyId group by r.workerId order by count (r.patientId) desc")
    List<IReviewInvoiceCount> getReviewsCount(@Param("from") Long from, @Param("to") Long to, @Param("companyId") UUID companyId);

}
