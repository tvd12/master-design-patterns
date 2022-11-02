package com.tvd12.master_design_patterns.factory;

public interface EntityFactory {

    <E> E newEntity(Class<E> entityType, String name);
}
