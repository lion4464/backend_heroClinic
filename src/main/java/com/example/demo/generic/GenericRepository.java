package com.example.demo.generic;

import java.util.List;
import java.util.Optional;

public interface GenericRepository<T extends GenericEntity<ID>, ID> {
    long count();

    List<T> findAll();

    Optional<T> findById(ID id);

    List<T> findAllById(Iterable<ID> ids);

    <S extends T> List<S> saveAll(Iterable<S> entityList);

    void deleteById(ID id);
}