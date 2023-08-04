package com.example.demo.generic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface JpaGenericRepository<T extends GenericEntity<ID>, ID> extends GenericRepository<T, ID>, JpaRepository<T, ID>, JpaSpecificationExecutor<T> {
    long count();

    List<T> findAll();

    Page<T> findAll(@Nullable Pageable pageable);

    List<T> findAll(Specification<T> specification);

    Page<T> findAll(Specification<T> specification, @Nullable Pageable pageable);

    Optional<T> findById(@Nullable ID id);

    List<T> findAllById(@Nullable Iterable<ID> ids);

    <S extends T> S save(S entity);

    <S extends T> List<S> saveAll(@Nullable Iterable<S> entityList);

    void deleteById(@Nullable ID id);
}
