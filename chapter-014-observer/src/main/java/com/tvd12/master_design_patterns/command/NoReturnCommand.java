package com.tvd12.master_design_patterns.command;

public interface NoReturnCommand extends Command<Void> {

    @Override
    default Void execute() throws Exception {
        voidExecute();
        return null;
    }

    void voidExecute() throws Exception;
}
