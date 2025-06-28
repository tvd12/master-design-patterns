package com.tvd12.master_design_patterns.adapter.in.web;

import com.tvd12.master_design_patterns.domain.port.in.CreateOrderUseCase;

import java.util.Map;

public class OrderController {
    private final CreateOrderUseCase createOrderUseCase;

    public OrderController(CreateOrderUseCase useCase) {
        this.createOrderUseCase = useCase;
    }

    public void create(Map<String, Object> request) {
        createOrderUseCase.createOrder(
            request.get("id") != null
                ? Long.parseLong(request.get("id").toString())
                : 0L,
            request.get("bookName") != null
                ? request.get("bookName").toString()
                : ""
        );
    }
}
