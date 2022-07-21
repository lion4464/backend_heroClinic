package com.example.demo.patient_dept;

import com.example.demo.generic.AuditingGeneric;
import com.example.demo.generic.PaymentType;
import com.example.demo.patients.PatientEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "patient_dept")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "update patient_dept set deleted = 'true' where id = ?")
@Where(clause = "deleted = 'false'")
public class PatientDeptEntity extends AuditingGeneric<UUID> {
    @Id
    @GeneratedValue
    @Column(unique = true)
    private UUID id;
    @Enumerated(value = EnumType.STRING)
    private PaymentType paymentType;
    private Float sum;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private PatientEntity patient;

    @Column(name = "patient_id",insertable = false,updatable = false)
    private UUID patientId;
}
