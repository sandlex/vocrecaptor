package com.vocrecaptor.fx.model.async;

import com.vocrecaptor.fx.http.HttpManager;
import com.sun.javafx.runtime.async.AbstractAsyncOperation;
import com.sun.javafx.runtime.async.AsyncOperationListener;

/**
 *
 * @author Alexey_Peskov
 */
public class LanguagesLoaderImpl extends AbstractAsyncOperation {

    /**
     * User login.
     */
    private String login;

    /**
     * Constructor. Initializes user credentials.
     *
     * @param login user login
     * @param password user password
     * @param listener
     */
    public LanguagesLoaderImpl(String login, AsyncOperationListener listener) {
        super(listener);
        this.login = login;
    }

    /**
     * Performs call of http request and returns a response.
     *
     * @return Long object containing user id or an error code -1L if login is not found
     * or -2L if password is incorrect
     */
    @Override
    public Object call() {

        String result = "";
        try {
            result = HttpManager.getLanguageList(login);
        } catch (Exception e) {
            result = "";
        }

        return result;
    }


}
