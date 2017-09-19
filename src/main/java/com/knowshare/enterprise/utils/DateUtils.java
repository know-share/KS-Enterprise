/**
 * 
 */
package com.knowshare.enterprise.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Clase utilitaria para el manejo de fechas.
 * @author Miguel Montañez
 *
 */
public class DateUtils {
	
	private DateUtils(){}
	
	/**
	 * Dado el timestamp se genera la fecha con formato UTC
	 * @param timestamp
	 * @return fecha en formato UTC
	 */
	public static Date getDate(long timestamp){
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		final Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		calendar.setTimeInMillis(timestamp);
		return calendar.getTime();
	}
}
