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

public class NewCamera extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtFirstname;
	private JTextField txtLastname;
	private JButton addButton;
	private JButton cancelButton;
	private boolean fromNewDVD = false, vorhanden = false;
	private Insert insert;
	private Connection con;
	private NewDVD newDVD = null;
	private EditDVD editDVD = null;
	private Check check;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NewCamera dialog = new NewCamera();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Standardkonstruktor
	 */
	public NewCamera() {
		setWindow();
	}

	/**
	 * Konstruktor fŸr den Aufruf aus dem newDVD Dialog
	 * 
	 * @param newDVD
	 *            --> Klasseninformationen
	 */
	public NewCamera(NewDVD newDVD) {
		this.newDVD = newDVD;
		fromNewDVD = true;
		setWindow();
	}

	/**
	 * Konstruktor fŸr den Aufruf aus dem editDVD Dialog
	 * 
	 * @param newDVD
	 *            --> Klasseninformationen
	 */
	public NewCamera(EditDVD editDVD) {
		this.editDVD = editDVD;
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
		txtFirstname.setColumns(22);

		txtLastname = new JTextField();
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
							// auf ActionListener reagieren
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
				cancelButton = new JButton("Abbrechen");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						// Fenster schlie§en
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
		// HinzufŸgen-Button gedrŸckt
		String firstName, lastName;
		boolean go = true;
		firstName = txtFirstname.getText();
		lastName = txtLastname.getText();

		// Auf leere Pflichtfelder ŸberprŸfen
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
			MysqlAccess mysql = new MysqlAccess();
			check = new Check("dvd_verleih", mysql.getConnection());

			// Check durchführen, ob Name des Autors schon vorhanden
			vorhanden = check.check("camera", "Camera_ID", firstName, lastName);

			if (vorhanden == false) {
				insert = new Insert("dvd_verleih", mysql.getConnection());
				// Auf Aufruf aus dem NewDVD oder EditDVD Dialog prŸfen
				if (fromNewDVD == false) {

					try {
						insert.insertCamera(this, firstName, lastName);
					} catch (InvalidParameterException e) {
						// Fehlercode 002
						e.printStackTrace();
						throw new Exception(
								"Bei der Uebertragung der Parameter ist ein Fehler aufgetreten! Fehlercode: 002");
					}
				} else if (fromNewDVD == true) {
					try {
						if (newDVD != null) {
							insert.insertCamera(newDVD, this, firstName,
									lastName);
						} else if (editDVD != null) {
							insert.insertCamera(editDVD, this, firstName,
									lastName);
						} else {
							insert.insertCamera(this, firstName, lastName);
						}

					} catch (InvalidParameterException e) {
						// Fehlercode 002
						e.printStackTrace();
						throw new Exception(
								"Bei der Uebertragung der Parameter ist ein Fehler aufgetreten! Fehlercode: 002");
					}
				}
			} else {
				JOptionPane.showMessageDialog(this, "Der Kameramann \""
						+ firstName + " " + lastName
						+ "\" ist bereits vorhanden!",
						"Neuen Kameramann anlegen", JOptionPane.ERROR_MESSAGE);
			}

		} else {
			JOptionPane
					.showMessageDialog(
							this,
							"Sie haben ein Pflichtfeld nicht ausgefüllt! Bitte überprüfen Sie ihre Angaben in den Feldern",
							"Regisseurerstellung", JOptionPane.ERROR_MESSAGE);

		}
	}

	public void cameraAdded(String firstName, String lastName) {
		// Wenn Benutzer erfolgreich hinzu gefügt wurde, die mitteilen und
		// neues, leeres Eingabefenster öffnen.

		JOptionPane.showMessageDialog(this, ("Der Kameramann \"" + firstName
				+ " " + lastName + "\" wurde erfolgreich angelegt!"),
				"Vorgang erfolgreich", JOptionPane.INFORMATION_MESSAGE);
		this.setVisible(false);
		this.dispose();

	}

}
