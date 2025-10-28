package com.example.helloservlet1;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
	private MyServlet servlet;

	private AutoCloseable closeable;

	@BeforeEach
	void setup() throws Exception {
		closeable = MockitoAnnotations.openMocks(this);
		servlet = new MyServlet();
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
	@DisplayName("test post without name")
	void t2() throws Exception {
		when(request.getRequestDispatcher("response.jsp")).thenReturn(requestDispatcher);

		servlet.doPost(request, response);
		verify(request).setAttribute("user", "World");
		verify(requestDispatcher).forward(request, response);
	}

	@Test
	@DisplayName("test post with name")
	void t3() throws Exception {

		when(request.getParameter("name")).thenReturn("A Name");
		when(request.getRequestDispatcher("response.jsp")).thenReturn(requestDispatcher);

		servlet.doPost(request, response);
		verify(request).setAttribute("user", "A Name");
		verify(requestDispatcher).forward(request, response);
	}

}
