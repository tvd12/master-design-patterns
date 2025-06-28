package com.tvd12.master_design_patterns.adapter.out.persistence;

import com.tvd12.master_design_patterns.domain.model.Order;
import com.tvd12.master_design_patterns.domain.port.out.SaveOrderPort;

public class OrderRepositoryAdapter implements SaveOrderPort {

    @Override
    public void save(Order order) {
    }
}
