package com.vocrecaptor.swing.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.vocrecaptor.swing.resource.PropertyGetter;

/**
 * Class includes some utility methods for working with dates.  
 *
 * @author Alexey Peskov
 */
public class DateUtil {
    
	/**
	 * Formats a given date into user-specified format. User format is 
	 * specified within application property file - 
	 * com/vocrecaptor/swing/resource/Application.properties.
	 * 
	 * @param date date to convert
	 * @return String representation of the date
	 */
    public static String formatDate(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(
        		PropertyGetter.getNamedProperty("session_format"));
        return dateFormat.format(date);
    }
}
