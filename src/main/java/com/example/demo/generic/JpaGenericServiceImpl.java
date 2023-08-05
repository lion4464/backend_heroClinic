package com.example.demo.generic;

import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.exceptions.InternalServerErrorException;
import com.example.demo.exceptions.InvalidDataException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public abstract class JpaGenericServiceImpl<T extends GenericEntity<ID>, ID>
        extends GenericServiceImpl<T, ID>
        implements JpaGenericService<T, ID> {
    private static final Logger logger = LogManager.getLogger();

    protected abstract JpaGenericRepository<T, ID> getRepository();

    @Override
    public <S extends T> T save(S entity) {
        S s = getRepository().saveAndFlush(entity);
        logger.debug(s);
        return s;
    }

    @Override
    public <S extends T> T update(S entity) throws DataNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        T oldEntity = findById(entity.getId());
        if (oldEntity.isDeleted()) {
            throw new DataNotFoundException();
        }
        return save(oldEntity.mergeObjects(entity));
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        Page<T> all = getRepository().findAll(pageable);
        if (all != null)
            logger.debug(all);
        return all;
    }

    @Override
    public List<T> findAll(Specification<T> specification) {
        List<T> all = getRepository().findAll(specification);
        if (all != null)
            logger.debug(all);
        return all;
    }

    @Override
    public Page<T> findAll(Specification<T> specification, Pageable pageable) {
        Page<T> all = getRepository().findAll(specification, pageable);
        if (all != null)
            logger.debug(all);
        return all;
    }

    @Override
    public void checkDataIds(Set<ID> dataIds, List<ID> ids, String message) throws InvalidDataException {
        List<ID> notFoundIds = new ArrayList<>();
        for(ID id: ids) {
            if(!dataIds.contains(id)) {
                notFoundIds.add(id);
            }
        }
        if(notFoundIds.size() > 0) {
            throw new InvalidDataException(message, notFoundIds);
        }
    }

    @Override
    public T updatePartial(Class<T> clazz, T model, HashMap<String,Object> data) throws InternalServerErrorException, InternalServerErrorException {
        Field[] postFields = clazz.getDeclaredFields();
        AtomicReference<String> exceptionText = new AtomicReference<>("");
        for (Field postField : postFields) {
            data.forEach((key, value) -> {
                if (key.equalsIgnoreCase(postField.getName())) {
                    try {
                        final Field declaredField = clazz.getDeclaredField(key);
                        Class<?> fieldClass = declaredField.getType();
                        declaredField.setAccessible(true);
                        if(Enum.class.isAssignableFrom(fieldClass)) {
                            declaredField.set(model,Enum.valueOf((Class<Enum>) fieldClass, (String) value));
                        } else {
                            declaredField.set(model, value);
                        }
                    } catch (Exception e) {
                        logger.error("Unable to do partial update field: " + key + " :: ", e);
                        exceptionText.set(e.getMessage());
                    }
                }
            });
        }
        if(!exceptionText.get().isEmpty()) {
            throw new InternalServerErrorException("unable_to_do_partial_update_field",exceptionText.get());
        }
        return save(model);
    }
}