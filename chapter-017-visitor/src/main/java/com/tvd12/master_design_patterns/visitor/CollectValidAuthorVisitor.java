package com.tvd12.master_design_patterns.visitor;

import com.tvd12.master_design_patterns.entity.Author;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import static com.tvd12.ezyfox.io.EzyStrings.isNotBlank;

public class CollectValidAuthorVisitor implements Visitor<Author> {

    @Getter
    private final List<Author> validAuthors = new ArrayList<>();

    @Override
    public void visit(Author obj) {
        if (isNotBlank(obj.getName()) && isNotBlank(obj.getCode())) {
            validAuthors.add(obj);
        }
    }
}
