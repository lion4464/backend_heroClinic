package com.example.demo.user;


import com.example.demo.company.CompanyEntity;
import com.example.demo.generic.AuditingGeneric;
import com.example.demo.role.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "update users set deleted = 'true' where id = ?")
@Where(clause = "deleted = 'false'")
public class UserEntity extends AuditingGeneric<UUID> {

    @Id
    @GeneratedValue
    @Column(unique = true)
    private UUID id;

    @Column(unique = true)
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private String hashPassword;
    @Enumerated(EnumType.STRING)
    private DataStatusEnum status;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private List<RoleEntity> role;




}

