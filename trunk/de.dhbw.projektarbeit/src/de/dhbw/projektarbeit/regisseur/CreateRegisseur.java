package de.dhbw.projektarbeit.regisseur;

import java.security.InvalidParameterException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import de.dhbw.projektarbeit.db.mysql.MysqlAccess;
import de.dhbw.projektarbeit.db.request.Insert;
import de.dhbw.projektarbeit.gui.Dialogs.NewRegisseur;

/**
 * Klasse erstellt beim Klick auf Hinzufügen im Dialog "Neuer Regisseur" einen
 * Eintrag auf der SQL-DB
 * 
 * @author Juli
 * 
 */

public class CreateRegisseur {

	private MysqlAccess access;
	private Insert insert;
	private Connection con;
	private String firstName, lastName;
	private NewRegisseur nr;

	/**
	 * createRegiseur Methode zum Anlegen eines Regisseurs auf der SQL DB
	 * 
	 * @param firstName
	 *            --> Vorname des Regisseurs
	 * @param lastName
	 *            --> Nachname des Regisseurs
	 * @throws Exception
	 *             --> Exceptionhandling
	 */
	public void createRegiseur(NewRegisseur nr,String firstName, String lastName)
			throws Exception {
		this.nr=nr;
		this.firstName = firstName;
		this.lastName = lastName;
		// Verbindung zum SQL Server aufbauen
		try {
			con = DriverManager
					.getConnection("jdbc:mysql://localhost/dvd_verleih?user=root");
		} catch (SQLException e) {
			// Verbindung zum SQL Server fehlgeschlagen. Fehlercode 005
			e.printStackTrace();
			throw new Exception(
					"Verbindung zum SQL Server fehlgeschlagen. Fehlercode 005");
		}
		insert = new Insert("dvd_verleih",con);

		try {
			insert.insertRegisseur(this.nr,this.firstName, this.lastName);

		} catch (InvalidParameterException e) {
			// Fehlercode 002
			e.printStackTrace();
			throw new Exception(
					"Bei der Uebertragung der Parameter ist ein Fehler aufgetreten! Fehlercode: 002");
		}
	}
}
