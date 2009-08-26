package com.vocrecaptor.web.server.servlet;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.vocrecaptor.web.client.remote.transferobjects.UserTransferObject;

public class Login extends HttpServlet {

	private static final long serialVersionUID = 3395192490485196922L;

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");

		String xml = readXMLFromRequestBody(request);
		
		xml = recodeToUTF(xml);
		
		Document xmlDoc = null;
		try {
			xmlDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
					.parse(new ByteArrayInputStream(xml.getBytes()));
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}

		String login = "";
		String password = "";
		
		NodeList fields = xmlDoc.getElementsByTagName("field");
        for (int i = 0; i < fields.getLength(); i++) {
        	Node field = fields.item(i);
        	String fieldName = field.getAttributes().getNamedItem("name").getTextContent();
        	if ("login".equals(fieldName)) login = field.getTextContent();
        	if ("password".equals(fieldName)) password = field.getTextContent();
        }
        UserTransferObject userTO = new UserTransferObject(login, password);
        
		request.getSession().setAttribute("userToLogin", userTO);
		response.sendRedirect("userService");
	}

	private String readXMLFromRequestBody(HttpServletRequest request) {
		StringBuffer xml = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null) {
				xml.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return xml.toString();
	}
	
	public static String recodeToUTF(String source) {
		if (source != null) {
			try {
				return new String(source.getBytes("ISO8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}			
		}
		
		return null;
	}
	
}
