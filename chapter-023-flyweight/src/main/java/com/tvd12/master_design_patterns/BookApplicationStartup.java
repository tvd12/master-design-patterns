package com.tvd12.master_design_patterns;

import com.tvd12.ezyhttp.server.boot.EzyHttpApplicationBootstrap;

public final class BookApplicationStartup {

    public static void main(String[] args) throws Exception {
        EzyHttpApplicationBootstrap.start(
            BookApplicationStartup.class
        );
    }
}
