package com.tvd12.master_design_patterns.service;

import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import com.tvd12.master_design_patterns.converter.EntityToModelConverter;
import com.tvd12.master_design_patterns.converter.ModelToEntityConverter;
import com.tvd12.master_design_patterns.entity.Category;
import com.tvd12.master_design_patterns.model.CategoryModel;
import com.tvd12.master_design_patterns.repository.CategoryRepository;
import lombok.AllArgsConstructor;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@EzySingleton
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelToEntityConverter modelToEntityConverter;
    private final EntityToModelConverter entityToModelConverter;

    public void addCategory(String categoryName) {
        categoryRepository.save(
            modelToEntityConverter.toCategoryEntity(categoryName)
        );
    }

    public CategoryModel getCategoryById(long id) {
        return entityToModelConverter.toModel(
            categoryRepository.findById(id)
        );
    }

    public List<CategoryModel> getCategories() {
        return categoryRepository
            .findAll()
            .stream()
            .map(entityToModelConverter::toModel)
            .collect(Collectors.toList());
    }

    public Map<Long, CategoryModel> getCategoryMapByIds(
        Collection<Long> categoryIds
    ) {
        return categoryRepository
            .findListByIds(categoryIds)
            .stream()
            .collect(
                Collectors.toMap(
                    Category::getId,
                    entityToModelConverter::toModel
                )
            );
    }

    public boolean containsCategoryByName(String categoryName) {
        return categoryRepository.containsByField(
            "name",
            categoryName
        );
    }
}
