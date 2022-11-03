package com.tvd12.master_design_patterns.visitor;

import com.tvd12.master_design_patterns.entity.Author;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import static com.tvd12.ezyfox.io.EzyStrings.isBlank;

public class CollectNoCodeAuthorVisitor implements Visitor<Author> {

    @Getter
    private final List<Author> noCodeAuthors = new ArrayList<>();

    @Override
    public void visit(Author obj) {
        if (isBlank(obj.getCode())) {
            noCodeAuthors.add(obj);
        }
    }
}
