package com.example.demo.generic;

import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.exceptions.InternalServerErrorException;
import com.example.demo.exceptions.InvalidDataException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public interface JpaGenericService<T extends GenericEntity<ID>, ID> extends GenericService<T, ID> {

    <S extends T> T save(S entity);

    <S extends T> T update(S entity) throws DataNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException;

    Page<T> findAll(Pageable pageable);

    List<T> findAll(Specification<T> specification);

    Page<T> findAll(Specification<T> specification, Pageable pageable);
    void checkDataIds(Set<ID> dataIds, List<ID> ids, String message) throws InvalidDataException;

    T updatePartial(Class<T> clazz,T model, HashMap<String,Object> data) throws InternalServerErrorException;
}

