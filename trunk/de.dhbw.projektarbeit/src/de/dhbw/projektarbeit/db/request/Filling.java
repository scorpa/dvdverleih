package de.dhbw.projektarbeit.db.request;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import de.dhbw.projektarbeit.db.mysql.MysqlAccess;

public class Filling {

	/**
	 * Klasse wird benutzt, um Comboboxen zu füllen
	 */
	private String schema = "dvd_verleih";
	private Connection con;
	private Statement stmt;
	private ResultSet rset;
	private ResultSetMetaData rsmd;
	private Object[][] listing, dvdData;;
	private MysqlAccess mysql;

	/**
	 * Stellt die Verbindung mit dem SQL Server über die Methode "getConnection"
	 * her
	 * 
	 * @throws Exception
	 *             --> Exceptionhandling
	 */
	private void getConnection() throws Exception {
		try {
			mysql = new MysqlAccess();
			con = mysql.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Vector<String> fillCbRegisseur() throws Exception {

		try {
			getConnection();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {

			// Erstelle Statement und Stringbuffer
			stmt = con.createStatement();
			StringBuffer fillCbRegisseur = new StringBuffer();

			/*
			 * Befüllen der Combobox Regisseur mit den Werten aus der DB
			 * "Select * from dvd_verleih.regisseur"
			 */
			fillCbRegisseur.append("Select * from ");
			fillCbRegisseur.append(schema);
			fillCbRegisseur.append(".regisseur");

			// Abfrage ausführen
			rset = stmt.executeQuery(fillCbRegisseur.toString());

			// Vektor für Abfragestrings erstellen
			Vector<String> results = new Vector();

			// Ergebnisse speichern
			while (rset.next()) {
				results.add(rset.getString("FirstName").concat(
						" " + rset.getString("LastName")));
			}

			return results;

		} catch (SQLException e) {
			// Im Falle eines Fehlers Rollback durchführen und Fehlermeldung
			// schreiben
			// Fehlercode 004
			e.printStackTrace();
			throw new Exception(
					"Fehler bei der Datenbankabfrage! Fehlercode 006");
		}

	}

	public Vector<String> fillCbProduction() throws Exception {

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

		try {

			// Erstelle Statement und Stringbuffer
			stmt = con.createStatement();
			StringBuffer fillCbProduction = new StringBuffer();

			/*
			 * Befüllen der Combobox Regisseur mit den Werten aus der DB
			 * "Select * from dvd_verleih.regisseur"
			 */
			fillCbProduction.append("Select * from ");
			fillCbProduction.append(schema);
			fillCbProduction.append(".production");

			// Abfrage ausführen
			rset = stmt.executeQuery(fillCbProduction.toString());

			// Vektor für Abfragestrings erstellen
			Vector<String> results = new Vector();

			// Ergebnisse speichern
			while (rset.next()) {
				results.add(rset.getString("FirstName").concat(
						" " + rset.getString("LastName")));
			}

			return results;

		} catch (SQLException e) {
			// Im Falle eines Fehlers Rollback durchführen und Fehlermeldung
			// schreiben
			// Fehlercode 004
			e.printStackTrace();
			throw new Exception(
					"Fehler bei der Datenbankabfrage! Fehlercode 006");
		}
	}

	public Vector<String> fillCbAuthor() throws Exception {

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

		try {

			// Erstelle Statement und Stringbuffer
			stmt = con.createStatement();
			StringBuffer fillCbAuthor = new StringBuffer();

			/*
			 * Befüllen der Combobox Regisseur mit den Werten aus der DB
			 * "Select * from dvd_verleih.regisseur"
			 */
			fillCbAuthor.append("Select * from ");
			fillCbAuthor.append(schema);
			fillCbAuthor.append(".author");

			// Abfrage ausführen
			rset = stmt.executeQuery(fillCbAuthor.toString());

			// Vektor für Abfragestrings erstellen
			Vector<String> results = new Vector();

			// Ergebnisse speichern
			while (rset.next()) {
				results.add(rset.getString("FirstName").concat(
						" " + rset.getString("LastName")));
			}

			return results;

		} catch (SQLException e) {
			// Im Falle eines Fehlers Rollback durchführen und Fehlermeldung
			// schreiben
			// Fehlercode 004
			e.printStackTrace();
			throw new Exception(
					"Fehler bei der Datenbankabfrage! Fehlercode 006");
		}
	}

	public Vector<String> fillCbCamera() throws Exception {

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

		try {

			// Erstelle Statement und Stringbuffer
			stmt = con.createStatement();
			StringBuffer fillCbCamera = new StringBuffer();

			/*
			 * Befüllen der Combobox Regisseur mit den Werten aus der DB
			 * "Select * from dvd_verleih.regisseur"
			 */
			fillCbCamera.append("Select * from ");
			fillCbCamera.append(schema);
			fillCbCamera.append(".camera");

			// Abfrage ausführen
			rset = stmt.executeQuery(fillCbCamera.toString());

			// Vektor für Abfragestrings erstellen
			Vector<String> results = new Vector();

			// Ergebnisse speichern
			while (rset.next()) {
				results.add(rset.getString("FirstName").concat(
						" " + rset.getString("LastName")));
			}

			return results;

		} catch (SQLException e) {
			// Im Falle eines Fehlers Rollback durchführen und Fehlermeldung
			// schreiben
			// Fehlercode 004
			e.printStackTrace();
			throw new Exception(
					"Fehler bei der Datenbankabfrage! Fehlercode 006");
		}
	}

	/**
	 * Methode zur Listenfüllung
	 * 
	 * @return --> Returnwert in Form eines Objektarrays für die Tabellensicht
	 * @tabelle --> Tabelle, die gefüllt werden soll
	 * @throws Exception
	 *             --> Exceptionhandling
	 */
	public Object[][] getTable(String tabelle) throws Exception {

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

		try {

			// Erstelle Statement und Stringbuffer
			stmt = con.createStatement();
			StringBuffer getDVD = new StringBuffer();

			/*
			 * Abruf der Tabellen Daten aus der SQL DB
			 * "SELECT * FROM dvd_verleih.<tabelle>"
			 */
			getDVD.append("SELECT * FROM ");
			getDVD.append(schema);
			getDVD.append(".");
			getDVD.append(tabelle);

			// Abfrage ausführen
			rset = stmt.executeQuery(getDVD.toString());

			// MetaDaten laden
			rsmd = rset.getMetaData();

			// ArrayList of Object-Arrays fuer die Abfrageergebnisse erstellen
			ArrayList<Object[]> results = new ArrayList<Object[]>();

			// Abfrageergebnisse in die ArrayList schreiben
			while (rset.next()) {
				// Zuerst einzelne Spalten in ein Object-Array schreiben
				Object[] row = new Object[rsmd.getColumnCount()];
				for (int i = 0; i < rsmd.getColumnCount(); i++)
					row[i] = rset.getObject(i + 1);

				// Object-Array an results ArrayList anhaengen
				results.add(row);
			}

			// results-ArrayList in neues zweidimensionales Object-Array
			// uebertragen, ueber das die Abfrageergebnisse zurueckgeliefert
			// werden
			listing = new Object[results.size()][rsmd.getColumnCount()];
			for (int i = 0; i < results.size(); i++)
				listing[i] = results.get(i);

			return listing;
		} catch (SQLException e) {
			// Im Falle eines Fehlers Rollback durchführen und Fehlermeldung
			// schreiben
			// Fehlercode 004
			e.printStackTrace();
			throw new Exception(
					"Fehler bei der Datenbankabfrage! Fehlercode 006");
		}

	}

	/**
	 * Methode zum Ersetzen der Fremdschlüssel in der DVD Tabelle zur
	 * Darstellung in einer JTable
	 * 
	 * @param dvdData
	 *            --> Objektarray[Zeile][Spalte]; ursprüngliche DVD Tabelle mit
	 *            allen Einträgen aus der SQL DB
	 * @return --> Objektarray[Zeile][Spalte]; Fremdschlüssel ersetzt durch
	 *         Einträge in den entsprechenden Tabellen
	 */
	public Object[][] getNameFromID(Object[][] dvdData) {
		// Variablendeklaration
		Object[][] ID, IDName;
		int k = 9;
		ID = new Object[4][dvdData.length];
		IDName = new Object[4][dvdData.length];

		/*
		 * ID Objektarray wird mit den Einträgen für Regisseur(dvdData[j][9]),
		 * Autor(dvdData[j][10], Produzent (dvdData[j][11]) und
		 * Kamera(dvdData[j][12]) befüllt i = Rubrikvariable (Regisseur=0,
		 * Autor=1, Produzent=2, Kamera=3) j = Zeilenzähler in der Spalte i k =
		 * Spaltenposition in der JTable dvdData
		 */
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < dvdData.length; j++) {
				ID[i][j] = dvdData[j][k];
			}
			k++;
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < dvdData.length; j++) {
				try {
					// Je nach Rubrik i, Aufruf der Methode getNamefromID mit
					// anderen Übergabeparametern
					switch (i) {
					case 0:
						IDName[i][j] = getNamefromID(i, j, "regisseur",
								"Regie_ID", ID);
						break;
					case 1:
						IDName[i][j] = getNamefromID(i, j, "author",
								"Author_ID", ID);
						break;
					case 2:
						IDName[i][j] = getNamefromID(i, j, "production",
								"Production_ID", ID);
						break;
					case 3:
						IDName[i][j] = getNamefromID(i, j, "camera",
								"Camera_ID", ID);
						break;
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		// Rückschreiben der Ergebnisse für die ausgelesenen Fremdschlüssel
		k = 9;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < dvdData.length; j++) {
				dvdData[j][k] = IDName[i][j];
			}
			k++;
		}
		// Rückgabe der decodierten dvdData
		return dvdData;
	}

	/**
	 * Methode zum Auslesen der Daten zu den jeweiligen Fremdschlüsseln
	 * 
	 * @param i
	 *            -->Rubrikvariable (Regisseur=0, Autor=1, Produzent=2,
	 *            Kamera=3)
	 * @param j
	 *            --> Zeilenzähler in der Spalte i
	 * @param table
	 *            --> Tabelle, in der gesucht werden soll
	 * @param fieldName
	 *            --> Feldbezeichnugn der ID
	 * @param IDArray
	 *            --> IDArray[Rubrikvariable][Zeilenzähler]Arrays mit den IDs
	 * @return --> Objekt; Rückgabe des Zeilenwerts des, zur ID gehörenden, Tabelleneintags
	 * @throws Exception
	 *             --> Exceptionhandling
	 */
	public Object getNamefromID(int i, int j, String table, String fieldName,
			Object[][] IDArray) throws Exception {

		// Objekt für Ergebnisstrings erstellen
		Object results = null;
		
		// Verbindung zur SQL DB herstellen
		try {
			getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			// Erstelle Statement 
			stmt = con.createStatement();

			/*
			 * Befüllen des Vektors mit dem Namen der abgefragten ID
			 * "SELECT * FROM dvd_verleih.table WHERE fieldName(z.B. Regie_ID) = IDArray[i][j]"
			 */
			// Erstellen des Stringbuffers
			StringBuffer getNamefromID = new StringBuffer();
			getNamefromID.append("SELECT * FROM ");
			getNamefromID.append("dvd_verleih.");
			getNamefromID.append(table);
			getNamefromID.append(" WHERE ");
			getNamefromID.append(fieldName);
			getNamefromID.append(" = \"");
			getNamefromID.append(IDArray[i][j]);
			getNamefromID.append("\"");

			// Abfrage ausführen
			rset = stmt.executeQuery(getNamefromID.toString());

			// Ergebnisse speichern
			while (rset.next()) {
				results = rset.getString("FirstName").concat(
						" " + rset.getString("LastName"));
			}

		} catch (SQLException e) {
			// Im Falle eines Fehlers Rollback durchführen und Fehlermeldung
			// schreiben
			// Fehlercode 006
			e.printStackTrace();
			throw new Exception(
					"Fehler bei der Datenbankabfrage! Fehlercode 006");
		}
		// Rückgabe des Tablleneintrags für die ID
		return results;

	}

}
