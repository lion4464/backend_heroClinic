package com.example.demo.generic;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditGeneric<U> {

    @CreatedBy
    @Column(name = "created_by", length = 50, updatable = false)
    protected UUID createdBy;

    @CreatedDate
    private Long createdDate;

    @LastModifiedDate
    private Long modifiedDate;

    @Column(name = "deleted", columnDefinition = " boolean DEFAULT false", updatable = false)
    private boolean deleted = false;

}
