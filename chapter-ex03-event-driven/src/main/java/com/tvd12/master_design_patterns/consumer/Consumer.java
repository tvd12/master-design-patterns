package com.tvd12.master_design_patterns.consumer;

import com.tvd12.master_design_patterns.event.Event;

public interface Consumer {

    void consume(Event event);
}
