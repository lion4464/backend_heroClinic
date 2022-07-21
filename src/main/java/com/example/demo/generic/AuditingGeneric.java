package com.example.demo.generic;

import com.example.demo.company.CompanyEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@MappedSuperclass
public abstract class AuditingGeneric<U> extends AuditGeneric<U> {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private CompanyEntity company;

    @Column(name = "company_id", insertable = false, updatable = false)
    private UUID companyId;
}
