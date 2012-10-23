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

import de.dhbw.projektarbeit.db.request.Insert;

public class NewAuthor extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtFirstname;
	private JTextField txtLastname;
	private JButton addButton;
	private JButton cancelButton;
	private boolean fromNewDVD = false;
	private Insert insert;
	private Connection con;
	private NewDVD newDVD;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NewAuthor dialog = new NewAuthor();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Standardkonstruktor
	 */
	public NewAuthor() {
		setWindow();
	}

	/**
	 * Konstruktor für den Aufruf aus dem newDVD Dialog
	 * @param newDVD --> Klasseninformationen
	 */
	public NewAuthor(NewDVD newDVD) {
		this.newDVD = newDVD;
		fromNewDVD = true;
		setWindow();
	}

	/**
	 * Generiert den JDialog
	 */
	private void setWindow() {

		setAlwaysOnTop(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setResizable(false);
		setTitle("Neuen Kameramann anlegen");
		setBounds(100, 100, 610, 155);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		JLabel lblVorname = new JLabel("Vorname");

		txtFirstname = new JTextField();
		txtFirstname.setText("FirstName");
		txtFirstname.setColumns(22);

		txtLastname = new JTextField();
		txtLastname.setText("LastName");
		txtLastname.setColumns(22);

		JLabel lblNachname = new JLabel("Nachname");
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_contentPanel
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								gl_contentPanel
										.createParallelGroup(Alignment.LEADING)
										.addComponent(txtFirstname,
												GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblVorname))
						.addGap(18)
						.addGroup(
								gl_contentPanel
										.createParallelGroup(Alignment.LEADING)
										.addComponent(txtLastname,
												GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNachname))
						.addContainerGap(20, Short.MAX_VALUE)));
		gl_contentPanel
				.setVerticalGroup(gl_contentPanel
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_contentPanel
										.createSequentialGroup()
										.addGroup(
												gl_contentPanel
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblVorname)
														.addComponent(
																lblNachname))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_contentPanel
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																txtFirstname,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																txtLastname,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addContainerGap(33, Short.MAX_VALUE)));
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				addButton = new JButton("Hinzuf\u00FCgen");
				addButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							addButtonActionPerforemd(arg0);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				addButton.setActionCommand("add");
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
							.addContainerGap(436, Short.MAX_VALUE)
							.addComponent(addButton)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cancelButton).addContainerGap()));
			gl_buttonPane.setVerticalGroup(gl_buttonPane.createParallelGroup(
					Alignment.LEADING).addGroup(
					Alignment.TRAILING,
					gl_buttonPane
							.createSequentialGroup()
							.addContainerGap(GroupLayout.DEFAULT_SIZE,
									Short.MAX_VALUE)
							.addGroup(
									gl_buttonPane
											.createParallelGroup(
													Alignment.BASELINE)
											.addComponent(cancelButton)
											.addComponent(addButton))
							.addContainerGap()));
			buttonPane.setLayout(gl_buttonPane);
		}
	}

	private void addButtonActionPerforemd(ActionEvent arg0) throws Exception {
		// Hinzufügen-Button gedrückt
		String firstName, lastName;
		boolean go = true;
		firstName = txtFirstname.getText();
		lastName = txtLastname.getText();

		// Auf leere Pflichtfelder überprüfen
		if (firstName.replaceAll(" ", "").equals("")) {
			go = false;
		} else if (lastName.replaceAll(" ", "").equals("")) {
			go = false;
		}
		
		// Aufrufen der Methode CreateRegisseur
		if (go == true) {
			try {
				con = DriverManager
						.getConnection("jdbc:mysql://localhost/dvd_verleih?user=root");
			} catch (SQLException e) {
				// Verbindung zum SQL Server fehlgeschlagen. Fehlercode 005
				e.printStackTrace();
				throw new Exception(
						"Verbindung zum SQL Server fehlgeschlagen. Fehlercode 005");
			}
			insert = new Insert("dvd_verleih",con);
			
			// Auf Aufruf aus dem NewDVD Dialog prüfen
			if (fromNewDVD = false){

			try {
				insert.insertAuthor(this,firstName, lastName);

			} catch (InvalidParameterException e) {
				// Fehlercode 002
				e.printStackTrace();
				throw new Exception(
						"Bei der Uebertragung der Parameter ist ein Fehler aufgetreten! Fehlercode: 002");
			}
			}
			else if (fromNewDVD = true){
				try {
					insert.insertAuthor(newDVD,this,firstName, lastName);

				} catch (InvalidParameterException e) {
					// Fehlercode 002
					e.printStackTrace();
					throw new Exception(
							"Bei der Uebertragung der Parameter ist ein Fehler aufgetreten! Fehlercode: 002");
				}
			}
		} else {
			JOptionPane
					.showMessageDialog(
							null,
							"Sie haben ein Pflichtfeld nicht ausgefüllt! Bitte überprüfen Sie ihre Angaben in den Feldern",
							"Regisseurerstellung", JOptionPane.ERROR_MESSAGE);

		}

	}

	public void authorAdded(String firstName, String lastName) {
		// Wenn Benutzer erfolgreich hinzu gefügt wurde, die mitteilen und
		// neues, leeres Eingabefenster öffnen.

		JOptionPane.showMessageDialog(null, ("Der Autor " + firstName + " "
				+ lastName + " wurde erfolgreich angelegt!"),
				"Vorgang erfolgreich", JOptionPane.INFORMATION_MESSAGE);
		this.setVisible(false);
		this.dispose();

		// Neues, leeres Erstellungsfenster instantiieren
		NewAuthor dialog = new NewAuthor();
		dialog.setVisible(true);

	}

}
