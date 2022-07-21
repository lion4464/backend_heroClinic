package com.example.demo.worker_expense;

import com.example.demo.expense_template.ExpenseTemplateEntity;
import com.example.demo.generic.AuditingGeneric;
import com.example.demo.generic.PaymentType;
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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "worker_expense")
@SQLDelete(sql = "update worker_expense set deleted = 'true' where id = ?")
@Where(clause = "deleted = 'false'")
public class WorkerExpenseEntity extends AuditingGeneric<UUID> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", insertable = false, updatable = false, nullable = false)
    private UUID id;
    private String description;

    @Enumerated(value = EnumType.STRING)
    private PaymentType paymentType;

    private Float sum;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "expense_template_id", referencedColumnName = "id",nullable = false)
    private ExpenseTemplateEntity expenseTemplate;

    @Column(name = "expense_template_id",insertable = false,updatable = false)
    private UUID expenseTemplateId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "worker_id", referencedColumnName = "id",nullable = false)
    private WorkersEntity worker;

    @Column(name = "worker_id",insertable = false,updatable = false)
    private UUID wokerId;



}
