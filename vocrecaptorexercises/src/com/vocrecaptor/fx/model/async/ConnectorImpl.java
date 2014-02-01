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
public class ConnectorImpl extends AbstractAsyncOperation {

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
     * @return Long object containing user id or an error code -1L if login is not found
     * or -2L if password is incorrect
     */
    @Override
    public Object call() {

        Long result = -1L;
        try {
            result = Long.valueOf(HttpManager.authorize(login, password));
        } catch (Exception e) {
            result = -1L;
        }

        return result;
    }

}
