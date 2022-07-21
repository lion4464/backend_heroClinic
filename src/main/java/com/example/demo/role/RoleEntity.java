package com.example.demo.role;

import com.example.demo.generic.AuditingGeneric;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "role")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "update role set deleted = 'true' where id = ?")
@Where(clause = "deleted = 'false'")
public class RoleEntity extends AuditingGeneric<UUID> {
    @Id
    @GeneratedValue
    @Column(unique = true)
    private UUID id;

    private String name;

}
