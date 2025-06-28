package com.tvd12.master_design_patterns.domain.port.out;

import com.tvd12.master_design_patterns.domain.model.Order;

public interface SaveOrderPort {
    void save(Order order);
}
