package de.dhbw.projektarbeit.db.request;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

public class Filling {
	
	/**
	 * Klasse wird benutzt, um Comboboxen zu füllen
	 */
	private String schema;

	private Connection con;
	private Statement stmt;
	private ResultSet rset;
	
	public Vector<String> fillCbRegisseur() throws Exception{
	
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
	
	schema = "dvd_verleih";
	
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
		rset=stmt.executeQuery(fillCbRegisseur.toString());
		
		// ArrayList für Abfragestrings erstellen
		Vector<String> results = new Vector();
		
		// Ergebnisse speichern
		while (rset.next()){
			results.add(rset.getString("FirstName").concat(" " + rset.getString("LastName")));
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

	public Vector<String> fillCbProduction() throws Exception{
		
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
		
		schema = "dvd_verleih";
		
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
			rset=stmt.executeQuery(fillCbProduction.toString());
			
			// ArrayList für Abfragestrings erstellen
			Vector<String> results = new Vector();
			
			// Ergebnisse speichern
			while (rset.next()){
				results.add(rset.getString("FirstName").concat(" " + rset.getString("LastName")));
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
}
