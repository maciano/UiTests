package com.Tesis.commons;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DataTestHelper {
	private Properties prop;
	private InputStream is;

	public DataTestHelper(){
	}
	
	public void openProperties() {
		prop = new Properties();
		try {
			String location=getClass().getResource("/com/tesis/configurations/dataSet.properties").toString();
			is = new FileInputStream(location.substring(6, location.length()));
			prop.load(is);
		} catch (IOException ioe) {
			//ioe.printStackTrace();
			System.out.println("System is not finding the file: dataSet.properties");
		}
	}
	
	public void closeProperties() {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}			
	}	
	
	public String getProperty(String key) {
		return prop.getProperty(key);
	}
	

}
