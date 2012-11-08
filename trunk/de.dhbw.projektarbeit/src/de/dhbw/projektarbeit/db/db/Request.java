package de.dhbw.projektarbeit.db.db;

import java.sql.Connection;
import java.sql.SQLException;

import de.dhbw.projektarbeit.db.mysql.MysqlAccess;
import de.dhbw.projektarbeit.db.request.Delete;
import de.dhbw.projektarbeit.db.request.Insert;
import de.dhbw.projektarbeit.db.request.Update;

/**
 * @author BrunEis
 * 
 */
public class Request {
	/**
	 * Das Objekt, über das die DB-Verbindung aufgebaut wird.
	 */
	private MysqlAccess mysqlAccess;
	private Connection con;

	private Delete delete;
	private Insert insert;
	private Update update;

	/**
	 * Constructor, stellt die Datenbank-Verbindung her und initialisiert
	 * weitere Klassen, die für spätere Anfragen an die Datenbank benötigt
	 * werden.
	 * 
	 * @throws Exception
	 *             Wenn beim Verbindungsaufbau zur Datenbank ein Fehler auftritt
	 *             oder wenn der Datenbanktreiber nicht gefunden wird.
	 */
	public Request(String dbUrl, String schema, String user, String password)
			throws Exception {
		mysqlAccess = new MysqlAccess();
		mysqlAccess.setDbUrl(dbUrl);
		mysqlAccess.setUser(user);
		mysqlAccess.setPassword(password);

		con = mysqlAccess.getConnection();

		delete = new Delete(schema, con);
		insert = new Insert(schema, con);
		update = new Update(schema, con);

	}


	/**
	 * @return Das Delete Objekt. Es wird verwendet, um Einträge aus der
	 *         Datenbank zu löschen, beispielsweise weil sie irrtümlich doppelt
	 *         oder falsch eingegeben wurden.
	 */
	public Delete delete() {
		return delete;
	}

	/**
	 * @return Das Insert Objekt. Es wird verwendet, um alle Neu-Einträge in die
	 *         DB durchzuführen
	 */
	public Insert insert() {
		return insert;
	}

	/**
	 * @return Das Update Objekt. Es wird verwendet, um Änderungen an den
	 *         DB-Eintrögen durchzuführen.
	 */
	public Update update() {
		return update;
	}

	/**
	 * Dient zum Beenden der DB-Verbindung.
	 * 
	 * @throws SQLException
	 *             Wenn ein Fehler beim Verbindungsabbau auftritt.
	 */
	public void tidyUp() throws Exception {
		mysqlAccess.closeConnection();
	}

}
