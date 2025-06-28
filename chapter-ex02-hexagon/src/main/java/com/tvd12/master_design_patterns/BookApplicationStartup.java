package com.tvd12.master_design_patterns;

import com.tvd12.master_design_patterns.adapter.in.web.OrderController;
import com.tvd12.master_design_patterns.adapter.out.persistence.OrderRepositoryAdapter;
import com.tvd12.master_design_patterns.application.service.OrderService;

import java.util.HashMap;
import java.util.Map;

public class BookApplicationStartup {

    public static void main(String[] args) {
        OrderRepositoryAdapter orderRepositoryAdapter = new OrderRepositoryAdapter();
        OrderService orderService = new OrderService(orderRepositoryAdapter);
        OrderController controller = new OrderController(
            orderService
        );
        Map<String, Object> request = new HashMap<>();
        request.put("id", 1L);
        request.put("bookName", "Design Patterns");
        controller.create(request);
    }
}
