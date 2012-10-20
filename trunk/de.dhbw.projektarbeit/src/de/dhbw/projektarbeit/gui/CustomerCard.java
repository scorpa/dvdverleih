package de.dhbw.projektarbeit.gui;

import javax.swing.JPanel;
import java.awt.CardLayout;
import org.jdesktop.swingx.JXTable;
import javax.swing.JTable;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class CustomerCard extends JPanel {
	private JTable tbCustomer;

	/**
	 * Create the panel.
	 */
	public CustomerCard() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		tbCustomer = new JTable();
		GridBagConstraints gbc_tbCustomer = new GridBagConstraints();
		gbc_tbCustomer.fill = GridBagConstraints.BOTH;
		gbc_tbCustomer.gridx = 0;
		gbc_tbCustomer.gridy = 0;
		add(tbCustomer, gbc_tbCustomer);

	}

}
