package com.vocrecaptor.web.server.servlet;

import java.io.BufferedReader;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@SuppressWarnings("serial")
public abstract class AbstractServiceServlet extends HttpServlet {

	//TODO Do it the more service way
	protected String readXMLFromRequestBody(HttpServletRequest request) {
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
	
	protected static String recodeToUTF(String source) {
		if (source != null) {
			try {
				return new String(source.getBytes("ISO8859-1"), "utf8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}			
		}
		
		return null;
	}

	//FIXME use this
	/*Document doc =
XMLParser.parse(responseText);
Element root = (Element) doc.getFirstChild();
String firstName = root
.getElementsByTagName("firstName")
.item(0)
.getFirstChild()
.getNodeValue();
String lastName = root
.getElementsByTagName("lastName")
.item(0)
.getFirstChild()
.getNodeValue();*/
	
}
