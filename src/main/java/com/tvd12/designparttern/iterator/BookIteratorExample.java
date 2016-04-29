package com.tvd12.designparttern.iterator;

public class BookIteratorExample {

	public static void main(String[] args) {
		BooksCollection books = new BooksCollection();
		IIterator iterator = books.createInterator();
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}
	
}

interface IIterator {
	public boolean hasNext();
	public Object next();
}

interface IContainer {
	public IIterator createInterator();
}

class BooksCollection implements IContainer {

	@Override
	public IIterator createInterator() {
		return new BookIterator();
	}
	
	private class BookIterator implements IIterator {

		@Override
		public boolean hasNext() {
			return (mPosition < mTitles.length);
		}

		@Override
		public Object next() {
			return hasNext()
					? mTitles[mPosition ++]
					: null;
		}
		
		private int mPosition;
	}

	private String mTitles[] = {
			"Design Patterns",
			"1", "2", "3", "4"
	};
}