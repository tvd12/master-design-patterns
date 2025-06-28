package com.tvd12.master_design_patterns.broker;

import com.tvd12.master_design_patterns.consumer.Consumer;
import com.tvd12.master_design_patterns.event.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MessageBroker {

    private final BlockingQueue<Event> queue = new LinkedBlockingQueue<>();
    private final List<Consumer> consumers = new ArrayList<>();

    public void addConsumer(Consumer consumer) {
        consumers.add(consumer);
    }

    public void addEvent(Event event) {
        queue.offer(event);
    }

    public void start() {
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Event event = queue.take();
                    consumers.forEach(consumer -> {
                        try {
                            consumer.consume(event);
                        } catch (Exception e) {
                            System.err.println("Error consuming event: " + e.getMessage());
                        }
                    });
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        thread.start();
    }
}
