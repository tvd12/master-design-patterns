package com.tvd12.master_design_patterns.interpreter;

import java.util.HashMap;
import java.util.Map;

public class InterpreterProvider {

    private final Map<Class<?>, Interpreter> interpreterByType;

    public InterpreterProvider() {
        interpreterByType = new HashMap<>();
        interpreterByType.put(
            AuthorCodeInterpreter.class,
            new AuthorCodeInterpreter()
        );
    }

    public <T extends Interpreter> T getInterpreter(Class<T> interpreterClass) {
        return (T) interpreterByType.get(interpreterClass);
    }
}
