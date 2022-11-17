package com.tvd12.master_design_patterns.validator;

import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import com.tvd12.ezyhttp.core.exception.HttpBadRequestException;
import com.tvd12.master_design_patterns.request.AddBookRequest;

import java.util.HashMap;
import java.util.Map;

import static com.tvd12.ezyfox.io.EzyStrings.isBlank;

@EzySingleton
public class BookValidator {

    public void validate(AddBookRequest request) {
        final Map<String, String> errors = new HashMap<>();
        if (isBlank(request.getBookName())) {
            errors.put("bookName", "required");
        }
        if (request.getAuthorId() <= 0) {
            errors.put("authorId", "required");
        }
        if (request.getCategoryId() <= 0) {
            errors.put("categoryId", "required");
        }
        if (isBlank(request.getBookImage())) {
            errors.put("bookImage", "required");
        }
        if (!errors.isEmpty()) {
            throw new HttpBadRequestException(errors);
        }
    }
}
