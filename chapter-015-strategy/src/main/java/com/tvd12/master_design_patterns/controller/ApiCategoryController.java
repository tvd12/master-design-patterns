package com.tvd12.master_design_patterns.controller;

import com.tvd12.ezyhttp.core.exception.HttpBadRequestException;
import com.tvd12.ezyhttp.core.response.ResponseEntity;
import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoPost;
import com.tvd12.ezyhttp.server.core.annotation.RequestBody;
import com.tvd12.master_design_patterns.BookApplication;
import com.tvd12.master_design_patterns.builder.CategoryBuilder;
import com.tvd12.master_design_patterns.entity.Category;
import com.tvd12.master_design_patterns.factory.EntityFactory;
import com.tvd12.master_design_patterns.handler.ChainOfResponsibility;
import com.tvd12.master_design_patterns.repository.CategoryRepository;
import com.tvd12.master_design_patterns.request.AddCategoryRequest;
import com.tvd12.master_design_patterns.response.AddCategoryResponse;

import java.util.HashMap;
import java.util.Map;

import static com.tvd12.ezyfox.io.EzyStrings.isBlank;

@Controller("/api/v1")
public class ApiCategoryController {

    private final BookApplication bookApplication =
        BookApplication.getInstance();

    private final EntityFactory entityFactory =
        bookApplication.getEntityFactory();

    private final CategoryRepository categoryRepository =
        bookApplication
            .getDatabaseContext()
            .newRepository(Category.class);

    @DoPost("/categories/add")
    public ResponseEntity addCategory(
        @RequestBody AddCategoryRequest request
    ) {
        return new ChainOfResponsibility()
            .addFirstVoidHandler(() -> {
                final Map<String, String> errors = new HashMap<>();
                if (isBlank(request.getCategoryName())) {
                    errors.put("categoryName", "required");
                }
                if (!errors.isEmpty()) {
                    throw new HttpBadRequestException(errors);
                }
            })
            .addFirstHandle(() -> {
                final Category category = entityFactory
                    .newEntityBuilder(CategoryBuilder.class)
                    .name(request.getCategoryName())
                    .build();
                categoryRepository.save(category);
                return new AddCategoryResponse(category.getId());
            })
            .handle();
    }
}
