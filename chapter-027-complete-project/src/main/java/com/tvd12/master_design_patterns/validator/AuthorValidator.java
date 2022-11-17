package com.tvd12.master_design_patterns.validator;

import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import com.tvd12.ezyhttp.core.exception.HttpBadRequestException;
import com.tvd12.master_design_patterns.request.AddAuthorRequest;

import java.util.HashMap;
import java.util.Map;

import static com.tvd12.ezyfox.io.EzyStrings.isBlank;

@EzySingleton
public class AuthorValidator {

    public void validate(AddAuthorRequest request) {
        final Map<String, String> errors = new HashMap<>();
        if (isBlank(request.getAuthorName())) {
            errors.put("authorName", "required");
        }
        if (!errors.isEmpty()) {
            throw new HttpBadRequestException(errors);
        }
    }
}
