package de.dhbw.projektarbeit.db.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Konvertiert das Datum aus der GUI in ein Format für die mySQL DB oder zurück
 * in einen String
 * 
 * @author Juli
 * 
 */

public class DateConverter {

	public Date StringToDate(String date) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return sdf.parse(date);
		} catch (ParseException ex1) {
			ex1.printStackTrace();
			throw new Exception(
					"Bei der Umwandlung des Datums ist ein Fehler aufgetreten!");
		}

	}

	/**
	 * METHODE zurückumwandlung!
	 */

}
