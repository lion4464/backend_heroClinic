package com.example.demo.generic;

import com.example.demo.analyses_invoice.SearchSpecification;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.UUID;

public class UUIDSpecification <T> implements Specification<T> {

    private String param;
    private UUID value;

    public UUIDSpecification(String param, UUID value) {
        this.param = param;
        this.value = value;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.and(criteriaBuilder.equal(root.get(param),value));
    }

}
