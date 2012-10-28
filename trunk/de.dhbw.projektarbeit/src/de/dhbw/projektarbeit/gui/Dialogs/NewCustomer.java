package de.dhbw.projektarbeit.gui.Dialogs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.security.InvalidParameterException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

import org.jdesktop.swingx.JXDatePicker;

import de.dhbw.projektarbeit.db.request.Insert;

public class NewCustomer extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private SimpleDateFormat sdf;
	private Date dateBirthdate;
	private JTextField txtFirstname, txtLastname, txtZipcode, txtCity, txtEmail, txtStreet, txtStreetno, txtTelefone;
	private JButton cancelButton;
	private JXDatePicker dpBirthdate; 
	private Connection con;
	private Insert insert;
	private Pattern pattern;
	private Matcher matcher;
	private static final String EMAIL_PATTERN = 
		"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NewCustomer dialog = new NewCustomer();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * Create the dialog.
	 */
	public NewCustomer() {
		setModal(true);
		setResizable(false);
		setMaximumSize(new Dimension(600, 275));
		setMinimumSize(new Dimension(600, 275));
		setLocation(new Point(50, 50));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Neuen Kunden anlegen");
		setBounds(100, 100, 600, 275);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setToolTipText("");
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		JLabel lblVorname = new JLabel("Vorname");

		txtFirstname = new JTextField();
		txtFirstname.setColumns(22);

		txtLastname = new JTextField();
		txtLastname.setColumns(22);

		JLabel lblNachname = new JLabel("Nachname");

		JLabel lblPostleitzahl = new JLabel("Postleitzahl");

		txtZipcode = new JTextField();
		txtZipcode.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				txtZipcodeKeyReleased(arg0);
			}
		});
		txtZipcode.setColumns(5);

		txtCity = new JTextField();
		txtCity.setColumns(10);

		JLabel lblOrt = new JLabel("Ort");

		JLabel lblEmailAdresse = new JLabel("E-Mail Adresse");

		txtEmail = new JTextField();
		txtEmail.setColumns(22);

		txtStreet = new JTextField();
		txtStreet.setColumns(15);

		txtStreetno = new JTextField();
		txtStreetno.setColumns(5);

		JLabel lblStrasseUndHausnummer = new JLabel("Strasse und Hausnummer");

		txtTelefone = new JTextField();
		txtTelefone.setColumns(15);

		JLabel lblTelefonnummer = new JLabel("Telefonnummer");

		// Festlegung des Formats über die JXDate Funktion
		sdf = new SimpleDateFormat();
		sdf.applyPattern("yyyy-MM-dd");

		dpBirthdate = new JXDatePicker();
		
		dpBirthdate.setFormats(new String[] { "dd.MM.yyyy" });
		dpBirthdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Überfürung des JXDates in die Datevariable
				try {
					dateBirthdate = (Date.valueOf(sdf.format(dpBirthdate.getDate())));
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				}
				
			}

		});

		JLabel lblGeburtsdatum = new JLabel("Geburtsdatum");
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel
				.setHorizontalGroup(gl_contentPanel
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								gl_contentPanel
										.createSequentialGroup()
										.addGroup(
												gl_contentPanel
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_contentPanel
																		.createParallelGroup(
																				Alignment.LEADING,
																				false)
																		.addGroup(
																				gl_contentPanel
																						.createSequentialGroup()
																						.addContainerGap()
																						.addComponent(
																								txtFirstname,
																								GroupLayout.PREFERRED_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.PREFERRED_SIZE))
																		.addGroup(
																				gl_contentPanel
																						.createSequentialGroup()
																						.addContainerGap()
																						.addGroup(
																								gl_contentPanel
																										.createParallelGroup(
																												Alignment.LEADING)
																										.addComponent(
																												txtZipcode,
																												GroupLayout.PREFERRED_SIZE,
																												GroupLayout.DEFAULT_SIZE,
																												GroupLayout.PREFERRED_SIZE)
																										.addComponent(
																												lblPostleitzahl))
																						.addPreferredGap(
																								ComponentPlacement.UNRELATED)
																						.addGroup(
																								gl_contentPanel
																										.createParallelGroup(
																												Alignment.LEADING)
																										.addComponent(
																												lblOrt)
																										.addComponent(
																												txtCity))))
														.addGroup(
																gl_contentPanel
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				txtEmail,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																gl_contentPanel
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				lblVorname))
														.addGroup(
																gl_contentPanel
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				lblEmailAdresse)))
										.addGap(18)
										.addGroup(
												gl_contentPanel
														.createParallelGroup(
																Alignment.LEADING,
																false)
														.addGroup(
																gl_contentPanel
																		.createSequentialGroup()
																		.addComponent(
																				txtStreet,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				txtStreetno,
																				82,
																				82,
																				82))
														.addGroup(
																gl_contentPanel
																		.createSequentialGroup()
																		.addGroup(
																				gl_contentPanel
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								txtTelefone,
																								GroupLayout.PREFERRED_SIZE,
																								144,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								lblTelefonnummer))
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addGroup(
																				gl_contentPanel
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								lblGeburtsdatum)
																						.addComponent(
																								dpBirthdate,
																								GroupLayout.PREFERRED_SIZE,
																								131,
																								GroupLayout.PREFERRED_SIZE)))
														.addComponent(
																txtLastname)
														.addComponent(
																lblNachname)
														.addComponent(
																lblStrasseUndHausnummer))
										.addContainerGap()));
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
																lblNachname)
														.addComponent(
																lblVorname))
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
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addGroup(
												gl_contentPanel
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(lblOrt)
														.addComponent(
																lblStrasseUndHausnummer)
														.addComponent(
																lblPostleitzahl))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_contentPanel
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																txtZipcode,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																txtCity,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																txtStreet,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																txtStreetno,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addGroup(
												gl_contentPanel
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblGeburtsdatum)
														.addComponent(
																lblTelefonnummer)
														.addComponent(
																lblEmailAdresse))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_contentPanel
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																txtEmail,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																txtTelefone,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																dpBirthdate,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addContainerGap(76, Short.MAX_VALUE)));
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						cancelButtonActionPerfomred(arg0);
					}

				});
				cancelButton.setActionCommand("Cancel");
			}

			JButton btnAddcustomer = new JButton("Hinzufuegen");
			btnAddcustomer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						btnAddcustomerActionPerformed(arg0);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			});
			GroupLayout gl_buttonPane = new GroupLayout(buttonPane);
			gl_buttonPane.setHorizontalGroup(gl_buttonPane.createParallelGroup(
					Alignment.TRAILING).addGroup(
					gl_buttonPane.createSequentialGroup()
							.addContainerGap(428, Short.MAX_VALUE)
							.addComponent(btnAddcustomer)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(cancelButton).addGap(4)));
			gl_buttonPane.setVerticalGroup(gl_buttonPane.createParallelGroup(
					Alignment.LEADING).addGroup(
					gl_buttonPane
							.createSequentialGroup()
							.addGap(5)
							.addGroup(
									gl_buttonPane
											.createParallelGroup(
													Alignment.BASELINE)
											.addComponent(cancelButton)
											.addComponent(btnAddcustomer))
							.addContainerGap(GroupLayout.DEFAULT_SIZE,
									Short.MAX_VALUE)));
			buttonPane.setLayout(gl_buttonPane);
		}
	}


	protected void txtZipcodeKeyReleased(KeyEvent arg0) {
		// Prüfung ob eine Zahl oder anderes Zeichen eingegeben wurde oder länger als 11 Zeichen ist
		String text = txtZipcode.getText();
		if (txtZipcode.getText().matches("[0-9]*")) {

		} else {
			txtZipcode.setText(text.substring(0, text.length() - 1));
		}
		
		if (text.length() > 5){
			txtZipcode.setText(text.substring(0, text.length() - 1));
			JOptionPane
			.showMessageDialog(
					null,
					"Die Postleitzahl darf nur 5 Stellen haben!",
					"Postleitzahl", JOptionPane.ERROR_MESSAGE);
			
		}
	}

	
	private void btnAddcustomerActionPerformed(ActionEvent arg0)
			throws Exception {
		// Check, ob alle Felder ausgefüllt sind
		String firstName = null, lastName = null, city = null, street = null, streetNo = null, email = null, telefone = null, zipCode = null;
		boolean go = true;

		// Übergabe der Inputs
		firstName = txtFirstname.getText();
		lastName = txtLastname.getText();
		city = txtCity.getText();
		street = txtStreet.getText();
		streetNo = txtStreetno.getText();
		email = txtEmail.getText();
		telefone = txtTelefone.getText();
		zipCode = txtZipcode.getText();

		// Ueberpruefung auf Parsefehler bei der Postleitzahl mit entsprechender
		// Fehlermeldung 
		if (zipCode.replaceAll(" ", "").equals("")) {
		
		}
		
		// Auf leere Pflichtfelder ueberpruefen
		if (firstName.replaceAll(" ", "").equals("")) {
			   go = false;
			  } else if (lastName.replaceAll(" ", "").equals("")) {
			   go = false;
			  } else if (zipCode.replaceAll(" ", "").equals("")) {
			   go = false;
			  } else if (city.replaceAll(" ", "").equals("")) {
			   go = false;
			  } else if (street.replaceAll(" ", "").equals("")) {
			   go = false;
			  } else if (streetNo.replaceAll(" ", "").equals("")) {
			   go = false;
			  } else if (email.replaceAll(" ", "").equals("")) {
			   go = false;
			  } else if (telefone.replaceAll(" ", "").equals("")) {
			   go = false;
			  } else if (dateBirthdate == null) {
			   go = false;
			  }
		
		//Ueberpruefung EmailFeld
		//if(validate(txtEmail.getText()) == false){
		//	go=false;
		//}

		// Aufrufen der Methode CreateCustomer
		if (go == true) {
			con = DriverManager.getConnection("jdbc:mysql://localhost/dvd_verleih?user=root");
			insert = new Insert("dvd_verleih",con);
			try {
				insert.insertCustomer(this, firstName, lastName, zipCode, city, street,
						streetNo, email, telefone, dateBirthdate);
			} catch (InvalidParameterException e) {
				// Fehlercode 002
				e.printStackTrace();
				throw new Exception(
						"Bei der Uebertragung der Parameter ist ein Fehler aufgetreten! Fehlercode: 002");
			}
		} else {
			JOptionPane
					.showMessageDialog(
							null,
							"Sie haben ein Pflichtfeld nicht ausgefüllt! Bitte überprüfen Sie ihre Angaben in den Feldern",
							"Kundenerstellung", JOptionPane.ERROR_MESSAGE);

		}

	}

	private void cancelButtonActionPerfomred(ActionEvent arg0) {
		// Canclebutton gedrückt
		dispose();
	}
	
	/**
	 * @param firstName = Vorname des neuen Kunden
	 * @param lastName = Nachname des neuen Kunden
	 */
	public void customerAdded(String firstName, String lastName){
		// Wenn Benutzer erfolgreich hinzu gefügt wurde, die mitteilen und neues, leeres Eingabefenster öffnen.
		dispose();
		JOptionPane.showMessageDialog(null, ("Der Benutzer " + firstName + " " + lastName + " wurde erfolgreich angelegt!"), "Vorgang erfolgreich", JOptionPane.INFORMATION_MESSAGE);
		this.setVisible(false);
		this.dispose();

		// Neues, leeres Erstellungsfenster instantiieren
		NewCustomer dialog = new NewCustomer();
		dialog.setVisible(true);
	}
	
	private void EmailValidator() {
		pattern = Pattern.compile(EMAIL_PATTERN);
	}
 
	/**
	 * Validate hex with regular expression
	 * 
	 * @param hex
	 *            hex for validation
	 * @return true valid hex, false invalid hex
	 */
	private boolean validate(final String hex) {
 
		matcher = pattern.matcher(hex);
		return matcher.matches();
 
	}

}
