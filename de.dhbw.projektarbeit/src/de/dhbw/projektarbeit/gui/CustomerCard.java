package de.dhbw.projektarbeit.gui;

import javax.swing.JPanel;
import java.awt.CardLayout;
import org.jdesktop.swingx.JXTable;

public class CustomerCard extends JPanel {

	/**
	 * Create the panel.
	 */
	public CustomerCard() {
		setLayout(new CardLayout(0, 0));
		
		JXTable tbCustomer = new JXTable();
		add(tbCustomer, "name_1350572057225703000");

	}

}
