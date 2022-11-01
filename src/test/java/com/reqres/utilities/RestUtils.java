package com.reqres.utilities;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;

import io.restassured.response.Response;

public class RestUtils {

	public static String userName() {
		String generatedString = RandomStringUtils.randomAlphabetic(2);
		return ("Smith" + generatedString);
	}

	public static String userJob() {
		String generatedJob = RandomStringUtils.randomAlphabetic(5);
		return ("Job" + generatedJob);
	}

	// Verify the http response status returned. Check Status Code is 200?
	public static void checkStatusIs200(Response res) {
		Assert.assertEquals(res.getStatusCode(), 200, "Status Check Failed!");
	}

	// Verify the http response status returned. Check Status Code is 200?
	public static void checkStatusCode(int code, Response res) {
		Assert.assertEquals(res.getStatusCode(), code, "Status Check Failed!");
	}

	public static void checkContentType(String contentType, Response res) {
		Assert.assertEquals(res.header("Content-Type"), contentType, "Content Type Failed!");
	}

	public static void checkStatusLine(String statusLine, Response res) {
		Assert.assertEquals(res.getStatusLine(), statusLine);
	}

	public static void checkServer(String server, Response res) {
		Assert.assertEquals(res.header("Server"), server);
	}

	public static void checkContentEncoding(String contentEncoding, Response res) {
		Assert.assertEquals(res.header("Content-Encoding"), contentEncoding);
	}

	public static void checkResponseTime(long time, Response res) {
		Assert.assertTrue(res.getTime() < time);
	}
}
