package de.dhbw.projektarbeit.db.request;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import de.dhbw.projektarbeit.db.mysql.MysqlAccess;

/**
 * Methode, die Updatefunktionen für Einträge in der SQL DB zur Verfügung stellt
 * 
 * @author Brunner
 * @author Eisen
 * 
 */
public class Update {

	private String schema;
	private Connection con;
	private Statement stmt;
	private ResultSet rset;
	private MysqlAccess mysql = new MysqlAccess();

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
	 * Update-Methode zum Ändern der Einträge Regisseur, Autor, Produzent und
	 * Kamermann
	 * 
	 * @param id
	 *            --> ID des DB Eintrags
	 * @param firstname
	 *            --> Vorname
	 * @param lastname
	 *            --> Nachname
	 * @param form
	 *            --> Tabelle, in der geändert werden soll
	 * @param field
	 *            --> Spaltebezeichnung
	 * @throws Exception
	 *             --> Exceptionhandling
	 */
	public void updateEdits(int id, String firstname, String lastname,
			String form, String field) throws Exception {
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
			buffer.append(" SET FirstName = ");
			buffer.append("\"" + firstname + "\"");
			buffer.append(" , LastName = ");
			buffer.append("\"" + lastname + "\"");
			buffer.append(" WHERE ");
			buffer.append(field);
			buffer.append(" = ");
			buffer.append(id);

			stmt.executeUpdate(buffer.toString());

			// Neuerfasste Daten auf DB schreiben
			con.commit();
			con.setAutoCommit(true);
			mysql.closeConnection();
			// Bei erfolgreicher Änderung Nachricht bringen
			JOptionPane.showMessageDialog(null, ("Der Eintrag " + firstname
					+ " " + lastname + " wurde erfolgreich geändert!"),
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

	/**
	 * Methode zum Editieren von DVDs.
	 * 
	 * @param quantity
	 *            --> Anzahl der DVDs
	 * @param title
	 *            --> Titel der DVD
	 * @param originalTitle
	 *            --> Originaltitel der DVD
	 * @param genre
	 *            --> Genre der DVD
	 * @param prodCountry
	 *            --> Herstellungsland
	 * @param prod_year
	 *            --> Produktionsjahr
	 * @param release
	 *            --> Erscheinungsdatum
	 * @param duration
	 *            --> Filmdauer
	 * @param fsk
	 *            --> FSK Einstufung
	 * @param regisseur
	 *            --> Regisseur
	 * @param author
	 *            --> Autor
	 * @param production
	 *            --> Produzent
	 * @param camera
	 *            --> Kameramann
	 * @param eanCode
	 *            --> Barcode
	 * @param oldEAN
	 *            --> Alter Barcode, falls dieser geändert wurde
	 */
	public void editDVD(int quantity, String title, String originalTitle,
			String genre, String prodCountry, int prod_year,
			java.sql.Date release, int duration, String fsk, String regisseur,
			String author, String production, String camera, String eanCode,
			String oldEAN) {

		int regisseurID, authorID, productionID, cameraID;
		try {
			// IDs von Regisseur, Autor, Production und Camera beziehen
			Insert insert = new Insert("dvd_verleih", con);
			regisseurID = insert.getID("dvd_verleih", "regisseur", "Regie_ID",
					regisseur);
			authorID = insert.getID("dvd_verleih", "author", "Author_ID",
					author);
			productionID = insert.getID("dvd_verleih", "production",
					"Production_ID", production);
			cameraID = insert.getID("dvd_verleih", "camera", "Camera_ID",
					camera);

			// Alle Update-Operationen sollen als eine Transaktion und mittels
			// Stringbuffer ausgefuehrt
			// werden.
			con.setAutoCommit(false);
			stmt = con.createStatement();
			StringBuffer editDVD = new StringBuffer();
			editDVD.append("UPDATE ");
			editDVD.append(schema);
			editDVD.append(".dvd SET Quantity = ");
			editDVD.append("\"" + quantity + "\"");
			editDVD.append(", Title = ");
			editDVD.append("\"" + title + "\"");
			editDVD.append(", Original_Title = ");
			editDVD.append("\"" + originalTitle + "\"");
			editDVD.append(", Genre = ");
			editDVD.append("\"" + genre + "\"");
			editDVD.append(", Prod_Country = ");
			editDVD.append("\"" + prodCountry + "\"");
			editDVD.append(", Prod_Year = ");
			editDVD.append(prod_year);
			editDVD.append(", Rel_Date = ");
			editDVD.append("\"" + release + "\"");
			editDVD.append(", Duration = ");
			editDVD.append("\"" + duration + "\"");
			editDVD.append(", FSK = ");
			editDVD.append("\"" + fsk + "\"");
			editDVD.append(", Regie_ID = ");
			editDVD.append("\"" + regisseurID + "\"");
			editDVD.append(", Author_ID = ");
			editDVD.append("\"" + authorID + "\"");
			editDVD.append(", Production_ID = ");
			editDVD.append("\"" + productionID + "\"");
			editDVD.append(",Camera_ID = ");
			editDVD.append("\"" + cameraID + "\"");
			editDVD.append(", Barcode = ");
			editDVD.append("\"" + eanCode + "\"");
			editDVD.append(" WHERE Barcode = ");
			editDVD.append("\"" + oldEAN + "\"");

			stmt.executeUpdate(editDVD.toString());

			// Neuerfasste Daten auf DB schreiben
			con.commit();
			con.setAutoCommit(true);
			mysql.closeConnection();
			// Bei erfolgreicher Änderung Nachricht bringen
			JOptionPane.showMessageDialog(null, ("Der Eintrag in der DVD \""
					+ title + "\" wurde erfolgreich geändert!"),
					"Vorgang erfolgreich", JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			// Im Fehlerfall Rollback durchfÃ¼hren
			try {
				con.rollback();
				con.setAutoCommit(true);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
