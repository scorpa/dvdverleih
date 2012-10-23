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
import de.dhbw.projektarbeit.db.request.Insert;

public class NewProducer extends JDialog {
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JButton addButton;
	private JButton cancelButton;
	private Insert insert;
	private Connection con;
	private NewDVD newDVD;
	private boolean fromNewDVD = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NewProducer dialog = new NewProducer();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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

	private void setWindow() {
		setTitle("Neuen Produzent anlegen");
		setModal(true);
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 155);
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
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
				addButton.setActionCommand("OK");
				getRootPane().setDefaultButton(addButton);
			}
			{
				cancelButton = new JButton("Cancel");
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
		txtFirstName.setText("FirstName");
		txtFirstName.setColumns(22);

		JLabel label = new JLabel("Vorname");

		JLabel label_1 = new JLabel("Nachname");

		txtLastName = new JTextField();
		txtLastName.setText("LastName");
		txtLastName.setColumns(22);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(
				Alignment.TRAILING).addGroup(
				gl_panel.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(label)
										.addComponent(txtFirstName,
												GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
						.addGap(101)
						.addGroup(
								gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(label_1)
										.addComponent(txtLastName,
												GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
						.addContainerGap(20, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(label)
												.addComponent(label_1))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(
														txtFirstName,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(
														txtLastName,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE))
								.addContainerGap(32, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);
	}

	private void addButtonActionPerformed(ActionEvent arg0) throws Exception {
		// Hinzufügen Button gedrückt
		String firstName, lastName;
		boolean go = true;
		firstName = txtFirstName.getText();
		lastName = txtLastName.getText();

		// Auf leere Pflichtfelder überprüfen
		if (firstName.replaceAll(" ", "").equals("")) {
			go = false;
		} else if (lastName.replaceAll(" ", "").equals("")) {
			go = false;
		}

		// Aufrufen der Methode CreateRegisseur
		if (go == true) {
			// Verbindung zum SQL Server aufbauen
			try {
				con = DriverManager
						.getConnection("jdbc:mysql://localhost/dvd_verleih?user=root");
			} catch (SQLException e) {
				// Verbindung zum SQL Server fehlgeschlagen. Fehlercode 005
				e.printStackTrace();
				throw new Exception(
						"Verbindung zum SQL Server fehlgeschlagen. Fehlercode 005");
			}
			insert = new Insert("dvd_verleih", con);

			// Auf Aufruf aus dem NewDVD Dialog prüfen
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
					insert.insertProducer(newDVD, this, firstName, lastName);

				} catch (InvalidParameterException e) {
					// Fehlercode 002
					e.printStackTrace();
					throw new Exception(
							"Bei der Uebertragung der Parameter ist ein Fehler aufgetreten! Fehlercode: 002");
				}
			}

			else {
				JOptionPane
						.showMessageDialog(
								null,
								"Sie haben ein Pflichtfeld nicht ausgefüllt! Bitte überprüfen Sie ihre Angaben in den Feldern",
								"Regisseurerstellung",
								JOptionPane.ERROR_MESSAGE);
			}

		}

	}

	public void productionAdded(String firstName, String lastName) {
		// Wenn Benutzer erfolgreich hinzu gefügt wurde, die mitteilen und
		// neues, leeres Eingabefenster öffnen.

		JOptionPane.showMessageDialog(null, ("Der Produzent " + firstName + " "
				+ lastName + " wurde erfolgreich angelegt!"),
				"Vorgang erfolgreich", JOptionPane.INFORMATION_MESSAGE);
		this.setVisible(false);
		this.dispose();

		// Neues, leeres Erstellungsfenster instantiieren
		NewRegisseur dialog = new NewRegisseur();
		dialog.setVisible(true);

	}
}
