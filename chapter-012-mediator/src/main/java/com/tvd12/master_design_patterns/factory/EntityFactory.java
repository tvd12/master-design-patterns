package com.tvd12.master_design_patterns.factory;

import com.tvd12.master_design_patterns.builder.Builder;

public interface EntityFactory {

    <E> E newEntity(Class<E> entityType, String name);

    <E, B extends Builder<E>> B newEntityBuilder(Class<B> builderType);
}
