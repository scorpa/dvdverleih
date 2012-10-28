package de.dhbw.projektarbeit.db.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Die Klasse DBAccess dient zur Verwaltung der DB-Verbindung. Sie stellt hierzu
 * Methoden zum Aufbau und Trennen, sowie Abfragen der Verbindung bereit.
 * 
 * @author reindan
 */
public class MysqlAccess {

	/**
	 * Das DB-Connection Objekt.
	 */
	private Connection con;

	/**
	 * Die URL und Credentials mit denen die DB-Verbindung aufgebaut wird.
	 */
	private String dbUrl="localhost/dvd_verleih", user="root", password="";
		
	/**
	 * Setzt die URL, mit deren Hilfe die Verbindung zur Datenbank aufgebaut
	 * wird.
	 * 
	 * @param dbUrl
	 *            String enthält die URL zu der Datenbank mit der die Verbindung
	 *            aufgebaut werden soll in der Form: hostname/dbname z.B.
	 *            192.168.1.131/dhbw
	 */
	public void setDbUrl(String dbUrl) {
		this.dbUrl = "jdbc:mysql://" + dbUrl;
	}

	/**
	 * Setzt den Benutzernamen, mit dem die Datenbankverbindung aufgebaut wird.
	 * 
	 * @param user
	 *            String enthaelt den Benutzernamen, mit dem die DB-Verbindung
	 *            aufgebaut wird.
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * Setzt das Passwort, mit dem die Datenbankverbindung aufgebaut wird.
	 * 
	 * @param password
	 *            String enthält das Passwort, mit dem die DB-Verbindung
	 *            aufgebaut wird.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Liefert das Connection Objekt zurueck, über das die Abfragen an die DB
	 * gestellt werden.
	 * 
	 * @return Das Connection Objekt.
	 * @throws Exception
	 * @throws ClassNotFoundException
	 *             Wenn die Treiberklasse nicht gefunden wird.
	 * @throws SQLException
	 *             Wenn ein Fehler beim Verbindungsaufbau auftritt.
	 */
	public Connection getConnection() throws Exception {
		try {
			// Den MySQL Treiber Laden
			Class.forName("com.mysql.jdbc.Driver");

			// Login Timeout auf 10s setzen
			DriverManager.setLoginTimeout(10);

			// DB-Verbindungsobjekt erzeugen
			con = DriverManager.getConnection("jdbc:mysql://localhost/dvd_verleih?user=root");

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Verbindung zum SQL Server fehlgeschlagen. Fehlercode 005");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new Exception(
					"Datenbank-Treiber konnte nicht gefunden werden! Fehlercode 009");
		}

		return con;
	}

	/**
	 * Schließt die Datenbankverbindung.
	 * 
	 * @throws Exception
	 *             Wenn beim Trennen der DB-Verbindung ein Fehler auftritt.
	 */
	public void closeConnection() throws Exception {
		try {
			if (con != null)
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Fehler beim Trennen der Datenbankverbindung.");
		}
	}
}
