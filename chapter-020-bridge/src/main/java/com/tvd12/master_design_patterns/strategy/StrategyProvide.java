package com.tvd12.master_design_patterns.strategy;

import java.util.HashMap;
import java.util.Map;

public class StrategyProvide {

    private final Map<Class<?>, Object> strategyByType;

    public StrategyProvide() {
        strategyByType = new HashMap<>();
        strategyByType.put(AuthorLevelStrategy.class, new AuthorLevelStrategy());
    }

    @SuppressWarnings("unchecked")
    public <S> S getStrategy(Class<S> strategyClass) {
        return (S) strategyByType.get(strategyClass);
    }
}
