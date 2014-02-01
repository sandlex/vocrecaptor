package com.vocrecaptor.swing.view.updatetypes.base;

/**
 * Interface implemented by all types of Update events which can be received
 * by observers (views) from observable (model). Along with events itself
 * model sends a changed property value to be processed on the view-site.
 * In some cases it sends an old value as well. 
 * 
 * @author Alexey Peskov
 */
public interface UpdateType {

	/**
	 * Returns a new value of changed parameters from model.
	 * @return a new value of changed parameters as Object
	 */
	public Object getValue();
	
	/**
	 * Returns an old value of changed parameters from model.
	 * @return an old value of changed parameters as Object
	 */
	public Object getOldValue();
}
