package com.google.places.java.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class JavaUtil {

	public static Properties readPropertiesFileData(String filePath) throws IOException {
		Properties prop = new Properties();
		FileInputStream fs = new FileInputStream(filePath);
		prop.load(fs);
		return prop;
	}
}
