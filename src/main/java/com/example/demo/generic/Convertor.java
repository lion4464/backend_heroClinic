package com.example.demo.generic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.function.Function;
import java.util.stream.Collectors;

public class Convertor<T, U> {
    private final Function<T, U> fromDto;
    private final Function<U, T> fromEntity;

    public Convertor(Function<T, U> fromDto, Function<U, T> fromEntity) {
        this.fromDto = fromDto;
        this.fromEntity = fromEntity;
    }

    public final T convertFromEntity(final U entity) {
        return fromEntity.apply(entity);
    }

    public final Page<T> createFromEntities(final Page<U> entities) {
        return new PageImpl<T>(entities.stream().map(this::convertFromEntity).collect(Collectors.toList()),
                entities.getPageable(),
                entities.getTotalElements());
    }
}
