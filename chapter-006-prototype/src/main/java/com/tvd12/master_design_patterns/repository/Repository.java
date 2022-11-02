package com.tvd12.master_design_patterns.repository;

public interface Repository<T> {

    void save(T entity) throws Exception;
}
