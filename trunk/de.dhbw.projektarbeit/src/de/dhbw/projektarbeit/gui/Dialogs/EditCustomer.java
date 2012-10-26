package de.dhbw.projektarbeit.gui.Dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.CardLayout;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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

import de.dhbw.projektarbeit.db.request.Filling;
import de.dhbw.projektarbeit.db.request.Update;
import de.dhbw.projektarbeit.gui.CustomerCard;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JScrollPane;
import java.awt.Insets;
import java.awt.Component;
import javax.swing.ScrollPaneConstants;

public class EditCustomer extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	private static JTextField txtFirstName;
	private static JTextField txtZipCode;
	private static JTextField txtCity;
	private static JTextField txtEMail;
	private static JTextField txtStreet;
	private static JTextField txtStreetNo;
	private static JTextField txtTelefone;
	private static JTextField txtLastName;
	private static JXDatePicker dpBirthdate;
	private Update update;
	private Connection con;
	private static Integer selectedID;
	private JTable tbCustomer;
	private Object[][] customerData;
	private DefaultTableModel model;
	private JButton btnSave;
	private JButton cancelButton;
	
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
		loadTable();
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
				txtFirstName.setColumns(22);
				txtZipCode = new JTextField();
				txtZipCode.setColumns(5);
				JLabel label = new JLabel("Postleitzahl");
				JLabel label_1 = new JLabel("Ort");
				txtCity = new JTextField();
				txtCity.setColumns(10);
				txtEMail = new JTextField();
				txtEMail.setColumns(22);
				JLabel label_2 = new JLabel("Vorname");
				JLabel label_3 = new JLabel("E-Mail Adresse");
				txtStreet = new JTextField();
				txtStreet.setColumns(15);
				txtStreetNo = new JTextField();
				txtStreetNo.setColumns(5);
				txtTelefone = new JTextField();
				txtTelefone.setColumns(15);
				JLabel label_4 = new JLabel("Telefonnummer");
				JLabel label_5 = new JLabel("Geburtsdatum");
				dpBirthdate = new JXDatePicker();
				txtLastName = new JTextField();
				txtLastName.setColumns(22);
				JLabel label_6 = new JLabel("Nachname");
				JLabel label_7 = new JLabel("Strasse und Hausnummer");
				GroupLayout gl_panel = new GroupLayout(panel);
				gl_panel.setHorizontalGroup(
					gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
									.addComponent(txtFirstName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGroup(gl_panel.createSequentialGroup()
										.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
											.addComponent(txtZipCode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addComponent(label))
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
											.addComponent(label_1)
											.addComponent(txtCity))))
								.addComponent(txtEMail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_2)
								.addComponent(label_3))
							.addGap(24)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(txtStreet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtStreetNo, 82, 82, 82))
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(txtTelefone, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_4))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(label_5)
										.addComponent(dpBirthdate, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)))
								.addComponent(txtLastName)
								.addComponent(label_6)
								.addComponent(label_7))
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);
				gl_panel.setVerticalGroup(
					gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(label_2)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtFirstName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
										.addComponent(label_1)
										.addComponent(label))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
										.addComponent(txtZipCode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtCity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(label_3)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtEMail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(label_6)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtLastName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(label_7)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
										.addComponent(txtStreet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtStreetNo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
										.addComponent(label_5)
										.addComponent(label_4))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
										.addComponent(txtTelefone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(dpBirthdate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
							.addContainerGap(39, Short.MAX_VALUE))
				);
				panel.setLayout(gl_panel);
			}
			
			JPanel panel = new JPanel();
			splitPane.setLeftComponent(panel);
			
			
			// Spaltenüberschriften
			String[] columnNames = { "ID", "Vorname", "Nachname", "Postleitzahl",
					"Ort", "Strasse", "Hausnummer", "EMail", "Telefon", "Geurtstag" };

			loadCustomerData();
			
			model = new DefaultTableModel(customerData,
					columnNames);
			
			tbCustomer = new JTableNotEditable(model, columnNames);
			tbCustomer.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					tbCustomerMouseClicked(arg0);
				}
			});
			tbCustomer.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tbCustomer.setFocusable(false);
			tbCustomer.setModel(model);
						
			JScrollPane scrollPane = new JScrollPane(tbCustomer);
			scrollPane.setName("scrollCustomer");
			GroupLayout gl_panel = new GroupLayout(panel);
			gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 596, Short.MAX_VALUE)
			);
			gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel.createSequentialGroup()
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
						.addGap(5))
			);
			
			
			panel.setLayout(gl_panel);
			splitPane.setDividerLocation(200);
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnSave = new JButton("Speichern");
				btnSave.setActionCommand("OK");
				getRootPane().setDefaultButton(btnSave);
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
			gl_buttonPane.setHorizontalGroup(
				gl_buttonPane.createParallelGroup(Alignment.LEADING)
					.addGroup(Alignment.TRAILING, gl_buttonPane.createSequentialGroup()
						.addComponent(btnSave)
						.addPreferredGap(ComponentPlacement.RELATED, 449, Short.MAX_VALUE)
						.addComponent(cancelButton))
			);
			gl_buttonPane.setVerticalGroup(
				gl_buttonPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_buttonPane.createSequentialGroup()
						.addGap(5)
						.addGroup(gl_buttonPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(cancelButton)
							.addComponent(btnSave))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
			);
			buttonPane.setLayout(gl_buttonPane);
		}
	}

	protected void tbCustomerMouseClicked(MouseEvent arg0) {
		try {
			selectedID = (Integer) tbCustomer.getValueAt(tbCustomer.getSelectedRow(), 0);
			txtFirstName.setText((String) tbCustomer.getValueAt(tbCustomer.getSelectedRow(), 1));
			txtLastName.setText((String) tbCustomer.getValueAt(tbCustomer.getSelectedRow(), 2));
			txtZipCode.setText((String) tbCustomer.getValueAt(tbCustomer.getSelectedRow(), 3));
			txtCity.setText((String) tbCustomer.getValueAt(tbCustomer.getSelectedRow(), 4));
			txtStreet.setText((String) tbCustomer.getValueAt(tbCustomer.getSelectedRow(), 5));
			txtStreetNo.setText((String) tbCustomer.getValueAt(tbCustomer.getSelectedRow(), 6));
			txtEMail.setText((String) tbCustomer.getValueAt(tbCustomer.getSelectedRow(), 7));
			txtTelefone.setText((String) tbCustomer.getValueAt(tbCustomer.getSelectedRow(), 8));
			dpBirthdate.setDate((Date) tbCustomer.getValueAt(tbCustomer.getSelectedRow(), 9));
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
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
	public void loadTable(){		
		try {

			// Spaltenüberschriften
			String[] columnNames = { "ID", "Vorname", "Nachname", "Postleitzahl",
					"Ort", "Strasse", "Hausnummer", "EMail", "Telefon", "Geurtstag" };

			Filling fill = new Filling();

			Object[][] customerData = null;
			try {
				customerData = fill.getTable("customer");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			;
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private void loadCustomerData(){
		Filling fill = new Filling();
		try {
			customerData = fill.getTable("customer");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		;
	}
	
	private void updateCustomer(int id, String firstname, String lastname, String zipcode, String city, String street, String streetno, String email, String telefone, java.sql.Date birthdate)throws Exception{
		// Update des Regisseurs
				try {
					try {
						con = DriverManager
								.getConnection("jdbc:mysql://localhost/dvd_verleih?user=root");
					} catch (SQLException e) {
						// Verbindung zum SQL Server fehlgeschlagen. Fehlercode 005
						e.printStackTrace();
						throw new Exception(
								"Verbindung zum SQL Server fehlgeschlagen. Fehlercode 005");
					}
					
					update = new Update("dvd_verleih",con);
					update.updateCustomer(id, firstname, lastname, zipcode, city, street, streetno, email, telefone, birthdate);
					tbCustomer.setValueAt(txtFirstName.getText(), tbCustomer.getSelectedRow(), 1);
					tbCustomer.setValueAt(txtLastName.getText(), tbCustomer.getSelectedRow(), 2);
					tbCustomer.setValueAt(txtZipCode.getText(),tbCustomer.getSelectedRow() , 3);
					tbCustomer.setValueAt(txtCity.getText(),tbCustomer.getSelectedRow() , 4);
					tbCustomer.setValueAt(txtStreet.getText(),tbCustomer.getSelectedRow() , 5);
					tbCustomer.setValueAt(txtStreetNo.getText(),tbCustomer.getSelectedRow() , 6);
					tbCustomer.setValueAt(txtEMail.getText(),tbCustomer.getSelectedRow() , 7);
					tbCustomer.setValueAt(txtTelefone.getText(),tbCustomer.getSelectedRow() , 8);
					tbCustomer.setValueAt((Date) dpBirthdate.getDate(),tbCustomer.getSelectedRow() , 9);
					
					
					
				} catch (Exception e2) {
					e2.printStackTrace();
					throw new Exception(
							"Bei der Uebertragung der Parameter ist ein Fehler aufgetreten! Fehlercode: 002");
				}
	}
	

}
