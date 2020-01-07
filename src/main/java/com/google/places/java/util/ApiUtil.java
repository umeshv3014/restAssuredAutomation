package com.google.places.java.util;

import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class ApiUtil {

	public static String generateStringFromResource(String path)
			throws IOException {
		return new String(Files.readAllBytes(Paths.get(path)));
	}
	public static Properties readPropertiesFileData(String filePath)
			throws IOException {
		Properties prop = new Properties();
		FileInputStream fs = new FileInputStream(filePath);
		prop.load(fs);
		return prop;
	}

	public static XmlPath rawToXML(Response r) {

		String respon = r.asString();
		XmlPath x = new XmlPath(respon);
		return x;

	}

	public static JsonPath rawToJson(Response r) {
		String respon = r.asString();
		JsonPath x = new JsonPath(respon);
		return x;
	}
}
