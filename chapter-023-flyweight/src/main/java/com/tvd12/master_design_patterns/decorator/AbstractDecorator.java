package com.tvd12.master_design_patterns.decorator;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AbstractDecorator<T, R>
    implements Decorator<T, R> {

    protected final Decorator<T, R> component;

    @Override
    public R decorate(T data) throws Exception {
        return component.decorate(data);
    }
}
