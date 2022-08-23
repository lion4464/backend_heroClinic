package com.example.demo.workers;

import com.example.demo.department.DepartmentEntity;
import com.example.demo.generic.AuditingGeneric;
import com.example.demo.generic.DataStatusEnum;
import com.example.demo.salary_type.SalaryTypeEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "worker")
@SQLDelete(sql = "update worker set deleted = 'true' where id = ?")
public class WorkersEntity extends AuditingGeneric<UUID> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", insertable = false, updatable = false, nullable = false)
    private UUID id;
    private String fullname;
    private String mobile;

    private String region;
    private String destrict;
    private String area;
    private String gender;
    private long birthdate;
    private long startedDate;
    private long expiredDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private DepartmentEntity department;

    @Column(name = "department_id", insertable = false, updatable = false)
    private UUID departmentId;

    @OneToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "salary_type_id", referencedColumnName = "id")
    private SalaryTypeEntity salaryType;

    @Column(name = "salary_type_id", insertable = false, updatable = false)
    private UUID salaryTypeId;

    private String position;

    private Float reviewAmount;
    private Float salaryAmount;

    @Column(unique = true)
    private String keyId;

    private Integer room;

    @Enumerated(EnumType.STRING)
    private DataStatusEnum status = DataStatusEnum.ACTIVE;
}
