package com.tvd12.master_design_patterns.interceptor;

import com.tvd12.ezyfox.util.EzyLoggable;
import com.tvd12.ezyhttp.core.annotation.Interceptor;
import com.tvd12.ezyhttp.server.core.interceptor.RequestInterceptor;
import com.tvd12.ezyhttp.server.core.request.RequestArguments;

import java.lang.reflect.Method;

@Interceptor
public class LogInterceptor
    extends EzyLoggable
    implements RequestInterceptor {

    @Override
    public boolean preHandle(
        RequestArguments arguments,
        Method handler
    ) throws Exception {
        logger.info(
            "pre handle request: {}",
            arguments.getRequest().getRequestURI()
        );
        return true;
    }

    @Override
    public void postHandle(RequestArguments arguments, Method handler) {
        logger.info(
            "post handle request: {} - {}",
            arguments.getRequest().getRequestURI(),
            arguments.getResponse().getStatus()
        );
    }
}
