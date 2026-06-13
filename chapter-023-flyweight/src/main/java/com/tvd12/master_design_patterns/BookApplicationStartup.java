package com.tvd12.master_design_patterns;

import com.tvd12.ezyhttp.server.boot.EzyHttpApplicationBootstrap;
import com.tvd12.master_design_patterns.entity.Category;

public final class BookApplicationStartup {

    public static void main(String[] args) throws Exception {
        EzyHttpApplicationBootstrap.start(
            BookApplicationStartup.class
        );
        BookApplication.getInstance()
            .getDataSchedulerManager()
            .getBackupSchedulerByType("daily")
            .backup(data -> new Category());

    }
}
