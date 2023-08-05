package com.example.demo.generic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

public interface GenericAuditMapper<E, D>  {
    D toDto(E entity);
    List<D> toDtoList(List<E> entityList);


    default Page<D> toDtoPage(Page<E> entityPage) {
        List<D> dtos = toDtoList(entityPage.getContent());
        return new PageImpl<>(dtos, entityPage.getPageable(), entityPage.getTotalElements());
    }
}
