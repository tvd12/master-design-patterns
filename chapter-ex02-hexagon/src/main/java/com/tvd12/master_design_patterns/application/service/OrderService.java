package com.tvd12.master_design_patterns.application.service;

import com.tvd12.master_design_patterns.domain.model.Order;
import com.tvd12.master_design_patterns.domain.port.in.CreateOrderUseCase;
import com.tvd12.master_design_patterns.domain.port.out.SaveOrderPort;

public class OrderService implements CreateOrderUseCase {
    private final SaveOrderPort saveOrderPort;

    public OrderService(SaveOrderPort saveOrderPort) {
        this.saveOrderPort = saveOrderPort;
    }

    @Override
    public void createOrder(long id, String bookName) {
        Order order = Order.builder()
            .id(id)
            .bookName(bookName)
            .build();
        saveOrderPort.save(order);
    }
}
