package de.dhbw.projektarbeit.gui.Dialogs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;

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

import de.dhbw.projektarbeit.customer.CreateCustomer;

public class NewCustomer extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private SimpleDateFormat sdf;
	private Date dateBirthdate;
	private JTextField txtFirstname;
	private JTextField txtLastname;
	private JTextField txtZipcode;
	private JTextField txtCity;
	private JTextField txtEmail;
	private JTextField txtStreet;
	private JTextField txtStreetno;
	private JTextField txtTelefone;
	private JButton cancelButton;
	private String Albert;

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
		contentPanel.setToolTipText("Format dd.mm.yyyy");
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		JLabel lblVorname = new JLabel("Vorname");

		txtFirstname = new JTextField();
		txtFirstname.setColumns(22);
		txtFirstname.setText("FirstName");

		txtLastname = new JTextField();
		txtLastname.setText("LastName");
		txtLastname.setColumns(22);

		JLabel lblNachname = new JLabel("Nachname");

		JLabel lblPostleitzahl = new JLabel("Postleitzahl");

		txtZipcode = new JTextField();
		txtZipcode.setText("ZipCode");
		txtZipcode.setColumns(5);

		txtCity = new JTextField();
		txtCity.setText("City");
		txtCity.setColumns(10);

		JLabel lblOrt = new JLabel("Ort");

		JLabel lblEmailAdresse = new JLabel("E-Mail Adresse");

		txtEmail = new JTextField();
		txtEmail.setText("EMail");
		txtEmail.setColumns(22);

		txtStreet = new JTextField();
		txtStreet.setText("Street");
		txtStreet.setColumns(15);

		txtStreetno = new JTextField();
		txtStreetno.setText("StreetNo");
		txtStreetno.setColumns(5);

		JLabel lblStrasseUndHausnummer = new JLabel("Strasse und Hausnummer");

		txtTelefone = new JTextField();
		txtTelefone.setText("Telefone");
		txtTelefone.setColumns(15);

		JLabel lblTelefonnummer = new JLabel("Telefonnummer");

		// Festlegung des Formats über die JXDate Funktion
		sdf = new SimpleDateFormat();
		sdf.applyPattern("yyyy-MM-dd");

		final JXDatePicker dpBirthdate = new JXDatePicker();
		dpBirthdate.setFormats(new String[] { "dd.MM.yyyy" });
		dpBirthdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Überfürung des JXDates in die Datevariable
				dateBirthdate = Date.valueOf(sdf.format(dpBirthdate.getDate()));
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

			JButton btnAddcustomer = new JButton("Hinzuf\u00FCgen");
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
			gl_buttonPane.setHorizontalGroup(
				gl_buttonPane.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_buttonPane.createSequentialGroup()
						.addContainerGap(428, Short.MAX_VALUE)
						.addComponent(btnAddcustomer)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(cancelButton)
						.addGap(4))
			);
			gl_buttonPane.setVerticalGroup(
				gl_buttonPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_buttonPane.createSequentialGroup()
						.addGap(5)
						.addGroup(gl_buttonPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(cancelButton)
							.addComponent(btnAddcustomer))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
			);
			buttonPane.setLayout(gl_buttonPane);
		}
	}


	private void btnAddcustomerActionPerformed(ActionEvent arg0)
			throws Exception {
		// Check, ob alle Felder ausgefüllt sind
		String firstName = "", lastName = "", city = "", street = "", streetNo = "", email = "", telefone = "", zipCode = null;
		int zip_code = 0, index = -1;
		String isEmpty[] = new String[9];
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

		// Überprüfung auf Parsefehler bei der Postleitzahl mit entsprechender
		// Fehlermeldung && zipCode.replaceAll(" ", "") == ""
		if (zipCode == null ){
			zip_code = 0;
			return;
		}
		else{
		try {
			
			zip_code = Integer.parseInt(txtZipcode.getText());
			} catch (NumberFormatException e) {
			// Fehlercode 003
			e.printStackTrace();
			throw new Exception(
					"Bitte geben Sie eine gütlige Postleitzahl ein! Fehlercode 003");
		}
		}

		// Auf leere Pflichtfelder überprüfen
		if (firstName == null || firstName.replaceAll(" ", "") == "") {
			index++;
			isEmpty[index] = "Vorname";
			go = false;
		} else if (lastName == null || lastName.replaceAll(" ", "") == "") {
			index++;
			isEmpty[index] = "Nachname";
			go = false;
		} else if (zip_code == 0 || zip_code > 99999) {
			index++;
			isEmpty[index] = "Postleitzahl";
			go = false;
		} else if (city == null || city.replaceAll(" ", "") == "") {
			index++;
			isEmpty[index] = "Ort";
			go = false;
		} else if (street == null || street.replaceAll(" ", "") == "") {
			index++;
			isEmpty[index] = "Strasse";
			go = false;
		} else if (streetNo == null || streetNo.replaceAll(" ", "") == "") {
			index++;
			isEmpty[index] = "Hausnummer";
			go = false;
		} else if (email == null || email.replaceAll(" ", "") == "") {
			index++;
			isEmpty[index] = "Email";
			go = false;
		} else if (telefone == null || telefone.replaceAll(" ", "") == "") {
			index++;
			isEmpty[index] = "Telefonnummer";
			go = false;
		} else if (dateBirthdate == null) {
			index++;
			isEmpty[index] = "Geburtsdatum";
			go = false;
		}

		// Aufrufen der Methode CreateCustomer
		if (go = true) {
			CreateCustomer cc = new CreateCustomer();
			try {
				cc.CreateNewCustomer(firstName, lastName, zip_code, city,
						street, streetNo, email, telefone, dateBirthdate);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "Sie haben ein Pflichtfeld nicht ausgefüllt! Bitte überprüfen Sie ihre Angaben in den Feldern"  
					, "Kundenerstellung", ERROR);
			
		}

	}
	
	private void cancelButtonActionPerfomred(ActionEvent arg0) {
		// Canclebutton gedrückt
		dispose();
		
	}

}
