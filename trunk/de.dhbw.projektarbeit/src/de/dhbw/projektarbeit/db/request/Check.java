package de.dhbw.projektarbeit.db.request;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import de.dhbw.projektarbeit.db.mysql.MysqlAccess;

/**
 * Klasse �berpr�ft das Vorhandensein von Eintr�gen
 * 
 * @author Juli
 * 
 */

public class Check {

	private String schema;
	private Connection con;
	private Statement stmt;
	private ResultSet rset;
	private MysqlAccess mysql = new MysqlAccess();

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
	public Check(String schema, Connection con) {
		this.schema = schema;
		this.con = con;
	}
	
	/**
	 * Methode zum �berpr�fen ob ein Autor, Kameramann, Regisseur oder Produzent schon in der DB vorhanden ist
	 * @param table --> Tabelle, die �berpr�ft werden soll
	 * @param fieldID --> Spaltenbezeichnung in der DB, die die ID des Regisseurs enth�lt
	 * @param firstName --> Vorname 
	 * @param lastName --> Nachname
	 * @return --> return-Wert (true = vorhanden; false = nicht vorhanden)
	 */
	public Boolean check(String table, String fieldID, String firstName,
			String lastName) {
		boolean vorhanden = false;
		String ID = "0";

		try {
			stmt = con.createStatement();

			StringBuffer checkRegisseur = new StringBuffer();

			/*
			 * Hinzuf�gen eines neuen Regisseurs "Insert into
			 * dvd_verleih.regisseur (FirstName, LastName) VALUES (firstName,
			 * lastName)"
			 */
			checkRegisseur.append("SELECT ");
			checkRegisseur.append(fieldID);
			checkRegisseur.append(" FROM ");
			checkRegisseur.append(schema);
			checkRegisseur.append("." + table);
			checkRegisseur.append(" WHERE FirstName=\"");
			checkRegisseur.append(firstName + "\"");
			checkRegisseur.append(" AND LastName=\"");
			checkRegisseur.append(lastName + "\"");

			rset = stmt.executeQuery(checkRegisseur.toString());
			int counter = 0;
			// Pr�fen, ob Abfrage ein Ergebnis ergab
			while (rset.next()){
				counter++;
			}
			if (counter > 0){
				vorhanden = true;
			}
			
			try {
				mysql.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vorhanden;

	}

}
