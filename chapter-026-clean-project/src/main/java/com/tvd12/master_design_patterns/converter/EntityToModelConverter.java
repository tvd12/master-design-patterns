package com.tvd12.master_design_patterns.converter;

import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import com.tvd12.master_design_patterns.entity.Author;
import com.tvd12.master_design_patterns.entity.Book;
import com.tvd12.master_design_patterns.entity.Category;
import com.tvd12.master_design_patterns.model.AuthorModel;
import com.tvd12.master_design_patterns.model.BookModel;
import com.tvd12.master_design_patterns.model.CategoryModel;

@EzySingleton
public class EntityToModelConverter {

    public AuthorModel toModel(Author entity) {
        if (entity == null) {
            return null;
        }
        return AuthorModel
            .builder()
            .id(entity.getId())
            .name(entity.getName())
            .code(entity.getCode())
            .level(entity.getLevel())
            .build();
    }

    public CategoryModel toModel(Category entity) {
        if (entity == null) {
            return null;
        }
        return CategoryModel
            .builder()
            .id(entity.getId())
            .name(entity.getName())
            .build();
    }

    public BookModel toModel(
        Book book,
        AuthorModel author,
        CategoryModel category
    ) {
        if (book == null) {
            return null;
        }
        return BookModel
            .builder()
            .id(book.getId())
            .name(book.getName())
            .author(author)
            .category(category)
            .imageName(book.getImageName())
            .build();
    }
}
