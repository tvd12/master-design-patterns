package com.tvd12.master_design_patterns.service;

import com.tvd12.ezyhttp.server.core.annotation.Service;
import com.tvd12.master_design_patterns.converter.EntityToModelConverter;
import com.tvd12.master_design_patterns.converter.ModelToEntityConverter;
import com.tvd12.master_design_patterns.entity.Author;
import com.tvd12.master_design_patterns.model.AuthorModel;
import com.tvd12.master_design_patterns.repository.AuthorRepository;
import com.tvd12.master_design_patterns.strategy.AuthorLevelStrategy;
import lombok.AllArgsConstructor;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthorService {

    private final AuthorLevelStrategy authorLevelStrategy;
    private final AuthorRepository authorRepository;
    private final ModelToEntityConverter modelToEntityConverter;
    private final EntityToModelConverter entityToModelConverter;

    public void addAuthor(String authorName) {
        authorRepository.save(
            modelToEntityConverter.toAuthorEntity(authorName)
        );
    };

    public void updateAuthorLevel(long authorId, long bookCount) {
        authorRepository.updateAuthorLevel(
            authorId,
            authorLevelStrategy.decideAuthorLevel(bookCount)
        );
    }

    public List<AuthorModel> getAuthors() {
        return authorRepository
            .findAll()
            .stream()
            .map(entityToModelConverter::toModel)
            .collect(Collectors.toList());
    }

    public Map<Long, AuthorModel> getAuthorModelMapByIds(
        Collection<Long> authorIds
    ) {
        return authorRepository.findListByIds(
                authorIds
            )
            .stream()
            .collect(
                Collectors.toMap(
                    Author::getId,
                    entityToModelConverter::toModel
                )
            );
    }
}
