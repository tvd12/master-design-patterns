package com.tvd12.master_design_patterns.adapter;

import com.tvd12.master_design_patterns.BookApplication;
import com.tvd12.master_design_patterns.entity.Category;
import com.tvd12.master_design_patterns.service.DataService;

import java.util.HashMap;
import java.util.Map;

public class AdapterProvider {

    private final Map<String, Object> adapterByName;

    public AdapterProvider() {
        adapterByName = new HashMap<>();
        adapterByName.put(
            "categoryDataServiceToCategoryRepositoryAdapter",
            new CategoryDataServiceToCategoryRepositoryAdapter(
                BookApplication
                    .getInstance()
                    .getDatabaseContext()
                    .newRepository(Category.class)
            )
        );
    }

    @SuppressWarnings("unchecked")
    public <A extends DataService> A getAdapterByName(
        String adapterName
    ) {
        return (A) adapterByName.get(adapterName);
    }
}
