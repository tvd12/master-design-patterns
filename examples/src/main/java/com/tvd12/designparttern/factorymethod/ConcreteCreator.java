package com.tvd12.designparttern.factorymethod;

/**
 * @author tavandung12
 *
 */
public class ConcreteCreator extends Application {
	
	

	/* (non-Javadoc)
	 * @see com.tvd.designparttern.factorymethod.Application#createDocument(java.lang.String)
	 * @author: tavandung12
	 * @last_modified: Aug 6, 2015
	 */
	@Override
	public Document createDocument(String type) {
		if(type.equals(TYPE_HTML)) {
			return new HtmlDocument();
		}
		else if(type.equals(TYPE_PROPRIETARY)) {
			return new ProprietaryDocument();
		}
		else if(type.equals(TYPE_PDF)) {
			return new PdfDocument();
		}
		
		return null;
	}

	
	public static final String TYPE_HTML			= "html";
	public static final String TYPE_PROPRIETARY		= "proprietary";
	public static final String TYPE_PDF			 	= "pdf";
	
}
