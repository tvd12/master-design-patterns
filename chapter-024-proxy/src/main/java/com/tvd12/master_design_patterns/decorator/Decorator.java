package com.tvd12.master_design_patterns.decorator;

public interface Decorator<T, R> {

    R decorate(T data) throws Exception;
}
