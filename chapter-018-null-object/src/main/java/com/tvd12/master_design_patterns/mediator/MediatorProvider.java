package com.tvd12.master_design_patterns.mediator;

import java.util.HashMap;
import java.util.Map;

public class MediatorProvider {

    private final Map<Class<?>, Object> mediatorByType;

    public MediatorProvider() {
        mediatorByType = new HashMap<>();
        mediatorByType.put(BookAndAuthorMediator.class, new BookAndAuthorMediator());
    }

    @SuppressWarnings("unchecked")
    public <M> M getMediator(Class<M> mediatorClass) {
        return (M) mediatorByType.get(mediatorClass);
    }
}
