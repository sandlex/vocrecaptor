package com.vocrecaptor.web.server.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Download extends HttpServlet {

	private static final long serialVersionUID = 7879300334943766414L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");

		String id = request.getParameter("id");

		request.getSession().setAttribute("dictionaryToDownload", id);
		response.sendRedirect(response.encodeURL("dictionaryService"));
	}
	
}
