package com.example.helloservlet1;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.logging.Logger;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

class MyServletE2E {

	private static final String BASE_URL = "http://localhost:"
		+ System.getProperty("tomcat.http.port", "8080") + "/hello-servlet1";

	private FirefoxDriver driver;

	@BeforeAll
	static void setupAll() {
		Logger.getLogger(MyServletE2E.class.toString())
			.info("Using URL: " + BASE_URL);
		WebDriverManager.firefoxdriver().setup();
	}

	@BeforeEach
	void setupEach() {
		driver = new FirefoxDriver();
	}

	@AfterEach
	void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

	@Test
	void test1() {
		driver.get(BASE_URL);
		driver.findElement(By.linkText("Hello")).click();
		assertThat(driver.findElement(By.tagName("body")).getText())
			.isEqualTo("Served at:... /hello-servlet1");
	}

	@Test
	void test2() {
		driver.get(BASE_URL);
		driver.findElement(By.id("say-hello-text-input")).sendKeys("World");
		driver.findElement(By.id("say-hello-button")).click();

		assertThat(driver.getTitle()).isEqualTo("Hello World");
		assertThat(driver.findElement(By.tagName("body")).getText())
			.isEqualTo("Hello World");
	}

}
