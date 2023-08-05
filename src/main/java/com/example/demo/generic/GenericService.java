package com.example.demo.generic;

import com.example.demo.exceptions.DataNotFoundException;

import java.util.List;

public interface GenericService<T extends GenericEntity<ID>, ID> {
    long count();

    List<T> findAll();

    T findById(ID id) throws DataNotFoundException;

    List<T> findByIds(List<ID> ids);

    <S extends T> List<S> saveAll(Iterable<S> entityList);

    void delete(ID id);
}

