package com.tvd12.master_design_patterns.converter;

import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import com.tvd12.master_design_patterns.constant.AuthorLevels;
import com.tvd12.master_design_patterns.entity.Author;
import com.tvd12.master_design_patterns.entity.Book;
import com.tvd12.master_design_patterns.entity.Category;
import com.tvd12.master_design_patterns.interpreter.AuthorCodeInterpreter;
import com.tvd12.master_design_patterns.model.AddBookModel;
import lombok.AllArgsConstructor;

@EzySingleton
@AllArgsConstructor
public class ModelToEntityConverter {

    private final AuthorCodeInterpreter authorCodeInterpreter;

    public Book toEntity(AddBookModel model) {
        final Book entity = new Book();
        entity.setName(model.getBookName());
        entity.setAuthorId(model.getAuthorId());
        entity.setCategoryId(model.getCategoryId());
        entity.setImageName(model.getImageName());
        return entity;
    }

    public Author toAuthorEntity(String authorName) {
        final String code = authorCodeInterpreter
            .translate(authorName);
        final Author author = new Author();
        author.setName(authorName);
        author.setCode(code);
        author.setLevel(AuthorLevels.NEW_ONE);
        return author;
    }

    public Category toCategoryEntity(String categoryName) {
        final Category entity = new Category();
        entity.setName(categoryName);
        return entity;
    }
}
