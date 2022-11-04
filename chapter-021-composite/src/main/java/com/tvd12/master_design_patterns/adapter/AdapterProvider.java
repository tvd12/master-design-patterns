package com.tvd12.master_design_patterns.adapter;

import java.util.HashMap;
import java.util.Map;

public class AdapterProvider {

    private final Map<Class<?>, Object> adapterByType;

    public AdapterProvider() {
        adapterByType = new HashMap<>();
        adapterByType.put(
            CategoryDataServiceToCategoryRepositoryAdapter.class,
            new CategoryDataServiceToCategoryRepositoryAdapter()
        );
    }

    @SuppressWarnings("unchecked")
    public <A> A getAdapter(Class<A> adapterClass) {
        return (A) adapterByType.get(adapterClass);
    }
}
