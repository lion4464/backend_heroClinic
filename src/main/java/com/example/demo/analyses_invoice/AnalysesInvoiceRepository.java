package com.example.demo.analyses_invoice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface AnalysesInvoiceRepository extends JpaRepository<AnalysesInvoiceEntity, UUID> , JpaSpecificationExecutor<AnalysesInvoiceEntity> {

    @Query(value = "select a.workerId as workerId , count(a.id) as counts from AnalysesInvoiceEntity a  where a.createdDate >= :time1 and a.createdDate <= :time2 and a.companyId= :companyId GROUP BY a.workerId")
    List<IAnalyseInvoiceCount> groupByInvoicesBetweenTwoDateWhereCompanyId(@Param("companyId") UUID companyId,@Param("time1") Long time1, @Param("time2")Long time2);

}
//@Query(value = "select a.worker_id as workerId, count(a.id) as counts from analyse_invoice a WHERE " +
//            "a.created_date between UNIX_TIMESTAMP(:time1) and UNIX_TIMESTAMP(:time2) group by a.worker_id", nativeQuery = true)