package com.tvd12.master_design_patterns.storage;

import com.tvd12.master_design_patterns.BookApplication;
import com.tvd12.master_design_patterns.cache.CategoryCache;
import com.tvd12.master_design_patterns.entity.Category;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

public class StorageProvider {

    private final Map<String, Object> storageByName;
    private final Map<String, Supplier<Object>> storageSupplerByName;

    public StorageProvider() {
        storageByName = new ConcurrentHashMap<>();
        storageSupplerByName = new ConcurrentHashMap<>();
        storageSupplerByName.put(
            "dbCategoryStorage",
            () -> new DbCategoryStorage(
                BookApplication
                    .getInstance()
                    .getDatabaseContext()
                    .newRepository(Category.class)
            )
        );
        storageSupplerByName.put(
            "cacheAndDbCategoryStorage",
            () -> new CacheAndDbCategoryStorage(
                BookApplication
                    .getInstance()
                    .getCacheProvider()
                    .getCache(CategoryCache.class),
                BookApplication
                    .getInstance()
                    .getDatabaseContext()
                    .newRepository(Category.class)
            )
        );
    }

    @SuppressWarnings("unchecked")
    public <S> S getStorage(String storageName) {
        return (S) storageByName.computeIfAbsent(
            storageName,
            storageSupplerByName::get
        );
    }
}
