package com.tvd12.master_design_patterns.factory;

import com.tvd12.master_design_patterns.entity.Category;

public interface CategoryFactory {

    Category newCategory(String name);
}
