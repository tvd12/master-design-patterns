package com.tvd12.master_design_patterns.interpreter;

public interface Interpreter<T> {

    String translate(T input);
}
