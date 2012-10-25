package de.dhbw.projektarbeit.db.request;

import java.security.InvalidParameterException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import de.dhbw.projektarbeit.gui.Dialogs.NewAuthor;
import de.dhbw.projektarbeit.gui.Dialogs.NewCamera;
import de.dhbw.projektarbeit.gui.Dialogs.NewCustomer;
import de.dhbw.projektarbeit.gui.Dialogs.NewDVD;
import de.dhbw.projektarbeit.gui.Dialogs.NewProducer;
import de.dhbw.projektarbeit.gui.Dialogs.NewRegisseur;

/**
 * Mit Hilfe dieser Klasse lassen sich neu erfasst Buchungen in die DB
 * einfuegen.
 * 
 * @author Julian
 */

/**
 * @author Juli
 *
 */
/**
 * @author Juli
 * 
 */
public class Insert {

	private String schema;
	private Connection con;
	private Statement stmt;
	private ResultSet rset;

	/**
	 * Constructor, initialisiert das Objekt, es wird ein DB-Schema und ein
	 * Connection Objekt uebergeben.
	 * 
	 * @param schema
	 *            Das DB-Schema, in dem sich die abzufragenden Tabellen
	 *            befinden.
	 * @param con
	 *            Ein Connection Objekt, hierueber werden die DB-Abfragen
	 *            durchgefuehrt.
	 */

	public Insert(String schema, Connection con) {
		this.schema = schema;
		this.con = con;
	}

	/**
	 * Fügt einen neuen Datensatz in die DB ein.
	 * 
	 * @param nc
	 *            Fenstereigenschafen
	 * @param vorname
	 *            Vorname des neuen Kunden
	 * @param nachname
	 *            Nachname des neuen Kunden
	 * @param strasse
	 *            Straße des neuen Kunden
	 * @param hausnummer
	 *            Hausnummer des neuen Kunden
	 * @param plz
	 *            PLZ des neuen Kunden
	 * @param ort
	 *            Ort des neuen Kunden
	 * @param email
	 *            Email des neuen Kunden
	 * @param telefon
	 *            Telefonnummer des neuen Kunden
	 * @param bday
	 *            Geburtstag des neuen Kunden
	 * @throws Exception
	 *             Fehlerhandling
	 */

	public void insertCustomer(NewCustomer nc, String firstName,
			String lastName, int zip_code, String city, String street,
			String streetNo, String email, String telefone,
			java.sql.Date birthdate) throws Exception {

		try {
			// Alle Einfuege-Operationen sollen als eine Transaktion und mittels
			// Stringbuffer ausgefuehrt
			// werden.
			con.setAutoCommit(false);

			stmt = con.createStatement();

			StringBuffer customer = new StringBuffer();

			/**
			 * Hinzufügen der neuen erfassten Kundendaten für die Tabelle
			 * "customer" "Insert into dvd_verleih.customer (FirstName,
			 * Lastname, ZIP_Code, City, Street, StreetNo, Email, Telefone,
			 * Birthdate) VALUES
			 * (firstName,lastName,zip_code,city,street,streetNo
			 * ,email,telefone,birthdate)"
			 */

			customer.append("INSERT INTO ");
			customer.append(schema);
			customer.append(".customer (FirstName, LastName, ZIP_Code, City, Street, StreetNo, Email, Telefone, Birthdate) "
					+ "VALUES (\""
					+ firstName
					+ "\",\""
					+ lastName
					+ "\",\""
					+ zip_code
					+ "\",\""
					+ city
					+ "\",\""
					+ street
					+ "\",\""
					+ streetNo
					+ "\",\""
					+ email
					+ "\",\""
					+ telefone
					+ "\",\""
					+ birthdate + "\")");

			stmt.executeUpdate(customer.toString());

			// Neuerfasste Daten auf DB schreiben
			con.commit();
			con.setAutoCommit(true);

		} catch (SQLException e) {
			// Im Falle eines Fehlers Rollback durchführen und Fehlermeldung
			// schreiben
			// Fehlercode 004
			con.rollback();
			con.setAutoCommit(true);
			e.printStackTrace();
			throw new Exception(
					"Fehler beim Einfügen in die Datenbank! Fehlercode 004");

		}

		// Aufruf bei erfolgreicher Kundenneuanlage

		try {
			nc.customerAdded(firstName, lastName);
		} catch (InvalidParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception(
					"Ein Fehler beim Abschluss der Kundenneuerstellung ist aufgetreten! Fehlercode: 001");
		}

	}

	/**
	 * Methode zum Erstellen eines DB Eintrages für einen neuen Regisseur
	 * 
	 * @param nr
	 *            --> Klasseninformationen NewRegisseur
	 * 
	 * @param firstName
	 *            --> Vorname Regisseur
	 * @param lastName
	 *            --> Nachname Regisseur
	 * @throws Exception
	 *             --> Exceptionhandling auf der SQL DB
	 */
	public void insertRegisseur(NewRegisseur nr, String firstName,
			String lastName) throws Exception {
		try {

			// Alle Einfuege-Operationen sollen als eine Transaktion und mittels
			// Stringbuffer ausgefuehrt
			// werden.
			con.setAutoCommit(false);

			stmt = con.createStatement();

			StringBuffer regisseur = new StringBuffer();

			/*
			 * Hinzufügen eines neuen Regisseurs "Insert into
			 * dvd_verleih.regisseur (FirstName, LastName) VALUES (firstName,
			 * lastName)"
			 */
			regisseur.append("INSERT INTO ");
			regisseur.append(schema);
			regisseur.append(".regisseur (FirstName, LastName) " + "VALUES (\""
					+ firstName + "\",\"" + lastName + "\")");

			stmt.executeUpdate(regisseur.toString());

			// Neuerfasste Daten auf DB schreiben
			con.commit();
			con.setAutoCommit(true);

		} catch (SQLException e) {
			// Im Falle eines Fehlers Rollback durchführen und Fehlermeldung
			// schreiben
			// Fehlercode 004
			con.rollback();
			con.setAutoCommit(true);
			e.printStackTrace();
			throw new Exception(
					"Fehler beim Einfügen in die Datenbank! Fehlercode 004");
		}
		// Aufruf bei erfolgreicher Regisseuranlage
		// Rückmeldung an Regisseurerstellung
		try {
			nr.regisseurAdded(firstName, lastName);

		} catch (InvalidParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Methode zum Erstellen eines DB Eintrages für einen neuen Regisseur vom
	 * Fenster newDVD aus
	 * 
	 * @param newDVD
	 *            --> Klasseninformationen newDVD
	 * 
	 * @param nr
	 *            --> Klasseninformationen NewRegisseur
	 * 
	 * @param firstName
	 *            --> Vorname Regisseur
	 * @param lastName
	 *            --> Nachname Regisseur
	 * @throws Exception
	 *             --> Exceptionhandling auf der SQL DB
	 */
	public void insertRegisseur(NewDVD newDVD, NewRegisseur nr,
			String firstName, String lastName) throws Exception {
		try {

			// Alle Einfuege-Operationen sollen als eine Transaktion und mittels
			// Stringbuffer ausgefuehrt
			// werden.
			con.setAutoCommit(false);

			stmt = con.createStatement();

			StringBuffer regisseur = new StringBuffer();

			/*
			 * Hinzufügen eines neuen Regisseurs "Insert into
			 * dvd_verleih.regisseur (FirstName, LastName) VALUES (firstName,
			 * lastName)"
			 */
			regisseur.append("INSERT INTO ");
			regisseur.append(schema);
			regisseur.append(".regisseur (FirstName, LastName) " + "VALUES (\""
					+ firstName + "\",\"" + lastName + "\")");

			stmt.executeUpdate(regisseur.toString());

			// Neuerfasste Daten auf DB schreiben
			con.commit();
			con.setAutoCommit(true);

		} catch (SQLException e) {
			// Im Falle eines Fehlers Rollback durchführen und Fehlermeldung
			// schreiben
			// Fehlercode 004
			con.rollback();
			con.setAutoCommit(true);
			e.printStackTrace();
			throw new Exception(
					"Fehler beim Einfügen in die Datenbank! Fehlercode 004");
		}
		// Aufruf bei erfolgreicher Regisseuranlage
		// Update der Regisseurcombobox in der DVD Erstellung durchführen
		// Rückmeldung an Regisseurerstellung
		try {
			newDVD.updateComboboxRegisseur(firstName.concat(" " + lastName));
			nr.regisseurAdded(firstName, lastName);

		} catch (InvalidParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Methode zum Erstellen eines DB Eintrages für einen neuen Produzenten
	 * 
	 * @parm np --> Klasseninformationen aus NewProducer
	 * 
	 * @param firstName
	 *            --> Vorname Produzent
	 * @param lastName
	 *            --> Nachname Produzent
	 * @throws Exception
	 *             --> Exceptionhandling auf der SQL DB
	 */
	public void insertProducer(NewProducer np, String firstName, String lastName)
			throws Exception {
		try {

			// Alle Einfuege-Operationen sollen als eine Transaktion und mittels
			// Stringbuffer ausgefuehrt
			// werden.
			con.setAutoCommit(false);

			stmt = con.createStatement();

			StringBuffer produzent = new StringBuffer();

			/*
			 * Hinzufügen eines neuen Regisseurs "Insert into
			 * dvd_verleih.regisseur (FirstName, LastName) VALUES (firstName,
			 * lastName)"
			 */
			produzent.append("INSERT INTO ");
			produzent.append(schema);
			produzent.append(".production (FirstName, LastName) "
					+ "VALUES (\"" + firstName + "\",\"" + lastName + "\")");

			stmt.executeUpdate(produzent.toString());

			// Neuerfasste Daten auf DB schreiben
			con.commit();
			con.setAutoCommit(true);

		} catch (SQLException e) {
			// Im Falle eines Fehlers Rollback durchführen und Fehlermeldung
			// schreiben
			// Fehlercode 004
			con.rollback();
			con.setAutoCommit(true);
			e.printStackTrace();
			throw new Exception(
					"Fehler beim Einfügen in die Datenbank! Fehlercode 004");
		}
		// Aufruf bei erfolgreicher Regisseuranlage
		// Rückmeldung an Regisseurerstellung
		try {
			// dvd.updateComboboxRegisseur(firstName.concat(" " + lastName));
			np.productionAdded(firstName, lastName);

		} catch (InvalidParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Methode zum Erstellen eines DB Eintrages für einen neuen Produzenten
	 * 
	 * @parm newDVD --> Klasseninformationen aus dem Dialog NewDVD
	 * 
	 * @parm np --> Klasseninformationen aus NewProducer
	 * 
	 * @param firstName
	 *            --> Vorname Produzent
	 * @param lastName
	 *            --> Nachname Produzent
	 * @throws Exception
	 *             --> Exceptionhandling auf der SQL DB
	 */
	public void insertProducer(NewDVD newDVD, NewProducer np, String firstName,
			String lastName) throws Exception {
		try {

			// Alle Einfuege-Operationen sollen als eine Transaktion und mittels
			// Stringbuffer ausgefuehrt
			// werden.
			con.setAutoCommit(false);

			stmt = con.createStatement();

			StringBuffer produzent = new StringBuffer();

			/*
			 * Hinzufügen eines neuen Regisseurs "Insert into
			 * dvd_verleih.regisseur (FirstName, LastName) VALUES (firstName,
			 * lastName)"
			 */
			produzent.append("INSERT INTO ");
			produzent.append(schema);
			produzent.append(".production (FirstName, LastName) "
					+ "VALUES (\"" + firstName + "\",\"" + lastName + "\")");

			stmt.executeUpdate(produzent.toString());

			// Neuerfasste Daten auf DB schreiben
			con.commit();
			con.setAutoCommit(true);

		} catch (SQLException e) {
			// Im Falle eines Fehlers Rollback durchführen und Fehlermeldung
			// schreiben
			// Fehlercode 004
			con.rollback();
			con.setAutoCommit(true);
			e.printStackTrace();
			throw new Exception(
					"Fehler beim Einfügen in die Datenbank! Fehlercode 004");
		}
		// Aufruf bei erfolgreicher Regisseuranlage
		// Update der Regisseurcombobox in der DVD Erstellung durchführen
		// Rückmeldung an Regisseurerstellung
		try {
			newDVD.updateComboboxProduction(firstName.concat(" " + lastName));
			np.productionAdded(firstName, lastName);

		} catch (InvalidParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Methode zum Erstellen eines DB Eintrages für einen neuen Kameramann
	 * 
	 * @param nc
	 *            --> Klasseninformationen NewCamera
	 * 
	 * @param firstName
	 *            --> Vorname Kameramann
	 * @param lastName
	 *            --> Nachname Kameramann
	 * @throws Exception
	 *             --> Exceptionhandling auf der SQL DB
	 */
	public void insertCamera(NewCamera nc, String firstName, String lastName)
			throws Exception {
		try {

			// Alle Einfuege-Operationen sollen als eine Transaktion und mittels
			// Stringbuffer ausgefuehrt
			// werden.
			con.setAutoCommit(false);

			stmt = con.createStatement();

			StringBuffer camera = new StringBuffer();

			/*
			 * Hinzufügen eines neuen Regisseurs "Insert into
			 * dvd_verleih.regisseur (FirstName, LastName) VALUES (firstName,
			 * lastName)"
			 */
			camera.append("INSERT INTO ");
			camera.append(schema);
			camera.append(".camera (FirstName, LastName) " + "VALUES (\""
					+ firstName + "\",\"" + lastName + "\")");

			stmt.executeUpdate(camera.toString());

			// Neuerfasste Daten auf DB schreiben
			con.commit();
			con.setAutoCommit(true);

		} catch (SQLException e) {
			// Im Falle eines Fehlers Rollback durchführen und Fehlermeldung
			// schreiben
			// Fehlercode 004
			con.rollback();
			con.setAutoCommit(true);
			e.printStackTrace();
			throw new Exception(
					"Fehler beim Einfügen in die Datenbank! Fehlercode 004");
		}
		// Aufruf bei erfolgreicher Regisseuranlage
		// Rückmeldung an Regisseurerstellung
		try {
			nc.cameraAdded(firstName, lastName);

		} catch (InvalidParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Methode zum Erstellen eines DB Eintrages für einen neuen Kameramann vom
	 * Fenster newDVD aus
	 * 
	 * @param newDVD
	 *            --> Klasseninformationen newDVD
	 * 
	 * @param nr
	 *            --> Klasseninformationen NewCamera
	 * 
	 * @param firstName
	 *            --> Vorname Kameramann
	 * @param lastName
	 *            --> Nachname Kameramann
	 * @throws Exception
	 *             --> Exceptionhandling auf der SQL DB
	 */
	public void insertCamera(NewDVD newDVD, NewCamera nc, String firstName,
			String lastName) throws Exception {
		try {

			// Alle Einfuege-Operationen sollen als eine Transaktion und mittels
			// Stringbuffer ausgefuehrt
			// werden.
			con.setAutoCommit(false);

			stmt = con.createStatement();

			StringBuffer camera = new StringBuffer();

			/*
			 * Hinzufügen eines neuen Regisseurs "Insert into
			 * dvd_verleih.regisseur (FirstName, LastName) VALUES (firstName,
			 * lastName)"
			 */
			camera.append("INSERT INTO ");
			camera.append(schema);
			camera.append(".camera (FirstName, LastName) " + "VALUES (\""
					+ firstName + "\",\"" + lastName + "\")");

			stmt.executeUpdate(camera.toString());

			// Neuerfasste Daten auf DB schreiben
			con.commit();
			con.setAutoCommit(true);

		} catch (SQLException e) {
			// Im Falle eines Fehlers Rollback durchführen und Fehlermeldung
			// schreiben
			// Fehlercode 004
			con.rollback();
			con.setAutoCommit(true);
			e.printStackTrace();
			throw new Exception(
					"Fehler beim Einfügen in die Datenbank! Fehlercode 004");
		}
		// Aufruf bei erfolgreicher Regisseuranlage
		// Update der Regisseurcombobox in der DVD Erstellung durchführen
		// Rückmeldung an Regisseurerstellung
		try {
			newDVD.updateComboboxCamera(firstName.concat(" " + lastName));
			nc.cameraAdded(firstName, lastName);

		} catch (InvalidParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Methode zum Erstellen eines DB Eintrages für einen neuen Produzenten
	 * 
	 * @parm np --> Klasseninformationen aus NewProducer
	 * 
	 * @param firstName
	 *            --> Vorname Produzent
	 * @param lastName
	 *            --> Nachname Produzent
	 * @throws Exception
	 *             --> Exceptionhandling auf der SQL DB
	 */
	public void insertAuthor(NewAuthor na, String firstName, String lastName)
			throws Exception {
		try {

			// Alle Einfuege-Operationen sollen als eine Transaktion und mittels
			// Stringbuffer ausgefuehrt
			// werden.
			con.setAutoCommit(false);

			stmt = con.createStatement();

			StringBuffer author = new StringBuffer();

			/*
			 * Hinzufügen eines neuen Regisseurs "Insert into
			 * dvd_verleih.regisseur (FirstName, LastName) VALUES (firstName,
			 * lastName)"
			 */
			author.append("INSERT INTO ");
			author.append(schema);
			author.append(".author (FirstName, LastName) " + "VALUES (\""
					+ firstName + "\",\"" + lastName + "\")");

			stmt.executeUpdate(author.toString());

			// Neuerfasste Daten auf DB schreiben
			con.commit();
			con.setAutoCommit(true);

		} catch (SQLException e) {
			// Im Falle eines Fehlers Rollback durchführen und Fehlermeldung
			// schreiben
			// Fehlercode 004
			con.rollback();
			con.setAutoCommit(true);
			e.printStackTrace();
			throw new Exception(
					"Fehler beim Einfügen in die Datenbank! Fehlercode 004");
		}
		// Aufruf bei erfolgreicher Regisseuranlage
		// Rückmeldung an Regisseurerstellung
		try {
			// dvd.updateComboboxRegisseur(firstName.concat(" " + lastName));
			na.authorAdded(firstName, lastName);

		} catch (InvalidParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Methode zum Erstellen eines DB Eintrages für eines neuen Autors
	 * 
	 * @parm newDVD --> Klasseninformationen aus dem Dialog NewDVD
	 * 
	 * @parm np --> Klasseninformationen aus NewAuthor
	 * 
	 * @param firstName
	 *            --> Vorname Autor
	 * @param lastName
	 *            --> Nachname Autor
	 * @throws Exception
	 *             --> Exceptionhandling auf der SQL DB
	 */
	public void insertAuthor(NewDVD newDVD, NewAuthor na, String firstName,
			String lastName) throws Exception {
		try {

			// Alle Einfuege-Operationen sollen als eine Transaktion und mittels
			// Stringbuffer ausgefuehrt
			// werden.
			con.setAutoCommit(false);

			stmt = con.createStatement();

			StringBuffer author = new StringBuffer();

			/*
			 * Hinzufügen eines neuen Regisseurs "Insert into
			 * dvd_verleih.regisseur (FirstName, LastName) VALUES (firstName,
			 * lastName)"
			 */
			author.append("INSERT INTO ");
			author.append(schema);
			author.append(".author (FirstName, LastName) " + "VALUES (\""
					+ firstName + "\",\"" + lastName + "\")");

			stmt.executeUpdate(author.toString());

			// Neuerfasste Daten auf DB schreiben
			con.commit();
			con.setAutoCommit(true);

		} catch (SQLException e) {
			// Im Falle eines Fehlers Rollback durchführen und Fehlermeldung
			// schreiben
			// Fehlercode 004
			con.rollback();
			con.setAutoCommit(true);
			e.printStackTrace();
			throw new Exception(
					"Fehler beim Einfügen in die Datenbank! Fehlercode 004");
		}
		// Aufruf bei erfolgreicher Regisseuranlage
		// Update der Regisseurcombobox in der DVD Erstellung durchführen
		// Rückmeldung an Regisseurerstellung
		try {
			newDVD.updateComboboxAuthor(firstName.concat(" " + lastName));
			na.authorAdded(firstName, lastName);

		} catch (InvalidParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Methode zum Anlegen einer neuen DVD auf der SQL DB
	 * 
	 * @param dvd
	 *            --> Klasseneigenschaften
	 * @param eanCode
	 *            --> EAN Code der DVd
	 * @param regisseur
	 *            --> Name des Regisseurs
	 * @param production
	 *            --> Name des Produzenten
	 * @param camera
	 *            --> Name des Kamermamanns
	 * @param author
	 *            --> Name des Autors
	 * @param title
	 *            --> Titel der DVD
	 * @param genre
	 *            --> Genre der DVD
	 * @param fsk
	 *            --> FSK Kennzeichnung
	 * @param prod_year
	 *            --> Produktionsjahr
	 * @param release
	 *            --> Erscheinungsjahr
	 * @param duration
	 *            --> Dauer der DVD in Minuten
	 * @param prodCountry
	 *            --> Produktionsland
	 * @param originalTitle
	 *            --> Originaltitel
	 * @param quantity
	 *            --> Menge der DVD
	 * @throws Exception
	 *             --> Exceptionhandling
	 */
	public void insertDVD(NewDVD dvd, int quantity, String title,
			String originalTitle, String genre, String prodCountry,
			int prod_year, java.sql.Date release, int duration, String fsk,
			String regisseur, String author, String production, String camera,
			String eanCode) throws Exception {

		try {
			// Alle Einfuege-Operationen sollen als eine Transaktion und mittels
			// Stringbuffer ausgefuehrt
			// werden.
			con.setAutoCommit(false);
			stmt = con.createStatement();
			StringBuffer bufferDVD = new StringBuffer();

			// IDs der Fremdschlüssel abfragen
			int regisseurID = getID(schema,"regisseur","Regie_ID", regisseur);
			int productionID = getID(schema,"production", "Production_ID", production);
			int cameraID = getID(schema,"camera", "Camera_ID", camera);
			int authorID = getID(schema,"author", "Author_ID", author);

			/*
			 * Hinzufügen der neu erfassten DVD für die Tabelle "dvd" "Insert
			 * into dvd_verleih.dvd (Quantity, Title, Original_Title, Genre,
			 * Prod_Country, Prod_Year, Rel_Date, Duration, FSK, String
			 * Regie_ID, Author_ID, Production_ID, Camera_ID, Barcode) VALUES
			 * (eanCode, regisseurID, productionID, cameraID, authorID, title,
			 * genre, fsk, prod_year, release, duration, prodCountry,
			 * originalTitle, quantity)"
			 */

			bufferDVD.append("INSERT INTO ");
			bufferDVD.append(schema);
			bufferDVD
					.append(".dvd (Quantity, Title, Original_Title, Genre, Prod_Country, Prod_Year, Rel_Date, Duration, FSK, Regie_ID, Author_ID, Production_ID, "
							+ "Camera_ID, Barcode) " + "VALUES (\""
							+ quantity
							+ "\",\""
							+ title
							+ "\",\""
							+ originalTitle
							+ "\",\""
							+ genre
							+ "\",\""
							+ prodCountry
							+ "\",\""
							+ prod_year
							+ "\",\""
							+ release
							+ "\",\""
							+ duration
							+ "\",\""
							+ fsk
							+ "\",\""
							+ regisseurID
							+ "\",\""
							+ authorID
							+ "\",\""
							+ productionID
							+ "\",\""
							+ cameraID
							+ "\",\""
							+ eanCode
							+ "\")");

			stmt.executeUpdate(bufferDVD.toString());

			// Neuerfasste Daten auf DB schreiben
			con.commit();
			con.setAutoCommit(true);

		} catch (SQLException e) {
			// Im Falle eines Fehlers Rollback durchführen und Fehlermeldung
			// schreiben
			// Fehlercode 004
			con.rollback();
			con.setAutoCommit(true);
			e.printStackTrace();
			throw new Exception(
					"Fehler beim Einfügen in die Datenbank! Fehlercode 004");

		}

		// Aufruf bei erfolgreicher Kundenneuanlage

		try {
			dvd.dvdAdded(title);
		} catch (InvalidParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception(
					"Ein Fehler beim Abschluss der Kundenneuerstellung ist aufgetreten! Fehlercode: 001");
		}

	}

	/**
	 * Get Methode zum finden der IDs für Camera, Author, Production, Regisseur
	 * 
	 * @param table
	 *            --> Tabelle, die in der SQL DB durchsucht werden soll
	 * @param searchContent
	 *            --> Konkadinierter String des entsprechenden Namen
	 * @return --> ID des entsprechenden Eintrags
	 * @throws SQLException
	 *             --> Exceptionhandling
	 */
	private int getID(String schema, String table, String field, String searchContent)
			throws SQLException {
		String searchString, firstName = null, lastName = null, FieldID = null;
		searchString = searchContent;
		int ID = 0;

		// Trennen des Vornamen und Nachnamen
		if(searchString.contains(" ")){
		firstName = searchString.substring(0, searchString.indexOf(" "));
		lastName = searchString.substring(searchString.indexOf(" ")+1);
		lastName = lastName.replaceAll(" ","");}

	/*	// ID Begriff erstellen
		FieldID = table.substring(0, 1).toUpperCase() + table.substring(1);
		FieldID.concat("_ID");*/

		// Alle Einfuege-Operationen sollen als eine Transaktion und mittels
		// Stringbuffer ausgefuehrt
		// werden.
		con.setAutoCommit(false);

		stmt = con.createStatement();

		StringBuffer searchID = new StringBuffer();

		searchID.append("SELECT ");
		searchID.append(field);
		searchID.append(" FROM ");
		searchID.append(schema + "." + table);
		searchID.append(" WHERE FirstName = \"");
		searchID.append(firstName + "\" AND LastName = \"");
		searchID.append(lastName);
		searchID.append("\"");

		// Abfrage ausführen
		rset = stmt.executeQuery(searchID.toString());

		// Returnwert auffangen
		while(rset.next()){
		ID = Integer.valueOf(rset.getString(field));
		}
		return ID;
	}
}
