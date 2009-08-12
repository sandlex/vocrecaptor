package com.vocrecaptor.swing.resource;

import java.util.Locale;
import java.util.ResourceBundle;

import com.vocrecaptor.swing.util.Configurator;

/**
 * Utility class that ease to work with Application.properties file where localized
 * strings are stored.
 * 
 * @author Alexey Peskov
 *
 */
public class PropertyGetter {

	/**
	 * Gets a localized string from property bundle by its name.
	 * 
	 * @param propertyName name of property
	 * @return localized string value
	 */
	public static String getNamedProperty(String propertyName) {
		return ResourceBundle.getBundle("com/vocrecaptor/swing/resource/Application", 
				new Locale(Configurator.getInstance().getLocale())).getString(propertyName);
	}
}
