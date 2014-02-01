package com.vocrecaptor.fx.model.async;

import javafx.async.AbstractAsyncOperation;

/**
 * @author Alexey_Peskov
 */

public class LanguagesLoader extends AbstractAsyncOperation {

    /**
    * User login for authorization.
     */
    public var login: String;

    /**
     * Function to run on asynchronous operation completion.
     */
    public var onComplete: function(value: String): Void;

    /**
     * Languages loader implementation.
     */
    var loader: LanguagesLoaderImpl;

    /**
     * Starts asynchronous operation and displays progress
     * indicator.
     */
    public override function start() : Void {
        loader = new LanguagesLoaderImpl(login, listener);
        loader.start();
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
        if (loader != null) then loader.cancel();
    }

}
