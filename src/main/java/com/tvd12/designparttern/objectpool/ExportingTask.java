package com.tvd12.designparttern.objectpool;

public class ExportingTask implements Runnable{

	public ExportingTask(ObjectPool<ExportingProcess> pool,
			int threadNo) {
		this.mPool = pool;
		this.mThreadNo = threadNo;
	}
	
	@Override
	public void run() {
		ExportingProcess exportingProcess = mPool.borrowObject();
		
		System.out.println("DEBUG: Thread " 
				+ mThreadNo 
				+ ": Object with process no. "  
                + exportingProcess.getProcessNo() 
                + " was borrowed");
		
		mPool.returnObject(exportingProcess);
		
		System.out.println("DEBUG: Thread " 
				+ mThreadNo 
				+ ": Object with process no. "  
                + exportingProcess.getProcessNo() 
                + " was returned");
	}

	private int mThreadNo;
	private ObjectPool<ExportingProcess> mPool;
}
