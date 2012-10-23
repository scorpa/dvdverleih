package de.dhbw.projektarbeit.gui;

import javax.swing.JPanel;
import org.jdesktop.swingx.JXTable;

import de.dhbw.projektarbeit.db.request.Filling;

import java.awt.CardLayout;
import javax.swing.JSplitPane;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class DVDCard extends JPanel {

	/**
	 * Create the panel.
	 */
	public DVDCard() {
		getTables();
		
	}
		private void getTables() {
		// TODO Auto-generated method stub
		setLayout(new CardLayout(0, 0));
		
		JTable tbDVD = new JTable();
		//Spaltenüberschriften
		String[] columnNames = {"Menge","Titel", "Originaltitel", "Genre", "Produktionsland","Produktionsjahr","Erscheinungsdatum","Länge","Altersfreigabe","Regie","Autor","Produkiton","Kamera","EAN Code"};
		
		Filling fill = new Filling();
		
		Object[][] dvdData = null;
		try {
			dvdData = fill.getDVD();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			;
/*			{
				{ "2", "A1", "A2", "A3" ,"A4","A5"},
				{ "34","B1", "B2", "B3" ,"B4","B5"},
		};*/
		
		DefaultTableModel model = new DefaultTableModel(dvdData,columnNames);
		
		tbDVD.setModel(model);
		
		
		JScrollPane scrollPane = new JScrollPane(tbDVD);
		scrollPane.setName("scrollDVD");
		add(scrollPane, "name_25881698244854");

	}

}
