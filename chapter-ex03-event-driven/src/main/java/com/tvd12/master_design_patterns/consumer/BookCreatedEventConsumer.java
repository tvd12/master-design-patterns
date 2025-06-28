package com.tvd12.master_design_patterns.consumer;

import com.tvd12.master_design_patterns.event.Event;

public class BookCreatedEventConsumer implements Consumer {

    @Override
    public void consume(Event event) {
        System.out.println("Book created event consumed: " + event.getEventName());
    }
}
