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

	/**
	 * Aendert einen Datensatz in der Datenbank. Hierzu wird der alte Datensatz
	 * geloescht und ein neuer mit den aktualisierten Werten eingefuegt.
	 * 
	 * @param id
	 *            Die ID des zu loeschenden Eintrages
	 * @param wasBookingtype
	 *            Der urspruengliche Buchungstyp <li>Insert.EARNING (1) fuer
	 *            Einnahmen</li><li>Insert.EXPENSE (2) fuer Ausgaben</li>
	 * @param isBookingtype
	 *            Der neue Buchungstyp <li>Insert.EARNING (1) fuer Einnahmen</li>
	 *            <li>Insert.EXPENSE (2) fuer Ausgaben</li>
	 * @param category
	 *            String mit der Kategoriebezeichnung, also Art der Einnahme
	 *            oder Ausgabe
	 * @param comment
	 *            Freitext String, max. 50 Zeichen
	 * @param amount
	 *            Wert der Buchung, als double value
	 * @param paymentmethod
	 *            String mit der Zahlungsweise (Konto, Kreditkarte, Bar)
	 * @param date
	 *            java.sql.date enthaelt das Buchungsdatum
	 * @param wasAsset
	 *            Boolean gibt an, ob der Datensatz vor der Aenderung ein
	 *            Wertgegenstand war (true) oder nicht (false)
	 * @param isAsset
	 *            Boolean gibt an, ob die Buchung nach der Aenderung ein
	 *            Wertgegenstand ist (true) oder nicht (false)
	 * @throws Exception
	 *             Wenn ein Fehler beim Loeschen oder Eintragen in die Datenbank
	 *             auftritt.
	 */
	public void update(long id, int wasBookingtype, int isBookingtype,
			String category, String comment, double amount,
			String paymentmethod, Date date, boolean wasAsset, boolean isAsset)
			throws Exception {
		try {
			// Alle Operationen sollen als eine Transaktion ausgeführt
			// werden.
			con.setAutoCommit(false); // wird bei Insert wieder auf true gesetzt
			//req.delete().delete(id, wasBookingtype, wasAsset);
			//req.insert().insert(isBookingtype, category, comment, amount,
			//		paymentmethod, date, isAsset);

		} catch (SQLException e) {
			// Im Fehlerfall Rollback durchführen
			con.rollback();
			con.setAutoCommit(true);
			e.printStackTrace();
			throw new Exception("Fehler beim Einfügen in die Datenbank.");
		}
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
			
			// Bei erfolgreichem Hinzuf�gen Nachricht bringen
			JOptionPane.showMessageDialog(null, ("Der Name wurde erfolgreich ge�ndert!"),
					"Vorgang erfolgreich", JOptionPane.INFORMATION_MESSAGE);
			
		} catch (Exception e) {
			// Im Fehlerfall Rollback durchführen
			con.rollback();
			con.setAutoCommit(true);
			e.printStackTrace();
		}
		
	}
	
}
