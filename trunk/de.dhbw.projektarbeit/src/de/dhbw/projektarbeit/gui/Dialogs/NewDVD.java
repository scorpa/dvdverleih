package de.dhbw.projektarbeit.gui.Dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewDVD extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtTitle;
	private JTextField txtOriginaltitle;
	private JTextField txtEancode;
	private JTextField txtGenre;
	private JButton addButton;
	private JButton cancelButton;

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
	 */
	public NewDVD() {
		setResizable(false);
		setModal(true);
		setTitle("Neue DVD anlegen");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 610, 343);
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
		
		JComboBox cbFSK = new JComboBox();
		cbFSK.setModel(new DefaultComboBoxModel(new String[] {"ab 0 Jahre", "ab 6 Jahre", "ab 12 Jahre", "ab 16 Jahre", "ab 18 Jahre", "indiziert"}));
		
		JComboBox cbProdCountry = new JComboBox();
		cbProdCountry.setModel(new DefaultComboBoxModel(new String[] {"Australien", "Deutschland", "Frankreich", "Gro\u00DFbritannien", "Hong Kong", "Indien", "Italien", "Kanada", "Niederlande", "Russland", "Spanien", "USA"}));
		cbProdCountry.setSelectedIndex(11);
		
		JLabel lblHerstellungsland = new JLabel("Herstellungsland");
		
		JSpinner spProductionYear = new JSpinner();
		spProductionYear.setPreferredSize(new Dimension(40, 28));
		spProductionYear.setModel(new javax.swing.SpinnerNumberModel(2012, 1920, 2020, 1));
		spProductionYear.setEditor(new JSpinner.NumberEditor(spProductionYear, "0"));
		
		JLabel lblProduktionsjahr = new JLabel("Produktionsjahr");
		
		JXDatePicker dpRelease = new JXDatePicker();
		dpRelease.setFormats(new String[] {"DD.MM.YYYY"});
		
		JLabel lblVerffentlichungsdatum = new JLabel("Ver\u00F6ffentlichungsdatum");
		
		JLabel lblRegisseur = new JLabel("Regisseur");
		
		JComboBox cbRegisseur = new JComboBox();
		
		JButton btnNewRegisseur = new JButton("+");
		
		JLabel lblProduzent = new JLabel("Produzent");
		
		JButton btnNewProducent = new JButton("+");
		
		JComboBox cbProducent = new JComboBox();
		
		JLabel lblDauer = new JLabel("Dauer in Min.");
		
		JSpinner spDuration = new JSpinner();
		spDuration.setModel(new javax.swing.SpinnerNumberModel(1, 1, 10000, 1));
		
		
		JSpinner spCountDVD = new JSpinner();
		spCountDVD.setPreferredSize(new Dimension(40, 28));
		spCountDVD.setModel(new javax.swing.SpinnerNumberModel(1, 1, 1000, 1));
		spCountDVD.setEditor(new JSpinner.NumberEditor(spCountDVD, "0"));
		
		JLabel lblAnzahl = new JLabel("Anzahl der DVDs");
		
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(txtOriginaltitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblOriginaltitel)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(cbRegisseur, GroupLayout.PREFERRED_SIZE, 232, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewRegisseur, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(cbFSK, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblAltersbeschrnkung))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblHerstellungsland)
								.addComponent(cbProdCountry, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)))
						.addComponent(lblRegisseur)
						.addComponent(txtTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTitel)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDauer)
								.addComponent(spDuration, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(spCountDVD, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblAnzahl))))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(txtEancode, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGroup(gl_contentPanel.createSequentialGroup()
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
									.addComponent(lblProduktionsjahr, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblProduzent)
									.addComponent(spProductionYear, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
									.addComponent(dpRelease, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblVerffentlichungsdatum, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
							.addComponent(txtGenre, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblEanCode, Alignment.LEADING)
							.addComponent(lblGenre, Alignment.LEADING))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(cbProducent, 0, 235, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewProducent, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
						.addComponent(lblVerffentlichungsdatum)
						.addComponent(lblProduktionsjahr))
					.addGap(12)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
							.addComponent(cbFSK, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(cbProdCountry, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(dpRelease, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(spProductionYear, 0, 0, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRegisseur)
						.addComponent(lblProduzent))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(cbRegisseur, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewRegisseur, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(cbProducent, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewProducent, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDauer)
						.addComponent(lblAnzahl))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(spCountDVD, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
						.addComponent(spDuration, Alignment.LEADING))
					.addContainerGap(43, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				addButton = new JButton("Hinzuf\u00FCgen");
				addButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						addActionPerformed(arg0);
					}


				});
				addButton.setActionCommand("add");
				getRootPane().setDefaultButton(addButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
			}
			GroupLayout gl_buttonPane = new GroupLayout(buttonPane);
			gl_buttonPane.setHorizontalGroup(
				gl_buttonPane.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_buttonPane.createSequentialGroup()
						.addContainerGap(446, Short.MAX_VALUE)
						.addComponent(addButton)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(cancelButton))
			);
			gl_buttonPane.setVerticalGroup(
				gl_buttonPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_buttonPane.createSequentialGroup()
						.addGap(5)
						.addGroup(gl_buttonPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(cancelButton)
							.addComponent(addButton))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
			);
			buttonPane.setLayout(gl_buttonPane);
		}
	}
	
	private void addActionPerformed(ActionEvent arg0) {
		// Hinzufügen Button gedrückt
		String title, originalTitle, eanCode, genre, fsk, prodCountry, prodYear, release, regie, production;
		int duration, quanitity;
		
		// Übergabe der Inputs
		
		title = txtTitle.getText();
		originalTitle = txtOriginaltitle.getText();
		eanCode = txtEancode.getText();
		genre = txtGenre.getText();
		fsk = (String) cbFSK.getSelectedItem();
	}
}
