package com.tvd12.master_design_patterns.handler;

public interface VoidHandler<T> extends Handler<T, T> {

    @Override
    default T handle(T input) throws Exception {
        voidHandle(input);
        return input;
    }

    void voidHandle(T input) throws Exception;
}
