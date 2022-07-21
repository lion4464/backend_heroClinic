package com.example.demo.history_worker_salary;

import com.example.demo.generic.AuditingGeneric;
import com.example.demo.workers.WorkersEntity;
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
@Table(name = "history_worker_salary")
@SQLDelete(sql = "update history_worker_salary set deleted = 'true' where id = ?")
@Where(clause = "deleted = 'false'")
public class HistoryWorkerSalary extends AuditingGeneric<UUID> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "amount",updatable = false,nullable = false)
    private Float amount;

    @Column(name = "review_amount",updatable = false,nullable = false)
    private Float reviewAmount;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "worker_id", referencedColumnName = "id")
    private WorkersEntity worker;

    @Column(name = "worker_id",insertable = false,updatable = false)
    private UUID workerId;

    @Column(name = "started_date",updatable = false,nullable = true)
    private Long startedDate;

    @Column(name = "end_date",updatable = false,nullable = true)
    private Long endDate;

}
