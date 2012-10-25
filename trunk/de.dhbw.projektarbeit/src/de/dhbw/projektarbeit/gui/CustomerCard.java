package de.dhbw.projektarbeit.gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.CardLayout;
import org.jdesktop.swingx.JXTable;

import de.dhbw.projektarbeit.db.request.Filling;
import de.dhbw.projektarbeit.gui.Dialogs.JTableNotEditable;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.ListSelectionModel;

public class CustomerCard extends JPanel {
	private JTable tbCustomer;

	
	
	/**
	 * Create the panel.
	 */
	public CustomerCard() {
		getTables();

	}
	
	private void getTables(){
		//Layout setzten
		setLayout(new CardLayout(0, 0));
		
		// Spaltenüberschriften
		String[] columnNames = { "Vorname", "Nachname", "Postleitzahl", "Ort",
				"Strasse", "Hausnummer", "EMail",
				"Telefon", "Geurtstag"};

		Filling fill = new Filling();
		
		Object[][] customerData = null;
		try {
			customerData = fill.getTable("customer");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
			
		DefaultTableModel model = new DefaultTableModel(customerData, columnNames);
		
		tbCustomer = new JTableNotEditable(model,columnNames);
		tbCustomer.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbCustomer.setFocusable(false);
		tbCustomer.setModel(model);
				
		JScrollPane scrollPane = new JScrollPane(tbCustomer);
		scrollPane.setName("scrollCustomer");
		add(scrollPane, "name_25881698244854");
		
	}

}
