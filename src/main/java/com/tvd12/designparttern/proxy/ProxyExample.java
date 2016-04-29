package com.tvd12.designparttern.proxy;

public class ProxyExample {

	interface Image {
		public void display();
	}

	static class RealImage implements Image {

		public RealImage(String fileName) {
			this.mFileName = fileName;

			loadFromDisk(fileName);
		}

		@Override
		public void display() {
			System.out.println("Displaying " + mFileName);
		}

		private void loadFromDisk(String fileName) {
			System.out.println("Loading " + fileName);
		}

		private String mFileName;
	}

	static public class ProxyImage implements Image {

		public ProxyImage(String fileName) {
			this.mFileName = fileName;
		}

		@Override
		public void display() {
			if (mRealImage == null) {
				mRealImage = new RealImage(mFileName);
			}
			mRealImage.display();
		}
		
		private RealImage mRealImage;
		private String mFileName;
	}
	
	public static void main(String[] args) {
	      Image image = new ProxyImage("test_10mb.jpg");

	      //image will be loaded from disk
	      image.display(); 
	      System.out.println("");
	      
	      //image will not be loaded from disk
	      image.display(); 	
	   }
}
