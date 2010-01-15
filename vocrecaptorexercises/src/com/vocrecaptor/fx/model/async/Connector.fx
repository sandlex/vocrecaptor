package com.vocrecaptor.fx.model.async;

import javafx.async.AbstractAsyncOperation;

/**
 * Class performing an asynchronous operation on authorization.
 *
 * @author Alexey Peskov
 */
public class Connector extends AbstractAsyncOperation {

    /**
     * User login for authorization.
     */
    public var login: String;
    
    /**
     * User passwords for authorization.
     */
    public var password: String;

    /**
     * Function to run on asynchronous operation completion.
     */
    public var onComplete: function(value: Long): Void;

    /**
     * Connector implementation.
     */
    var connector: ConnectorImpl;

    /**
     * Starts asynchronous operation and displays progress
     * indicator.
     */
    public override function start() : Void {
        connector = new ConnectorImpl(login, password, listener);
        connector.start();
    }

    /**
     * Invokes user specified function when asynchronous operation
     * is completed.
     */
    protected override function onCompletion(value: Object) : Void {
        onComplete(value as Long);
    }
    
    /**
     * Cancel asynchronous operation.
     */
    public override function cancel() : Void {
        if (connector != null) then connector.cancel();
    }

}
