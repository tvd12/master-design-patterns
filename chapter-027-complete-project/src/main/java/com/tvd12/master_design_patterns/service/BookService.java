package com.tvd12.master_design_patterns.service;

import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import com.tvd12.master_design_patterns.converter.EntityToModelConverter;
import com.tvd12.master_design_patterns.converter.ModelToEntityConverter;
import com.tvd12.master_design_patterns.entity.Book;
import com.tvd12.master_design_patterns.model.AddBookModel;
import com.tvd12.master_design_patterns.model.AuthorModel;
import com.tvd12.master_design_patterns.model.BookModel;
import com.tvd12.master_design_patterns.model.CategoryModel;
import com.tvd12.master_design_patterns.repository.BookRepository;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@EzySingleton
@AllArgsConstructor
public class BookService {

    private final AuthorService authorService;
    private final CategoryService categoryService;
    private final BookRepository bookRepository;
    private final ModelToEntityConverter modelToEntityConverter;
    private final EntityToModelConverter entityToModelConverter;

    public void addBook(AddBookModel model) {
        bookRepository.save(
            modelToEntityConverter.toEntity(model)
        );
    }

    public List<BookModel> getBooks() {
        final List<Book> entities = bookRepository.findAll();
        final Set<Long> authorIds = entities
            .stream()
            .map(Book::getAuthorId)
            .collect(Collectors.toSet());
        final Set<Long> categoryIds = entities
            .stream()
            .map(Book::getCategoryId)
            .collect(Collectors.toSet());
        final Map<Long, AuthorModel> authorById = authorService
            .getAuthorModelMapByIds(authorIds);
        final Map<Long, CategoryModel> categoryById = categoryService
            .getCategoryMapByIds(categoryIds);
        return entities
            .stream()
            .map(it ->
                entityToModelConverter.toModel(
                    it,
                    authorById.get(it.getAuthorId()),
                    categoryById.get(it.getCategoryId())
                )
            )
            .collect(Collectors.toList());
    }

    public long countBooksByAuthor(long authorId) {
        return bookRepository.countByAuthorId(authorId);
    }
}
