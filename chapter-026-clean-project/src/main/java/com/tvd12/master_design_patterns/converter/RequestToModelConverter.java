package com.tvd12.master_design_patterns.converter;

import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import com.tvd12.master_design_patterns.model.AddBookModel;
import com.tvd12.master_design_patterns.request.AddBookRequest;

@EzySingleton
public class RequestToModelConverter {

    public AddBookModel toModel(AddBookRequest request) {
        return AddBookModel
            .builder()
            .authorId(request.getAuthorId())
            .categoryId(request.getCategoryId())
            .bookName(request.getBookName())
            .imageName(request.getBookImage())
            .build();
    }
}

