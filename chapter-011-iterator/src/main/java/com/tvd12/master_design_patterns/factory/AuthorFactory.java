package com.tvd12.master_design_patterns.factory;

import com.tvd12.master_design_patterns.builder.AuthorBuilder;
import com.tvd12.master_design_patterns.entity.Author;

public interface AuthorFactory {

    Author newAuthor(String name);

    AuthorBuilder newAuthorBuilder();
}
