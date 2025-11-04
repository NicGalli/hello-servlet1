package com.example.helloservlet1;

import java.io.IOException;

import com.example.helloservlet1.service.MyServletService;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MyServlet
 */
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Inject
	private MyServletService service;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MyServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at:... ")
			.append(request.getContextPath());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		String name = req.getParameter("name");
		name = service.processName(name);
		req.setAttribute("user", name);
		req.getRequestDispatcher("response.jsp").forward(req, resp);
	}
}
