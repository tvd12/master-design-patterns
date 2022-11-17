package com.tvd12.master_design_patterns.validator;

import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import com.tvd12.ezyhttp.core.exception.HttpBadRequestException;
import com.tvd12.master_design_patterns.request.AddCategoryRequest;
import com.tvd12.master_design_patterns.service.CategoryService;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;

import static com.tvd12.ezyfox.io.EzyStrings.isBlank;

@EzySingleton
@AllArgsConstructor
public class CategoryValidator {

    private final CategoryService categoryService;

    public void validate(AddCategoryRequest request) {
        final Map<String, String> errors = new HashMap<>();
        final String name = request.getCategoryName();
        if (isBlank(name)) {
            errors.put("categoryName", "required");
        } else if (categoryService.containsCategoryByName(name)) {
            errors.put("categoryName", "duplicated");
        }
        if (!errors.isEmpty()) {
            throw new HttpBadRequestException(errors);
        }
    }
}
