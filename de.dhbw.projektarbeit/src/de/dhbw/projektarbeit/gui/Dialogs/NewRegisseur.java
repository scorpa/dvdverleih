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
 * Klasse, die das Erstellen eines Regisseurs erm�glicht
 * 
 * @author Brunner
 * 
 */
public class NewRegisseur extends JDialog {

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
	 * Standardkonstruktor
	 */
	public NewRegisseur() {
		setWindow();
	}

	/**
	 * Konstruktor f�r den Aufruf aus dem newDVD Dialog
	 * 
	 * @param newDVD
	 *            --> Klasseninformationen
	 */
	public NewRegisseur(NewDVD newDVD) {
		this.newDVD = newDVD;
		fromNewDVD = true;
		setWindow();
	}

	/**
	 * Konstruktor f�r den Aufruf aus dem editDVD Dialog
	 * 
	 * @param editDVD
	 *            --> Klasseninformationen
	 */
	public NewRegisseur(EditDVD editDVD) {
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
		setTitle("Neuen Regisseur anlegen");
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
							addButtonActionPerforemd(arg0);
						} catch (Exception e) {
							// TODO Auto-generated catch block
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

	/**
	 * Hinzuf�gen-Button wurde gedr�ckt
	 * 
	 * @param arg0
	 *            --> Eventhandling
	 * @throws Exception
	 *             --> Exceptionhandling
	 */
	private void addButtonActionPerforemd(ActionEvent arg0) throws Exception {
		// Hinzufuegen-Button gedrueckt
		String firstName, lastName;
		boolean go = true;
		firstName = txtFirstname.getText();
		lastName = txtLastname.getText();

		// Auf leere Pflichtfelder ueberpruefen
		if (firstName.replaceAll(" ", "").equals("")) {
			go = false;
		} else if (lastName.replaceAll(" ", "").equals("")) {
			go = false;
		}

		// Wenn Leerzeichen im Vornamen eingegeben wurden, werden diese gel�scht
		while (firstName.indexOf(" ") == 0) {
			firstName = firstName.substring(1);
		}

		// Wenn Leerzeichen im Nachnamen eingegeben wurden, werden diese
		// gel�scht
		while (lastName.indexOf(" ") == 0) {
			lastName = lastName.substring(1);
		}

		// Aufrufen der Methode CreateRegisseur
		if (go == true) {
			MysqlAccess mysql = new MysqlAccess();
			check = new Check("dvd_verleih", mysql.getConnection());

			// Check durchf�hren, ob Name des Autors schon vorhanden
			vorhanden = check.check("regisseur", "Regie_ID", firstName,
					lastName);

			if (vorhanden == false) {

				insert = new Insert("dvd_verleih", mysql.getConnection());

				// Auf Aufruf aus dem NewDVD oder EditDVD Dialog pruefen
				if (fromNewDVD = false) {
					try {
						// Regisseur INSERT aufrufen
						insert.insertRegisseur(this, firstName, lastName);
					} catch (InvalidParameterException e) {
						// Fehlercode 002
						e.printStackTrace();
						throw new Exception(
								"Bei der Uebertragung der Parameter ist ein Fehler aufgetreten! Fehlercode: 002");
					}
				} else if (fromNewDVD = true) {
					try {
						// Regisseur INSERT aufrufen
						if (newDVD != null) {
							insert.insertRegisseur(newDVD, this, firstName,
									lastName);
						} else if (editDVD != null) {
							insert.insertRegisseur(editDVD, this, firstName,
									lastName);
						} else {
							insert.insertRegisseur(this, firstName, lastName);
						}
					} catch (InvalidParameterException e) {
						// Fehlercode 002
						e.printStackTrace();
						throw new Exception(
								"Bei der Uebertragung der Parameter ist ein Fehler aufgetreten! Fehlercode: 002");
					}
				}

			} else {
				JOptionPane.showMessageDialog(this, "Der Regisseur \""
						+ firstName + " " + lastName
						+ "\" ist bereits vorhanden!",
						"Neuen Regisseur anlegen", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane
					.showMessageDialog(
							this,
							"Sie haben ein Pflichtfeld nicht ausgef�llt! Bitte �berpr�fen Sie ihre Angaben in den Feldern",
							"Regisseurerstellung", JOptionPane.ERROR_MESSAGE);
		}

	}

	/**
	 * R�ckmeldung der Insert-Methode, dass der Eintrag erfolgreich hinzu gef�gt
	 * wurde
	 * 
	 * @param firstName
	 *            --> Vorname des Eintrags
	 * @param lastName
	 *            --> Nachname des Eintrags
	 */
	public void regisseurAdded(String firstName, String lastName) {
		// Wenn Benutzer erfolgreich hinzu gef�gt wurde, die mitteilen und
		// neues, leeres Eingabefenster �ffnen.
		JOptionPane.showMessageDialog(this, ("Der Regisseur \"" + firstName
				+ " " + lastName + "\" wurde erfolgreich angelegt!"),
				"Vorgang erfolgreich", JOptionPane.INFORMATION_MESSAGE);
		this.setVisible(false);
		this.dispose();

	}
}
