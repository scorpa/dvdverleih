package de.dhbw.projektarbeit.db.request;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Mit Hilfe dieser Klasse lassen sich neu erfasst Buchungen in die DB
 * einfuegen.
 * 
 * @author reindan
 */
public class Insert {

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
	public Insert(String schema, Connection con) {
		this.schema = schema;
		this.con = con;
	}

	/**
	 * Fuegt einen neuen Datensatz in die Datenbank ein.
	 * 
	 * @param bookingtype
	 *            Insert.EARNING (1) für Einnahmen oder Insert.EXPENSE (2) fuer
	 *            Ausgaben
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
	 * @param isAsset
	 *            Boolean gibt an, ob es sich um einen Wertgegenstand handelt
	 *            (true) oder nicht (false)
	 * @throws Exception
	 *             Wenn ein Fehler beim Eintragen in die Datenbank auftritt.
	 */
	public void insert(int bookingtype, String category, String comment,
			double amount, String paymentmethod, Date date, boolean isAsset)
			throws Exception {
		try {
			// Alle Einfuege-Operationen sollen als eine Transaktion ausgefuehrt
			// werden.
			con.setAutoCommit(false);

			stmt = con.createStatement();

			StringBuffer bankaccount, booking, asset;

			// Insert-Statement fuer Tabelle bankaccount
			// INSERT INTO dhbw.bankaccount (amount) VALUES (-199.9)
			bankaccount = new StringBuffer();
			bankaccount.append("INSERT INTO ");
			bankaccount.append(schema);
			bankaccount.append(".bankaccount (amount) VALUES (");
			if (bookingtype == Insert.EARNING && amount < 0.0)
				bankaccount.append(amount * (-1.0));
			else if (bookingtype == Insert.EXPENSE && amount > 0.0)
				bankaccount.append(amount * (-1.0));
			else
				bankaccount.append(amount);
			bankaccount.append(')');

			// Insert-Statement fuer Tabelle earnings bzw. expenses
			/*
			 * INSERT INTO dhbw.expenses (Bankaccount_ID, Paymentmethod_ID,
			 * Expensetype_ID, comment, date) VALUES ( (SELECT MAX(B.ID) FROM
			 * dhbw.bankaccount AS B), (SELECT P.ID FROM dhbw.paymentmethod AS P
			 * WHERE P.name = "Kreditkarte"), (SELECT E.ID FROM dhbw.expensetype
			 * AS E WHERE E.title = "Kleidung"), "Ein schicker Anzug",
			 * "2011-10-01")
			 */
			booking = new StringBuffer();
			booking.append("INSERT INTO ");
			booking.append(schema);
			if (bookingtype == Insert.EARNING)
				booking.append(".earnings (Bankaccount_ID, Paymentmethod_ID, Earningtype_ID, comment, date)");
			if (bookingtype == Insert.EXPENSE)
				booking.append(".expenses (Bankaccount_ID, Paymentmethod_ID, Expensetype_ID, comment, date)");
			booking.append(" VALUES (");
			booking.append(" (SELECT MAX(B.ID) FROM ");
			booking.append(schema);
			booking.append(".bankaccount AS B),");
			booking.append(" (SELECT P.ID FROM ");
			booking.append(schema);
			booking.append(".paymentmethod AS P WHERE P.name = \"");
			booking.append(paymentmethod);
			booking.append("\"), (SELECT E.ID FROM ");
			booking.append(schema);
			if (bookingtype == Insert.EARNING)
				booking.append(".earningtype AS E WHERE E.title = \"");
			if (bookingtype == Insert.EXPENSE)
				booking.append(".expensetype AS E WHERE E.title = \"");
			booking.append(category);
			booking.append("\"), \"");
			booking.append(comment);
			booking.append("\", \"");
			booking.append(date);
			booking.append("\")");

			stmt.executeUpdate(bankaccount.toString());
			stmt.executeUpdate(booking.toString());

			// Wertgegenstand
			if (isAsset) {
				// INSERT INTO dhbw.assets (Expenses_ID) VALUES ((SELECT
				// MAX(E.ID) FROM dhbw.expenses AS E))
				asset = new StringBuffer();
				asset.append("INSERT INTO ");
				asset.append(schema);
				asset.append(".assets (Expenses_ID) VALUES ((SELECT MAX(E.ID) FROM ");
				asset.append(schema);
				asset.append(".expenses AS E))");

				stmt.executeUpdate(asset.toString());
			}

			// Aenderungen schreiben
			con.commit();
			con.setAutoCommit(true);

		} catch (SQLException e) {
			// Im Fehlerfall Rollback durchfuehren
			con.rollback();
			con.setAutoCommit(true);
			e.printStackTrace();
			throw new Exception("Fehler beim Einfügen in die Datenbank.");
		}
	}


	public void insertCustomer(String firstname, String lastname, String street, String streetnumber, Date birthdate){
		
	}
} 
