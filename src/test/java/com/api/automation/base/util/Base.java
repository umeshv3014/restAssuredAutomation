package com.api.automation.base.util;

import java.io.IOException;
import java.util.Properties;

import org.junit.BeforeClass;

import com.google.places.java.util.ApiUtil;
import com.google.places.resoureces.Constant;

public class Base {
	protected static Properties prop;

	@BeforeClass
	public void readDataFromExternalFiles() throws IOException {
		prop = ApiUtil
				.readPropertiesFileData(Constant.CONFIGURATION_PROPERTIES);
	}
}
