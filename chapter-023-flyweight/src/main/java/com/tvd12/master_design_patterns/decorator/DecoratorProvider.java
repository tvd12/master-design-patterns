package com.tvd12.master_design_patterns.decorator;

import java.util.HashMap;
import java.util.Map;

public class DecoratorProvider {

    private final Map<Class<?>, Object> decoratorByType;

    public DecoratorProvider() {
        decoratorByType = new HashMap<>();
        decoratorByType.put(CategoryDecorator.class, new CategoryDecorator());
    }

    @SuppressWarnings("unchecked")
    public <D> D getDecorator(Class<D> decoratorClass) {
        return (D) decoratorByType.get(decoratorClass);
    }
}
