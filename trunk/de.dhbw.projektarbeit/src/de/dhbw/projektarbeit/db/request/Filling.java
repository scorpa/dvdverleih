package de.dhbw.projektarbeit.db.request;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

public class Filling {

	/**
	 * Klasse wird benutzt, um Comboboxen zu füllen
	 */
	private String schema = "dvd_verleih";
	private Connection con;
	private Statement stmt;
	private ResultSet rset;
	private ResultSetMetaData rsmd;
	private Object[][] listing;

	public Vector<String> fillCbRegisseur() throws Exception {

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

			// ArrayList für Abfragestrings erstellen
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

			// ArrayList für Abfragestrings erstellen
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

			// ArrayList für Abfragestrings erstellen
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

			// ArrayList für Abfragestrings erstellen
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
			// uebertragen, ueber das die Abfrageergebnisse zurueckgeliefert werden
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
	
}
