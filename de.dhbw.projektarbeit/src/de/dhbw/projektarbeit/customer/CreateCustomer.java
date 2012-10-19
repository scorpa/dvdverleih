package de.dhbw.projektarbeit.customer;

import java.security.InvalidParameterException;
import java.sql.Date;

import de.dhbw.projektarbeit.db.mysql.MysqlAccess;
import de.dhbw.projektarbeit.db.request.Insert;

public class CreateCustomer {

	/**
	 * Klasse erstellt beim Klick in der GUI einen Customereintrag in der DB.
	 * Über die Methode... kann ein neuer Kundeneintrag in die DB gemacht
	 * werden.
	 */

	private MysqlAccess access;
	private java.util.Date bdate;
	private Insert insert;
	private String firstName, lastName, city, street, streetNo, email,
			telefone;
	private Date dateBirthdate;
	private int zip_code;

	/**
	 * CREATE NEW CUSTOMER ** *********************** Methode, die das
	 * Uebernehmen der eingegebenen Daten uebernimmt, das Datum in ein
	 * Dateformat parset und die mySQL DB schreibt.
	 * 
	 * @param firstName
	 *            --> Vorname
	 * @param lastName
	 *            --> Nachname
	 * @param zip_code
	 *            --> PLZ
	 * @param city
	 *            --> Ort
	 * @param street
	 *            --> Strasse
	 * @param streetNo
	 *            --> Hausnummer
	 * @param email
	 *            --> E-Mail
	 * @param telefone
	 *            --> Telefonnummer
	 * @param dateBirthdate
	 *            --> Geburtstag
	 * @throws Exception
	 *             --> Exeptionhandling
	 */

	public void CreateNewCustomer(String firstName, String lastName,
			int zip_code, String city, String street, String streetNo,
			String email, String telefone, Date dateBirthdate) throws Exception {

		this.firstName = firstName;
		this.lastName = lastName;
		this.zip_code = zip_code;
		this.city = city;
		this.street = street;
		this.streetNo = streetNo;
		this.email = email;
		this.telefone = telefone;
		this.dateBirthdate = dateBirthdate;


		// Ruft die Methode zum Übertragen und Speichern in die mySQL DB auf
		try {
			insert.insertCustomer(firstName, lastName, zip_code, city, street,
					streetNo, email, telefone, dateBirthdate);
		} catch (InvalidParameterException e) {
			// Fehlercode 002
			e.printStackTrace();
			throw new Exception(
					"Bei der Uebertragung der Parameter ist ein Fehler aufgetreten! Fehlercode: 002");
		}

	}

}
