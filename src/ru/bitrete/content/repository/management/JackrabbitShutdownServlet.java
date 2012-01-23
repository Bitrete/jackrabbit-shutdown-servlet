package ru.bitrete.content.repository.management;

import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class JackrabbitShutdownServlet extends GenericServlet {

	private static final long serialVersionUID = 5975146328701381422L;
	
	@Override
	public String getServletInfo() {
		return "Jackrabbit shutdown servlet 1.0 by Bitrete LLC";
	}
	
	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException,
			IOException {
	}
}
