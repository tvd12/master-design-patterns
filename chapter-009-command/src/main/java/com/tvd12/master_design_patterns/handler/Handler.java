package com.tvd12.master_design_patterns.handler;

public interface Handler<T, R> {

    R handle(T input) throws Exception;
}
