package de.dhbw.projektarbeit.gui.Dialogs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.jdesktop.swingx.JXDatePicker;

import de.dhbw.projektarbeit.db.mysql.MysqlAccess;
import de.dhbw.projektarbeit.db.request.Delete;
import de.dhbw.projektarbeit.db.request.Filling;
import de.dhbw.projektarbeit.db.request.Update;
import de.dhbw.projektarbeit.gui.MainFrame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

/**
 * Klasse, die das Abändern von DVDs ermöglicht
 * 
 * @author Brunner
 * 
 */

public class EditDVD extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtTitle;
	private JTextField txtOriginalTitle;
	private JTextField txtEANCode;
	private JTextField txtGenre;
	private JSpinner spCountDVD, spDuration, spProductionYear;
	private JComboBox cbProducent, cbFSK, cbRegisseur, cbProdCountry, cbAuthor,
			cbCamera;
	private DefaultComboBoxModel cAuswahlRegisseur, cAuswahlProduction,
			cAuswahlCamera, cAuswahlAuthor;
	private Date releaseDate;
	private SimpleDateFormat sdf;
	private JXDatePicker dpReleaseDate;
	private JButton btnUpdate, btnDelete;
	private JButton cancelButton;
	private JTable tbDVD;
	private Object[][] dvdData;
	private DefaultTableModel model;
	private String[] columnNames = { "Menge", "Titel", "Originaltitel",
			"Genre", "Produktionsland", "Produktionsjahr", "Erscheinungsdatum",
			"Länge", "Altersfreigabe", "Regie", "Autor", "Produktion",
			"Kamera", "EAN Code" };
	private String oldEAN;
	private Filling fill = new Filling();
	private Vector<String> dbRegisseur = new Vector();
	private Vector<String> dbProduction = new Vector();
	private Vector<String> dbCamera = new Vector();
	private Vector<String> dbAuthor = new Vector();
	private MainFrame mf;
	private JTableNotEditable jTable;

	/**
	 * Standardkonstruktor
	 * 
	 * @throws Exception
	 */
	/*
	 * public EditDVD() throws Exception { loadTable(); setWindow(); }
	 */

	/**
	 * Konstruktor für die Übergabe der MainFrame Klasse
	 * 
	 * @param mf
	 *            --> Klasseninformationen der MainFrame Klasse
	 */
	public EditDVD(MainFrame mf) {
		this.mf = mf;
		loadTable();
		try {
			setWindow();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Methode zur Dialogerstellung
	 * 
	 * @throws Exception
	 */
	private void setWindow() throws Exception {
		setResizable(false);
		setModal(true);
		setTitle("DVDs bearbeiten");
		setBounds(100, 100, 615, 723);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		{
			JSplitPane splitPane = new JSplitPane();
			splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
			splitPane.setDividerSize(4);
			GridBagConstraints gbc_splitPane = new GridBagConstraints();
			gbc_splitPane.fill = GridBagConstraints.BOTH;
			gbc_splitPane.gridx = 0;
			gbc_splitPane.gridy = 0;
			contentPanel.add(splitPane, gbc_splitPane);
			{
				// Instantiierung von der Fillmethode für das Combofield
				Filling fill = new Filling();
				JPanel panel = new JPanel();
				panel.setBorder(new EmptyBorder(5, 5, 5, 5));
				splitPane.setRightComponent(panel);
				txtTitle = new JTextField();
				txtTitle.setEnabled(false);
				txtTitle.setColumns(22);
				txtOriginalTitle = new JTextField();
				txtOriginalTitle.setEnabled(false);
				txtOriginalTitle.setColumns(22);
				JLabel label = new JLabel("Titel");
				JLabel label_1 = new JLabel("Originaltitel");
				// Instantiierung von Combofield Variablen
				// ArrayList mit DB Daten von Regisseuren füllen
				try {
					dbRegisseur = fill.fillCbs("regisseur");
				} catch (IllegalArgumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				cAuswahlRegisseur = new DefaultComboBoxModel(dbRegisseur);
				cbRegisseur = new JComboBox();
				cbRegisseur.setEnabled(false);
				cbRegisseur.setModel(cAuswahlRegisseur);
				JButton btnNewRegisseur = new JButton("+");
				btnNewRegisseur.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						btnNewRegisseurActionPerformed(e);
					}
				});
				cbFSK = new JComboBox();
				cbFSK.setEnabled(false);
				cbFSK.setModel(new DefaultComboBoxModel(new String[] {
						"ab 0 Jahre", "ab 6 Jahre", "ab 12 Jahre",
						"ab 16 Jahre", "ab 18 Jahre", "indiziert" }));
				JLabel label_2 = new JLabel("Altersbeschr\u00E4nkung");
				JLabel label_3 = new JLabel("Herstellungsland");
				cbProdCountry = new JComboBox();
				cbProdCountry.setEnabled(false);
				cbProdCountry.setModel(new DefaultComboBoxModel(new String[] {
						"Australien", "Deutschland", "Frankreich",
						"Gro\u00DFbritannien", "Hong Kong", "Indien",
						"Italien", "Kanada", "Niederlande", "Russland",
						"Spanien", "USA" }));
				cbProdCountry.setSelectedIndex(11);
				JLabel lblRegisseur = new JLabel("Regisseur");
				JLabel label_5 = new JLabel("Produzent");
				JLabel label_6 = new JLabel("Genre");
				JLabel label_7 = new JLabel("EAN Code");
				txtEANCode = new JTextField();
				txtEANCode.setEnabled(false);
				txtEANCode.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent arg0) {
						txtEANCodeKeyReleased(arg0);
					}
				});
				txtEANCode.setColumns(22);
				JLabel label_8 = new JLabel("Produktionsjahr");
				spProductionYear = new JSpinner();
				spProductionYear.setEnabled(false);
				spProductionYear.setPreferredSize(new Dimension(40, 28));
				spProductionYear.setModel(new SpinnerNumberModel(1920, 1920,
						2020, 1));
				spProductionYear.setEditor(new JSpinner.NumberEditor(
						spProductionYear, "0"));
				dpReleaseDate = new JXDatePicker();
				dpReleaseDate.setEnabled(false);
				dpReleaseDate.setFormats(new String[] { "dd.MM.yyyy" });
				JLabel label_9 = new JLabel("Ver\u00F6ffentlichungsdatum");
				txtGenre = new JTextField();
				txtGenre.setEnabled(false);
				txtGenre.setColumns(22);
				// Instantiierung von Combofield Variablen
				// ArrayList mit DB Daten von Regisseuren füllen
				try {
					dbProduction = fill.fillCbs("production");
				} catch (IllegalArgumentException e1) {
					e1.printStackTrace();
				}
				cAuswahlProduction = new DefaultComboBoxModel(dbProduction);
				cbProducent = new JComboBox();
				cbProducent.setEnabled(false);
				cbProducent.setModel(cAuswahlProduction);
				JButton btnNewProducer = new JButton("+");
				btnNewProducer.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						btnNewProducerActionPerformed(e);
					}
				});
				JLabel lblDauerInMin = new JLabel("Dauer in Min.");
				spDuration = new JSpinner();
				spDuration.setEnabled(false);
				spDuration.setToolTipText("Max. 1000 Minuten");
				spDuration.setModel(new SpinnerNumberModel(1, 1, 1000, 1));

				JLabel lblAnzahl = new JLabel("Anzahl von DVDs");

				spCountDVD = new JSpinner();
				spCountDVD.setEnabled(false);
				spCountDVD.setPreferredSize(new Dimension(40, 28));
				spCountDVD.setModel(new javax.swing.SpinnerNumberModel(1, 1,
						1000, 1));
				spCountDVD
						.setEditor(new JSpinner.NumberEditor(spCountDVD, "0"));

				JLabel lblCamera = new JLabel("Kamera");

				// Instantiierung von Combofield Variablen
				// ArrayList mit DB Daten von Regisseuren füllen
				try {
					dbCamera = fill.fillCbs("camera");
				} catch (IllegalArgumentException e1) {
					e1.printStackTrace();
				}
				cAuswahlCamera = new DefaultComboBoxModel(dbCamera);
				cbCamera = new JComboBox();
				cbCamera.setEnabled(false);
				cbCamera.setModel(cAuswahlCamera);

				JButton btnNewCamera = new JButton("+");
				btnNewCamera.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						btnNewCameraActionPerformed(e);
					}
				});

				JLabel lblAuthor = new JLabel("Autor");

				// Instantiierung von Combofield Variablen
				// ArrayList mit DB Daten von Regisseuren füllen
				try {
					dbAuthor = fill.fillCbs("author");
				} catch (IllegalArgumentException e1) {
					e1.printStackTrace();
				}
				cAuswahlAuthor = new DefaultComboBoxModel(dbAuthor);
				cbAuthor = new JComboBox();
				cbAuthor.setEnabled(false);
				cbAuthor.setModel(cAuswahlAuthor);

				JButton btnNewAuthor = new JButton("+");
				btnNewAuthor.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						btnNewAuthorActionPerformed(e);
					}
				});
				GroupLayout gl_panel = new GroupLayout(panel);
				gl_panel.setHorizontalGroup(gl_panel
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_panel.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_panel.createParallelGroup(
														Alignment.LEADING)
														.addGroup(
																gl_panel.createSequentialGroup()
																		.addComponent(
																				cbCamera,
																				GroupLayout.PREFERRED_SIZE,
																				232,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				btnNewCamera,
																				GroupLayout.PREFERRED_SIZE,
																				40,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(18)
																		.addComponent(
																				cbAuthor,
																				GroupLayout.PREFERRED_SIZE,
																				224,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.UNRELATED)
																		.addComponent(
																				btnNewAuthor,
																				GroupLayout.PREFERRED_SIZE,
																				40,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																gl_panel.createSequentialGroup()
																		.addGroup(
																				gl_panel.createParallelGroup(
																						Alignment.LEADING)
																						.addComponent(
																								txtTitle,
																								GroupLayout.PREFERRED_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								txtOriginalTitle,
																								GroupLayout.PREFERRED_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								label)
																						.addComponent(
																								label_1)
																						.addGroup(
																								gl_panel.createSequentialGroup()
																										.addComponent(
																												cbRegisseur,
																												GroupLayout.PREFERRED_SIZE,
																												232,
																												GroupLayout.PREFERRED_SIZE)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												btnNewRegisseur,
																												GroupLayout.PREFERRED_SIZE,
																												40,
																												GroupLayout.PREFERRED_SIZE))
																						.addGroup(
																								gl_panel.createSequentialGroup()
																										.addGroup(
																												gl_panel.createParallelGroup(
																														Alignment.LEADING,
																														false)
																														.addComponent(
																																cbFSK,
																																GroupLayout.PREFERRED_SIZE,
																																129,
																																GroupLayout.PREFERRED_SIZE)
																														.addComponent(
																																label_2))
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addGroup(
																												gl_panel.createParallelGroup(
																														Alignment.LEADING)
																														.addComponent(
																																label_3)
																														.addComponent(
																																cbProdCountry,
																																GroupLayout.PREFERRED_SIZE,
																																140,
																																GroupLayout.PREFERRED_SIZE)))
																						.addComponent(
																								lblRegisseur)
																						.addComponent(
																								lblCamera))
																		.addGap(18)
																		.addGroup(
																				gl_panel.createParallelGroup(
																						Alignment.LEADING)
																						.addComponent(
																								lblAuthor)
																						.addComponent(
																								txtGenre,
																								GroupLayout.PREFERRED_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.PREFERRED_SIZE)
																						.addGroup(
																								gl_panel.createParallelGroup(
																										Alignment.LEADING,
																										false)
																										.addComponent(
																												label_5)
																										.addComponent(
																												label_6)
																										.addComponent(
																												label_7)
																										.addGroup(
																												gl_panel.createParallelGroup(
																														Alignment.TRAILING)
																														.addGroup(
																																gl_panel.createSequentialGroup()
																																		.addComponent(
																																				cbProducent,
																																				0,
																																				224,
																																				Short.MAX_VALUE)
																																		.addPreferredGap(
																																				ComponentPlacement.UNRELATED)
																																		.addComponent(
																																				btnNewProducer,
																																				GroupLayout.PREFERRED_SIZE,
																																				40,
																																				GroupLayout.PREFERRED_SIZE)
																																		.addGap(8))
																														.addGroup(
																																gl_panel.createSequentialGroup()
																																		.addGroup(
																																				gl_panel.createParallelGroup(
																																						Alignment.TRAILING)
																																						.addComponent(
																																								spProductionYear,
																																								GroupLayout.PREFERRED_SIZE,
																																								114,
																																								GroupLayout.PREFERRED_SIZE)
																																						.addComponent(
																																								label_8,
																																								GroupLayout.PREFERRED_SIZE,
																																								114,
																																								GroupLayout.PREFERRED_SIZE))
																																		.addPreferredGap(
																																				ComponentPlacement.RELATED)
																																		.addGroup(
																																				gl_panel.createParallelGroup(
																																						Alignment.LEADING)
																																						.addGroup(
																																								gl_panel.createSequentialGroup()
																																										.addComponent(
																																												dpReleaseDate,
																																												GroupLayout.DEFAULT_SIZE,
																																												148,
																																												Short.MAX_VALUE)
																																										.addContainerGap())
																																						.addComponent(
																																								label_9)))))
																						.addComponent(
																								txtEANCode,
																								GroupLayout.PREFERRED_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.PREFERRED_SIZE)))
														.addGroup(
																gl_panel.createSequentialGroup()
																		.addGroup(
																				gl_panel.createParallelGroup(
																						Alignment.LEADING)
																						.addComponent(
																								lblDauerInMin)
																						.addComponent(
																								spDuration,
																								GroupLayout.PREFERRED_SIZE,
																								73,
																								GroupLayout.PREFERRED_SIZE))
																		.addGap(18)
																		.addGroup(
																				gl_panel.createParallelGroup(
																						Alignment.LEADING)
																						.addComponent(
																								spCountDVD,
																								GroupLayout.PREFERRED_SIZE,
																								114,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								lblAnzahl))))));
				gl_panel.setVerticalGroup(gl_panel
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_panel.createSequentialGroup()
										.addGroup(
												gl_panel.createParallelGroup(
														Alignment.BASELINE)
														.addComponent(label)
														.addComponent(label_7))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_panel.createParallelGroup(
														Alignment.BASELINE)
														.addComponent(
																txtTitle,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																txtEANCode,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addGroup(
												gl_panel.createParallelGroup(
														Alignment.BASELINE)
														.addComponent(label_1)
														.addComponent(label_6))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_panel.createParallelGroup(
														Alignment.BASELINE)
														.addComponent(
																txtOriginalTitle,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																txtGenre,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addGroup(
												gl_panel.createParallelGroup(
														Alignment.BASELINE)
														.addComponent(label_3)
														.addComponent(label_2)
														.addComponent(label_9)
														.addComponent(label_8))
										.addGap(12)
										.addGroup(
												gl_panel.createParallelGroup(
														Alignment.TRAILING,
														false)
														.addGroup(
																gl_panel.createParallelGroup(
																		Alignment.BASELINE)
																		.addComponent(
																				cbFSK,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)
																		.addComponent(
																				cbProdCountry,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE))
														.addComponent(
																dpReleaseDate,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																spProductionYear,
																0, 0,
																Short.MAX_VALUE))
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addGroup(
												gl_panel.createParallelGroup(
														Alignment.BASELINE)
														.addComponent(
																lblRegisseur)
														.addComponent(label_5))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_panel.createParallelGroup(
														Alignment.BASELINE)
														.addComponent(
																cbRegisseur,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																btnNewRegisseur,
																GroupLayout.PREFERRED_SIZE,
																28,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																cbProducent,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																btnNewProducer,
																GroupLayout.PREFERRED_SIZE,
																28,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addGroup(
												gl_panel.createParallelGroup(
														Alignment.BASELINE)
														.addComponent(lblCamera)
														.addComponent(lblAuthor))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_panel.createParallelGroup(
														Alignment.BASELINE)
														.addComponent(
																cbCamera,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																btnNewCamera,
																GroupLayout.PREFERRED_SIZE,
																28,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																cbAuthor,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																btnNewAuthor,
																GroupLayout.PREFERRED_SIZE,
																28,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addGroup(
												gl_panel.createParallelGroup(
														Alignment.BASELINE)
														.addComponent(
																lblDauerInMin)
														.addComponent(lblAnzahl))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_panel.createParallelGroup(
														Alignment.LEADING,
														false)
														.addComponent(
																spCountDVD, 0,
																0,
																Short.MAX_VALUE)
														.addComponent(
																spDuration,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addContainerGap(9, Short.MAX_VALUE)));
				panel.setLayout(gl_panel);
			}

			// Zuweisung des JTable Models, der Daten und der
			// Spaltenüberschriften
			model = new DefaultTableModel(dvdData, columnNames);
			tbDVD = new JTableNotEditable(model, columnNames);
			tbDVD.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			tbDVD.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					// Mausklick auf Tabellenspalte
					tbDVDMouseClicked(e);
				}
			});
			tbDVD.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tbDVD.setFocusable(false);
			tbDVD.setModel(model);
			RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
			tbDVD.setRowSorter(sorter);

			// Tabelleneigenschaften setzen
			jTable = new JTableNotEditable();
			tbDVD = jTable.setColumnSize(tbDVD);

			JScrollPane scrollPane = new JScrollPane(tbDVD);
			splitPane.setLeftComponent(scrollPane);
			scrollPane.setViewportView(tbDVD);
			splitPane.setDividerLocation(260);
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				cancelButton = new JButton("Abbrechen");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
			}

			btnDelete = new JButton("Loeschen");
			btnDelete.setEnabled(false);
			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					btnDeleteActionPerformed(arg0);
				}
			});

			btnUpdate = new JButton("Speichern");
			btnUpdate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					btnUpdateActionPerformed(arg0);
				}
			});

			btnUpdate.setEnabled(false);
			GroupLayout gl_buttonPane = new GroupLayout(buttonPane);
			gl_buttonPane.setHorizontalGroup(gl_buttonPane.createParallelGroup(
					Alignment.TRAILING).addGroup(
					gl_buttonPane
							.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnDelete,
									GroupLayout.PREFERRED_SIZE, 96,
									GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(btnUpdate,
									GroupLayout.PREFERRED_SIZE, 103,
									GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 319,
									Short.MAX_VALUE).addComponent(cancelButton)
							.addContainerGap()));
			gl_buttonPane.setVerticalGroup(gl_buttonPane.createParallelGroup(
					Alignment.LEADING).addGroup(
					gl_buttonPane
							.createSequentialGroup()
							.addGap(5)
							.addGroup(
									gl_buttonPane
											.createParallelGroup(
													Alignment.LEADING)
											.addComponent(btnDelete)
											.addComponent(btnUpdate)
											.addComponent(cancelButton))
							.addContainerGap(GroupLayout.DEFAULT_SIZE,
									Short.MAX_VALUE)));
			buttonPane.setLayout(gl_buttonPane);
		}
	}

	/**
	 * Methode zur Zeichenkürzung ab 13 Stellen im EAN Code um einen Overflow
	 * beim Update zu verhindern
	 * 
	 * @param arg0
	 *            --> Eventhandling
	 */
	protected void txtEANCodeKeyReleased(KeyEvent arg0) {
		// Prüfung ob eine Zahl oder anderes Zeichen eingegeben wurde oder
		// länger als 11 Zeichen ist
		String text = txtEANCode.getText();
		if (txtEANCode.getText().matches("[0-9]*")) {

		} else {
			JOptionPane.showMessageDialog(null,
					"Ein EAN Code besteht nur aus Zahlen!", "EAN Code",
					JOptionPane.ERROR_MESSAGE);
			txtEANCode.setText(text.substring(0, text.length() - 1));
		}

		if (text.length() > 13) {
			txtEANCode.setText(text.substring(0, text.length() - 1));
			JOptionPane.showMessageDialog(null,
					"Der EAN Code darf maximal 13 Stellen haben!", "EAN Code",
					JOptionPane.ERROR_MESSAGE);

		}
	}

	/**
	 * Aufruf der FillMethode zur Befüllung der DVD JTable
	 */
	private void loadTable() {
		// Spaltenüberschriften
		try {
			dvdData = fill.getTable("dvd");
			// Ersetzen der Fremdschlüssel mit entsprechenden Einträgen aus den
			// Tabellen auf der DB
			fill.getNameFromID(dvdData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		;
	}

	/**
	 * Tabellenzeile wurde ausgewählt, Felder werden aus ausgewählter Zeile
	 * geladen
	 * 
	 * @param e
	 *            --> Eventhandling
	 */
	private void tbDVDMouseClicked(MouseEvent e) {
		try {
			// SimpleDateFormat für das Parsen von String in java.util.Date
			sdf = new SimpleDateFormat("dd.MM.yyyy");

			spCountDVD.setValue(tbDVD.getValueAt(tbDVD.getSelectedRow(), 0));
			txtTitle.setText((String) tbDVD.getValueAt(tbDVD.getSelectedRow(),
					1));
			txtOriginalTitle.setText((String) tbDVD.getValueAt(
					tbDVD.getSelectedRow(), 2));
			txtGenre.setText((String) tbDVD.getValueAt(tbDVD.getSelectedRow(),
					3));
			cbProdCountry.setSelectedItem(tbDVD.getValueAt(
					tbDVD.getSelectedRow(), 4));
			spProductionYear.setValue(tbDVD.getValueAt(tbDVD.getSelectedRow(),
					5));
			dpReleaseDate.setDate((java.util.Date) sdf.parse((String) (tbDVD
					.getValueAt(tbDVD.getSelectedRow(), 6))));
			spDuration.setValue(tbDVD.getValueAt(tbDVD.getSelectedRow(), 7));
			cbFSK.setSelectedItem(tbDVD.getValueAt(tbDVD.getSelectedRow(), 8));
			cbRegisseur.setSelectedItem(tbDVD.getValueAt(
					tbDVD.getSelectedRow(), 9));
			cbAuthor.setSelectedItem(tbDVD.getValueAt(tbDVD.getSelectedRow(),
					10));
			cbProducent.setSelectedItem(tbDVD.getValueAt(
					tbDVD.getSelectedRow(), 11));
			cbCamera.setSelectedItem(tbDVD.getValueAt(tbDVD.getSelectedRow(),
					12));

			txtEANCode.setText((String) tbDVD.getValueAt(
					tbDVD.getSelectedRow(), 13));
			// Befüllen der alten EAN, um sie später übergeben zu können
			oldEAN = txtEANCode.getText();
			if (txtEANCode != null) {
				btnUpdate.setEnabled(true);
				btnDelete.setEnabled(true);
				txtEANCode.setEnabled(true);
				txtGenre.setEnabled(true);
				txtOriginalTitle.setEnabled(true);
				txtTitle.setEnabled(true);
				cbAuthor.setEnabled(true);
				cbCamera.setEnabled(true);
				cbFSK.setEnabled(true);
				cbProdCountry.setEnabled(true);
				cbProducent.setEnabled(true);
				cbRegisseur.setEnabled(true);
				spCountDVD.setEnabled(true);
				spDuration.setEnabled(true);
				spProductionYear.setEnabled(true);
				dpReleaseDate.setEnabled(true);
			}
		} catch (Exception a) {
			// TODO: handle exception
			a.printStackTrace();
		}
	}

	/**
	 * Updatebutton wurde gedrückt Methode ruft die Updatefunktion auf
	 * 
	 * @param e
	 *            --> Actionhandling
	 */
	private void btnUpdateActionPerformed(ActionEvent e) {
		MysqlAccess mysql = new MysqlAccess();
		boolean go = true;
		try {
			// Updatemethode aufrufen und Connection zum SQL Server herstellen
			Update update = new Update("dvd_verleih", mysql.getConnection());
			// Festlegung des Formats für das SQL Date Feld
			sdf = new SimpleDateFormat();
			sdf.applyPattern("yyyy-MM-dd");
			// Auf leere Pflichtfelder überprüfen
			if (txtTitle.getText().replaceAll(" ", "").equals("")) {
				go = false;
			} else if (txtOriginalTitle.getText().replaceAll(" ", "")
					.equals("")) {
				go = false;
			} else if (txtEANCode.getText().replaceAll(" ", "").equals("")) {
				go = false;
			} else if (txtGenre.getText().replaceAll(" ", "").equals("")) {
				go = false;
			} else if (((String) cbRegisseur.getSelectedItem()).replaceAll(" ",
					"").equals("")) {
				go = false;
			} else if (((String) cbProducent.getSelectedItem()).replaceAll(" ",
					"").equals("")) {
				go = false;
			} else if (((String) cbCamera.getSelectedItem())
					.replaceAll(" ", "").equals("")) {
				go = false;
			} else if (((String) cbAuthor.getSelectedItem())
					.replaceAll(" ", "").equals("")) {
				go = false;
			} else if (dpReleaseDate.getDate() == null) {
				go = false;
			} else if (dpReleaseDate.getDate() != null) {
				try {
					releaseDate = (Date.valueOf(sdf.format(dpReleaseDate
							.getDate())));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if (go == true) {
				update.editDVD((Integer) spCountDVD.getValue(),
						txtTitle.getText(), txtOriginalTitle.getText(),
						txtGenre.getText(),
						(String) cbProdCountry.getSelectedItem(),
						(Integer) spProductionYear.getValue(), releaseDate,
						(Integer) spDuration.getValue(),
						(String) cbFSK.getSelectedItem(),
						(String) cbRegisseur.getSelectedItem(),
						(String) cbAuthor.getSelectedItem(),
						(String) cbProducent.getSelectedItem(),
						(String) cbCamera.getSelectedItem(),
						txtEANCode.getText(), oldEAN);

				// Aktualisieren der JTable tbDVD
				tbDVD.setValueAt(spCountDVD.getValue(), tbDVD.getSelectedRow(),
						0);
				tbDVD.setValueAt(txtTitle.getText(), tbDVD.getSelectedRow(), 1);
				tbDVD.setValueAt(txtOriginalTitle.getText(),
						tbDVD.getSelectedRow(), 2);
				tbDVD.setValueAt(txtGenre.getText(), tbDVD.getSelectedRow(), 3);
				tbDVD.setValueAt((String) cbProdCountry.getSelectedItem(),
						tbDVD.getSelectedRow(), 4);
				tbDVD.setValueAt(spProductionYear.getValue(),
						tbDVD.getSelectedRow(), 5);
				tbDVD.setValueAt(releaseDate, tbDVD.getSelectedRow(), 6);
				tbDVD.setValueAt(spDuration.getValue(), tbDVD.getSelectedRow(),
						7);
				tbDVD.setValueAt((String) cbFSK.getSelectedItem(),
						tbDVD.getSelectedRow(), 8);
				tbDVD.setValueAt((String) cbRegisseur.getSelectedItem(),
						tbDVD.getSelectedRow(), 9);
				tbDVD.setValueAt((String) cbAuthor.getSelectedItem(),
						tbDVD.getSelectedRow(), 10);
				tbDVD.setValueAt((String) cbProducent.getSelectedItem(),
						tbDVD.getSelectedRow(), 11);
				tbDVD.setValueAt((String) cbCamera.getSelectedItem(),
						tbDVD.getSelectedRow(), 12);
				tbDVD.setValueAt(txtEANCode.getText(), tbDVD.getSelectedRow(),
						13);
				// MainFrame Tabelle aktualisieren
				mf.refreshTable();

			} else {
				JOptionPane
						.showMessageDialog(
								null,
								"Sie haben ein Pflichtfeld nicht ausgefüllt! Bitte überprüfen Sie ihre Angaben in den Feldern",
								"Regisseurerstellung",
								JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * Löschen Button wurde gedrückt
	 * 
	 * @param arg0
	 *            --> Actionhandling
	 */
	protected void btnDeleteActionPerformed(ActionEvent arg0) {
		MysqlAccess mysql = new MysqlAccess();
		boolean go = true;

		try {
			// Auf leere Pflichtfelder ueberpruefen
			if (txtEANCode.getText().replaceAll(" ", "").equals("")) {
				go = false;
			}
			if (go == true) {
				// Deletemethode aufrufen und Connection zum SQL Server
				// herstellen
				Delete delete = new Delete("dvd_verleih", mysql.getConnection());
				delete.deleteDVD(this, "Barcode", "dvd", txtEANCode.getText());
				// MainFrame Tabelle aktualisieren
				mf.refreshTable();
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/**
	 * Rückmeldung aus der Methode Delete
	 * 
	 * @param code
	 */
	public void dvdDeleted() {
		try {
			this.setVisible(false);
			this.dispose();

			// Neues, leeres Erstellungsfenster instantiieren
			EditDVD dialog = new EditDVD(mf);
			dialog.setVisible(true);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Button "Neuer Regisseur" gedrückt
	 * 
	 * @param e
	 *            --> Actionhandling
	 */
	private void btnNewRegisseurActionPerformed(ActionEvent e) {
		NewRegisseur nr = new NewRegisseur(this);
		nr.setVisible(true);
	}

	/**
	 * Button "Neuer Produzent" gedrückt
	 * 
	 * @param e
	 *            --> Actionhandling
	 */
	private void btnNewProducerActionPerformed(ActionEvent e) {
		NewProducer np = new NewProducer(this);
		np.setVisible(true);
	}

	/**
	 * Button "Neuer Kameramann" gedrückt
	 * 
	 * @param e
	 *            --> Actionhandling
	 */
	private void btnNewCameraActionPerformed(ActionEvent e) {
		NewCamera nc = new NewCamera(this);
		nc.setVisible(true);
	}

	/**
	 * Button "Neuer Autor" gedrückt
	 * 
	 * @param e
	 *            --> Actionhandling
	 */
	private void btnNewAuthorActionPerformed(ActionEvent e) {
		NewAuthor na = new NewAuthor(this);
		na.setVisible(true);
	}

	/**
	 * Methode zum Updaten der Combobox Regisseur nach Neueingabe
	 * 
	 * @param regisseur
	 *            Konkadinierter Rückgabewerte aus der Insertmethode
	 */
	public void updateComboboxRegisseur(String regisseur) {
		dbRegisseur.add(regisseur);
		cAuswahlRegisseur = new DefaultComboBoxModel(dbRegisseur);
		cbRegisseur.setModel(cAuswahlRegisseur);
		cbRegisseur.setSelectedItem(regisseur);

	}

	/**
	 * Methode zum Updaten der Combobox Produzent nach Neueingabe
	 * 
	 * @param production
	 *            = Konkadinierter Rückgabewerte aus der Insertmethode
	 */
	public void updateComboboxProduction(String production) {
		dbProduction.add(production);
		cAuswahlProduction = new DefaultComboBoxModel(dbProduction);
		cbProducent.setModel(cAuswahlProduction);
		cbProducent.setSelectedItem(production);

	}

	/**
	 * Methode zum Updaten der Combobox Kamera nach Neueingabe
	 * 
	 * @param production
	 *            = Konkadinierter Rückgabewerte aus der Insertmethode
	 */
	public void updateComboboxCamera(String camera) {
		dbCamera.add(camera);
		cAuswahlCamera = new DefaultComboBoxModel(dbCamera);
		cbCamera.setModel(cAuswahlCamera);
		cbCamera.setSelectedItem(camera);

	}

	/**
	 * Methode zum Updaten der Combobox Author nach Neueingabe
	 * 
	 * @param author
	 *            = Konkadinierter Rückgabewerte aus der Insertmethode
	 */
	public void updateComboboxAuthor(String author) {
		dbAuthor.add(author);
		cAuswahlAuthor = new DefaultComboBoxModel(dbAuthor);
		cbAuthor.setModel(cAuswahlAuthor);
		cbAuthor.setSelectedItem(author);

	}
}
