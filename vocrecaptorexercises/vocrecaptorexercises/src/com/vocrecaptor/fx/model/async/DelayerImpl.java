package com.vocrecaptor.fx.model.async;

import com.sun.javafx.runtime.async.AbstractAsyncOperation;
import com.sun.javafx.runtime.async.AsyncOperationListener;

/**
 * Delayer implementation performing sleeping of a precess. Used for
 * countdown-timers in recaping and exercises as well as in notification
 * messages.
 *
 * @author Alexey Peskov
 */
public class DelayerImpl extends AbstractAsyncOperation  {

    /**
     * Delay time.
     */
    private int delay;

    /**
     * Constructor. Sets default delay time.
     *
     * @param delay
     * @param listener
     */
    public DelayerImpl(int delay, AsyncOperationListener listener) {
        super(listener);
        this.delay = delay;
    }

    /**
     * Performs delaying of execution.
     *
     * @return nothing
     * @throws java.lang.Exception
     */
    @Override
    public Object call() throws Exception {
        Thread.sleep(delay * 1000);

        return null;
    }

}
