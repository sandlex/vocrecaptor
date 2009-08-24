package com.vocrecaptor.web.server;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.vocrecaptor.web.client.remote.StaticContentService;

@SuppressWarnings("serial")
public class StaticContentServiceImpl extends RemoteServiceServlet implements
StaticContentService {

	@Override
	public String getHtmlContent(String fileName) {
		StringBuffer resultHtml = new StringBuffer();
		BufferedReader inputStream = null;
		try {
			inputStream = new BufferedReader(new InputStreamReader(
					new FileInputStream(fileName)));
					//new FileInputStream("/usr/local/shared/tomcat/vocrecaptor/webapps/vocrecaptorgwt/" + fileName)));

			String str;
			while ((str = inputStream.readLine()) != null) {
				resultHtml.append(str);
			}
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
		
		return resultHtml.toString();
	}

}
