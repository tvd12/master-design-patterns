package com.tvd12.master_design_patterns.service;

public interface DataService<M> {

    long saveModel(M model) throws Exception;
}
