package com.tvd12.designparttern.objectpool;

public class ExportingProcess {

	public ExportingProcess(long processNo) {
		this.mProcessNo = processNo;
		
		System.out.println("DEBUG: Object with process no." 
				+ processNo 
				+ " was created");
	}
	
	public long getProcessNo() {
		return mProcessNo;
	}
	
	private long mProcessNo;
}
