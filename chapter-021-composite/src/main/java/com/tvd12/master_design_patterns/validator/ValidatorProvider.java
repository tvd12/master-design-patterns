package com.tvd12.master_design_patterns.validator;

import java.util.HashMap;
import java.util.Map;

public class ValidatorProvider {

    private final Map<Class<?>, Object> validatorByType;

    public ValidatorProvider() {
        validatorByType = new HashMap<>();
        validatorByType.put(AuthorValidator.class, new AuthorValidator());
    }

    @SuppressWarnings("unchecked")
    public <V> V getValidator(Class<V> validatorClass) {
        return (V) validatorByType.get(validatorClass);
    }
}
