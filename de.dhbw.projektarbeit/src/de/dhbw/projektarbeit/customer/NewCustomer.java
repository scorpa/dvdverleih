package de.dhbw.projektarbeit.customer;

import de.dhbw.projektarbeit.db.date.DateConverter;
import de.dhbw.projektarbeit.db.mysql.MysqlAccess;
import de.dhbw.projektarbeit.db.request.Insert;

/**
 * Klasse erstellt beim Klick in der GUI einen Customereintrag in der DB. Über
 * die Methode... kann ein neuer Kundeneintrag in die DB gemacht werden.
 */

public class NewCustomer {

	private MysqlAccess access;
	private DateConverter dateConv;
	private java.util.Date birthdate;
	private Insert insert;
	

	public void CreateNewCustomer(String firstName, String lastName,
			int zip_code, String city, String street, String streetNo,
			String email, String telefone, String birthdate) throws Exception {
	
	this.birthdate = dateConv.StringToDate(birthdate);
	
	insert.insertCustomer(firstName, lastName, zip_code, city, street, streetNo, email, telefone, birthdate);
	
	
	

	}

}
