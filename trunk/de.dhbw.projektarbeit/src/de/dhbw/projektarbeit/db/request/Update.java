package de.dhbw.projektarbeit.db.request;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import de.dhbw.projektarbeit.db.db.Request;

/**
 * @author Albert
 * 
 */
public class Update {

	private String schema;
	private Connection con;
	private Statement stmt;
	private ResultSet rset;

	

	/**
	 * Constructor, initialisiert das Objekt, es wird ein DB-Schema und ein
	 * Connection Objekt uebergeben.
	 * 
	 * @param con
	 *            Ein Connection Objekt, hierueber werden die DB-Abfragen
	 *            durchgefuehrt.
	 * @param req
	 *            Das Request Objekt
	 */
	public Update(String schema, Connection con) {
		this.schema = schema;
		this.con = con;
	}

	public void updateEdits(int id, String firstname, String lastname, String form, String field) throws Exception{
		try {
			// Alle Einfuege-Operationen sollen als eine Transaktion und mittels
			// Stringbuffer ausgefuehrt
			// werden.
			con.setAutoCommit(false);
			stmt = con.createStatement();
			StringBuffer buffer = new StringBuffer();
			buffer.append("UPDATE ");
			buffer.append(schema);
			buffer.append(".");
			buffer.append(form);
			buffer.append(" SET FirstName = " );
			buffer.append("\"" + firstname + "\"");
			buffer.append(" , LastName = " );
			buffer.append("\"" + lastname + "\"");
			buffer.append(" WHERE ");
			buffer.append(field);
			buffer.append(" = ");
			buffer.append(id);
			
			stmt.executeUpdate(buffer.toString());
	
			// Neuerfasste Daten auf DB schreiben
			con.commit();
			con.setAutoCommit(true);
			
			// Bei erfolgreicher Änderung Nachricht bringen
			JOptionPane.showMessageDialog(null, ("Der Eintrag " + firstname + " " + lastname + " wurde erfolgreich geändert!"),
					"Vorgang erfolgreich", JOptionPane.INFORMATION_MESSAGE);
			
		} catch (Exception e) {
			// Im Fehlerfall Rollback durchfÃ¼hren
			con.rollback();
			con.setAutoCommit(true);
			e.printStackTrace();
			throw new Exception(
					"Fehler beim Ändern der Zeile in der Datenbank! Fehlercode 008");
		}
		
	}
	
}
