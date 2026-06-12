package com.tvd12.master_design_patterns.command;

import com.tvd12.ezyfox.exception.NotFoundException;

public class NullFindCommand<T> implements Command<T> {

    private final long entityId;

    public NullFindCommand(
        long entityId
    ) {
        this.entityId = entityId;
    }

    @Override
    public T execute() {
        throw new NotFoundException(
            "there is no entity with id: " + entityId
        );
    }
}
