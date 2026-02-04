package com.mbd.mercado.sk;

import static java.util.Collections.unmodifiableList;

import java.util.List;
import java.util.UUID;

public abstract class AbstractRepository<T extends DatabaseEntity> {

    public abstract List<T> getRepository();

    public void save(T entity) {
        this.getRepository().removeIf(item -> item.equals(entity));
        this.getRepository().add(entity);
    }

    public boolean delete(T entity) {
        return this.getRepository().removeIf(item -> item.equals(entity));
    }

    public boolean findById(UUID id) {
        return this.getRepository().stream().anyMatch(item -> item.getId().equals(id));
    }

    public boolean deleteById(UUID id) {
        return this.getRepository().removeIf(item -> item.getId().equals(id));
    }

    public List<T> findAll() {
        return unmodifiableList(this.getRepository());
    }

}
