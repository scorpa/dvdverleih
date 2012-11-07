package de.dhbw.projektarbeit.gui.Dialogs;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Ableitung der Klasse JTable, die verwendet wird, um eine nicht
 * editierbare Tabelle zu erstellen.
 * 
 * 
 */

public class JTableNotEditable extends JTable {
	
	/**
	 * Standardkonstruktor
	 */
	public JTableNotEditable(){

	}
	
	/**
	 * @param accountListing
	 *            Objectarray mit den aus der Tabelle abgefragten
	 *            Datensätzen
	 * @param strings
	 *            Stringarray mit den Titeln der Tabellenspalten
	 */
	public JTableNotEditable(DefaultTableModel model, String[] strings) {
		super(model);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.JTable#isCellEditable(int, int)
	 */
	public boolean isCellEditable(int row, int col) {
		return false;
	}
	
	/**
	 * Methode zur Festlegung der Spaltenbreite
	 * @param table --> JTable, deren Spalten festgelegt werden soll
	 * @return --> Angepasste JTable
	 */
	public JTable setColumnSize(JTable table){
		// Spaltenbreiten setzen
		table.getColumn("Menge").setPreferredWidth(45);
		table.getColumn("Titel").setPreferredWidth(300);
		table.getColumn("Originaltitel").setPreferredWidth(300);
		table.getColumn("Genre").setPreferredWidth(150);
		table.getColumn("Produktionsland").setPreferredWidth(150);
		table.getColumn("Produktionsjahr").setPreferredWidth(100);
		table.getColumn("Erscheinungsdatum").setPreferredWidth(130);
		table.getColumn("Länge").setPreferredWidth(50);
		table.getColumn("Altersfreigabe").setPreferredWidth(90);
		table.getColumn("Regie").setPreferredWidth(200);
		table.getColumn("Autor").setPreferredWidth(200);
		table.getColumn("Produktion").setPreferredWidth(200);
		table.getColumn("Kamera").setPreferredWidth(200);
		table.getColumn("EAN Code").setPreferredWidth(100);
		table.getTableHeader().setReorderingAllowed(false);
		
		return table;
	}
}
