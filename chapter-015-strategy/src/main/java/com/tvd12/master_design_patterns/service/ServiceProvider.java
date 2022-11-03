package com.tvd12.master_design_patterns.service;

import java.util.HashMap;
import java.util.Map;

public class ServiceProvider {

    private final Map<Class<?>, Object> serviceByType;

    public ServiceProvider() {
        serviceByType = new HashMap<>();
        serviceByType.put(AuthorService.class, new AuthorService());
        serviceByType.put(BookService.class, new BookService());
    }

    @SuppressWarnings("unchecked")
    public <S> S getService(Class<S> serviceClass) {
        return (S) serviceByType.get(serviceClass);
    }
}
