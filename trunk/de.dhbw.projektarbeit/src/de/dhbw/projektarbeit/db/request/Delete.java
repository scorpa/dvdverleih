package de.dhbw.projektarbeit.db.request;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

/**
 * Mit Hilfe dieser Klasse lassen sich Datensaetze loeschen.
 * 
 * @author reindan
 * 
 */
public class Delete {

	private String schema;

	private Connection con;
	private Statement stmt;

	public static final int EARNING = 1;
	public static final int EXPENSE = 2;

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
	public Delete(String schema, Connection con) {
		this.schema = schema;
		this.con = con;
	}

	public void deleteEdits(int id, String firstname, String lastname, String form, String field)
		throws Exception {
		try {
			con.setAutoCommit(false);
			stmt = con.createStatement();
			StringBuffer buffer = new StringBuffer();
			buffer.append("DELETE FROM ");
			buffer.append(schema);
			buffer.append(".");
			buffer.append(form);
			buffer.append(" WHERE ");
			buffer.append(field);
			buffer.append(" = ");
			buffer.append(id);
			
			
			stmt.executeUpdate(buffer.toString());

			// Neuerfasste Daten auf DB schreiben
			con.commit();
			con.setAutoCommit(true);
			
			// Bei erfolgreichem L�schen Nachricht bringen
			JOptionPane.showMessageDialog(null, ("Der Eintrag " + firstname + " " + lastname + " wurde erfolgreich gel�scht!"),
					"Vorgang erfolgreich", JOptionPane.INFORMATION_MESSAGE);
			
		} catch (SQLException e) {
		
			con.rollback();
			con.setAutoCommit(true);
			e.printStackTrace();
			throw new Exception(
					"Fehler beim L�schen der Zeile aus der Datenbank! Fehlercode 007");
		}
	}
}
