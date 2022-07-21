package com.example.demo.expense;

import com.example.demo.expense_template.ExpenseTemplateEntity;
import com.example.demo.generic.AuditingGeneric;
import com.example.demo.generic.PaymentType;
import com.example.demo.patients.PatientEntity;
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
@Table(name = "expense")
@SQLDelete(sql = "update expense set deleted = 'true' where id = ?")
@Where(clause = "deleted = 'false'")
public class ExpenseEntity extends AuditingGeneric<UUID> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", insertable = false, updatable = false, nullable = false)
    private UUID id;
    private String description;

    @Enumerated(value = EnumType.STRING)
    private PaymentType paymentType;

    private Float sum;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "expense_template_id", referencedColumnName = "id",nullable = false)
    private ExpenseTemplateEntity expenseTemplate;

    @Column(name = "expense_template_id",insertable = false,updatable = false)
    private UUID expenseTemplateId;



}
