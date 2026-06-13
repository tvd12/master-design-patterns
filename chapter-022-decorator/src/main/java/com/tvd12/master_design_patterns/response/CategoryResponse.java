package com.tvd12.master_design_patterns.response;

import com.tvd12.master_design_patterns.model.BookWithoutAuthorModel;
import lombok.Getter;

import java.util.List;

@Getter
public class CategoryResponse {
    private final long id;
    private final String name;
    private final List<BookWithoutAuthorModel> books;

    protected CategoryResponse(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.books = builder.books;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Getter
    public static class Builder {
        private long id;
        private String name;
        private List<BookWithoutAuthorModel> books;

        public Builder copy(CategoryResponse data) {
            this.id = data.id;
            this.name = data.name;
            this.books = data.books;
            return this;
        }

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder books(
            List<BookWithoutAuthorModel> books
        ) {
            this.books = books;
            return this;
        }

        public CategoryResponse build() {
            return new CategoryResponse(this);
        }
    }
}
