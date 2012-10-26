package de.dhbw.projektarbeit.gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import java.awt.CardLayout;
import org.jdesktop.swingx.JXTable;

import de.dhbw.projektarbeit.db.request.Filling;
import de.dhbw.projektarbeit.gui.Dialogs.EditCustomer;
import de.dhbw.projektarbeit.gui.Dialogs.JTableNotEditable;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.MouseEvent;
import java.sql.Date;

import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;

public class CustomerCard extends JPanel {
	private JTable tbCustomer;
	private String txtFirstName, txtLastName, txtCity, txtEmail, txtStreet, txtStreetno, txtTelefone;
	private String txtZipcode;
	private Date birthdate;

	/**
	 * Create the panel.
	 */
	public CustomerCard() {
		getTables();

	}

	private void getTables() {
		// Layout setzten
		setLayout(new CardLayout(0, 0));

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

		DefaultTableModel model = new DefaultTableModel(customerData,
				columnNames);

		tbCustomer = new JTableNotEditable(model, columnNames);
		tbCustomer.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				tbCustomerMouseClicked(e);
			}
		});
		tbCustomer.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbCustomer.setFocusable(false);
		tbCustomer.setModel(model);

		JScrollPane scrollPane = new JScrollPane(tbCustomer);
		scrollPane.setName("scrollCustomer");
		add(scrollPane, "name_25881698244854");

	}
	
/*	private void tbCustomerMouseClicked(MouseEvent e) {
		getSelectedRow(e);
	}
*/
	/**
	 * Methode zum Auslesen des ausgewählten CustomerCard Eintrags
	 * 
	 * @param e
	 *            --> MouseEvent aus dem entsprechenden Dialog, aus dem die
	 *            Anfrage kommt
	 * @return 
	 * @return returnArray enthält die ausgelesenen Felder [0] = ID; [1] =
	 *         FirstName(String); [2] = LastName(String); [3] = Zipcode(String);
	 *         [4] = City(String); [5] = Street(String); [6] = StreetNo(String);
	 *         [7] = Email(String); [8] = Telefone(String); [9] = birthdate(Date)
	 */
	public void tbCustomerMouseClicked(MouseEvent e) {
		EditCustomer ec = new EditCustomer();
		
		int selectedID;
		
		Object[] returnArray = new Object[10];
		// Felder der ausgewählten Zeile auslesen
		try {
			selectedID = (Integer) tbCustomer.getValueAt(
					tbCustomer.getSelectedRow(), 0);
			txtFirstName= (String) tbCustomer.getValueAt(
					tbCustomer.getSelectedRow(), 1);
			txtLastName = (String) tbCustomer.getValueAt(
					tbCustomer.getSelectedRow(), 2);
			txtZipcode = (String) tbCustomer.getValueAt(
					tbCustomer.getSelectedRow(), 3);
			txtCity = (String) tbCustomer.getValueAt(
					tbCustomer.getSelectedRow(), 4);
			txtStreet = (String) tbCustomer.getValueAt(
					tbCustomer.getSelectedRow(), 5);
			txtStreetno = (String) tbCustomer.getValueAt(
					tbCustomer.getSelectedRow(), 6);
			txtEmail = (String) tbCustomer.getValueAt(
					tbCustomer.getSelectedRow(), 7);
			txtTelefone = (String) tbCustomer.getValueAt(
					tbCustomer.getSelectedRow(), 8);
			birthdate = (Date) tbCustomer.getValueAt(
					tbCustomer.getSelectedRow(), 9);
			// Ausgelesene Felder in ObjectArray übergeben
			returnArray[0] = selectedID;
			returnArray[1] = txtFirstName;
			returnArray[2] = txtLastName;
			returnArray[3] = txtZipcode;
			returnArray[4] = txtCity;
			returnArray[5] = txtStreet;
			returnArray[6] = txtStreetno;
			returnArray[7] = txtEmail;
			returnArray[8] = txtTelefone;
			returnArray[9] = birthdate;

		} catch (Exception e2) {
			e2.printStackTrace();
		}
		// ObjectArray zurück geben
		ec = new EditCustomer();
		ec.fillTextFields(returnArray);
	}

}
