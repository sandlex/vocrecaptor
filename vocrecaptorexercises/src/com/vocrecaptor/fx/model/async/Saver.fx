package com.vocrecaptor.fx.model.async;

import javafx.async.AbstractAsyncOperation;

/**
 * Class emulating saving of data (results of exercising in this case).
 * Operation made as asynchronous operation and currently just waits
 * for several seconds emulating process.
 *
 * @author Alexey Peskov
 */
public class Saver extends AbstractAsyncOperation {

    /**
     * Function to run on asynchronous operation completion.
     */
    public var onComplete: function(value: String): Void;

    /**
    * Saver implementation.
     */
    var saver: SaverImpl;

    /**
     * Starts asynchronous operation and displays progress
     * indicator.
     */
    public override function start() : Void {
        saver = new SaverImpl(listener);
        saver.start();
    }

    /**
     * Invokes user specified function when asynchronous operation
     * is completed.
     */
    protected override function onCompletion(value: Object) : Void {
        onComplete(value as String);
    }

    /**
     * Cancel asynchronous operation.
     */
    public override function cancel() : Void {
        if (saver != null) then saver.cancel();
    }

}
