package com.reqres.testCases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.reqres.base.TestBase;
import com.reqres.utilities.JSONReading;
import com.reqres.utilities.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;

// 
public class TC03_Create_User extends TestBase {

	@SuppressWarnings("unchecked")
	@BeforeClass
	void createUser() throws InterruptedException {
		logger.info("------- Started TC03_Create_User -------");
		RestAssured.baseURI = "https://reqres.in";
		httpReq = RestAssured.given();
		JSONObject reqParam = new JSONObject();

		jsonReading = new JSONReading();
		String jsonFile = "./src/test/resources/CreateUserData.json";
		reqParam.put("name", jsonReading.getJSONData("name", jsonFile));
		reqParam.put("job", jsonReading.getJSONData("job", jsonFile));
//		System.out.println(jsonReading.getJSONData("name", jsonFile));
//		System.out.println(jsonReading.getJSONData("job", jsonFile));

		httpReq.header("Conten-Type", "application/json");
		httpReq.body(reqParam.toJSONString());
		response = httpReq.request(Method.POST, "/api/users");
		Thread.sleep(3);
	}

	@Test
	void checkResponseBody() {
		logger.info("-------Check Response Body -------");
		String responseBody = response.getBody().asPrettyString();
		logger.info("Reponse Body ==> " + responseBody);
		Assert.assertTrue(responseBody != null);
	}

	@Test
	void checkStatusCode() {
		logger.info("-------Check Status Code -------");
		int statusCode = response.getStatusCode();
		logger.info("Status Code ==> " + statusCode);
		RestUtils.checkStatusCode(201, response);
	}

	@Test
	void checkResponseTime() {
		logger.info("-------Check Response Time -------");
		long respsonseTime = response.getTime();
		logger.info("Response time is ==> " + respsonseTime);
		if (respsonseTime > 5000)
			logger.warn("Response time is greater than 5000");
		RestUtils.checkResponseTime((int) 5000, response);
//		Assert.assertTrue(respsonseTime < 5000);
	}

	@Test
	void checkStatusLine() {
		logger.info("-------Check Status Line -------");
		String statusLine = response.getStatusLine();
		logger.info("Status Line ==> " + statusLine);
		RestUtils.checkStatusLine("HTTP/1.1 201 Created", response);
//		Assert.assertEquals(statusLine, "HTTP/1.1 201 Created");
	}

	@Test
	void checkContentType() {
		logger.info("-------Check Content Type -------");
		String contentType = response.header("Content-Type");
		logger.info("Content type is : " + contentType);
		RestUtils.checkContentType("application/json; charset=utf-8", response);
//		Assert.assertEquals(contentType, "application/json; charset=utf-8");
	}

	@Test
	void checkServer() {
		logger.info("-------Check Server -------");
		String server = response.header("Server");
		logger.info("Server is : " + server);
		RestUtils.checkServer("cloudflare", response);
//		Assert.assertEquals(server, "cloudflare");
	}

	@AfterClass
	void tearDown() {
		logger.info("-------Finished TC03_Create_User -------");
	}
}
