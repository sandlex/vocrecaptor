package com.vocrecaptor.fx.model.async;

import com.sun.javafx.runtime.async.AbstractAsyncOperation;
import com.sun.javafx.runtime.async.AsyncOperationListener;

/**
 * Saver implementation will performs asynchronous call of saving data
 * procedure. Currently it just waits for several seconds emulating saving
 * process.
 *
 * @author Alexey Peskov
 */
public class SaverImpl extends AbstractAsyncOperation {

    /**
     * Constructor.
     *
     * @param listener
     */
    public SaverImpl(AsyncOperationListener listener) {
        super(listener);
    }

    /**
     * Will call http request to save data and returns a response.
     * Now just waiting for 5 seconds.
     *
     * @return String object containing server response
     */
    @Override
    public Object call() {

        String result = "true";
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            result = "false";
        }

        return result;
    }

}
