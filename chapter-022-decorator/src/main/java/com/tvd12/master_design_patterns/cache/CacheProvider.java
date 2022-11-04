package com.tvd12.master_design_patterns.cache;

import java.util.HashMap;
import java.util.Map;

public class CacheProvider {

    private final Map<Class<?>, Object> cacheByType;

    public CacheProvider() {
        cacheByType = new HashMap<>();
        cacheByType.put(CategoryCache.class, new CategoryCache());
    }

    @SuppressWarnings("unchecked")
    public <C> C getCache(Class<C> cacheClass) {
        return (C) cacheByType.get(cacheClass);
    }
}
