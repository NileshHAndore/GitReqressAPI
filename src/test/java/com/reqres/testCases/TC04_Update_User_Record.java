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

public class TC04_Update_User_Record extends TestBase {
	String userName = RestUtils.userName();
	String userJob = RestUtils.userJob();

	@SuppressWarnings("unchecked")
	@BeforeClass
	void updateUserRecord() throws InterruptedException {
		logger.info("------- Started TC04_Update_User_Record -------");
		RestAssured.baseURI = "https://reqres.in";
		httpReq = RestAssured.given();
		JSONObject reqParam = new JSONObject();

		jsonReading = new JSONReading();
		String jsonFile = "./src/test/resources/updateUserData.json";
		reqParam.put("name", jsonReading.getJSONData("name", jsonFile));
		reqParam.put("job", jsonReading.getJSONData("job", jsonFile));
//		System.out.println(jsonReading.getJSONData("name", jsonFile));
//		System.out.println(jsonReading.getJSONData("job", jsonFile));

//		reqParam.put("name", userName);
//		reqParam.put("job", userJob);
		httpReq.header("Conten-Type", "application/json");
		httpReq.body(reqParam.toJSONString());
		response = httpReq.request(Method.PUT, "/api/users/2");
		Thread.sleep(3);
	}

	@Test
	void checkResponseBody() {
		logger.info("-------Check Response Body -------");
		String responseBody = response.getBody().asPrettyString();
		logger.info("Response Body ==> " + responseBody);
		Assert.assertTrue(responseBody != null);
	}

	@Test
	void checkStatusCode() {
		logger.info("-------Check Status Code -------");
		int statusCode = response.getStatusCode();
		logger.info("Status Code ==> " + statusCode);
		RestUtils.checkStatusIs200(response);
//		Assert.assertEquals(statusCode, 200);
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
		RestUtils.checkStatusLine("HTTP/1.1 200 OK", response);
//		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
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
		logger.info("-------Finished TC04_Update_User_Recordr -------");
	}
}
