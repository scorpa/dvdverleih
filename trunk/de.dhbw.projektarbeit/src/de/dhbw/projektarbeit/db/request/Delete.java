package de.dhbw.projektarbeit.db.request;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import de.dhbw.projektarbeit.db.mysql.MysqlAccess;
import de.dhbw.projektarbeit.gui.Dialogs.EditDVD;
import de.dhbw.projektarbeit.gui.Dialogs.EditRegisseur;

/**
 * Mit Hilfe dieser Klasse lassen sich Datensaetze loeschen.
 * 
 * @author BrunEis
 * 
 */
public class Delete {

	private String schema;
	private ResultSet rset;
	private Connection con;
	private Statement stmt;
	private MysqlAccess mysql = new MysqlAccess();
	private EditRegisseur er;

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

	public void deleteEdits(int id, String firstname, String lastname,
			String form, String field) throws Exception {
		try {
			con.setAutoCommit(false);
			stmt = con.createStatement();
			StringBuffer buffer = new StringBuffer();

			buffer.append("SELECT ");
			buffer.append(field);
			buffer.append(" FROM DVD WHERE ");
			buffer.append(field);
			buffer.append("=");
			buffer.append(id);

			rset = stmt.executeQuery(buffer.toString());
			int count = 0;
			while (rset.next()) {
				count++; // Zeilen-ZŠhler erhšhen
			}
			if (count <= 0) {
				buffer = new StringBuffer();
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
				mysql.closeConnection();

				// Bei erfolgreichem Löschen Nachricht bringen
				JOptionPane.showMessageDialog(null, ("Der Eintrag " + firstname
						+ " " + lastname + " wurde erfolgreich geloescht!"),
						"Vorgang erfolgreich", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane
						.showMessageDialog(
								null,
								("Der Eintrag konnte nicht geloescht werden, da dieser noch benutzt wird!"),
								"Vorgang abgebrochen",
								JOptionPane.WARNING_MESSAGE);
			}
		} catch (SQLException e) {

			con.rollback();
			con.setAutoCommit(true);
			e.printStackTrace();
			throw new Exception(
					"Fehler beim Loeschen der Zeile aus der Datenbank! Fehlercode 007");
		}
	}

	/**
	 * Löschen einer DVD aus dem JDialog EditDVD
	 * 
	 * @param ed
	 *            --> Klasseninformationen aus EditDVD
	 * @param field
	 *            --> Zeilenbezeichnung, die als Select genommen werden soll
	 * @param form
	 *            --> Tabelle, aus der gelöscht werden soll
	 * @param code
	 *            --> EAN Code (Primärschlüssel) der DVD
	 * @throws Exception
	 */
	public void deleteDVD(EditDVD ed, String field, String form, String code)
			throws Exception {
		try {

			con.setAutoCommit(false);
			stmt = con.createStatement();
			StringBuffer buffer = new StringBuffer();

			// †berprŸfen ob selektierte ID vorhanden ist
			buffer.append("SELECT COUNT(");
			buffer.append(field + ")");
			buffer.append(" FROM DVD");

			rset = stmt.executeQuery(buffer.toString());
			int count = 0;
			while (rset.next()) {
				count++; // Zeilen-ZŠhler erhšhen
			}
			if (count > 0) {
				buffer = new StringBuffer();
				buffer.append("DELETE FROM ");
				buffer.append(schema);
				buffer.append(".");
				buffer.append(form);
				buffer.append(" WHERE ");
				buffer.append(field);
				buffer.append(" = ");
				buffer.append(code);
				stmt.executeUpdate(buffer.toString());

				// Neuerfasste Daten auf DB schreiben
				con.commit();
				con.setAutoCommit(true);
				mysql.closeConnection();
				// Bei erfolgreichem Lšschen Nachricht bringen
				JOptionPane.showMessageDialog(null,
						("Die DVD mit dem EAN Code " + code + " wurde erfolgreich geloescht!"),
						"Vorgang erfolgreich", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null,
						("Die DVD mit dem EAN Code " + code + " konnte nicht geloescht werden!"),
						"Vorgang abgebrochen", JOptionPane.WARNING_MESSAGE);
			}

		} catch (SQLException e) {

			con.rollback();
			con.setAutoCommit(true);
			e.printStackTrace();
			throw new Exception(
					"Fehler beim Loeschen der Zeile aus der Datenbank! Fehlercode 007");
		}
		try {
			ed.dvdDeleted();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
