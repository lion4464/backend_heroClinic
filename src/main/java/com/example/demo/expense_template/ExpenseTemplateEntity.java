package com.example.demo.expense_template;

import com.example.demo.generic.AuditingGeneric;
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
@Table(name = "expense_template")
@SQLDelete(sql = "update expense_template set deleted = 'true' where id = ?")
@Where(clause = "deleted = 'false'")
public class ExpenseTemplateEntity extends AuditingGeneric<UUID> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", insertable = false, updatable = false, nullable = false)
    private UUID id;
    private String name;


}
