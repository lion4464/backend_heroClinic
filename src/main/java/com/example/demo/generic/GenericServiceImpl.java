package com.example.demo.generic;

import com.example.demo.exceptions.DataNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public abstract class GenericServiceImpl<T extends GenericEntity<ID>, ID> implements GenericService<T, ID> {

    protected abstract GenericRepository<T, ID> getRepository();

    private static final Logger logger = LogManager.getLogger();

    @Override
    public long count() {
        return getRepository().count();
    }

    @Override
    public List<T> findAll() {
        return getRepository().findAll();
    }

    @Override
    public T findById(ID id) throws DataNotFoundException {
        Optional<T> t = getRepository().findById(id);
        if(t.isEmpty()) {
            String className = this.getClass().getSimpleName().replaceAll("ServiceImpl","");
            throw new DataNotFoundException("data_not_found:" + className);
        }
        logger.debug(t);
        return t.get();
    }

    @Override
    public List<T> findByIds(List<ID> ids) {
        List<T> allById = getRepository().findAllById(ids);
        logger.debug(allById);
        return allById;
    }

    @Override
    public <S extends T> List<S> saveAll(Iterable<S> entityList) {
        List<S> saveAll = getRepository().saveAll(entityList);
        logger.debug(saveAll);
        return saveAll;
    }

    @Override
    public void delete(ID id) {
        logger.debug("deleted by id = " + id);
        getRepository().deleteById(id);
    }
}
