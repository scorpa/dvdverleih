package de.dhbw.projektarbeit.db.request;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Verwaltet die moeglichen Zahlungsweisen, sowie die Einnahmen- und
 * Ausgabenkategorien
 * 
 * @author reindan
 */
public class ControlData {

	private String schema;

	private Connection con;
	private Statement stmt;
	private ResultSet rset;

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
	public ControlData(String schema, Connection con) {
		this.schema = schema;
		this.con = con;
	}

	/**
	 * Liefert die Liste der zur Verfuegung stehenden Zahlungsarten.
	 * 
	 * @return ArrayList mit den moeglichen Zahlungsarten als Strings.
	 * @throws Exception
	 *             Wenn ein Fehler bei der DB-Abfrage auftritt.
	 */
	public ArrayList<String> getPaymentMethods() throws Exception {
		ArrayList<String> paymentMethods = new ArrayList<String>();
		try {
			// Statement erstellen
			stmt = con.createStatement();

			// Select-String erstellen
			StringBuffer selectString = new StringBuffer();
			selectString.append("SELECT name FROM `");
			selectString.append(schema);
			selectString.append("`.`paymentmethod` ORDER BY ID");

			// DB-Abfrage durchfuehren
			rset = stmt.executeQuery(selectString.toString());

			// Abfrageergebnisse in die String-Array schreiben
			while (rset.next()) {
				paymentMethods.add(rset.getString("name"));
			}

		} catch (Exception e) {
			// e.printStackTrace();
			throw new Exception(
					"Fehler beim Datenbankzugriff. Liste der Zahlungsmittel konnte nicht geladen werden.");
		}
		return paymentMethods;
	}

	/**
	 * Liefert die Liste der zur Verfuegung stehenden Einnahmearten.
	 * 
	 * @return ArrayList mit den moeglichen Einnahmearten als Strings.
	 * @throws Exception
	 *             Wenn ein Fehler bei der DB-Abfrage auftritt.
	 */
	public ArrayList<String> getEarningTypes() throws Exception {
		ArrayList<String> earningTypes = new ArrayList<String>();
		try {
			// Statement erstellen
			stmt = con.createStatement();

			// Select-String erstellen
			StringBuffer selectString = new StringBuffer();
			selectString.append("SELECT title FROM `");
			selectString.append(schema);
			selectString.append("`.`earningtype` ORDER BY title");

			// DB-Abfrage durchfuehren
			rset = stmt.executeQuery(selectString.toString());

			// Abfrageergebnisse in die String-Array schreiben
			while (rset.next()) {
				earningTypes.add(rset.getString("title"));
			}

		} catch (Exception e) {
			// e.printStackTrace();
			throw new Exception(
					"Fehler beim Datenbankzugriff. Liste der Kategorien konnte nicht geladen werden.");
		}
		return earningTypes;
	}

	/**
	 * Liefert die Liste der zur Verfuegung stehenden Ausgabearten.
	 * 
	 * @return ArrayList mit den moeglichen Ausgabearten als Strings.
	 * @throws Exception
	 *             Wenn ein Fehler bei der DB-Abfrage auftritt.
	 */
	public ArrayList<String> getExpenseTypes() throws Exception {
		ArrayList<String> expenseTypes = new ArrayList<String>();
		try {
			// Statement erstellen
			stmt = con.createStatement();

			// Select-String erstellen
			StringBuffer selectString = new StringBuffer();
			selectString.append("SELECT title FROM `");
			selectString.append(schema);
			selectString.append("`.`expensetype` ORDER BY title");

			// DB-Abfrage durchfuehren
			rset = stmt.executeQuery(selectString.toString());

			// Abfrageergebnisse in die String-Array schreiben
			while (rset.next()) {
				expenseTypes.add(rset.getString("title"));
			}

		} catch (Exception e) {
			// e.printStackTrace();
			throw new Exception(
					"Fehler beim Datenbankzugriff. Liste der Kategorien konnte nicht geladen werden.");
		}
		return expenseTypes;
	}

	/**
	 * Liefert die Liste der zur Verfuegung stehenden Kategorien inkl. der ID,
	 * abhaengig von der gewahlten Buchungsart. Die Liste wird sortiert nach dem
	 * Namen der Kategorie geliefert.
	 * 
	 * @param type
	 *            Die Art der Kategorie, <li>Einnahme: ControlData.EARNING</li>
	 *            <li>Ausgabe: ControlData.EXPENSE</li>
	 * @return Zweidimensionales Object-Array, Object[][] Enthaelt Arrays in der
	 *         Form {long ID, String Title}
	 * @throws Exception
	 *             Wenn ein Fehler bei der DB-Abfrage auftritt.
	 */
	public Object[][] getCategoryWithId(int type) throws Exception {
		Object[][] listing = null;
		try {
			// Statement erstellen
			stmt = con.createStatement();

			// Select-String erstellen
			StringBuffer selectString = new StringBuffer();
			selectString.append("SELECT * FROM `");
			selectString.append(schema);
			if (type == ControlData.EARNING)
				selectString.append("`.`earningtype` ORDER BY title");
			else if (type == ControlData.EXPENSE)
				selectString.append("`.`expensetype` ORDER BY title");

			// DB-Abfrage durchfuehren
			rset = stmt.executeQuery(selectString.toString());

			ArrayList<Object[]> results = new ArrayList<Object[]>();
			// Abfrageergebnisse ArrayList schreiben
			while (rset.next()) {
				results.add(new Object[] { rset.getObject(1), rset.getObject(2) });
			}

			// Ergebnisse in zweidimensionales Object-Array uebertragen
			listing = new Object[results.size()][2];
			for (int i = 0; i < listing.length; i++) {
				listing[i] = results.get(i);
			}

		} catch (Exception e) {
			// e.printStackTrace();
			throw new Exception(
					"Fehler beim Datenbankzugriff. Liste der Kategorien konnte nicht geladen werden.");
		}
		return listing;
	}

	/**
	 * Dient zum Eintragen einer neuen Einnahmen-Kategorie in die Datenbank.
	 * 
	 * @param name
	 *            String enthaelt den Titel der Kategorie
	 * @throws Exception
	 *             Wenn beim Eintragen ein Fehler auftritt
	 */
	public void insertEarningType(String name) throws Exception {
		try {
			StringBuffer insertString = new StringBuffer();
			insertString.append("INSERT INTO ");
			insertString.append(schema);
			insertString.append(".earningtype (title) VALUES (\"");
			insertString.append(name);
			insertString.append("\")");

			stmt = con.createStatement();
			stmt.executeUpdate(insertString.toString());
		} catch (Exception e) {
			throw new Exception("Fehler beim Eintragen der Kategoriedaten.");
		}
	}

	/**
	 * Dient zum Eintragen einer neuen Ausgaben-Kategorie in die Datenbank.
	 * 
	 * @param name
	 *            String enthaelt den Titel der Kategorie
	 * @throws Exception
	 *             Wenn beim Eintragen ein Fehler auftritt
	 */
	public void insertExpenseType(String name) throws Exception {
		try {
			StringBuffer insertString = new StringBuffer();
			insertString.append("INSERT INTO ");
			insertString.append(schema);
			insertString.append(".expensetype (title) VALUES (\"");
			insertString.append(name);
			insertString.append("\")");

			System.out.println(insertString.toString());

			stmt = con.createStatement();
			stmt.executeUpdate(insertString.toString());
		} catch (Exception e) {
			throw new Exception("Fehler beim Eintragen der Kategoriedaten.");
		}
	}

	/**
	 * Dient zum Loeschen einer Einnahmen-Kategorie aus der Datenbank. Sie kann
	 * nur geloescht werden, wenn die Kategorie nicht verwendet wird.
	 * 
	 * @param id
	 *            Die ID der zu loeschenden Kategorie
	 * @throws Exception
	 *             Wenn die Kategorie noch verwendet wird oder ein sonstiger
	 *             DB-Fehler auftritt.
	 */
	public void deleteEarningType(long id) throws Exception {
		try {
			StringBuffer deleteString = new StringBuffer();
			deleteString.append("DELETE FROM ");
			deleteString.append(schema);
			deleteString.append(".earningtype WHERE id=");
			deleteString.append(id);

			stmt = con.createStatement();
			stmt.executeUpdate(deleteString.toString());
		} catch (SQLException e) {
			throw new Exception(
					"Kategorie wird verwendet und kann nicht gelöscht werden");
		}
	}

	/**
	 * Dient zum Loeschen einer Einnahmen-Kategorie aus der Datenbank. Sie kann
	 * nur geloescht werden, wenn die Kategorie nicht verwendet wird.
	 * 
	 * @param id
	 *            Die ID der zu loeschenden Kategorie
	 * @throws Exception
	 *             Wenn die Kategorie noch verwendet wird oder ein sonstiger
	 *             DB-Fehler auftritt.
	 */
	public void deleteExpenseType(long id) throws Exception {
		try {
			StringBuffer deleteString = new StringBuffer();
			deleteString.append("DELETE FROM ");
			deleteString.append(schema);
			deleteString.append(".expensetype WHERE id=");
			deleteString.append(id);

			stmt = con.createStatement();
			stmt.executeUpdate(deleteString.toString());
		} catch (SQLException e) {
			throw new Exception(
					"Kategorie wird verwendet und kann nicht gelöscht werden");
		}
	}

	/**
	 * Aendert den Namen einer Einnahmen-Kategorie.
	 * 
	 * @param id
	 *            Die ID der zu aendernden Kategorie
	 * @param title
	 *            Der neue Titel der zu aendernden Kategorie
	 * @throws Exception
	 *             Wenn beim Eintrag in die DB ein Fehler auftritt
	 */
	public void updateEarningType(long id, String title) throws Exception {
		try {
			StringBuffer updateString = new StringBuffer();
			updateString.append("UPDATE ");
			updateString.append(schema);
			updateString.append(".earningtype SET title=\"");
			updateString.append(title);
			updateString.append("\" WHERE id=");
			updateString.append(id);

			stmt = con.createStatement();
			stmt.executeUpdate(updateString.toString());
		} catch (SQLException e) {
			throw new Exception("Fehler beim Ändern der Kategorie.");
		}
	}

	/**
	 * Aendert den Namen einer Ausgaben-Kategorie.
	 * 
	 * @param id
	 *            Die ID der zu aendernden Kategorie
	 * @param title
	 *            Der neue Titel der zu aendernden Kategorie
	 * @throws Exception
	 *             Wenn beim Eintrag in die DB ein Fehler auftritt
	 */
	public void updateExpenseType(long id, String title) throws Exception {
		try {
			StringBuffer updateString = new StringBuffer();
			updateString.append("UPDATE ");
			updateString.append(schema);
			updateString.append(".expensetype SET title=\"");
			updateString.append(title);
			updateString.append("\" WHERE id=");
			updateString.append(id);

			stmt = con.createStatement();
			stmt.executeUpdate(updateString.toString());
		} catch (SQLException e) {
			throw new Exception("Fehler beim Ändern der Kategorie.");
		}
	}
}
