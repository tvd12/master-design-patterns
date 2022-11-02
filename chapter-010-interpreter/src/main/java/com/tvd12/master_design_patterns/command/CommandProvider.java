package com.tvd12.master_design_patterns.command;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@SuppressWarnings({"rawtypes", "unchecked"})
public class CommandProvider {

    private final Map<Class, Supplier<Command>> commandByType;

    public CommandProvider() {
        commandByType = new HashMap<>();
        commandByType.put(SaveCommand.class, SaveCommand::new);
    }

    public <C extends Command> C provide(Class<C> commandClass) {
        return (C) commandByType
            .get(commandClass)
            .get();
    }
}
