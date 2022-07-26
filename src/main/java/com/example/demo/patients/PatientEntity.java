package com.example.demo.patients;

import com.example.demo.generic.AuditingGeneric;
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
@Table(name = "patient",uniqueConstraints = { @UniqueConstraint(name = "patient_passport_k1", columnNames = { "passport"}) })
@SQLDelete(sql = "update patient set deleted = 'true' where id = ?")
public class PatientEntity extends AuditingGeneric<UUID> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", insertable = false, updatable = false, nullable = false)
    private UUID id;
    private String fullname;
    private Long birthdate;
    private String mobile;
    private String gender;
    private String region;
    private String destrict;
    private String area;
    private String passport;



}
