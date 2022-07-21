package com.example.demo.analyses_invoice;

import com.example.demo.analyses.AnalysesEntity;
import com.example.demo.department.DepartmentEntity;
import com.example.demo.generic.AuditingGeneric;
import com.example.demo.generic.PaymentType;
import com.example.demo.patients.PatientEntity;
import com.example.demo.workers.WorkersEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "analyse_invoice")
@SQLDelete(sql = "update analyse_invoice set deleted = 'true' where id = ?")
@Where(clause = "deleted = 'false'")
public class AnalysesInvoiceEntity extends AuditingGeneric<UUID> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", insertable = false, updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private PatientEntity patient;

    @Column(name = "patient_id",insertable = false,updatable = false)
    private UUID patientId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "analyse_id", referencedColumnName = "id")
    private AnalysesEntity analyse;

    @Column(name = "analyse_id",insertable = false,updatable = false)
    private UUID analyseId;

    private Float paidAmount;

    private Float totalAmount;

    private Integer discount;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private DepartmentEntity department;

    @Column(name = "department_id",insertable = false,updatable = false)
    private UUID departmentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "worker_id", referencedColumnName = "id")
    private WorkersEntity worker;

    @Column(name = "worker_id",insertable = false,updatable = false)
    private UUID workerId;

}
