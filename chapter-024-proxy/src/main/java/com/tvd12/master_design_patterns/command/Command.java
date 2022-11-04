package com.tvd12.master_design_patterns.command;

public interface Command<R> {

    R execute() throws Exception;
}
