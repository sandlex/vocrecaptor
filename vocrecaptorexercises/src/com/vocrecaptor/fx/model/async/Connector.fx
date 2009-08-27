package com.vocrecaptor.fx.model.async;

import com.vocrecaptor.fx.view.AbstractPanel;
import javafx.async.AbstractAsyncOperation;
import javafx.scene.Group;

/**
 * Class performing an asynchronous operation on authorization.
 *
 * @author Alexey Peskov
 */
public class Connector extends AbstractAsyncOperation {

    /**
     * Parent panel for progress indicator panel.
     */
    public var parentPanel: AbstractPanel;

    /**
     * Parent group to insert progress indicator to.
     */
    public var parent: Group;

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
        insert ProcessIndicator {
            parent: parentPanel
            message: "Connecting"
        }.getInstance() into parent.content;
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
