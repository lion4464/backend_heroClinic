package com.example.demo.analyses_invoice;

import com.example.demo.generic.SearchCriteria;
import lombok.ToString;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
@ToString
public class SearchSpecification implements Specification<AnalysesInvoiceEntity> {
    private List<SearchCriteria> params;

    public SearchSpecification(List<SearchCriteria> params) {
        this.params = params;
    }


    @Override
    public Specification<AnalysesInvoiceEntity> and(Specification<AnalysesInvoiceEntity> other) {
        return Specification.super.and(other);
    }

    @Override
    public Specification<AnalysesInvoiceEntity> or(Specification<AnalysesInvoiceEntity> other) {
        return Specification.super.or(other);
    }

    @Override
    public Predicate toPredicate(Root<AnalysesInvoiceEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Predicate predicate =criteriaBuilder.conjunction();
        if (params!=null){
            for (SearchCriteria param:params){
                Predicate predicateParam =getPredicate(param,criteriaBuilder,root);
                if (predicate!=null){
                    predicate = criteriaBuilder.and(predicate,predicateParam);
                }
            }
        }
        return predicate;

    }

    private Predicate getPredicate(SearchCriteria param, CriteriaBuilder criteriaBuilder, Root<AnalysesInvoiceEntity> root) {
        switch (param.getOperation()){
            case ">":
                return criteriaBuilder.greaterThan(root.get(param.getKey()), param.getValue().toString());
            case ">=":
                return criteriaBuilder.greaterThanOrEqualTo(root.get(param.getKey()),param.getValue().toString());
            case  "<":
                return criteriaBuilder.lessThan(root.get(param.getKey()),param.getValue().toString());
            case "<=":
                return criteriaBuilder.lessThanOrEqualTo(root.get(param.getKey()),param.getValue().toString());
            case "=":
                return criteriaBuilder.equal(root.get(param.getKey()),param.getValue().toString());
            case "!=":
                return criteriaBuilder.notEqual(root.get(param.getKey()),param.getValue().toString());
            case ":":
                return criteriaBuilder.like(criteriaBuilder.upper(root.get(param.getKey())), "%" + param.getValue().toString().toUpperCase() + "%");
            default:
                return null;
        }
    }
}
