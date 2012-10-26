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

		// Spalten�berschriften
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
		tbCustomer.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbCustomer.setFocusable(false);
		tbCustomer.setModel(model);

		JScrollPane scrollPane = new JScrollPane(tbCustomer);
		scrollPane.setName("scrollCustomer");
		add(scrollPane, "name_25881698244854");

	}
	
}
