package de.dhbw.projektarbeit.gui;

import javax.swing.JPanel;
import org.jdesktop.swingx.JXTable;
import java.awt.CardLayout;
import javax.swing.JSplitPane;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;

public class DVDCard extends JPanel {
	private JTable tbDVD;

	/**
	 * Create the panel.
	 */
	public DVDCard() {
		setLayout(new CardLayout(0, 0));
		
		tbDVD = new JTable();
		add(tbDVD, "name_3715346602670");

	}

}
