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

import com.vocrecaptor.web.client.remote.transferobjects.DictionaryCategorySessionBean;

public class GetDictionary extends AbstractServiceServlet {

	private static final long serialVersionUID = 3268566231232242630L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf8");
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

		DictionaryCategorySessionBean bean = new DictionaryCategorySessionBean();
		
		NodeList fields = xmlDoc.getElementsByTagName("field");
        for (int i = 0; i < fields.getLength(); i++) {
        	Node field = fields.item(i);
        	String fieldName = field.getAttributes().getNamedItem("name").getTextContent();
        	if ("dictId".equals(fieldName)) bean.setDictId(field.getTextContent());
        	if ("category".equals(fieldName)) bean.setCategory(field.getTextContent());
        	if ("session".equals(fieldName)) bean.setSession(field.getTextContent());
        }
        
		request.getSession().setAttribute("dictionaryToGetDictionary", bean);
		response.sendRedirect(response.encodeURL("dictionaryService"));
	}
	
}
