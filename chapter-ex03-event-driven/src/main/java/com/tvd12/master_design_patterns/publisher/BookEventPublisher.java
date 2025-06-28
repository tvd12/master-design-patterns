package com.tvd12.master_design_patterns.publisher;

import com.tvd12.master_design_patterns.broker.MessageBroker;
import com.tvd12.master_design_patterns.event.Event;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BookEventPublisher implements Publisher {

    private final MessageBroker messageBroker;

    @Override
    public void publish(Event event) {
        messageBroker.addEvent(event);
    }
}
