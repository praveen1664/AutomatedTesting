package com.optum.acoe.Utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class PropertyReader 
{
	Properties properties = new Properties();
    InputStream inputStreamConfig = null;

    private static String filePath=System.getProperty("user.dir")+"//src//main//resources//Config.properties" ;
    public PropertyReader(String str) {
    	filePath = str;
        loadProperties();
    }
    
    public PropertyReader() {
    	filePath=System.getProperty("user.dir")+"//src//main//resources//Config.properties";
        loadProperties();
    }

    private void loadProperties() {
        try {
        	
            inputStreamConfig = new FileInputStream(filePath);
            properties.load(inputStreamConfig);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readProperty(String key) {
        return properties.getProperty(key);
    }
    
    public void setProperty(String key, String value) {
    	try {
         properties.setProperty(key, value);
         OutputStream output = new FileOutputStream(filePath);
         properties.store(output, "");
    	}
    	catch(IOException e) {
    		System.out.println("Error while writing to .properties file");
    		System.out.println(e.getMessage());
    	}
    }
    
}
