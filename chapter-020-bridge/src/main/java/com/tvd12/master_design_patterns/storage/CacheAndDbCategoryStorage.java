package com.tvd12.master_design_patterns.storage;

import com.tvd12.master_design_patterns.entity.Category;
import com.tvd12.master_design_patterns.repository.Repository;

public class CacheAndDbCategoryStorage extends Storage<Category> {

    private final Repository<Category> fallbackRepository;

    public CacheAndDbCategoryStorage(
        Repository<Category> repository,
        Repository<Category> fallbackRepository
    ) {
        super(repository);

        this.fallbackRepository = fallbackRepository;
    }

    @Override
    public Category getEntityById(long id) throws Exception {
        Category entity = repository.findById(id);
        if (entity == null) {
            entity = fallbackRepository.findById(id);
            repository.save(entity);
        }
        return entity;
    }
}
