package com.reqres.base;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

import com.reqres.utilities.JSONReading;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase {
	public static RequestSpecification httpReq;
	public static Response response;
	public static JSONReading jsonReading;
	public Logger logger;//predefine class present in Log4j
	@BeforeClass
	public void setUp() {
		logger = Logger.getLogger("ReqresAPITesting");
		PropertyConfigurator.configure("Log4j.properties");//configure it is a Static Method
		logger.setLevel(Level.DEBUG);//setLevel-Set Level ki log konse jga pe chaiy
	}
}
