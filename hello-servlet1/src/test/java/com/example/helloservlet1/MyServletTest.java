package com.example.helloservlet1;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.helloservlet1.service.MyServletService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

class MyServletTest {

	@Mock
	private HttpServletRequest request;
	@Mock
	private HttpServletResponse response;
	@Mock
	private RequestDispatcher requestDispatcher;
	@Mock
	private MyServletService service;
	@InjectMocks
	private MyServlet servlet;

	private AutoCloseable closeable;

	@BeforeEach
	void setup() throws Exception {
		closeable = MockitoAnnotations.openMocks(this);
	}

	@AfterEach
	void releaseMocks() throws Exception {
		closeable.close();
	}

	@Test
	@DisplayName("test get")
	void t1() throws Exception {
		StringWriter stringWriter = new StringWriter();
		when(response.getWriter()).thenReturn(new PrintWriter(stringWriter));
		when(request.getContextPath()).thenReturn("/");

		servlet.doGet(request, response);
		assertThat(stringWriter.toString()).isEqualTo("Served at:... /");
	}

	@Test
	@DisplayName("test post with name")
	void t2() throws Exception {

		when(request.getParameter("name")).thenReturn("John");
		when(request.getRequestDispatcher("response.jsp")).thenReturn(requestDispatcher);
		when(service.processName(anyString())).thenReturn("Processed name");
		
		servlet.doPost(request, response);
		verify(request).setAttribute("user", "Processed name");
		verify(requestDispatcher).forward(request, response);
	}

}
