package com.tvd12.master_design_patterns.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public interface Repository<E> {

    void save(E entity) throws Exception;

    void forEach(Consumer<E> consumer) throws Exception;

    default List<E> findAll() throws Exception {
        final List<E> entities = new ArrayList<>();
        forEach(entities::add);
        return entities;
    }
}
