package com.vocrecaptor.web.server.servlet;

import java.io.ByteArrayInputStream;
import java.io.IOException;

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

public class GetSessions extends AbstractServiceServlet {

	//TODO Somehow unite with GetCategories
	
	private static final long serialVersionUID = 4981709526793015088L;

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

		String dictId = "";
		
		NodeList fields = xmlDoc.getElementsByTagName("field");
        for (int i = 0; i < fields.getLength(); i++) {
        	Node field = fields.item(i);
        	String fieldName = field.getAttributes().getNamedItem("name").getTextContent();
        	if ("dictId".equals(fieldName)) dictId = field.getTextContent();
        }
        
		request.getSession().setAttribute("dictionaryToGetSessions", dictId);
		response.sendRedirect(response.encodeURL("dictionaryService"));
	}
	
}
