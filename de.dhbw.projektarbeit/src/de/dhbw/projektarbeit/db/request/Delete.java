package de.dhbw.projektarbeit.db.request;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

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

	/**
	 * Loescht einen Eintrag aus den Tabellen assets, earnings buw. expenses, und
	 * bankaccount. Dies sollte durchgefuehrt werden, wenn ein Eintrag falsch
	 * oder doppelt eingegeben wurde.
	 * 
	 * @param id
	 *            Die ID des zu löschenden Eintrags
	 * @param bookingtype
	 *            Die Art der Buchung <li>Einnahme: Delete.EARNING</li><li>
	 *            Ausgabe: Delete.EXPENSE</li>
	 * @param isAsset
	 *            Boolean, der angibt, ob es sich bei dem zu loeschenden Eintrag
	 *            um einen Wertgegenstand handelt. true = ist Wertgegenstand,
	 *            false = kein Wertgegenstand
	 * @throws Exception
	 *             Wenn beim Loeschen der Eintraege aus den Tabellen ein Fehler
	 *             auftritt.
	 */
	public void delete(long id, int bookingtype, boolean isAsset)
			throws Exception {
		try {

			// Alle Lösch-Operationen sollen als eine Transaktion ausgeführt
			// werden.
			con.setAutoCommit(false);

			stmt = con.createStatement();

			StringBuffer deleteAsset, deleteBooking, deleteAccount;

			// Löschen aus Tabelle assets
			if (isAsset) {
				deleteAsset = new StringBuffer();
				deleteAsset.append("DELETE FROM ");
				deleteAsset.append(schema);
				deleteAsset
						.append(".assets WHERE Expenses_ID = (SELECT E.ID FROM ");
				deleteAsset.append(schema);
				deleteAsset.append(".expenses AS E WHERE E.Bankaccount_ID = ");
				deleteAsset.append(id);
				deleteAsset.append(")");

				stmt.executeUpdate(deleteAsset.toString());
			}

			// Löschen aus Tabelle earnings bzw. expenses
			deleteBooking = new StringBuffer();
			deleteBooking.append("DELETE FROM ");
			deleteBooking.append(schema);
			deleteBooking.append(".");
			if (bookingtype == Delete.EARNING)
				deleteBooking.append("earnings");
			if (bookingtype == Delete.EXPENSE)
				deleteBooking.append("expenses");
			deleteBooking.append(" WHERE Bankaccount_ID = ");
			deleteBooking.append(id);

			stmt.executeUpdate(deleteBooking.toString());

			// Löschen aus Tabelle bankaccount
			deleteAccount = new StringBuffer();
			deleteAccount.append("DELETE FROM ");
			deleteAccount.append(schema);
			deleteAccount.append(".bankaccount WHERE ID = ");
			deleteAccount.append(id);

			stmt.executeUpdate(deleteAccount.toString());

			// Änderungen schreiben, AutoCommit wieder einschalten
			con.commit();
			con.setAutoCommit(true);

		} catch (SQLException e) {
			// Im Fehlerfall Rollback durchführen, AutoCommit wieder einschalten
			con.rollback();
			con.setAutoCommit(true);
			e.printStackTrace();
			throw new Exception("Fehler beim Löschen aus der Datenbank.");
		}
	}
}
