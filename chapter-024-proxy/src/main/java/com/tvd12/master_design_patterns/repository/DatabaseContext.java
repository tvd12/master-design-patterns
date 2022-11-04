package com.tvd12.master_design_patterns.repository;

public interface DatabaseContext {

    <E, R extends Repository<E>> R getRepository(Class<E> entityType);
}
