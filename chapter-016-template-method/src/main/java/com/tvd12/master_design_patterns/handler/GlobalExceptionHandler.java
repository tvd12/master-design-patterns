package com.tvd12.master_design_patterns.handler;

import com.tvd12.ezyhttp.core.response.ResponseEntity;

public class GlobalExceptionHandler
    implements Handler<Exception, ResponseEntity> {

    @Override
    public ResponseEntity handle(Exception input) {
        return ResponseEntity.badRequest(input.getMessage());
    }
}
