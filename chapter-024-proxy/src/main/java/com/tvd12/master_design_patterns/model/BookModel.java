package com.tvd12.master_design_patterns.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class BookModel implements Cloneable {
    private long id;
    private String name;
    private AuthorModel author;

    @Override
    public BookModel clone() {
        final BookModel clone = new BookModel();
        clone.id = id;
        clone.name = name;
        clone.author = author;
        return clone;
    }
}
