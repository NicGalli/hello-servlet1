package com.example.helloservlet1.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;


class MyServletServiceImplTest {

	@Test
	void testProcessNameWithEmptyValue() {
		MyServletServiceImpl service = new MyServletServiceImpl();
		String name = "";
		assertThat(service.processName(name)).isEqualTo("World");
		name = null;
		assertThat(service.processName(name)).isEqualTo("World");
	}

	@Test
	void testProcessNameWithPermittedValue() {
		MyServletServiceImpl service = new MyServletServiceImpl();
		String name = "John";
		assertThat(service.processName(name)).isEqualTo("John");
	}

}
