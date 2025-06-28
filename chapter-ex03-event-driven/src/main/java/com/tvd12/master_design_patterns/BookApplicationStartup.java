package com.tvd12.master_design_patterns;

import com.tvd12.master_design_patterns.broker.MessageBroker;
import com.tvd12.master_design_patterns.consumer.BookCreatedEventConsumer;
import com.tvd12.master_design_patterns.event.BookCreatedEvent;
import com.tvd12.master_design_patterns.publisher.BookEventPublisher;

public class BookApplicationStartup {

    public static void main(String[] args) {
        MessageBroker messageBroker = new MessageBroker();
        messageBroker.addConsumer(new BookCreatedEventConsumer());
        messageBroker.start();
        BookEventPublisher bookEventPublisher = new BookEventPublisher(messageBroker);
        bookEventPublisher.publish(
            BookCreatedEvent.builder()
                .id(1L)
                .bookName("Design Patterns")
                .build()
        );
    }
}
