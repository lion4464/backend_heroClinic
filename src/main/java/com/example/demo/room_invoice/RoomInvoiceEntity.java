package com.example.demo.room_invoice;

import com.example.demo.cashier.CashierEntity;
import com.example.demo.department.DepartmentEntity;
import com.example.demo.generic.AuditingGeneric;
import com.example.demo.generic.PaymentType;
import com.example.demo.patient_dept.PatientDeptEntity;
import com.example.demo.patients.PatientEntity;
import com.example.demo.room.RoomEntity;
import com.example.demo.room_place.RoomPlaceEntity;
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
@Table(name = "room_invoice")
@SQLDelete(sql = "update room_invoice set deleted = 'true' where id = ?")
@Where(clause = "deleted = 'false'")
public class RoomInvoiceEntity extends AuditingGeneric<UUID> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", insertable = false, updatable = false, nullable = false)
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private PatientEntity patient;

    @Column(name = "patient_id",insertable = false,updatable = false)
    private UUID patientId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_place_id", referencedColumnName = "id")
    private RoomPlaceEntity roomPlace;

    @Column(name = "room_place_id",insertable = false,updatable = false)
    private UUID roomPlaceId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cashier_id", referencedColumnName = "id", nullable = true)
    private CashierEntity cashier;

    @Column(name = "cashier_id",insertable = false,updatable = false)
    private UUID cashierId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_dept_id", referencedColumnName = "id")
    private PatientDeptEntity patientDept;

    @Column(name = "patient_dept_id",insertable = false,updatable = false)
    private UUID patientDeptId;


    private Float paidAmount;

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

    @Column(name="protcent")
    private Float protcent;


}
