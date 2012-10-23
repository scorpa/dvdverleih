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

/**
 * JDialog zum Einpflegen neuer DVDs
 * @author Julian Brunner
 */

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
	private JSpinner spCountDVD, spProductionYear;
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
		setBounds(100, 100, 612, 450);
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

		// Festlegung des Formats über die JXDate Funktion
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

		// Instantiierung von der Fillmethode für das Combofield
		Filling fill = new Filling();

		// Instantiierung von Combofield Variablen
		// ArrayList mit DB Daten von Regisseuren füllen
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
				btnNewRegisseurActionPerformed(arg0);
			}
		});

		JLabel lblProduzent = new JLabel("Produzent");

		JButton btnNewProducent = new JButton("+");
		btnNewProducent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewProducentActionPerformed(e);
			}

		});

		// Instantiierung von Combofield Variablen
		// ArrayList mit DB Daten von Regisseuren füllen
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
		// SpinnerNumberModel smDuration setzen
		smDuration = new SpinnerNumberModel(1, 1, 10000, 1);

		spCountDVD = new JSpinner();
		spCountDVD.setPreferredSize(new Dimension(500, 400));
		// SpinnerNumberModel smDuration setzen
		smDuration = new SpinnerNumberModel(1, 1, 1000, 1);
		spCountDVD.setModel(smDuration);
		spCountDVD.setEditor(new JSpinner.NumberEditor(spCountDVD, "0"));
		
		JLabel lblCamera = new JLabel("Kamera");
		
		JComboBox cbCamera = new JComboBox();
		
		JButton btnNewCamera = new JButton("+");
		
		JLabel lblAuthor = new JLabel("Autor");
		
		JComboBox cbAuthor = new JComboBox();
		
		JButton btnNewAuthor = new JButton("+");
		
		JSpinner spDuration = new JSpinner();
		
		JLabel label = new JLabel("Dauer in Min.");
		
		JLabel lblAnzahlDvds = new JLabel("Anzahl der DVDs");

		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(txtOriginaltitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblOriginaltitel)
								.addComponent(txtTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTitel)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(lblAltersbeschrnkung)
									.addGap(9)
									.addComponent(lblHerstellungsland))
								.addComponent(lblRegisseur)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
									.addGroup(gl_contentPanel.createSequentialGroup()
										.addComponent(cbRegisseur, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(btnNewRegisseur, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_contentPanel.createSequentialGroup()
										.addComponent(cbFSK, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(cbProdCountry, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)))
								.addComponent(lblCamera)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(cbCamera, GroupLayout.PREFERRED_SIZE, 227, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnNewCamera, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)))
							.addGap(34)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
									.addComponent(cbAuthor, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnNewAuthor, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
								.addComponent(txtEancode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblProduktionsjahr, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
										.addComponent(spProductionYear, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblProduzent))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPanel.createSequentialGroup()
											.addComponent(lblVerffentlichungsdatum)
											.addPreferredGap(ComponentPlacement.RELATED, 6, Short.MAX_VALUE))
										.addComponent(dpRelease, GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)))
								.addComponent(txtGenre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(cbProducent, 0, 231, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnNewProducent, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblEanCode, Alignment.LEADING)
								.addComponent(lblGenre, Alignment.LEADING)
								.addComponent(lblAuthor, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(label, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
								.addComponent(spDuration, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(spCountDVD, 0, 0, Short.MAX_VALUE)
								.addComponent(lblAnzahlDvds, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE))
							.addGap(388)))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTitel)
						.addComponent(lblEanCode))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtEancode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblOriginaltitel)
						.addComponent(lblGenre))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtOriginaltitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtGenre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblHerstellungsland)
						.addComponent(lblAltersbeschrnkung)
						.addComponent(lblProduktionsjahr)
						.addComponent(lblVerffentlichungsdatum))
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(12)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(dpRelease, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(spProductionYear, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(cbFSK, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(cbProdCountry, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblProduzent)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(cbProducent, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNewProducent, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblRegisseur)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(cbRegisseur, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNewRegisseur, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
							.addComponent(lblCamera)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(cbCamera, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNewCamera, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)))
						.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
							.addComponent(lblAuthor)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnNewAuthor, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
								.addComponent(cbAuthor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(label)
						.addComponent(lblAnzahlDvds))
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(3)
							.addComponent(spDuration, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(spCountDVD, 0, 0, Short.MAX_VALUE)))
					.addContainerGap())
		);
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

						// Zuteilung der Filmlänge und Menge auf die
						// Integervaribalen
						try {
							duration = (Integer) spDuration.getValue();
							quantity = (Integer) spCountDVD.getValue();
						} catch (NumberFormatException e) {
							// Auto-generated catch block
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

	private void btnNewRegisseurActionPerformed(ActionEvent arg0) {
		// Regisseur Hinzufügen Button gedrückt
		NewRegisseur nr = new NewRegisseur(this);
		nr.setVisible(true);
	}

	private void btnNewProducentActionPerformed(ActionEvent e) {
		// Produzent Hinzufügen Button gedrückt
		NewProducer np = new NewProducer(this);
		np.setVisible(true);

	}

	/**
	 * Button "DVD Hinzufügen" wurde gedrückt
	 * @param arg0 = Aktionevent
	 * @throws Exception = Exceptionhandling
	 */
	private void addActionPerformed(ActionEvent arg0) throws Exception {
		// DVD Hinzufügen Button gedrückt
		String title, originalTitle, eanCode, genre;
		boolean go = true;

		// Übergabe der Inputs aus Textfeldern
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

		// Überfürung des JXDates in die Datevariable
		try {
			release = (Date.valueOf(sdf.format(dpRelease.getDate())));
		} catch (IllegalArgumentException i) {
			i.printStackTrace();
		}

		// JSpinner auslesen spDuration, spCountDVD, spProductionYear
		duration = smDuration.getNumber().intValue();
		quantity = smCountDVD.getNumber().intValue();
		prod_year = smProductionYear.getNumber().intValue();

		// Auf leere Pflichtfelder überprüfen
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

	/**
	 * Methode zum Updaten der Combobox Regisseur nach Neueingabe
	 * 
	 * @param regisseur
	 *            Konkadinierter Rückgabewerte aus der Insertmethode
	 */
	public void updateComboboxRegisseur(String regisseur) {
		dbRegisseur.add(regisseur);
		cAuswahlRegisseur = new DefaultComboBoxModel<String>(dbRegisseur);
		cbRegisseur.setModel(cAuswahlRegisseur);

	}

	public void fillRegisseur() {

	}

	/**
	 * Wenn DVD erfolgreich hinzu gefügt wurde, dies mitteilen und neues, leeres
	 * Eingabefenster öffnen.
	 * 
	 * @param title
	 *            = Hinzugefügter DVD Titel
	 * @throws Exception
	 *             = Exceptionhandling
	 */
	public void dvdAdded(String title) throws Exception {
		JOptionPane.showMessageDialog(null,
				("Die DVD" + title + " wurde erfolgreich angelegt!"),
				"Vorgang erfolgreich", JOptionPane.INFORMATION_MESSAGE);
		this.setVisible(false);
		this.dispose();

		// Neues, leeres Erstellungsfenster instantiieren
		NewDVD dialog = new NewDVD();
		dialog.setVisible(true);

	}

	/**
	 * Methode zum Updaten der Combobox Produzent nach Neueingabe
	 * 
	 * @param production
	 *            = Konkadinierter Rückgabewerte aus der Insertmethode
	 */
	public void updateComboboxProduction(String production) {
		dbProduction.add(production);
		cAuswahlProduction = new DefaultComboBoxModel<String>(dbProduction);
		cbProducent.setModel(cAuswahlProduction);

	}
}
