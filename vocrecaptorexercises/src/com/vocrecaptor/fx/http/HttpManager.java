package com.vocrecaptor.fx.http;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Performs retreiving of data from server via http protocol.
 * Server to communicate with is specified in SERVER_URL constant.
 * 
 * @author Alexey Peskov
 */
public class HttpManager {

    /**
     * Server URL. Currently pointing to the test server where
     * web-application is running under Apache Tomcat application server.
     */
    //static private final String SERVER_URL = "http://vocrecaptor.appspot.com";
    static private final String SERVER_URL = "http://localhost:1852";

    /**
     * Performs login operation using user login and password.
     * 
     * @param login user login
     * @param password user password
     * @return "false" if user is not authorized or any other problem occurs
     * otherwise returns user id
     * @throws java.lang.Exception
     */
    static public String authorize(String login, String password) throws Exception {
        return performHttpPostRequest(SERVER_URL + "/vocrecaptorweb/login",
                "<fields><field name=\"login\">" + login + "</field>" +
                "<field name=\"password\">" + password + "</field></fields>");
    }

    /**
     * Retrieves a list of user dictionaries to display them on account panel.
     *
     * @param userId id of user
     * @return xml-string containing user dictionaries
     * @throws java.lang.Exception
     */
    static public String getDictionaryList(int userId) throws Exception {
        return performHttpPostRequest(SERVER_URL + "/vocrecaptorweb/getDictionaries",
                "<fields><field name=\"userId\">" + userId + "</field></fields>");
    }

    /**
     * Retrieves a list of words matching a criteria: user, dictionary, category
     * and session.
     *
     * @param userId id of user
     * @param dictId dictionary to retrieve words from
     * @param category category of words
     * @param session session meaning time when words were entered
     * @return xml-string containing words
     * @throws java.lang.Exception
     */
    static public String getWordList(int userId, int dictId,
            String category, String session) throws Exception {
        return performHttpPostRequest(SERVER_URL + "/vocrecaptorweb/getDictionary",
                "<fields><field name=\"userId\">" + userId + "</field>" +
                "<field name=\"dictId\">" + dictId + "</field>" +
                "<field name=\"category\">" + category + "</field>" +
                "<field name=\"session\">" + session + "</field></fields>");
    }

    /**
     * Retrieves a list of sessions existing in user dictionary.
     *
     * @param userId id of user
     * @param dictId dictionary to retrieve sessions from
     * @return xml-string containing sessions
     * @throws java.lang.Exception
     */
    static public String getSessionList(long dictId) throws Exception {
        return performHttpPostRequest(SERVER_URL + "/vocrecaptorweb/getSessions",
                "<fields><field name=\"dictId\">" + dictId + "</field></fields>");
    }

    /**
     * Retrieves a list of categories existing in user dictionary.
     *
     * @param userId id of user
     * @param dictId dictionary to retrieve categories from
     * @return xml-string containing categories
     * @throws java.lang.Exception
     */
    static public String getCategoryList(long dictId) throws Exception {
        return performHttpPostRequest(SERVER_URL + "/vocrecaptorweb/getCategories",
                "<fields><field name=\"dictId\">" + dictId + "</field></fields>");
    }

    /**
     * Common method that performs http request to the servlet in order to
     * retrieve required data
     *
     * @param urlString servlet name to send request
     * @param xml xml-body of request
     * @return xml-response from servlet
     * @throws java.lang.Exception
     */
    static private String performHttpPostRequest(String urlString, String xml) throws Exception {
        URL url;
        HttpURLConnection connection;
        String response = "false";
        url = new URL(urlString);
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setUseCaches(false);
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setAllowUserInteraction(true);
        connection.setRequestProperty("Content-Type:", "application/x-www-form-urlencoded");
        connection.getOutputStream().write(xml.getBytes());

        InputStream inpStr;
        inpStr = connection.getInputStream();

        response = convertStreamToString(inpStr);

        inpStr.close();

        return response;
    }

    /**
     * Converts input stream received from servlet into string.
     *
     * @param is input stream
     * @return input stream as a string
     */
    static private String convertStreamToString(InputStream is) throws UnsupportedEncodingException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, "utf8"));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return sb.toString();
    }

}
