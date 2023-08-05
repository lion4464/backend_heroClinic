package com.example.demo.generic;

import com.example.demo.generic.SearchCriteria;
import lombok.ToString;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.*;

@ToString
public class SearchSpecification<T> implements Specification<T> {
    private static final String joinSeparate = "->";
    private final List<SearchCriteria> params;


    public SearchSpecification(List<SearchCriteria> params) {
        this.params = params == null ? new ArrayList<>() : params;
    }

    @Override
    public Predicate toPredicate(@NotNull Root<T> root, @NotNull CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Predicate predicate = criteriaBuilder.conjunction();
        if (params != null) {
            for (SearchCriteria param : params) {
                if(!param.getKey().contains(joinSeparate)) {
                    Predicate predicateParam = getPredicate(param, criteriaBuilder, root);
                    if (predicateParam != null)
                        predicate = criteriaBuilder.and(predicate, predicateParam);
                }
            }
        }
        Predicate joinedPredicate = getJoinedPredicate(predicate,root,criteriaBuilder);
        if(joinedPredicate != null)
            predicate = criteriaBuilder.and(predicate,joinedPredicate);
        return predicate;
    }

    private Predicate getJoinedPredicate(Predicate predicate, Root<T> root, CriteriaBuilder criteriaBuilder) {
        HashMap<String,List<SearchCriteria>> joinedParamsMap = getJoinedParams();
        if(joinedParamsMap.size() > 0) {
            for(Map.Entry<String,List<SearchCriteria>> joinedParamMap: joinedParamsMap.entrySet()) {
                List<SearchCriteria> joinedParams = joinedParamMap.getValue();
                Join join = root.join(joinedParamMap.getKey(),JoinType.LEFT);
                for(SearchCriteria joinedParam: joinedParams) {
                    Predicate predicateParam = getPredicate(joinedParam,criteriaBuilder,join);
                    if (predicateParam != null)
                        predicate = criteriaBuilder.and(predicate, predicateParam);
                }
            }
        }
        return predicate;
    }

    private HashMap<String,List<SearchCriteria>> getJoinedParams() {
        HashMap<String,List<SearchCriteria>> result = new HashMap<>();
        for(SearchCriteria param: params) {
            if(param.getKey().contains(joinSeparate)) {
                String[] parts = param.getKey().split(joinSeparate);
                if(!result.containsKey(parts[0])) {
                    result.put(parts[0],new ArrayList<>());
                }
                result.get(parts[0]).add(new SearchCriteria(parts[1],param.getOperation(),param.getValue()));
            }
        }
        return result;
    }

    private Predicate getPredicate(SearchCriteria param, CriteriaBuilder builder, From<T,T> root) {
        switch (param.getOperation()) {
            case ">":
                return builder.greaterThan(root.get(param.getKey()), param.getValue().toString());
            case ">=":
                return builder.greaterThanOrEqualTo(root.get(param.getKey()), param.getValue().toString());
            case "<":
                return builder.lessThan(root.get(param.getKey()), param.getValue().toString());
            case "<=":
                return builder.lessThanOrEqualTo(root.get(param.getKey()), param.getValue().toString());
            case "=":
                return getPredicateOnEqual(param, builder, root);
            case "!=":
                return getPredicateOnNotEqual(param, builder, root);
            default:
                return null;
        }
    }

    private Predicate getPredicateOnEqual(SearchCriteria param, CriteriaBuilder builder, From<T,T> root) {
        String[] keys = param.getKey().split("\\.");
        Class<?> paramType = root.get(keys[0]).getJavaType();
        if(param.getValue() instanceof Boolean)
            return builder.equal(root.get(keys[0]), param.getValue());
        String className = param.getValue() == null ? "" : param.getValue().getClass().getSimpleName();
        if(keys.length == 2) {
            return builder.equal(builder.function(
                    "jsonb_extract_path_text",
                    String.class,
                    root.get(keys[0]), builder.literal(keys[1])), param.getValue());
        }
        else if (paramType.equals(LocalDate.class)) {
            LocalDate dateValue = LocalDate.parse((String) param.getValue()); // Convert the string to LocalDate
            return builder.equal(root.get(keys[0]), dateValue);
        }
        else if(param.getValue() == null)
            return builder.isNull(root.get(keys[0]));
        else if (paramType.equals(String.class))
            return builder.like(builder.lower(root.get(keys[0])), "%" + ((String) param.getValue()).toLowerCase() + "%");
        else if (paramType.equals(UUID.class))
            return builder.equal(root.get(keys[0]), UUID.fromString((String) param.getValue()));
        else if (Enum.class.isAssignableFrom((Class<?>) paramType))
            return builder.equal(root.get(keys[0]), Enum.valueOf((Class<Enum>) paramType, ((String) param.getValue())));
        else
            return builder.equal(root.get(keys[0]), param.getValue());
    }

    private Predicate getPredicateOnNotEqual(SearchCriteria param, CriteriaBuilder builder, From<T,T> root) {
        String[] keys = param.getKey().split("\\.");
        Class<?> paramType = root.get(keys[0]).getJavaType();
        if(param.getValue() instanceof Boolean)
            return builder.notEqual(root.get(keys[0]), param.getValue());
        String className = param.getValue() == null ? "" :param.getValue().getClass().getSimpleName();
        String value = param.getValue() == null ? null : (className.equals("Integer") ? String.valueOf(param.getValue()) : (String) param.getValue());
        if(keys.length == 2) {
            return builder.notEqual(builder.function(
                    "jsonb_extract_path_text",
                    String.class,
                    root.get(keys[0]), builder.literal(keys[1])), value);
        } else if(value == null)
            return builder.isNotNull(root.get(keys[0]));
        else if (paramType.equals(String.class))
            return builder.notLike(builder.lower(root.get(keys[0])), "%" + value.toLowerCase() + "%");
        else if (paramType.equals(UUID.class))
            return builder.notEqual(root.get(keys[0]), UUID.fromString(value));
        else if (Enum.class.isAssignableFrom(paramType))
            return builder.notEqual(root.get(keys[0]), Enum.valueOf((Class<Enum>) paramType, value));
        else
            return builder.notEqual(root.get(keys[0]), param.getValue());
    }

}
