package com.example.helloservlet1;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;

class MyServletIT {

	private static final String BASE_URL = "hello-servlet1/say-hello";

	@BeforeAll
	static void setup() {
		int tomcatPort = Integer
			.parseInt(System.getProperty("tomcat.http.port", "8080"));
		RestAssured.port = tomcatPort;
		Logger.getLogger(MyServletIT.class.toString())
			.info("Using URL: " + RestAssured.baseURI + ":" + RestAssured.port);
	}

	@Test
	void test() {
		when().get(BASE_URL).then().statusCode(200)
			.body(containsString("Served at:... /hello-servlet"));
	}

	@Test
	void test2() {
		given().param("name", "TEST").when().post(BASE_URL).then()
			.statusCode(200).body(containsString("Hello"))
			.body(containsString("TEST"));

		when().get(BASE_URL).then().statusCode(200)
			.body(containsString("Served at:... /hello-servlet"));
	}
}
