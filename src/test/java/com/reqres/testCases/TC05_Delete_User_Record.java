package com.reqres.testCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.reqres.base.TestBase;
import com.reqres.utilities.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC05_Delete_User_Record extends TestBase {
	@BeforeClass
	void deleteUserRecord() throws InterruptedException {
		logger.info("------- Started TC05_Delete_User_Record -------");
		RestAssured.baseURI = "https://reqres.in";
		httpReq = RestAssured.given();
		response = httpReq.request(Method.DELETE, "/api/users/2");
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
		RestUtils.checkStatusCode(204, response);
//		Assert.assertEquals(statusCode, 204);
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
		RestUtils.checkStatusLine("HTTP/1.1 204 No Content", response);
//		Assert.assertEquals(statusLine, "HTTP/1.1 204 No Content");
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
		logger.info("-------Finished TC05_Delete_User_Record -------");
	}
}