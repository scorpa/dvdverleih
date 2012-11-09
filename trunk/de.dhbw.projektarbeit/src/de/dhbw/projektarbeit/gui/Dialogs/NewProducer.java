package de.dhbw.projektarbeit.gui.Dialogs;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.InvalidParameterException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import de.dhbw.projektarbeit.db.mysql.MysqlAccess;
import de.dhbw.projektarbeit.db.request.Check;
import de.dhbw.projektarbeit.db.request.Insert;

/**
 * Methode zum Anlegen eines neuen Produzenten
 * @author Brunner
 *
 */
public class NewProducer extends JDialog {
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JButton addButton;
	private JButton cancelButton;
	private Insert insert;
	private Connection con;
	private NewDVD newDVD = null;
	private EditDVD editDVD = null;
	private boolean fromNewDVD = false, vorhanden = false;
	private Check check;

	/**
	 * Standardkonstruktor
	 */
	public NewProducer() {
		setWindow();
	}

	/**
	 * Konstruktor für den Aufruf aus dem newDVD Dialog
	 * 
	 * @param newDVD
	 *            --> Klasseninformationen
	 */
	public NewProducer(NewDVD newDVD) {
		this.newDVD = newDVD;
		fromNewDVD = true;
		setWindow();
	}

	/**
	 * Konstruktor für den Aufruf aus dem editDVD Dialog
	 * 
	 * @param newDVD
	 *            --> Klasseninformationen
	 */
	public NewProducer(EditDVD editDVD) {
		this.editDVD = editDVD;
		fromNewDVD = true;
		setWindow();
	}

	private void setWindow() {
		setTitle("Neuen Produzent anlegen");
		setModal(true);
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 155);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				addButton = new JButton("Hinzuf\u00FCgen");
				addButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							addButtonActionPerformed(arg0);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				addButton.setActionCommand("OK");
				getRootPane().setDefaultButton(addButton);
			}
			{
				cancelButton = new JButton("Abbrechen");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
			}
			GroupLayout gl_buttonPane = new GroupLayout(buttonPane);
			gl_buttonPane.setHorizontalGroup(gl_buttonPane.createParallelGroup(
					Alignment.TRAILING).addGroup(
					gl_buttonPane.createSequentialGroup()
							.addContainerGap(326, Short.MAX_VALUE)
							.addComponent(addButton)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cancelButton).addContainerGap()));
			gl_buttonPane.setVerticalGroup(gl_buttonPane.createParallelGroup(
					Alignment.TRAILING).addGroup(
					gl_buttonPane
							.createSequentialGroup()
							.addContainerGap(GroupLayout.DEFAULT_SIZE,
									Short.MAX_VALUE)
							.addGroup(
									gl_buttonPane
											.createParallelGroup(
													Alignment.BASELINE)
											.addComponent(addButton)
											.addComponent(cancelButton))
							.addContainerGap()));
			buttonPane.setLayout(gl_buttonPane);
		}

		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(panel, BorderLayout.CENTER);

		txtFirstName = new JTextField();
		txtFirstName.setColumns(22);

		JLabel label = new JLabel("Vorname");

		JLabel label_1 = new JLabel("Nachname");

		txtLastName = new JTextField();
		txtLastName.setColumns(22);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(
				Alignment.TRAILING).addGroup(
				Alignment.LEADING,
				gl_panel.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(label)
										.addComponent(txtFirstName,
												GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(
								gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(label_1)
										.addComponent(txtLastName,
												GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
						.addContainerGap(123, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.TRAILING)
												.addGroup(
														gl_panel.createSequentialGroup()
																.addComponent(
																		label_1)
																.addPreferredGap(
																		ComponentPlacement.RELATED)
																.addComponent(
																		txtLastName,
																		GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE))
												.addGroup(
														gl_panel.createSequentialGroup()
																.addComponent(
																		label)
																.addPreferredGap(
																		ComponentPlacement.RELATED)
																.addComponent(
																		txtFirstName,
																		GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE)))
								.addContainerGap(32, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);
	}
	/**
	 * Hinzufügen-Button wurde gedrückt
	 * @param arg0 --> Eventhandling
	 * @throws Exception --> Exceptionhandling
	 */
	private void addButtonActionPerformed(ActionEvent arg0) throws Exception {
		// Hinzufuegen Button gedrueckt
		String firstName, lastName;
		boolean go = true;
		firstName = txtFirstName.getText();
		lastName = txtLastName.getText();

		// Auf leere Pflichtfelder ueberpruefen
		if (firstName.replaceAll(" ", "").equals("")) {
			go = false;
		} else if (lastName.replaceAll(" ", "").equals("")) {
			go = false;
		}

		// Wenn Leerzeichen im Vornamen eingegeben wurden, werden diese gelöscht
		while (firstName.indexOf(" ") == 0) {
			firstName = firstName.substring(1);
		}

		// Wenn Leerzeichen im Nachnamen eingegeben wurden, werden diese
		// gelöscht
		while (lastName.indexOf(" ") == 0) {
			lastName = lastName.substring(1);
		}

		// Aufrufen der Methode CreateRegisseur
		if (go == true) {
			// Verbindung zum SQL Server aufbauen
			MysqlAccess mysql = new MysqlAccess();
			check = new Check("dvd_verleih", mysql.getConnection());
			// Check durchführen, ob Name des Autors schon vorhanden
			vorhanden = check.check("production", "Production_ID", firstName,
					lastName);

			if (vorhanden == false) {
				insert = new Insert("dvd_verleih", mysql.getConnection());

				// Auf Aufruf aus dem NewDVD oder EditDVD Dialog prüfen
				if (fromNewDVD = false) {
					try {
						insert.insertProducer(this, firstName, lastName);

					} catch (InvalidParameterException e) {
						// Fehlercode 002
						e.printStackTrace();
						throw new Exception(
								"Bei der Uebertragung der Parameter ist ein Fehler aufgetreten! Fehlercode: 002");
					}
				} else if (fromNewDVD = true) {
					try {
						if (newDVD != null) {
							insert.insertProducer(newDVD, this, firstName,
									lastName);
						} else if (editDVD != null) {
							insert.insertProducer(editDVD, this, firstName,
									lastName);
						} else {
							insert.insertProducer(this, firstName, lastName);
						}

					} catch (InvalidParameterException e) {
						// Fehlercode 002
						e.printStackTrace();
						throw new Exception(
								"Bei der Uebertragung der Parameter ist ein Fehler aufgetreten! Fehlercode: 002");
					}
				}

			} else {
				JOptionPane.showMessageDialog(this, "Der Produzent \""
						+ firstName + " " + lastName
						+ "\" ist bereits vorhanden!",
						"Neuen Produzenten anlegen", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane
					.showMessageDialog(
							this,
							"Sie haben ein Pflichtfeld nicht ausgefüllt! Bitte überprüfen Sie ihre Angaben in den Feldern",
							"Regisseurerstellung", JOptionPane.ERROR_MESSAGE);
		}
	}
	/**
	 * Methode zur Rückmeldung, wenn der Produzent erfolgreich hinzugefügt wurde
	 * @param firstName --> Vorname
	 * @param lastName --> Nachname
	 */
	public void productionAdded(String firstName, String lastName) {
		// Wenn Benutzer erfolgreich hinzu gefügt wurde, die mitteilen und
		// neues, leeres Eingabefenster öffnen.

		JOptionPane.showMessageDialog(this, ("Der Produzent \"" + firstName
				+ " " + lastName + "\" wurde erfolgreich angelegt!"),
				"Vorgang erfolgreich", JOptionPane.INFORMATION_MESSAGE);
		this.setVisible(false);
		this.dispose();

	}
}
