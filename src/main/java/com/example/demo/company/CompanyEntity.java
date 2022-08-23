package com.example.demo.company;

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
@Table(name = "company")
@SQLDelete(sql = "update company set deleted = 'true' where id = ?")
public class CompanyEntity extends AuditGeneric<UUID> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", updatable = false, nullable = false)
    private UUID id;
    private String name;

}
