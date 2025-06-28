package com.tvd12.master_design_patterns.publisher;

import com.tvd12.master_design_patterns.event.Event;

public interface Publisher {

    void publish(Event event);
}
