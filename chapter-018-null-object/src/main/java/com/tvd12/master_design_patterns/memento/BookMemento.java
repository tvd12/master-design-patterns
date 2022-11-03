package com.tvd12.master_design_patterns.memento;

import com.tvd12.master_design_patterns.model.BookModel;

import java.util.LinkedList;

public class BookMemento {

    private final LinkedList<BookModel> bookStateQueue = new LinkedList<>();

    public BookModel takePreviousBookState() {
        synchronized (bookStateQueue) {
            return bookStateQueue.pollFirst();
        }
    }

    public void storeNewBookState(BookModel bookState) {
        synchronized (bookStateQueue) {
            bookStateQueue.addFirst(bookState.clone());
        }
    }
}
