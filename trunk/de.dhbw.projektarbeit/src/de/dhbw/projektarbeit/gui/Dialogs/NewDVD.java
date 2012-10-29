package de.dhbw.projektarbeit.gui.Dialogs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.InvalidParameterException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;

import org.jdesktop.swingx.JXDatePicker;

import de.dhbw.projektarbeit.db.request.Filling;
import de.dhbw.projektarbeit.db.request.Insert;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * JDialog zum Einpflegen neuer DVDs
 * 
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
	private DefaultComboBoxModel cAuswahlRegisseur, cAuswahlProduction,
			cAuswahlCamera, cAuswahlAuthor;
	private SimpleDateFormat sdf;
	private Date release;
	private int prod_year, duration, quantity;
	private JComboBox cbRegisseur, cbFSK, cbProdCountry, cbProducent, cbCamera,
			cbAuthor;
	private Vector<String> dbRegisseur = new Vector();
	private Vector<String> dbProduction = new Vector();
	private Vector<String> dbCamera = new Vector();
	private Vector<String> dbAuthor = new Vector();
	private JXDatePicker dpRelease;
	private JSpinner spCountDVD, spProductionYear, spDuration;
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
		setBounds(100, 100, 620, 450);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel lblTitel = new JLabel("Titel");
		txtTitle = new JTextField();
		txtTitle.setColumns(22);
		txtOriginaltitle = new JTextField();
		txtOriginaltitle.setColumns(22);
		JLabel lblOriginaltitel = new JLabel("Originaltitel");
		txtEancode = new JTextField();
		txtEancode.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				txtEancodeKeyReleased(e);

			}
		});

		txtEancode.setColumns(22);

		JLabel lblEanCode = new JLabel("EAN Code");

		txtGenre = new JTextField();
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
		cbFSK.setModel(new DefaultComboBoxModel(new String[] {"ab 0 Jahre", "ab 6 Jahre", "ab 12 Jahre", "ab 16 Jahre", "ab 18 Jahre", "indiziert"}));

		// Auslesen des Combofields
		cbProdCountry = new JComboBox();
		cbProdCountry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		cbProdCountry.setModel(new DefaultComboBoxModel(new String[] {"Australien", "Deutschland", "Frankreich", "Gro\u00DFbritannien", "Hong Kong", "Indien", "Italien", "Kanada", "Niederlande", "Russland", "Spanien", "USA"}));
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

		JLabel lblVerffentlichungsdatum = new JLabel(
				"Ver\u00F6ffentlichungsdatum");

		JLabel lblRegisseur = new JLabel("Regisseur");

		// Instantiierung von der Fillmethode für das Combofield
		Filling fill = new Filling();

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
		cbRegisseur.setModel(cAuswahlRegisseur);
		
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
			dbProduction = fill.fillCbs("production");
		} catch (IllegalArgumentException e1) {
			e1.printStackTrace();
		}
		cAuswahlProduction = new DefaultComboBoxModel(dbProduction);
		cbProducent = new JComboBox();
		cbProducent.setModel(cAuswahlProduction);
		// SpinnerNumberModel smCount setzen
		smCountDVD = new SpinnerNumberModel(1, 1, 10000, 1);
		spCountDVD = new JSpinner();
		spCountDVD.setPreferredSize(new Dimension(500, 400));
		spCountDVD.setModel(smCountDVD);
		spCountDVD.setEditor(new JSpinner.NumberEditor(spCountDVD, "0"));

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
		cbCamera.setModel(cAuswahlCamera);
		cbCamera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Auslesen des Combofields
				camera = (String) cbCamera.getSelectedItem();
			}
		});

		JButton btnNewCamera = new JButton("+");
		btnNewCamera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnNewCameraActionPerformes(arg0);
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
		cbAuthor.setModel(cAuswahlAuthor);
		cbAuthor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Auslesen des Combofields
				author = (String) cbAuthor.getSelectedItem();
			}
		});

		JButton btnNewAuthor = new JButton("+");
		btnNewAuthor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnNewAuthorActionPerformed(arg0);
			}
		});

		spDuration = new JSpinner();
		// SpinnerNumberModel smDuration setzen
		smDuration = new SpinnerNumberModel(1, 1, 1000, 1);
		spDuration.setModel(smDuration);
		JLabel label = new JLabel("Dauer in Min.");
		JLabel lblAnzahlDvds = new JLabel("Anzahl der DVDs");

		NumberFormat format = NumberFormat.getInstance();

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
														.addGroup(
																gl_contentPanel
																		.createParallelGroup(
																				Alignment.LEADING,
																				false)
																		.addComponent(
																				txtOriginaltitle,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)
																		.addComponent(
																				lblOriginaltitel)
																		.addComponent(
																				txtTitle,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)
																		.addComponent(
																				lblTitel)
																		.addComponent(
																				lblRegisseur)
																		.addComponent(
																				lblCamera)
																		.addGroup(
																				gl_contentPanel
																						.createSequentialGroup()
																						.addComponent(
																								lblAltersbeschrnkung)
																						.addPreferredGap(
																								ComponentPlacement.UNRELATED)
																						.addComponent(
																								lblHerstellungsland))
																		.addGroup(
																				gl_contentPanel
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addGroup(
																								gl_contentPanel
																										.createSequentialGroup()
																										.addComponent(
																												cbCamera,
																												0,
																												227,
																												Short.MAX_VALUE)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												btnNewCamera,
																												GroupLayout.PREFERRED_SIZE,
																												42,
																												GroupLayout.PREFERRED_SIZE))
																						.addGroup(
																								gl_contentPanel
																										.createSequentialGroup()
																										.addComponent(
																												cbRegisseur,
																												0,
																												230,
																												Short.MAX_VALUE)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												btnNewRegisseur,
																												GroupLayout.PREFERRED_SIZE,
																												42,
																												GroupLayout.PREFERRED_SIZE))
																						.addGroup(
																								gl_contentPanel
																										.createSequentialGroup()
																										.addComponent(
																												cbFSK,
																												GroupLayout.PREFERRED_SIZE,
																												129,
																												GroupLayout.PREFERRED_SIZE)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												cbProdCountry,
																												GroupLayout.PREFERRED_SIZE,
																												140,
																												GroupLayout.PREFERRED_SIZE))))
														.addGroup(
																gl_contentPanel
																		.createSequentialGroup()
																		.addGroup(
																				gl_contentPanel
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								label,
																								GroupLayout.PREFERRED_SIZE,
																								84,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								spDuration,
																								GroupLayout.PREFERRED_SIZE,
																								73,
																								GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				ComponentPlacement.UNRELATED)
																		.addGroup(
																				gl_contentPanel
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								spCountDVD,
																								GroupLayout.PREFERRED_SIZE,
																								112,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								lblAnzahlDvds,
																								GroupLayout.PREFERRED_SIZE,
																								106,
																								GroupLayout.PREFERRED_SIZE))))
										.addGap(32)
										.addGroup(
												gl_contentPanel
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_contentPanel
																		.createSequentialGroup()
																		.addComponent(
																				lblEanCode)
																		.addContainerGap())
														.addGroup(
																gl_contentPanel
																		.createSequentialGroup()
																		.addComponent(
																				lblGenre)
																		.addContainerGap())
														.addGroup(
																gl_contentPanel
																		.createParallelGroup(
																				Alignment.LEADING)
																		.addGroup(
																				gl_contentPanel
																						.createSequentialGroup()
																						.addGroup(
																								gl_contentPanel
																										.createParallelGroup(
																												Alignment.TRAILING)
																										.addGroup(
																												gl_contentPanel
																														.createSequentialGroup()
																														.addComponent(
																																cbAuthor,
																																0,
																																231,
																																Short.MAX_VALUE)
																														.addPreferredGap(
																																ComponentPlacement.RELATED)
																														.addComponent(
																																btnNewAuthor,
																																GroupLayout.PREFERRED_SIZE,
																																41,
																																GroupLayout.PREFERRED_SIZE))
																										.addComponent(
																												txtEancode)
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
																																		.addGroup(
																																				gl_contentPanel
																																						.createSequentialGroup()
																																						.addComponent(
																																								spProductionYear,
																																								GroupLayout.DEFAULT_SIZE,
																																								108,
																																								Short.MAX_VALUE)
																																						.addGap(12))
																																		.addComponent(
																																				lblProduzent))
																														.addPreferredGap(
																																ComponentPlacement.RELATED)
																														.addGroup(
																																gl_contentPanel
																																		.createParallelGroup(
																																				Alignment.LEADING)
																																		.addComponent(
																																				lblVerffentlichungsdatum)
																																		.addComponent(
																																				dpRelease,
																																				GroupLayout.PREFERRED_SIZE,
																																				158,
																																				GroupLayout.PREFERRED_SIZE)))
																										.addComponent(
																												txtGenre)
																										.addGroup(
																												gl_contentPanel
																														.createSequentialGroup()
																														.addComponent(
																																cbProducent,
																																0,
																																231,
																																Short.MAX_VALUE)
																														.addPreferredGap(
																																ComponentPlacement.RELATED)
																														.addComponent(
																																btnNewProducent,
																																GroupLayout.PREFERRED_SIZE,
																																41,
																																GroupLayout.PREFERRED_SIZE)))
																						.addGap(16))
																		.addGroup(
																				gl_contentPanel
																						.createSequentialGroup()
																						.addComponent(
																								lblAuthor,
																								GroupLayout.PREFERRED_SIZE,
																								63,
																								GroupLayout.PREFERRED_SIZE)
																						.addContainerGap())))));
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
																lblAltersbeschrnkung)
														.addComponent(
																lblProduktionsjahr)
														.addComponent(
																lblVerffentlichungsdatum)
														.addComponent(
																lblHerstellungsland))
										.addGroup(
												gl_contentPanel
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_contentPanel
																		.createSequentialGroup()
																		.addGap(12)
																		.addGroup(
																				gl_contentPanel
																						.createParallelGroup(
																								Alignment.TRAILING,
																								false)
																						.addComponent(
																								dpRelease,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.addComponent(
																								spProductionYear,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)))
														.addGroup(
																gl_contentPanel
																		.createSequentialGroup()
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
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
																								GroupLayout.PREFERRED_SIZE))))
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addGroup(
												gl_contentPanel
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_contentPanel
																		.createSequentialGroup()
																		.addComponent(
																				lblProduzent)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addGroup(
																				gl_contentPanel
																						.createParallelGroup(
																								Alignment.BASELINE)
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
																								GroupLayout.PREFERRED_SIZE)))
														.addGroup(
																gl_contentPanel
																		.createSequentialGroup()
																		.addComponent(
																				lblRegisseur)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				cbRegisseur,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)))
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addGroup(
												gl_contentPanel
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_contentPanel
																		.createSequentialGroup()
																		.addComponent(
																				lblCamera)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				cbCamera,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																gl_contentPanel
																		.createSequentialGroup()
																		.addComponent(
																				lblAuthor)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addGroup(
																				gl_contentPanel
																						.createParallelGroup(
																								Alignment.BASELINE)
																						.addComponent(
																								btnNewAuthor,
																								GroupLayout.PREFERRED_SIZE,
																								28,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								cbAuthor,
																								GroupLayout.PREFERRED_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								btnNewCamera,
																								GroupLayout.PREFERRED_SIZE,
																								28,
																								GroupLayout.PREFERRED_SIZE))))
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addGroup(
												gl_contentPanel
														.createParallelGroup(
																Alignment.TRAILING)
														.addComponent(label)
														.addComponent(
																lblAnzahlDvds))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_contentPanel
														.createParallelGroup(
																Alignment.TRAILING)
														.addComponent(
																spDuration,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																spCountDVD,
																GroupLayout.PREFERRED_SIZE,
																20,
																GroupLayout.PREFERRED_SIZE))
										.addGap(11)));
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
				cancelButton = new JButton("Abbrechen");
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

	protected void txtEancodeKeyReleased(KeyEvent e) {
		// Prüfung ob eine Zahl oder anderes Zeichen eingegeben wurde oder länger als 11 Zeichen ist
		String text = txtEancode.getText();
		if (txtEancode.getText().matches("[0-9]*")) {

		} else {
			txtEancode.setText(text.substring(0, text.length() - 1));
		}
		
		if (text.length() > 13){
			txtEancode.setText(text.substring(0, text.length() - 1));
			JOptionPane
			.showMessageDialog(
					null,
					"Der EAN Code darf maximal 13 Stellen haben!",
					"EAN Code", JOptionPane.ERROR_MESSAGE);
			
		}
	}

	protected void btnNewAuthorActionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		NewAuthor na = new NewAuthor(this);
		na.setVisible(true);
	}

	protected void btnNewCameraActionPerformes(ActionEvent arg0) {
		// Kamera hinzufuegen Button gedrŸckt
		NewCamera nc = new NewCamera(this);
		nc.setVisible(true);
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
	 * 
	 * @param arg0
	 *            = Aktionevent
	 * @throws Exception
	 *             = Exceptionhandling
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
		camera = (String) cbCamera.getSelectedItem();
		author = (String) cbAuthor.getSelectedItem();
		production = (String) cbProducent.getSelectedItem();

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
		} else if (camera.replaceAll(" ", "").equals("")) {
			go = false;
		} else if (author.replaceAll(" ", "").equals("")) {
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
				insert.insertDVD(this, quantity, title, originalTitle, genre,
						prodCountry, prod_year, release, duration, fsk, regie,
						author, production, camera, eanCode);
			} catch (InvalidParameterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			JOptionPane
					.showMessageDialog(
							null,
							"Sie haben ein Pflichtfeld nicht ausgefüllt! Bitte überprüfen Sie ihre Angaben in den Feldern",
							"Regisseurerstellung", JOptionPane.ERROR_MESSAGE);
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
		cAuswahlRegisseur = new DefaultComboBoxModel(dbRegisseur);
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
		cAuswahlProduction = new DefaultComboBoxModel(dbProduction);
		cbProducent.setModel(cAuswahlProduction);

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

	}
}
