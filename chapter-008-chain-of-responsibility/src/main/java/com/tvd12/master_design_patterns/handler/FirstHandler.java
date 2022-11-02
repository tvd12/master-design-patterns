package com.tvd12.master_design_patterns.handler;

public interface FirstHandler<R> extends Handler<Void, R> {

    @Override
    default R handle(Void input) throws Exception {
        return firstHandle();
    }

    R firstHandle() throws Exception;
}
