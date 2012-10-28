package de.dhbw.projektarbeit.gui;

import javax.swing.JPanel;
import org.jdesktop.swingx.JXTable;

import de.dhbw.projektarbeit.db.request.Filling;
import de.dhbw.projektarbeit.gui.Dialogs.JTableNotEditable;

import java.awt.CardLayout;
import java.awt.GridBagLayout;

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
import javax.swing.ScrollPaneConstants;
import javax.swing.ListSelectionModel;

public class DVDCard extends JPanel {

	private JTable tbDVD;
	private Object[][] dvdData;
	private DefaultTableModel model;
	Filling fill = new Filling();
	// Spaltenüberschriften
	private String[] columnNames = { "Menge", "Titel", "Originaltitel",
			"Genre", "Produktionsland", "Produktionsjahr", "Erscheinungsdatum",
			"Länge", "Altersfreigabe", "Regie", "Autor", "Produkiton",
			"Kamera", "EAN Code" };

	/**
	 * Standardkonstruktor
	 */
	public DVDCard() {
		getTables();
		// Ersetzen der Fremdschlüssel mit entsprechenden Einträgen aus den
		// Tabellen auf der DB
		fill.getNameFromID(dvdData);
		setWindow();
	}

	/**
	 * Methode zum generieren der Gesamtübersicht vorhandener DVDs aus der DB
	 */
	private void getTables() {
		// Aufruf der Fill-Methode
		try {
			dvdData = fill.getTable("dvd");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		;
	}

	/**
	 * Methode zur Carddarstellung
	 */
	private void setWindow() {
		setLayout(new CardLayout(0, 0));
		model = new DefaultTableModel(dvdData, columnNames);
		tbDVD = new JTableNotEditable(model, columnNames);
		tbDVD.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbDVD.setFocusable(false);
		tbDVD.setModel(model);

		JScrollPane scrollPane = new JScrollPane(tbDVD);
		scrollPane.setName("scrollDVD");
		add(scrollPane, "name_25881698244854");
	}
}
