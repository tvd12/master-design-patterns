package com.tvd12.designparttern.iterator;

import java.util.Iterator;

public class MyCollection<T> implements Iterable<T> {

	private int size = 0;
	private Object[] items = new Object[100];
	
	public void add(T item) {
		this.items[size ++] = item;
	}
	
	@Override
	public Iterator<T> iterator() {
		return new MyIterator();
	}
	
	public static void main(String[] args) {
		MyCollection<String> collection = new MyCollection<>();
		collection.add("a");
		collection.add("b");
		collection.add("c");
		for (String item : collection) {
			System.out.print(item + ", ");
		}
	}
	
	class MyIterator implements Iterator<T> {

		private int index;
		
		@Override
		public boolean hasNext() {
			return index < size;
		}

		@SuppressWarnings("unchecked")
		@Override
		public T next() {
			return (T)items[index ++];
		}
	}
}