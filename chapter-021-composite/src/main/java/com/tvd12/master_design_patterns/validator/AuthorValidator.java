package com.tvd12.master_design_patterns.validator;

import com.tvd12.ezyhttp.core.exception.HttpBadRequestException;
import com.tvd12.master_design_patterns.request.AddAuthorRequest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.tvd12.ezyfox.io.EzyStrings.isBlank;

public class AuthorValidator {

    private final List<Validator> validators;

    public AuthorValidator() {
        this.validators = Arrays.asList(
            new AuthorNameValidator(),
            new AuthorAgeValidator()
        );
    }

    public void validate(AddAuthorRequest request) {
        final Map<String, String> errors = new HashMap<>();
        for (Validator validator : validators) {
            validator.validate(request, errors);
        }
        if (!errors.isEmpty()) {
            throw new HttpBadRequestException(errors);
        }
    }

    private static class AuthorNameValidator implements Validator {

        @Override
        public void validate(AddAuthorRequest request, Map<String, String> errors) {
            if (isBlank(request.getAuthorName())) {
                errors.put("authorName", "required");
            }
        }
    }

    private static class AuthorAgeValidator implements Validator {

        @Override
        public void validate(AddAuthorRequest request, Map<String, String> errors) {
            if (request.getAge() < 0) {
                errors.put("authorName", "invalid");
            }
        }
    }

    private interface Validator {
        void validate(AddAuthorRequest request, Map<String, String> errors);
    }
}
