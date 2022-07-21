package com.example.demo.salary_type;

import com.example.demo.generic.AuditGeneric;
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
@Table(name = "salary_type")
@SQLDelete(sql = "update salary_type set deleted = 'true' where id = ?")
@Where(clause = "deleted = 'false'")
public class SalaryTypeEntity extends AuditGeneric<UUID> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", insertable = false, updatable = false, nullable = false)
    private UUID id;
    private String name;


}
