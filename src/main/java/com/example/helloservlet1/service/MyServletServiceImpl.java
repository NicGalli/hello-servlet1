package com.example.helloservlet1.service;

import static org.apache.commons.lang3.StringUtils.isEmpty;

public class MyServletServiceImpl implements MyServletService {

	@Override
	public String processName(String name) {
		if(isEmpty(name)) {
			name = "World";
		}
		return name;
	}

}
