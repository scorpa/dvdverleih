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

}
