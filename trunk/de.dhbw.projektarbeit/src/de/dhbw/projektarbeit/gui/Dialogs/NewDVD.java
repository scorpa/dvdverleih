package de.dhbw.projektarbeit.gui.Dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListDataListener;
import javax.swing.ComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SpinnerModel;

import java.awt.Dimension;
import javax.swing.JSpinner;
import org.jdesktop.swingx.JXDatePicker;

import de.dhbw.projektarbeit.db.request.Filling;
import de.dhbw.projektarbeit.db.request.Insert;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.security.InvalidParameterException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;

public class NewDVD extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtTitle;
	private JTextField txtOriginaltitle;
	private JTextField txtEancode;
	private JTextField txtGenre;
	private JButton addButton;
	private JButton cancelButton;
	private String fsk, prodCountry, regie, production, author, camera;
	private DefaultComboBoxModel cAuswahlRegisseur, cAuswahlProduction;
	private Vector<String> vAuswahlRegisseur;
	private SimpleDateFormat sdf;
	private Date release;
	private int prod_year, duration, quantity;
	private JComboBox cbRegisseur, cbFSK, cbProdCountry, cbProducent;
	private Vector<String> dbRegisseur = new Vector();
	private Vector<String> dbProduction = new Vector();
	private JXDatePicker dpRelease;
	private JSpinner spDuration, spCountDVD, spProductionYear;
	private SpinnerNumberModel smDuration, smCountDVD, smProductionYear;
	private Insert insert;
	private Connection con;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NewDVD dialog = new NewDVD();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * 
	 * @throws Exception
	 */
	public NewDVD() throws Exception {
		setResizable(false);
		setModal(true);
		setTitle("Neue DVD anlegen");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 612, 365);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel lblTitel = new JLabel("Titel");
		txtTitle = new JTextField();
		txtTitle.setText("Title");
		txtTitle.setColumns(22);
		txtOriginaltitle = new JTextField();
		txtOriginaltitle.setText("OriginalTitle");
		txtOriginaltitle.setColumns(22);
		JLabel lblOriginaltitel = new JLabel("Originaltitel");
		txtEancode = new JTextField();
		txtEancode.setText("EANCode");
		txtEancode.setColumns(22);

		JLabel lblEanCode = new JLabel("EAN Code");

		txtGenre = new JTextField();
		txtGenre.setText("Genre");
		txtGenre.setColumns(22);

		JLabel lblGenre = new JLabel("Genre");

		JLabel lblAltersbeschrnkung = new JLabel("Altersbeschr\u00E4nkung");

		// Auslesen des Combofields
		cbFSK = new JComboBox();
		cbFSK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fsk = (String) cbFSK.getSelectedItem();
			}

		});
		cbFSK.setModel(new DefaultComboBoxModel(new String[] { "ab 0 Jahre",
				"ab 6 Jahre", "ab 12 Jahre", "ab 16 Jahre", "ab 18 Jahre",
				"indiziert" }));

		// Auslesen des Combofields
		cbProdCountry = new JComboBox();
		cbProdCountry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		cbProdCountry.setModel(new DefaultComboBoxModel(new String[] {
				"Australien", "Deutschland", "Frankreich",
				"Gro\u00DFbritannien", "Hong Kong", "Indien", "Italien",
				"Kanada", "Niederlande", "Russland", "Spanien", "USA" }));
		cbProdCountry.setSelectedIndex(11);

		JLabel lblHerstellungsland = new JLabel("Herstellungsland");

		spProductionYear = new JSpinner();
		spProductionYear.setPreferredSize(new Dimension(40, 28));
		// SpinnerNumberModel smProductionYear setzen
		smProductionYear = new SpinnerNumberModel(2012, 1920, 2020, 1);
		spProductionYear.setModel(smProductionYear);
		spProductionYear.setEditor(new JSpinner.NumberEditor(spProductionYear,
				"0"));

		// Festlegung des Formats �ber die JXDate Funktion
		sdf = new SimpleDateFormat();
		sdf.applyPattern("yyyy-MM-dd");

		JLabel lblProduktionsjahr = new JLabel("Produktionsjahr");

		dpRelease = new JXDatePicker();
		dpRelease.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		dpRelease.setFormats(new String[] { "DD.MM.YYYY" });

		JLabel lblVerffentlichungsdatum = new JLabel(
				"Ver\u00F6ffentlichungsdatum");

		JLabel lblRegisseur = new JLabel("Regisseur");

		// Instantiierung von der Fillmethode f�r das Combofield
		Filling fill = new Filling();

		// Instantiierung von Combofield Variablen
		// ArrayList mit DB Daten von Regisseuren f�llen
		try {
			dbRegisseur = fill.fillCbRegisseur();
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		cAuswahlRegisseur = new DefaultComboBoxModel(dbRegisseur);
		cbRegisseur = new JComboBox();
		cbRegisseur.setModel(cAuswahlRegisseur);
		cbRegisseur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

		JButton btnNewRegisseur = new JButton("+");
		btnNewRegisseur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NewRegisseur nr = new NewRegisseur();
				nr.setVisible(true);
			}
		});

		JLabel lblProduzent = new JLabel("Produzent");

		JButton btnNewProducent = new JButton("+");
		btnNewProducent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewProducer np = new NewProducer();
				np.setVisible(true);
			}
		});

		// Instantiierung von Combofield Variablen
		// ArrayList mit DB Daten von Regisseuren f�llen
		try {
			dbProduction = fill.fillCbProduction();
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		cAuswahlProduction = new DefaultComboBoxModel(dbProduction);
		cbProducent = new JComboBox();
		cbProducent.setModel(cAuswahlProduction);
		cbProducent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Auslesen des Combofields
				production = (String) cbProducent.getSelectedItem();
			}
		});

		JLabel lblDauer = new JLabel("Dauer in Min.");

		spDuration = new JSpinner();
		// SpinnerNumberModel smDuration setzen
		smDuration = new SpinnerNumberModel(1, 1, 10000, 1);
		spDuration.setModel(smDuration);

		spCountDVD = new JSpinner();
		spCountDVD.setPreferredSize(new Dimension(40, 28));
		// SpinnerNumberModel smDuration setzen
		smDuration = new SpinnerNumberModel(1, 1, 1000, 1);
		spCountDVD.setModel(smDuration);
		spCountDVD.setEditor(new JSpinner.NumberEditor(spCountDVD, "0"));

		JLabel lblAnzahl = new JLabel("Anzahl der DVDs");

		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel
				.setHorizontalGroup(gl_contentPanel
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_contentPanel
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_contentPanel
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																txtOriginaltitle,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																lblOriginaltitel)
														.addGroup(
																gl_contentPanel
																		.createSequentialGroup()
																		.addComponent(
																				cbRegisseur,
																				GroupLayout.PREFERRED_SIZE,
																				232,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				btnNewRegisseur,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE))
														.addGroup(
																gl_contentPanel
																		.createSequentialGroup()
																		.addGroup(
																				gl_contentPanel
																						.createParallelGroup(
																								Alignment.LEADING,
																								false)
																						.addComponent(
																								cbFSK,
																								GroupLayout.PREFERRED_SIZE,
																								129,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								lblAltersbeschrnkung))
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addGroup(
																				gl_contentPanel
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								lblHerstellungsland)
																						.addComponent(
																								cbProdCountry,
																								GroupLayout.PREFERRED_SIZE,
																								140,
																								GroupLayout.PREFERRED_SIZE)))
														.addComponent(
																lblRegisseur)
														.addComponent(
																txtTitle,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(lblTitel)
														.addGroup(
																gl_contentPanel
																		.createSequentialGroup()
																		.addGroup(
																				gl_contentPanel
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								lblDauer)
																						.addComponent(
																								spDuration,
																								GroupLayout.PREFERRED_SIZE,
																								73,
																								GroupLayout.PREFERRED_SIZE))
																		.addGap(18)
																		.addGroup(
																				gl_contentPanel
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								spCountDVD,
																								GroupLayout.PREFERRED_SIZE,
																								114,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								lblAnzahl))))
										.addPreferredGap(
												ComponentPlacement.RELATED, 59,
												Short.MAX_VALUE)
										.addGroup(
												gl_contentPanel
														.createParallelGroup(
																Alignment.TRAILING,
																false)
														.addGroup(
																gl_contentPanel
																		.createParallelGroup(
																				Alignment.TRAILING,
																				false)
																		.addComponent(
																				txtEancode,
																				Alignment.LEADING,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)
																		.addGroup(
																				gl_contentPanel
																						.createSequentialGroup()
																						.addGroup(
																								gl_contentPanel
																										.createParallelGroup(
																												Alignment.LEADING)
																										.addComponent(
																												lblProduktionsjahr,
																												GroupLayout.PREFERRED_SIZE,
																												114,
																												GroupLayout.PREFERRED_SIZE)
																										.addComponent(
																												lblProduzent)
																										.addComponent(
																												spProductionYear,
																												GroupLayout.PREFERRED_SIZE,
																												114,
																												GroupLayout.PREFERRED_SIZE))
																						.addPreferredGap(
																								ComponentPlacement.UNRELATED)
																						.addGroup(
																								gl_contentPanel
																										.createParallelGroup(
																												Alignment.LEADING,
																												false)
																										.addComponent(
																												dpRelease,
																												GroupLayout.DEFAULT_SIZE,
																												GroupLayout.DEFAULT_SIZE,
																												Short.MAX_VALUE)
																										.addComponent(
																												lblVerffentlichungsdatum,
																												GroupLayout.DEFAULT_SIZE,
																												GroupLayout.DEFAULT_SIZE,
																												Short.MAX_VALUE)))
																		.addComponent(
																				txtGenre,
																				Alignment.LEADING,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)
																		.addComponent(
																				lblEanCode,
																				Alignment.LEADING)
																		.addComponent(
																				lblGenre,
																				Alignment.LEADING))
														.addGroup(
																gl_contentPanel
																		.createSequentialGroup()
																		.addComponent(
																				cbProducent,
																				0,
																				GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				btnNewProducent,
																				GroupLayout.PREFERRED_SIZE,
																				41,
																				GroupLayout.PREFERRED_SIZE)))
										.addContainerGap(
												GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));
		gl_contentPanel
				.setVerticalGroup(gl_contentPanel
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_contentPanel
										.createSequentialGroup()
										.addGroup(
												gl_contentPanel
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(lblTitel)
														.addComponent(
																lblEanCode))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_contentPanel
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																txtEancode,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																txtTitle,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addGroup(
												gl_contentPanel
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblOriginaltitel)
														.addComponent(lblGenre))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_contentPanel
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																txtOriginaltitle,
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
												gl_contentPanel
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblHerstellungsland)
														.addComponent(
																lblAltersbeschrnkung)
														.addComponent(
																lblVerffentlichungsdatum)
														.addComponent(
																lblProduktionsjahr))
										.addGap(12)
										.addGroup(
												gl_contentPanel
														.createParallelGroup(
																Alignment.TRAILING,
																false)
														.addGroup(
																gl_contentPanel
																		.createParallelGroup(
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
																dpRelease,
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
												gl_contentPanel
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblRegisseur)
														.addComponent(
																lblProduzent))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_contentPanel
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																cbRegisseur,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																cbProducent,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																btnNewProducent,
																GroupLayout.PREFERRED_SIZE,
																28,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																btnNewRegisseur,
																GroupLayout.PREFERRED_SIZE,
																28,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_contentPanel
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(lblDauer)
														.addComponent(lblAnzahl))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_contentPanel
														.createParallelGroup(
																Alignment.TRAILING,
																false)
														.addComponent(
																spCountDVD,
																Alignment.LEADING,
																0, 0,
																Short.MAX_VALUE)
														.addComponent(
																spDuration,
																Alignment.LEADING,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addContainerGap(27, Short.MAX_VALUE)));
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				addButton = new JButton("Hinzuf\u00FCgen");
				addButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							addActionPerformed(arg0);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						// Zuteilung der Filml�nge und Menge auf die
						// Integervaribalen
						try {
							duration = (Integer) spDuration.getValue();
							quantity = (Integer) spCountDVD.getValue();
						} catch (NumberFormatException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}

				});
				addButton.setActionCommand("add");
				getRootPane().setDefaultButton(addButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
			}
			GroupLayout gl_buttonPane = new GroupLayout(buttonPane);
			gl_buttonPane.setHorizontalGroup(gl_buttonPane.createParallelGroup(
					Alignment.TRAILING).addGroup(
					gl_buttonPane.createSequentialGroup()
							.addContainerGap(436, Short.MAX_VALUE)
							.addComponent(addButton)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cancelButton).addContainerGap()));
			gl_buttonPane.setVerticalGroup(gl_buttonPane.createParallelGroup(
					Alignment.LEADING).addGroup(
					Alignment.TRAILING,
					gl_buttonPane
							.createSequentialGroup()
							.addContainerGap(GroupLayout.DEFAULT_SIZE,
									Short.MAX_VALUE)
							.addGroup(
									gl_buttonPane
											.createParallelGroup(
													Alignment.BASELINE)
											.addComponent(cancelButton)
											.addComponent(addButton))
							.addContainerGap()));
			buttonPane.setLayout(gl_buttonPane);
		}
	}

	private void addActionPerformed(ActionEvent arg0) throws Exception {
		// Hinzuf�gen Button gedr�ckt
		String title, originalTitle, eanCode, genre;
		boolean go = true;

		// �bergabe der Inputs aus Textfeldern
		title = txtTitle.getText();
		originalTitle = txtOriginaltitle.getText();
		eanCode = txtEancode.getText();
		genre = txtGenre.getText();

		// Auslesen der Combofields
		regie = (String) cbRegisseur.getSelectedItem();
		prodCountry = (String) cbProdCountry.getSelectedItem();
		fsk = (String) cbFSK.getSelectedItem();
		camera = "";
		author = "";

		// �berf�rung des JXDates in die Datevariable
		try {
			release = (Date.valueOf(sdf.format(dpRelease.getDate())));
		} catch (IllegalArgumentException i) {
			i.printStackTrace();
		}

		// JSpinner auslesen spDuration, spCountDVD, spProductionYear
		duration = smDuration.getNumber().intValue();
		quantity = smCountDVD.getNumber().intValue();
		prod_year = smProductionYear.getNumber().intValue();

		// Auf leere Pflichtfelder �berpr�fen
		if (title.replaceAll(" ", "").equals("")) {
			go = false;
		} else if (originalTitle.replaceAll(" ", "").equals("")) {
			go = false;
		} else if (eanCode.replaceAll(" ", "").equals("")) {
			go = false;
		} else if (genre.replaceAll(" ", "").equals("")) {
			go = false;
		} else if (regie.replaceAll(" ", "").equals("")) {
			go = false;
		} else if (production.replaceAll(" ", "").equals("")) {
			go = false;
		} else if (release == null) {
			go = false;
		}

		// Aufrufen der Methode zum Anlegen einer DVD
		if (go == true) {
			// Verbindung zum SQL Server aufbauen
			try {
				con = DriverManager
						.getConnection("jdbc:mysql://localhost/dvd_verleih?user=root");
			} catch (SQLException e) {
				// Verbindung zum SQL Server fehlgeschlagen. Fehlercode 005
				e.printStackTrace();
				throw new Exception(
						"Verbindung zum SQL Server fehlgeschlagen. Fehlercode 005");
			}
			insert = new Insert("dvd_verleih", con);
			try {
				insert.insertDVD(this, eanCode, regie, production, camera,
						author, title, genre, fsk, prod_year, release,
						duration, prodCountry, originalTitle, quantity);
			} catch (InvalidParameterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public void updateComboboxRegisseur(String regisseur) {
		dbRegisseur.add(regisseur);
		cAuswahlRegisseur = new DefaultComboBoxModel<String>(dbRegisseur);
		cbRegisseur.setModel(cAuswahlRegisseur);

	}

	public void fillRegisseur() {

	}
	
	public void dvdAdded(String title) throws Exception{
		// Wenn Benutzer erfolgreich hinzu gef�gt wurde, die mitteilen und neues, leeres Eingabefenster �ffnen.
		
		JOptionPane.showMessageDialog(null, ("Die DVD" + title + " wurde erfolgreich angelegt!"), "Vorgang erfolgreich", JOptionPane.INFORMATION_MESSAGE);
		this.setVisible(false);
		this.dispose();
		
		// Neues, leeres Erstellungsfenster instantiieren
		NewDVD dialog = new NewDVD();
		dialog.setVisible(true);

		
	}
}
