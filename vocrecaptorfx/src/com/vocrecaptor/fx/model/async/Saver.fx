package com.vocrecaptor.fx.model.async;

import com.vocrecaptor.fx.view.AbstractPanel;
import javafx.async.AbstractAsyncOperation;
import javafx.scene.Group;

/**
 * Class emulating saving of data (results of exercising in this case).
 * Operation made as asynchronous operation and currently just waits
 * for several seconds emulating process.
 *
 * @author Alexey Peskov
 */
public class Saver extends AbstractAsyncOperation {

    /**
     * Parent panel for progress indicator panel.
     */
    public var parentPanel: AbstractPanel;

    /**
     * Parent group to insert progress indicator to.
     */
    public var parent: Group;

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
        insert ProcessIndicator {
            parent: parentPanel
            message: "Saving data"
        }.getInstance() into parent.content;
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
