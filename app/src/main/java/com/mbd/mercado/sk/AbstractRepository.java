package com.mbd.mercado.sk;

import static java.util.Collections.unmodifiableList;

import java.util.List;
import java.util.UUID;

public abstract class AbstractRepository<T extends DatabaseEntity> {

    public abstract List<T> getRepository();

    public T save(T entity) {
        getRepository().removeIf(item -> item.equals(entity));
        getRepository().add(entity);
        return entity;
    }

    public boolean delete(T entity) {
        return getRepository().removeIf(item -> item.equals(entity));
    }

    public boolean findById(UUID id) {
        return getRepository().stream().anyMatch(item -> item.getId().equals(id));
    }

    public boolean deleteById(UUID id) {
        return getRepository().removeIf(item -> item.getId().equals(id));
    }

    public List<T> findAll() {
        return unmodifiableList(getRepository());
    }

}
