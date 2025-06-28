package com.tvd12.master_design_patterns.event;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BookCreatedEvent implements Event {
    private final long id;
    private final String bookName;

    @Override
    public String getEventName() {
        return BookCreatedEvent.class.getName();
    }
}
