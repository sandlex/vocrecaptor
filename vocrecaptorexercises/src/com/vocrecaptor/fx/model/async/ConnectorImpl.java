package com.vocrecaptor.fx.model.async;

import com.vocrecaptor.fx.http.HttpManager;
import com.sun.javafx.runtime.async.AbstractAsyncOperation;
import com.sun.javafx.runtime.async.AsyncOperationListener;

/**
 * Connector implementation performing asynchronous call of authorization
 * function.
 *
 * @author Alexey Peskov
 */
public class ConnectorImpl extends AbstractAsyncOperation  {

    /**
     * User login.
     */
    private String login;

    /**
     * User password.
     */
    private String password;

    /**
     * Constructor. Initializes user credentials.
     *
     * @param login user login
     * @param password user password
     * @param listener
     */
    public ConnectorImpl(String login, String password, AsyncOperationListener listener) {
        super(listener);
        this.login = login;
        this.password = password;
    }

    /**
     * Performs call of http request and returns a response.
     * 
     * @return String object containing server response
     */
    @Override
    public Object call() {

        String result = "false";
        try {
            result = HttpManager.authorize(login, password);
        } catch (Exception e) {
            result = "false";
        }

        return result;
    }

}
