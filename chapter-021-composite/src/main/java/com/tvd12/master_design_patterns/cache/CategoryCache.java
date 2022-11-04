package com.tvd12.master_design_patterns.cache;

import com.tvd12.master_design_patterns.entity.Category;
import com.tvd12.master_design_patterns.repository.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CategoryCache implements Repository<Category> {

    private final Map<Long, Category> authorById =
        new ConcurrentHashMap<>();

    @Override
    public Category findById(long entityId) {
        return authorById.get(entityId);
    }

    @Override
    public void save(Category entity) {
        authorById.put(entity.getId(), entity);
    }
}
