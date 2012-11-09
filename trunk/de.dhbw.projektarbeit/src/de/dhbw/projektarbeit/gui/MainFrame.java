package de.dhbw.projektarbeit.gui;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.util.regex.PatternSyntaxException;

import javax.swing.DefaultRowSorter;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.UIManager;
import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import de.dhbw.projektarbeit.db.request.Filling;
import de.dhbw.projektarbeit.gui.Dialogs.JTableNotEditable;

/**
 * Mainframe Klasse
 * 
 * @author Eisen
 * @author Brunner
 * 
 */
public class MainFrame extends javax.swing.JFrame {
	private MenuBar menuBar;
	private TopPanel topPanel;
	private JPanel CardPanel, panelMain;
	private JTable tbDVD;
	private DefaultTableModel model;
	private String[] columnNames = { "Menge", "Titel", "Originaltitel",
			"Genre", "Produktionsland", "Produktionsjahr", "Erscheinungsdatum",
			"Länge", "Altersfreigabe", "Regie", "Autor", "Produktion",
			"Kamera", "EAN Code" };
	private Object[][] dvdData;
	private Filling fill;
	private JTableNotEditable jTable;
	private RowSorter<TableModel> sorter;
	private JScrollPane scrollPane;

	public MainFrame() {
		super();
		setMinimumSize(new Dimension(1024, 768));
		setSize(new Dimension(800, 600));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("DVD Verwaltung");
		try {
			UIManager.setLookAndFeel(UIManager
					.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {

		}
		loadTable();
		initGUI();
	}

	private void initGUI() {
		try {

			{
				menuBar = new MenuBar(this);
				topPanel = new TopPanel();
			}
			{
				this.setSize(800, 600);
				this.setJMenuBar(menuBar);

			}

			TopPanel topPanel_1 = new TopPanel(this);

			JPanel panelMain = new JPanel();
			panelMain.setLayout(new CardLayout(0, 0));
			GroupLayout groupLayout = new GroupLayout(getContentPane());
			groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
					Alignment.LEADING).addGroup(
					Alignment.TRAILING,
					groupLayout
							.createSequentialGroup()
							.addGroup(
									groupLayout
											.createParallelGroup(
													Alignment.TRAILING)
											.addComponent(topPanel_1,
													Alignment.LEADING,
													GroupLayout.DEFAULT_SIZE,
													1440, Short.MAX_VALUE)
											.addComponent(panelMain,
													GroupLayout.DEFAULT_SIZE,
													1440, Short.MAX_VALUE))
							.addGap(0)));
			groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
					Alignment.LEADING).addGroup(
					groupLayout
							.createSequentialGroup()
							.addComponent(topPanel_1,
									GroupLayout.PREFERRED_SIZE, 29,
									GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panelMain, GroupLayout.DEFAULT_SIZE,
									795, Short.MAX_VALUE)));

			// Zuweisung des JTable Models, der Daten und der
			// Spaltenüberschriften, sowie des Rowsorters
			model = new DefaultTableModel(dvdData, columnNames);
			tbDVD = new JTableNotEditable(model, columnNames);
			tbDVD.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			tbDVD.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tbDVD.setFocusable(false);
			tbDVD.setModel(model);
			sorter = new TableRowSorter<TableModel>(model);
			tbDVD.setRowSorter(sorter);

			// Tabelleneigenschaften setzen
			jTable = new JTableNotEditable();
			tbDVD = jTable.setColumnSize(tbDVD);

			scrollPane = new JScrollPane(tbDVD);
			panelMain.add(scrollPane, "name_45280400366220");
			scrollPane.setViewportView(tbDVD);
			getContentPane().setLayout(groupLayout);
			this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void cardLayoutDVD() {
		CardLayout card = (CardLayout) panelMain.getLayout();
		card.show(panelMain, "custom_1350565561472");
	}

	/**
	 * Aufruf der FillMethode zur Befüllung der DVD JTable
	 */
	private void loadTable() {
		fill = new Filling();
		// Spaltenüberschriften
		try {
			dvdData = fill.getTable("dvd");
			// Ersetzen der Fremdschlüssel mit entsprechenden Einträgen aus den
			// Tabellen auf der DB
			fill.getNameFromID(dvdData);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Methode zum Updaten der MainFrame Tabelle nach Update der DVDs.
	 */
	public void refreshTable() {
		loadTable();
		DefaultTableModel model1 = new DefaultTableModel(dvdData, columnNames);
		tbDVD.setModel(model1);
		tbDVD.revalidate();
		
		// Tabelleneigenschaften setzen
		tbDVD = jTable.setColumnSize(tbDVD);
		
		MainFrame mf1 = new MainFrame();
		this.setVisible(false);
		mf1.setVisible(true);
		this.dispose();
		
	}

	/**
	 * Filtermethode für den jTable Inhalt, wird vom TopPanel aufgerufen
	 * 
	 * @param filterText
	 *            --> Filtertext aus dem TopPabel
	 */
	public void filterText(String filterText) {
		String txt;
		txt = filterText;
		if (txt.length() == 0) {
			((DefaultRowSorter<TableModel, Integer>) sorter).setRowFilter(null);
		} else {
			try {
				((DefaultRowSorter<TableModel, Integer>) sorter)
						.setRowFilter(RowFilter.regexFilter(txt));
			} catch (PatternSyntaxException pse) {
				System.err.println("Falscher Filterbegriff");
			}
		}
	}
}