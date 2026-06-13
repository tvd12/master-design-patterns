package com.tvd12.master_design_patterns.storage;

import com.tvd12.master_design_patterns.repository.Repository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class Storage<E> {

    protected final Repository<E> repository;

    public abstract E getEntityById(
        long id
    ) throws Exception;
}
