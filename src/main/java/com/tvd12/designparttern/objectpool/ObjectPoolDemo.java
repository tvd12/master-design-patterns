package com.tvd12.designparttern.objectpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class ObjectPoolDemo {
	
	public void setUp() {
		mPool = new ObjectPool<ExportingProcess>(4, 10, 5) {
			
			@Override
			protected ExportingProcess createObject() {
				return new ExportingProcess(mProcessNo.incrementAndGet());
			}
		};
	}
	
	public void tearDown() {
		mPool.shutdown();
	}
	
	public void testObjectPool() {
		ExecutorService executor = Executors.newFixedThreadPool(8);
		
		executor.execute(new ExportingTask(mPool, 1));  
        executor.execute(new ExportingTask(mPool, 2));  
        executor.execute(new ExportingTask(mPool, 3));  
        executor.execute(new ExportingTask(mPool, 4));  
        executor.execute(new ExportingTask(mPool, 5));  
        executor.execute(new ExportingTask(mPool, 6));  
        executor.execute(new ExportingTask(mPool, 7));  
        executor.execute(new ExportingTask(mPool, 8)); 
        
        executor.shutdown();
        
        try {
        	executor.awaitTermination(30, TimeUnit.SECONDS);
        } 
        catch(InterruptedException e) {
        	e.printStackTrace();
        }
	}
	
	private ObjectPool<ExportingProcess> mPool;
	private AtomicLong mProcessNo = new AtomicLong(0);
	
	public static void main(String[] args) {
		 ObjectPoolDemo opDemo=new ObjectPoolDemo();  
		 opDemo.setUp();  
		 opDemo.tearDown();  
		 opDemo.testObjectPool();  
	}
	
}
