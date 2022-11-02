package com.tvd12.designparttern.builder;

public class Client {
	
	public void createASCIIText(Document doc){
		ASCIIConverter asciiBuilder = new ASCIIConverter();
		
		RTFReader rtfReader = new RTFReader(asciiBuilder);
		rtfReader.parseRTF(doc);
		
		System.out.println("INFO: result = " + asciiBuilder.getResult());
	}
	
	public static void main(String args[]) {
		Client client = new Client();
		Document doc = new Document();
		
		client.createASCIIText(doc);
		
	}
	
}
