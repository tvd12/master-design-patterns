package com.tvd12.master_design_patterns.decorator;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class AbstractDecorator<T, R>
    implements Decorator<T, R> {

    protected final Decorator<T, R> decorator;

    @Override
    public R decorate(T data) throws Exception {
        return decorator.decorate(data);
    }
}
