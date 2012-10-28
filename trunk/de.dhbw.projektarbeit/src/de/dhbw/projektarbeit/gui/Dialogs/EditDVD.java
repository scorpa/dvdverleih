package de.dhbw.projektarbeit.gui.Dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.CardLayout;
import javax.swing.JSplitPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;

import java.awt.Dimension;
import org.jdesktop.swingx.JXDatePicker;
import javax.swing.LayoutStyle.ComponentPlacement;
import org.jdesktop.swingx.JXTable;

import de.dhbw.projektarbeit.db.request.Filling;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;

/**
 * @author Juli
 *
 */
/**
 * @author Juli
 *
 */
/**
 * @author Juli
 *
 */
/**
 * @author Juli
 *
 */
/**
 * @author Juli
 *
 */
public class EditDVD extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtTitle;
	private JTextField txtOriginalTitle;
	private JTextField txtEANCode;
	private JTextField txtGenre;
	private JSpinner spCountDVD, spDuration;
	private JComboBox cbProduction, cbFSK, cbRegisseur;
	private JXDatePicker dpReleaseDate;
	private JButton okButton;
	private JButton cancelButton;
	private JTable tbDVD;
	private Object[][] dvdData;
	private DefaultTableModel model;
	private String[] columnNames = { "Menge", "Titel", "Originaltitel",
			"Genre", "Produktionsland", "Produktionsjahr", "Erscheinungsdatum",
			"Länge", "Altersfreigabe", "Regie", "Autor", "Produkiton",
			"Kamera", "EAN Code" };
	private static Integer selectedID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EditDVD dialog = new EditDVD();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Standardkonstruktor
	 */
	public EditDVD() {
		loadTable();
		setWindow();

	}

	/**
	 * Methode zur Dialogerstellung
	 */
	private void setWindow() {
		setResizable(false);
		setModal(true);
		setTitle("DVDs bearbeiten");
		setBounds(100, 100, 610, 723);
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
				JPanel panel = new JPanel();
				panel.setBorder(new EmptyBorder(5, 5, 5, 5));
				splitPane.setRightComponent(panel);
				txtTitle = new JTextField();
				txtTitle.setText("Title");
				txtTitle.setColumns(22);
				txtOriginalTitle = new JTextField();
				txtOriginalTitle.setText("OriginalTitle");
				txtOriginalTitle.setColumns(22);
				JLabel label = new JLabel("Titel");
				JLabel label_1 = new JLabel("Originaltitel");
				cbRegisseur = new JComboBox();
				JButton btnNewRegisseur = new JButton("+");
				cbFSK = new JComboBox();
				JLabel label_2 = new JLabel("Altersbeschr\u00E4nkung");
				JLabel label_3 = new JLabel("Herstellungsland");
				JComboBox cbProdCountry = new JComboBox();
				cbProdCountry.setSelectedIndex(11);
				JLabel lblRegisseur = new JLabel("Regisseur");
				JLabel label_5 = new JLabel("Produzent");
				JLabel label_6 = new JLabel("Genre");
				JLabel label_7 = new JLabel("EAN Code");
				txtEANCode = new JTextField();
				txtEANCode.setText("EANCode");
				txtEANCode.setColumns(22);
				JLabel label_8 = new JLabel("Produktionsjahr");
				JSpinner spProductionYear = new JSpinner();
				spProductionYear.setPreferredSize(new Dimension(40, 28));
				spProductionYear.setModel(new SpinnerNumberModel(1920, 1920,
						2020, 1));
				spProductionYear.setEditor(new JSpinner.NumberEditor(
						spProductionYear, "0"));
				dpReleaseDate = new JXDatePicker();
				dpReleaseDate.setFormats(new String[] { "MM.DD.YYYY" });
				JLabel label_9 = new JLabel("Ver\u00F6ffentlichungsdatum");
				txtGenre = new JTextField();
				txtGenre.setText("Genre");
				txtGenre.setColumns(22);
				cbProduction = new JComboBox();
				JButton btnNewProducer = new JButton("+");
				JLabel lblDauerInMin = new JLabel("Dauer in Min.");
				spDuration = new JSpinner();

				JLabel lblAnzahl = new JLabel("Anzahl von DVDs");

				spCountDVD = new JSpinner();
				spCountDVD.setPreferredSize(new Dimension(40, 28));
				spCountDVD.setModel(new javax.swing.SpinnerNumberModel(1, 1,
						1000, 1));
				spCountDVD
						.setEditor(new JSpinner.NumberEditor(spCountDVD, "0"));
				
				JLabel lblCamera = new JLabel("Kamera");
				
				JComboBox cbCamera = new JComboBox();
				
				JButton button = new JButton("+");
				
				JLabel lblAuthor = new JLabel("Autor");
				
				JComboBox cbAuthor = new JComboBox();
				
				JButton btnNewAuthor = new JButton("+");
				GroupLayout gl_panel = new GroupLayout(panel);
				gl_panel.setHorizontalGroup(
					gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(cbCamera, GroupLayout.PREFERRED_SIZE, 232, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(button, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(cbAuthor, GroupLayout.PREFERRED_SIZE, 224, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnNewAuthor, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(txtTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtOriginalTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(label)
										.addComponent(label_1)
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(cbRegisseur, GroupLayout.PREFERRED_SIZE, 232, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(btnNewRegisseur, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panel.createSequentialGroup()
											.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
												.addComponent(cbFSK, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
												.addComponent(label_2))
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
												.addComponent(label_3)
												.addComponent(cbProdCountry, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)))
										.addComponent(lblRegisseur)
										.addComponent(lblCamera))
									.addGap(18)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblAuthor)
										.addComponent(txtGenre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
											.addComponent(label_5)
											.addComponent(label_6)
											.addComponent(label_7)
											.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
												.addGroup(gl_panel.createSequentialGroup()
													.addComponent(cbProduction, 0, 224, Short.MAX_VALUE)
													.addPreferredGap(ComponentPlacement.UNRELATED)
													.addComponent(btnNewProducer, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
													.addGap(8))
												.addGroup(gl_panel.createSequentialGroup()
													.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
														.addComponent(spProductionYear, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
														.addComponent(label_8, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE))
													.addPreferredGap(ComponentPlacement.RELATED)
													.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
														.addGroup(gl_panel.createSequentialGroup()
															.addComponent(dpReleaseDate, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
															.addContainerGap())
														.addComponent(label_9)))))
										.addComponent(txtEANCode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblDauerInMin)
										.addComponent(spDuration, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE))
									.addGap(18)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(spCountDVD, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblAnzahl)))))
				);
				gl_panel.setVerticalGroup(
					gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(label)
								.addComponent(label_7))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtEANCode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_1)
								.addComponent(label_6))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtOriginalTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtGenre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_3)
								.addComponent(label_2)
								.addComponent(label_9)
								.addComponent(label_8))
							.addGap(12)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
									.addComponent(cbFSK, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(cbProdCountry, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(dpReleaseDate, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(spProductionYear, 0, 0, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblRegisseur)
								.addComponent(label_5))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(cbRegisseur, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNewRegisseur, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
								.addComponent(cbProduction, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNewProducer, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCamera)
								.addComponent(lblAuthor))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(cbCamera, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(button, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
								.addComponent(cbAuthor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNewAuthor, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblDauerInMin)
								.addComponent(lblAnzahl))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(spCountDVD, 0, 0, Short.MAX_VALUE)
								.addComponent(spDuration, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addContainerGap(9, Short.MAX_VALUE))
				);
				panel.setLayout(gl_panel);
			}

			model = new DefaultTableModel(dvdData, columnNames);
			tbDVD = new JTableNotEditable(model, columnNames);
			tbDVD.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					tbDVDMouseClicked(e);
				}
			});
			tbDVD.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tbDVD.setFocusable(false);
			tbDVD.setModel(model);

			JScrollPane scrollPane = new JScrollPane(tbDVD);
			splitPane.setLeftComponent(scrollPane);
			scrollPane.setViewportView(tbDVD);
			splitPane.setDividerLocation(260);
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
			}

			JButton button = new JButton("L\u00F6schen");

			JButton button_1 = new JButton("Speichern");
			button_1.setEnabled(false);
			GroupLayout gl_buttonPane = new GroupLayout(buttonPane);
			gl_buttonPane.setHorizontalGroup(gl_buttonPane.createParallelGroup(
					Alignment.TRAILING).addGroup(
					gl_buttonPane
							.createSequentialGroup()
							.addContainerGap()
							.addComponent(button, GroupLayout.PREFERRED_SIZE,
									96, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(button_1, GroupLayout.PREFERRED_SIZE,
									103, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 261,
									Short.MAX_VALUE).addComponent(okButton)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cancelButton).addContainerGap()));
			gl_buttonPane
					.setVerticalGroup(gl_buttonPane
							.createParallelGroup(Alignment.LEADING)
							.addGroup(
									gl_buttonPane
											.createSequentialGroup()
											.addGap(5)
											.addGroup(
													gl_buttonPane
															.createParallelGroup(
																	Alignment.LEADING)
															.addComponent(
																	button)
															.addComponent(
																	button_1)
															.addGroup(
																	gl_buttonPane
																			.createParallelGroup(
																					Alignment.BASELINE)
																			.addComponent(
																					cancelButton)
																			.addComponent(
																					okButton)))
											.addContainerGap(
													GroupLayout.DEFAULT_SIZE,
													Short.MAX_VALUE)));
			buttonPane.setLayout(gl_buttonPane);
		}
	}

	/**
	 * Aufruf der FillMethode zur Befüllung der DVD JTable
	 */
	private void loadTable() {
		// Spaltenüberschriften

		Filling fill = new Filling();

		dvdData = null;
		try {
			dvdData = fill.getTable("dvd");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}
	
	/**
	 * Tabellenzeile wurde ausgewählt, Felder im Splitmenü werden aus Tabelle geladen
	 * @param e --> Eventhandling
	 */
	private void tbDVDMouseClicked(MouseEvent e) {
		try {
			spCountDVD.setValue(tbDVD.getValueAt(tbDVD.getSelectedRow(), 0));
			txtTitle.setText((String) tbDVD.getValueAt(tbDVD.getSelectedRow(), 1));
			txtOriginalTitle.setText((String) tbDVD.getValueAt(tbDVD.getSelectedRow(), 2));
			txtGenre.setText((String) tbDVD.getValueAt(tbDVD.getSelectedRow(), 3));
			cbProduction.setSelectedItem(tbDVD.getValueAt(tbDVD.getSelectedRow(), 4));
			dpReleaseDate.setDate((Date) tbDVD.getValueAt(tbDVD.getSelectedRow(), 5));
			spDuration.setValue(tbDVD.getValueAt(tbDVD.getSelectedRow(), 6));
			cbFSK.setSelectedItem(tbDVD.getValueAt(tbDVD.getSelectedRow(), 7));
			cbRegisseur.setSelectedItem(tbDVD.getValueAt(tbDVD.getSelectedRow(), 8));
			dpBirthdate.setDate((Date) tbDVD.getValueAt(tbDVD.getSelectedRow(), 9));
			selectedID = (Integer) tbDVD.getValueAt(tbDVD.getSelectedRow(), 0);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
