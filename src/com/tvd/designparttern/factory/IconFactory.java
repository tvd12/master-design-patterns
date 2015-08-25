package com.tvd.designparttern.factory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class IconFactory {

	private IconFactory() {
		this.init();
		this.register();
	}
	
	public static IconFactory getInstance() {
		if(sInstance == null) {
			synchronized (IconFactory.class) {
				if(sInstance == null) {
					sInstance = new IconFactory();
				}
			}
		}
		
		return sInstance;
	}
	
	public IconView getIconView(String name) {
		String className = mClasses.get(name);
		if(className == null) {
			return null;
		}
		
		try {
			return getClassByName(className);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private void init() {
		mClasses = new HashMap<>();
	}
	
	private void register() {
		InputStream inputStream = getClass()
				.getResourceAsStream("factory.properties");
		try {
			InputStreamReader inputReader = 
					new InputStreamReader(inputStream, "UTF-8");
			BufferedReader reader = new BufferedReader(inputReader);
			
			String str = reader.readLine();
			while(str != null) {
				String[] strs = str.split("=");
				mClasses.put(strs[0].trim(), strs[1].trim());
				
				str = reader.readLine();
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private IconView getClassByName(String className) 
			throws InstantiationException, 
			IllegalAccessException, 
			ClassNotFoundException {
		IconView obj = (IconView) Class.forName(className).newInstance();
		
		return obj;
	}
	
	private static IconFactory sInstance;
	private Map<String, String> mClasses;
}
