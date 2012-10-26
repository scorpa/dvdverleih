package de.dhbw.projektarbeit.gui.Dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.util.Date;

import javax.swing.JSplitPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
import org.jdesktop.swingx.JXDatePicker;
import javax.swing.LayoutStyle.ComponentPlacement;
import org.jdesktop.swingx.JXTable;
import javax.swing.JTable;

import de.dhbw.projektarbeit.db.request.Update;
import de.dhbw.projektarbeit.gui.CustomerCard;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;

public class EditCustomer extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtFirstName;
	private JTextField txtZipCode;
	private JTextField txtCity;
	private JTextField txtEMail;
	private JTextField txtStreet;
	private JTextField txtStreetNo;
	private JTextField txtTelefone;
	private JTextField txtLastName;
	private JXDatePicker dpBirthdate;
	private Update update;
	private Connection con;
	private Integer selectedID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EditCustomer dialog = new EditCustomer();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EditCustomer() {
		setWindow();
	}

	/**
	 * Methode zur Dialogerstellung
	 */
	private void setWindow() {
		setBounds(100, 100, 610, 500);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new CardLayout(0, 0));
		{
			JSplitPane splitPane = new JSplitPane();
			splitPane.setDividerSize(4);
			splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
			contentPanel.add(splitPane, "name_1350573015312231000");
			{
				JPanel panel = new JPanel();
				panel.setToolTipText("Format dd.mm.yyyy");
				panel.setBorder(new EmptyBorder(5, 5, 5, 5));
				splitPane.setRightComponent(panel);
				txtFirstName = new JTextField();
				txtFirstName.setText("FirstName");
				txtFirstName.setColumns(22);
				txtZipCode = new JTextField();
				txtZipCode.setText("ZipCode");
				txtZipCode.setColumns(5);
				JLabel label = new JLabel("Postleitzahl");
				JLabel label_1 = new JLabel("Ort");
				txtCity = new JTextField();
				txtCity.setText("City");
				txtCity.setColumns(10);
				txtEMail = new JTextField();
				txtEMail.setText("EMail");
				txtEMail.setColumns(22);
				JLabel label_2 = new JLabel("Vorname");
				JLabel label_3 = new JLabel("E-Mail Adresse");
				txtStreet = new JTextField();
				txtStreet.setText("Street");
				txtStreet.setColumns(15);
				txtStreetNo = new JTextField();
				txtStreetNo.setText("StreetNo");
				txtStreetNo.setColumns(5);
				txtTelefone = new JTextField();
				txtTelefone.setText("Telefone");
				txtTelefone.setColumns(15);
				JLabel label_4 = new JLabel("Telefonnummer");
				JLabel label_5 = new JLabel("Geburtsdatum");
				dpBirthdate = new JXDatePicker();
				txtLastName = new JTextField();
				txtLastName.setText("LastName");
				txtLastName.setColumns(22);
				JLabel label_6 = new JLabel("Nachname");
				JLabel label_7 = new JLabel("Strasse und Hausnummer");
				GroupLayout gl_panel = new GroupLayout(panel);
				gl_panel.setHorizontalGroup(gl_panel
						.createParallelGroup(Alignment.TRAILING)
						.addGap(0, 600, Short.MAX_VALUE)
						.addGroup(
								gl_panel.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_panel.createParallelGroup(
														Alignment.LEADING)
														.addGroup(
																gl_panel.createParallelGroup(
																		Alignment.LEADING,
																		false)
																		.addComponent(
																				txtFirstName,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)
																		.addGroup(
																				gl_panel.createSequentialGroup()
																						.addGroup(
																								gl_panel.createParallelGroup(
																										Alignment.LEADING)
																										.addComponent(
																												txtZipCode,
																												GroupLayout.PREFERRED_SIZE,
																												GroupLayout.DEFAULT_SIZE,
																												GroupLayout.PREFERRED_SIZE)
																										.addComponent(
																												label))
																						.addPreferredGap(
																								ComponentPlacement.UNRELATED)
																						.addGroup(
																								gl_panel.createParallelGroup(
																										Alignment.LEADING)
																										.addComponent(
																												label_1)
																										.addComponent(
																												txtCity))))
														.addComponent(
																txtEMail,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(label_2)
														.addComponent(label_3))
										.addGap(18)
										.addGroup(
												gl_panel.createParallelGroup(
														Alignment.LEADING,
														false)
														.addGroup(
																gl_panel.createSequentialGroup()
																		.addComponent(
																				txtStreet,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				txtStreetNo,
																				82,
																				82,
																				82))
														.addGroup(
																gl_panel.createSequentialGroup()
																		.addGroup(
																				gl_panel.createParallelGroup(
																						Alignment.LEADING)
																						.addComponent(
																								txtTelefone,
																								GroupLayout.PREFERRED_SIZE,
																								144,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								label_4))
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addGroup(
																				gl_panel.createParallelGroup(
																						Alignment.LEADING)
																						.addComponent(
																								label_5)
																						.addComponent(
																								dpBirthdate,
																								GroupLayout.PREFERRED_SIZE,
																								131,
																								GroupLayout.PREFERRED_SIZE)))
														.addComponent(
																txtLastName)
														.addComponent(label_6)
														.addComponent(label_7))
										.addContainerGap()));
				gl_panel.setVerticalGroup(gl_panel
						.createParallelGroup(Alignment.LEADING)
						.addGap(0, 214, Short.MAX_VALUE)
						.addGroup(
								gl_panel.createSequentialGroup()
										.addGroup(
												gl_panel.createParallelGroup(
														Alignment.BASELINE)
														.addComponent(label_6)
														.addComponent(label_2))
										.addPreferredGap(
												ComponentPlacement.RELATED)
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
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addGroup(
												gl_panel.createParallelGroup(
														Alignment.BASELINE)
														.addComponent(label_1)
														.addComponent(label_7)
														.addComponent(label))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_panel.createParallelGroup(
														Alignment.BASELINE)
														.addComponent(
																txtZipCode,
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
																txtStreetNo,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addGroup(
												gl_panel.createParallelGroup(
														Alignment.BASELINE)
														.addComponent(label_5)
														.addComponent(label_4)
														.addComponent(label_3))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_panel.createParallelGroup(
														Alignment.BASELINE)
														.addComponent(
																txtEMail,
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
										.addContainerGap(30, Short.MAX_VALUE)));
				panel.setLayout(gl_panel);
			}

			CustomerCard customerCard = new CustomerCard();
			splitPane.setLeftComponent(customerCard);
			splitPane.setDividerLocation(200);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

/*	protected void customerCardMouseClicked(MouseEvent e) throws Exception {
		// Daten aus Table mit Methode getSelectedRow lesen
		Object[] result = new Object[10];
		CustomerCard cc = new CustomerCard();
		result = cc.getSelectedRow(e);
		
		try {
			selectedID = (Integer) result[0];
			txtFirstName.setText((String) result[1]);
			txtLastName.setText((String) result[2]);
			txtZipCode.setText((String) result[3]);
			txtCity.setText((String) result[4]);
			txtStreet.setText((String) result[5]);
			txtStreetNo.setText((String) result[6]);
			txtEMail.setText((String) result[7]);
			txtTelefone.setText((String) result[8]);
			dpBirthdate.setDate((Date) result[9]);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}*/
	public void fillTextFields(Object[] returnArray){
		// Daten aus Table mit Methode getSelectedRow lesen
		Object[] result = new Object[10];
		CustomerCard cc = new CustomerCard();
		
		try {
			selectedID = (Integer) result[0];
			txtFirstName.setText((String) result[1]);
			txtLastName.setText((String) result[2]);
			txtZipCode.setText((String) result[3]);
			txtCity.setText((String) result[4]);
			txtStreet.setText((String) result[5]);
			txtStreetNo.setText((String) result[6]);
			txtEMail.setText((String) result[7]);
			txtTelefone.setText((String) result[8]);
			dpBirthdate.setDate((Date) result[9]);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
