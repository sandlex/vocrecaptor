package com.vocrecaptor.web.server.servlet;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;

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

public class GetDictionaries extends AbstractServiceServlet {

	private static final long serialVersionUID = 3755143675418154707L;
	
	public class GetDictionariesBean implements Serializable {

		private static final long serialVersionUID = -3962791980932343024L;
		
		String userId;
		String publicCriteria;
		String learningLanguage;
		String throughLanguage;
		
		public GetDictionariesBean() {
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		public void setPublicCriteria(String publicCriteria) {
			this.publicCriteria = publicCriteria;
		}

		public String getUserId() {
			return userId;
		}

		public String getPublicCriteria() {
			return publicCriteria;
		}

		public String getLearningLanguage() {
			return learningLanguage;
		}

		public void setLearningLanguage(String learningLanguage) {
			this.learningLanguage = learningLanguage;
		}

		public String getThroughLanguage() {
			return throughLanguage;
		}

		public void setThroughLanguage(String throughLanguage) {
			this.throughLanguage = throughLanguage;
		}
		
	}

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

		GetDictionariesBean bean = new GetDictionariesBean();
		
		NodeList fields = xmlDoc.getElementsByTagName("field");
        for (int i = 0; i < fields.getLength(); i++) {
        	Node field = fields.item(i);
        	String fieldName = field.getAttributes().getNamedItem("name").getTextContent();
        	if ("userId".equals(fieldName)) bean.setUserId(field.getTextContent());
        	if ("pub".equals(fieldName)) bean.setPublicCriteria(field.getTextContent());
        	if ("learn".equals(fieldName)) bean.setLearningLanguage(field.getTextContent());
        	if ("through".equals(fieldName)) bean.setThroughLanguage(field.getTextContent());
        }
        
		request.getSession().setAttribute("beanToGetDictionaries", bean);
		response.sendRedirect(response.encodeURL("dictionaryService"));
	}

}
