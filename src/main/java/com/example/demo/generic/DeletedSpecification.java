package com.example.demo.generic;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class DeletedSpecification<T> implements Specification<T> {
    private String param;
    private boolean value;

    public DeletedSpecification(String param, boolean value) {
        this.param = param;
        this.value = value;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.and(criteriaBuilder.equal(root.get(param),value));
    }
}
