package com.tvd12.master_design_patterns.repository;

import com.tvd12.ezydata.database.EzyDatabaseRepository;
import com.tvd12.ezyfox.database.annotation.EzyRepository;
import com.tvd12.master_design_patterns.entity.Category;

@EzyRepository
public interface CategoryRepository extends EzyDatabaseRepository<Long, Category> {
}
