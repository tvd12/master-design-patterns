package com.tvd12.master_design_patterns.domain.port.in;

public interface CreateOrderUseCase {
    void createOrder(long id, String bookName);
}
