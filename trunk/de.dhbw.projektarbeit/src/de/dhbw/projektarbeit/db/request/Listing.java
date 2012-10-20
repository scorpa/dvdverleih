package de.dhbw.projektarbeit.db.request;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Diese Klasse wird dazu verwendet, Uebersichtslisten zu erzeugen. Dies umfasst
 * Listen, die alle Eingaben, Ausgaben ode Gesamttransaktionen zeigen. Sie
 * lassen sich filtern nach der gewuenschten Zahlungsweise Konto, Kreditkarte
 * oder Bar. Darueber hinaus werden Methoden bereitgestellt, die die
 * entsprechenden Summen zu den Listen berechnen und zurueckliefern.
 * 
 * @author reindan
 * 
 */
public class Listing {

	private String schema;

	private Connection con;
	private Statement stmt;
	private ResultSet rset;
	private ResultSetMetaData rsmd;

	// payment method
	public static final String ALL = "*";
	public static final String ACCOUNT = "Konto";
	public static final String CREDITCARD = "Kreditkarte";
	public static final String CASH = "Bar";

	// sort
	public static final String DATE = "date";
	public static final String AMOUNT = "amount";

	private Object[][] listing;

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
	public Listing(String schema, Connection con) {
		this.schema = schema;
		this.con = con;
	}

	/**
	 * Fuehrt die Datenbankabfrage von der gewaehlten Tabelle durch, dabei wird
	 * nach der Zahlungsweise (Konto, Kreditkarte, Bar), sowie nach Anfangs- und
	 * Enddatum gefiltert.
	 * 
	 * @param table
	 *            Der Tabellenname, ueber den die Abfrage ausgefuehrt wird.
	 * @param paymentMethod
	 *            Die Zahlungsweise, nach der gefiltert wird.
	 * @param from
	 *            Anfangsdatum, ab dem Transationen gesucht werden.
	 * @param to
	 *            Enddatum, bis zu dem Transaktionen gesucht werden.
	 * @throws Exception
	 *             "Fehler bei DB-Abfrage", wenn ein Problem mit der Abfrage
	 *             auftritt.
	 */
	private void readListing(String table, String paymentMethod, Date from,
			Date to) throws Exception {
		try {
			// Statement erstellen
			stmt = con.createStatement();

			// SELECT String zusammenbauen
			String selectString = buildSelectString(table, paymentMethod, from,
					to, Listing.DATE);

			// Abfrage durchfuehren
			rset = stmt.executeQuery(selectString.toString());

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

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Fehler bei DB Abfrage.");
		}

	}

	/**
	 * Erstellt mit Hilfe der uebergebenen Parameter den den Select String, der
	 * zur Abfrage der Daten aus der Datenbank verwendet wird.
	 * 
	 * @param table
	 *            Der Tabellenname, ueber den die Abfrage ausgefuehrt wird.
	 * @param paymentMethod
	 *            Die Zahlungsweise, nach der gefiltert wird.
	 * @param from
	 *            Anfangsdatum, ab dem Transationen gesucht werden.
	 * @param to
	 *            Enddatum, bis zu dem Transaktionen gesucht werden.
	 */
	private String buildSelectString(String table, String paymentMethod,
			Date from, Date to, String order) {

		// SELECT * FROM schema.table
		StringBuffer selectString = new StringBuffer();
		selectString.append("SELECT * FROM ");
		selectString.append(schema);
		selectString.append('.');
		selectString.append(table);

		// Kein Datum angegeben:
		if (from == null || to == null) {
			// WHERE paymentmethod = 'xxx'
			if (!paymentMethod.equals(Listing.ALL)) {
				selectString.append(" WHERE paymentmethod = '");
				selectString.append(paymentMethod);
				selectString.append('\'');
			}
		}
		// Datum angegeben
		else { // WHERE
			selectString.append(" WHERE ");
			if (!paymentMethod.equals(Listing.ALL)) {
				// paymentmethod = 'xxx' AND
				selectString.append("paymentmethod = '");
				selectString.append(paymentMethod);
				selectString.append("' AND ");
			} // date BETWEEN 'from' AND 'to'
			selectString.append("date BETWEEN '");
			selectString.append(from);
			selectString.append("' AND '");
			selectString.append(to);
			selectString.append('\'');
		}
		if (order != null) {
			selectString.append(" ORDER BY ");
			selectString.append(order);
			selectString.append(" DESC");
		}

		return selectString.toString();
	}

	/*
	 * =========================== GETTERS ===========================
	 */

	/**
	 * Liefert die Summe aller Transaktionen, abhaengig von der gewaehlten
	 * Zahlungsart.
	 * 
	 * @param paymentMethod
	 *            Die Zahlungsweise, zu der die Abfrage ausgefuehrt werden soll.
	 *            Verwende die Konstanten ALL, ACCOUNT, CREDITCARD oder CASH
	 * @return Double-Value mit der Summe aller Transaktionen.
	 * @throws Exception
	 *             Wenn bei der Abfrage der Transaktionen ein Fehler auftritt.
	 */
	public double getTotal(String paymentMethod) throws Exception {
		getListing(paymentMethod);
		double total = 0.0;
		for (Object[] row : listing) {
			total += (Double) row[4];
		}
		return total;
	}

	/**
	 * Liefert die Summe aller Transaktionen, abhaengig von der gewaehlten
	 * Zahlungsart sowie Anfangs- und Enddatum.
	 * 
	 * @param paymentMethod
	 *            Die Zahlungsweise, zu der die Abfrage ausgefuehrt werden soll.
	 *            Verwende die Konstanten ALL, ACCOUNT, CREDITCARD oder CASH
	 * @param from
	 *            Anfangsdatum, ab dem Transationen gesucht werden.
	 * @param to
	 *            Enddatum, bis zu dem Transaktionen gesucht werden.
	 * @return Double-Value mit der Summe aller Transaktionen.
	 * @throws Exception
	 *             Wenn bei der Abfrage der Transaktionen ein Fehler auftritt.
	 */
	public double getTotal(String paymentMethod, Date from, Date to)
			throws Exception {
		getListing(paymentMethod, from, to);
		double total = 0.0;
		for (Object[] row : listing) {
			total += (Double) row[4];
		}
		return total;
	}

	/**
	 * Liefert die Summe aller Einnahmen, abhaengig von der gewaehlten
	 * Zahlungsart.
	 * 
	 * @param paymentMethod
	 *            Die Zahlungsweise, zu der die Abfrage ausgefuehrt werden soll.
	 *            Verwende die Konstanten ALL, ACCOUNT, CREDITCARD oder CASH
	 * @return Double-Value mit der Summe aller Einnahmen.
	 * @throws Exception
	 *             Wenn bei der Abfrage der Einnahmen ein Fehler auftritt.
	 */
	public double getEarningsTotal(String paymentMethod) throws Exception {
		getEarningsListing(paymentMethod);
		double total = 0.0;
		for (Object[] row : listing) {
			total += (Double) row[4];
		}
		return total;
	}

	/**
	 * Liefert die Summe aller Einnahmen, abhaengig von der gewaehlten
	 * Zahlungsart, sowie Anfangs- und Enddatum.
	 * 
	 * @param paymentMethod
	 *            Die Zahlungsweise, zu der die Abfrage ausgefuehrt werden soll.
	 *            Verwende die Konstanten ALL, ACCOUNT, CREDITCARD oder CASH
	 * @param from
	 *            Anfangsdatum, ab dem Transationen gesucht werden.
	 * @param to
	 *            Enddatum, bis zu dem Transaktionen gesucht werden.
	 * @return Double-Value mit der Summe aller Einnahmen.
	 * @throws Exception
	 *             Wenn bei der Abfrage der Einnahmen ein Fehler auftritt.
	 */
	public double getEarningsTotal(String paymentMethod, Date from, Date to)
			throws Exception {
		getEarningsListing(paymentMethod, from, to);
		double total = 0.0;
		for (Object[] row : listing) {
			total += (Double) row[4];
		}
		return total;
	}

	/**
	 * Liefert die Summe aller Ausgaben, abhaengig von der gewaehlten Zahlungsart.
	 * 
	 * @param paymentMethod
	 *            Die Zahlungsweise, zu der die Abfrage ausgefuehrt werden soll.
	 *            Verwende die Konstanten ALL, ACCOUNT, CREDITCARD oder CASH
	 * @return Double-Value mit der Summe aller Einnahmen.
	 * @throws Exception
	 *             Wenn bei der Abfrage der Ausgaben ein Fehler auftritt.
	 */
	public double getExpensesTotal(String paymentMethod) throws Exception {

		getExpensesListing(paymentMethod);
		double total = 0.0;
		for (Object[] row : listing) {
			total += (Double) row[4];
		}
		return total;
	}

	/**
	 * Liefert die Summe aller Ausgaben, abhaengig von der gewaehlten Zahlungsart,
	 * sowie dem Anfangs- und Enddatum.
	 * 
	 * @param paymentMethod
	 *            Die Zahlungsweise, zu der die Abfrage ausgefuehrt werden soll.
	 *            Verwende die Konstanten ALL, ACCOUNT, CREDITCARD oder CASH
	 * @param from
	 *            Anfangsdatum, ab dem Transationen gesucht werden.
	 * @param to
	 *            Enddatum, bis zu dem Transaktionen gesucht werden.
	 * @return Double-Value mit der Summe aller Einnahmen.
	 * @throws Exception
	 *             Wenn bei der Abfrage der Ausgaben ein Fehler auftritt.
	 */
	public double getExpensesTotal(String paymentMethod, Date from, Date to)
			throws Exception {
		getExpensesListing(paymentMethod, from, to);
		double total = 0.0;
		for (Object[] row : listing) {
			total += (Double) row[4];
		}
		return total;
	}

	/**
	 * Liefert die Summe aller Ausgaben fuer Wertgegenstaende, abhaengig von der
	 * gewaehlten Zahlungsart.
	 * 
	 * @param paymentMethod
	 *            Die Zahlungsweise, zu der die Abfrage ausgefuehrt werden soll.
	 *            Verwende die Konstanten ALL, ACCOUNT, CREDITCARD oder CASH
	 * @return Double-Value mit der Summe aller Einnahmen.
	 * @throws Exception
	 *             Wenn bei der Abfrage der Ausgaben ein Fehler auftritt.
	 */
	public double getAssetsTotal(String paymentMethod) throws Exception {

		getAssetsListing(paymentMethod);
		double total = 0.0;
		for (Object[] row : listing) {
			total += (Double) row[4];
		}
		return total;
	}

	/**
	 * Liefert die Summe aller Ausgaben fuer Wertgegenstaende, abhaengig von der
	 * gewaehlten Zahlungsart, sowie dem Anfangs- und Enddatum.
	 * 
	 * @param paymentMethod
	 *            Die Zahlungsweise, zu der die Abfrage ausgefuehrt werden soll.
	 *            Verwende die Konstanten ALL, ACCOUNT, CREDITCARD oder CASH
	 * @param from
	 *            Anfangsdatum, ab dem Transationen gesucht werden.
	 * @param to
	 *            Enddatum, bis zu dem Transaktionen gesucht werden.
	 * @return Double-Value mit der Summe aller Einnahmen.
	 * @throws Exception
	 *             Wenn bei der Abfrage der Ausgaben ein Fehler auftritt.
	 */
	public double getAssetsTotal(String paymentMethod, Date from, Date to)
			throws Exception {
		getAssetsListing(paymentMethod, from, to);
		double total = 0.0;
		for (Object[] row : listing) {
			total += (Double) row[4];
		}
		return total;
	}

	/**
	 * Liefert die Liste aller Transaktionen, abhaengig von der gewaehlten
	 * Zahlungsart.
	 * 
	 * @param paymentMethod
	 *            Die Zahlungsweise, zu der die Abfrage ausgefuehrt werden soll.
	 *            Verwende die Konstanten ALL, ACCOUNT, CREDITCARD oder CASH
	 * @return Zweidimensionales Object-Array, enthaelt die Liste der
	 *         Transaktionen.
	 * @throws Exception
	 *             Wenn bei der Abfrage der Ausgaben ein Fehler auftritt.
	 */
	public Object[][] getListing(String paymentMethod) throws Exception {
		readListing("alltransactions", paymentMethod, null, null);
		for (Object[] row : listing) {
			if (row[6] == null)
				row[6] = false;
			else
				row[6] = true;
		}
		return listing;
	}

	/**
	 * Liefert die Liste aller Transaktionen, abhaengig von der gewaehlten
	 * Zahlungsart sowie dem Anfangs- und Enddatum.
	 * 
	 * @param paymentMethod
	 *            Die Zahlungsweise, zu der die Abfrage ausgefuehrt werden soll.
	 *            Verwende die Konstanten ALL, ACCOUNT, CREDITCARD oder CASH
	 * @param from
	 *            Anfangsdatum, ab dem Transationen gesucht werden.
	 * @param to
	 *            Enddatum, bis zu dem Transaktionen gesucht werden.
	 * @return Zweidimensionales Object-Array, enthaelt die Liste der
	 *         Transaktionen.
	 * @throws Exception
	 *             Wenn bei der Abfrage der Ausgaben ein Fehler auftritt.
	 */
	public Object[][] getListing(String paymentMethod, Date from, Date to)
			throws Exception {
		readListing("alltransactions", paymentMethod, from, to);
		for (Object[] row : listing) {
			if (row[6] == null)
				row[6] = false;
			else
				row[6] = true;
		}
		return listing;
	}

	/**
	 * Liefert die Liste aller Einnahmen, abhaengig von der gewaehlten
	 * Zahlungsart.
	 * 
	 * @param paymentMethod
	 *            Die Zahlungsweise, zu der die Abfrage ausgefuehrt werden soll.
	 *            Verwende die Konstanten ALL, ACCOUNT, CREDITCARD oder CASH
	 * @return Zweidimensionales Object-Array, enthaelt die Liste der Einnahmen.
	 * @throws Exception
	 *             Wenn bei der Abfrage der Ausgaben ein Fehler auftritt.
	 */
	public Object[][] getEarningsListing(String paymentMethod) throws Exception {
		readListing("earningsdetails", paymentMethod, null, null);
		return listing;
	}

	/**
	 * Liefert die Liste aller Einnahmen, abhaengig von der gewaehlten Zahlungsart
	 * sowie dem Anfangs- und Enddatum.
	 * 
	 * @param paymentMethod
	 *            Die Zahlungsweise, zu der die Abfrage ausgefuehrt werden soll.
	 *            Verwende die Konstanten ALL, ACCOUNT, CREDITCARD oder CASH
	 * @param from
	 *            Anfangsdatum, ab dem Transationen gesucht werden.
	 * @param to
	 *            Enddatum, bis zu dem Transaktionen gesucht werden.
	 * @return Zweidimensionales Object-Array, enthaelt die Liste der Einnahmen.
	 * @throws Exception
	 *             Wenn bei der Abfrage der Ausgaben ein Fehler auftritt.
	 */
	public Object[][] getEarningsListing(String paymentMethod, Date from,
			Date to) throws Exception {
		readListing("earningsdetails", paymentMethod, from, to);
		return listing;
	}

	/**
	 * Liefert die Liste aller Ausgaben, abhaengig von der gewaehlten Zahlungsart.
	 * 
	 * @param paymentMethod
	 *            Die Zahlungsweise, zu der die Abfrage ausgefuehrt werden soll.
	 *            Verwende die Konstanten ALL, ACCOUNT, CREDITCARD oder CASH
	 * @return Zweidimensionales Object-Array, enthaelt die Liste der Ausgaben.
	 * @throws Exception
	 *             Wenn bei der Abfrage der Ausgaben ein Fehler auftritt.
	 */
	public Object[][] getExpensesListing(String paymentMethod) throws Exception {
		readListing("expensesdetails", paymentMethod, null, null);
		return listing;
	}

	/**
	 * Liefert die Liste aller Ausgaben, abhaengig von der gewaehlten Zahlungsart
	 * sowie dem Anfangs- und Enddatum.
	 * 
	 * @param paymentMethod
	 *            Die Zahlungsweise, zu der die Abfrage ausgefuehrt werden soll.
	 *            Verwende die Konstanten ALL, ACCOUNT, CREDITCARD oder CASH
	 * @param from
	 *            Anfangsdatum, ab dem Transationen gesucht werden.
	 * @param to
	 *            Enddatum, bis zu dem Transaktionen gesucht werden.
	 * @return Zweidimensionales Object-Array, enthaelt die Liste der Ausgaben.
	 * @throws Exception
	 *             Wenn bei der Abfrage der Ausgaben ein Fehler auftritt.
	 */
	public Object[][] getExpensesListing(String paymentMethod, Date from,
			Date to) throws Exception {
		readListing("expensesdetails", paymentMethod, from, to);
		return listing;
	}

	/**
	 * Liefert die Liste aller Ausgaben fuer Wertgegenstaende, abhaengig von der
	 * gewaehlten Zahlungsart.
	 * 
	 * @param paymentMethod
	 *            Die Zahlungsweise, zu der die Abfrage ausgefuehrt werden soll.
	 *            Verwende die Konstanten ALL, ACCOUNT, CREDITCARD oder CASH
	 * @return Zweidimensionales Object-Array, enthaelt die Liste der Ausgaben.
	 * @throws Exception
	 *             Wenn bei der Abfrage der Ausgaben ein Fehler auftritt.
	 */
	public Object[][] getAssetsListing(String paymentMethod) throws Exception {
		readListing("assetsdetails", paymentMethod, null, null);
		for (Object[] row : listing) {
			if (row[6] == null)
				row[6] = false;
			else
				row[6] = true;
		}
		return listing;
	}

	/**
	 * Liefert die Liste aller Ausgaben fuer Wertgegenstaende, abhaengig von der
	 * gewaehlten Zahlungsart sowie dem Anfangs- und Enddatum.
	 * 
	 * @param paymentMethod
	 *            Die Zahlungsweise, zu der die Abfrage ausgefuehrt werden soll.
	 *            Verwende die Konstanten ALL, ACCOUNT, CREDITCARD oder CASH
	 * @param from
	 *            Anfangsdatum, ab dem Transationen gesucht werden.
	 * @param to
	 *            Enddatum, bis zu dem Transaktionen gesucht werden.
	 * @return Zweidimensionales Object-Array, enthaelt die Liste der Ausgaben.
	 * @throws Exception
	 *             Wenn bei der Abfrage der Ausgaben ein Fehler auftritt.
	 */
	public Object[][] getAssetsListing(String paymentMethod, Date from, Date to)
			throws Exception {
		readListing("assetsdetails", paymentMethod, from, to);
		for (Object[] row : listing) {
			if (row[6] == null)
				row[6] = false;
			else
				row[6] = true;
		}

		return listing;
	}
}
