package com.vocrecaptor.fx.model.async;

import javafx.async.AbstractAsyncOperation;

/**
 * Class emulating timer with specified period of delay.
 * The reason we need use it at all is that javafx application
 * is a single-threaded (DET) and we cannot pause the execution
 * process using Thread.sleep() method.
 *
 * @author Alexey Peskov
 */
public class Delayer extends AbstractAsyncOperation {

    /**
     * Time of sleeping. Default value will be 2 seconds.
     */
    public var delay: Integer = 2;

    /**
     * Function to run on asynchronous operation completion.
     */
    public var onComplete: function(): Void;

    /**
     * Delayer implementation.
     */
    var delayer: DelayerImpl;
    
    /**
     * Starts asynchronous operation.
     */
    public override function start() : Void {
        delayer = new DelayerImpl(delay, listener);
        delayer.start();
    }

    /**
     * Invokes user specified function when asynchronous operation
     * is completed.
     */
    protected override function onCompletion(value : Object) : Void {
        onComplete();
    }

    /**
     * Cancel asynchronous operation.
     */
    public override function cancel() : Void {
        if (delayer != null) then delayer.cancel();
    }

    /**
     * Sets delay time.
     */
    public function setDelay(delay : Integer) : Void {
        this.delay = delay;
    }

}
