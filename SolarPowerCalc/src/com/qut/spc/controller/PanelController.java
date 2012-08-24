package com.qut.spc.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class PanelController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.getWriter().println("oif");
		resp.setContentType("application/xml");
		
		resp.sendRedirect("/view/PanelView.jsp");
	}


	
	public String parseRequest(HttpServletRequest request,String name) {
		return "";
	}



	public String parseParameter(HttpServletRequest request, String string) {
		return request.getParameter(string);
	}

}
