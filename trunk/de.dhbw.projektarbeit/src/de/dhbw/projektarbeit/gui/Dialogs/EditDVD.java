package de.dhbw.projektarbeit.gui.Dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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
import javax.swing.SpinnerNumberModel;

import java.awt.Dimension;
import org.jdesktop.swingx.JXDatePicker;
import javax.swing.LayoutStyle.ComponentPlacement;
import org.jdesktop.swingx.JXTable;

public class EditDVD extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtTitle;
	private JTextField txtOriginalTitle;
	private JTextField txtEANCode;
	private JTextField txtGenre;
	private JButton okButton;
	private JButton cancelButton;

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
	 * Create the dialog.
	 */
	public EditDVD() {
		setResizable(false);
		setModal(true);
		setTitle("DVDs bearbeiten");
		setBounds(100, 100, 610, 660);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0};
		gbl_contentPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
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
				JComboBox cbRegisseur = new JComboBox();
				JButton btnNewRegisseur = new JButton("+");
				JComboBox cbFSK = new JComboBox();
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
				spProductionYear.setModel(new SpinnerNumberModel(1920, 1920, 2020, 1));
				spProductionYear.setEditor(new JSpinner.NumberEditor(spProductionYear, "0"));
				JXDatePicker dpReleaseDate = new JXDatePicker();
				dpReleaseDate.setFormats(new String[] {"MM.DD.YYYY"});
				JLabel label_9 = new JLabel("Ver\u00F6ffentlichungsdatum");
				txtGenre = new JTextField();
				txtGenre.setText("Genre");
				txtGenre.setColumns(22);
				JComboBox cbProduction = new JComboBox();
				JButton btnNewProducer = new JButton("+");
				JLabel lblDauerInMin = new JLabel("Dauer in Min.");
				JSpinner spDuration = new JSpinner();
				
				JLabel lblAnzahl = new JLabel("Anzahl von DVDs");
				
				JSpinner spCountDVD = new JSpinner();
				spCountDVD.setPreferredSize(new Dimension(40, 28));
				spCountDVD.setModel(new javax.swing.SpinnerNumberModel(1, 1, 1000, 1));
				spCountDVD.setEditor(new JSpinner.NumberEditor(spCountDVD, "0"));
				
				JLabel lblLagerort = new JLabel("Lagerort");
				
				JComboBox comboBox = new JComboBox();
				
				JButton button = new JButton("+");
				GroupLayout gl_panel = new GroupLayout(panel);
				gl_panel.setHorizontalGroup(
					gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
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
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblDauerInMin)
										.addComponent(spDuration, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE))
									.addGap(18)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(spCountDVD, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblAnzahl))))
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(txtGenre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addContainerGap())
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_panel.createSequentialGroup()
										.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
											.addComponent(lblLagerort)
											.addGroup(Alignment.TRAILING, gl_panel.createParallelGroup(Alignment.LEADING, false)
												.addComponent(label_5)
												.addComponent(label_6)
												.addComponent(label_7)
												.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
													.addGroup(gl_panel.createSequentialGroup()
														.addComponent(cbProduction, 0, 236, Short.MAX_VALUE)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(btnNewProducer, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED))
													.addGroup(gl_panel.createSequentialGroup()
														.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
															.addComponent(spProductionYear, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
															.addComponent(label_8, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE))
														.addPreferredGap(ComponentPlacement.RELATED)
														.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
															.addGroup(gl_panel.createSequentialGroup()
																.addComponent(dpReleaseDate, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
																.addPreferredGap(ComponentPlacement.RELATED))
															.addComponent(label_9)))))
											.addGroup(gl_panel.createSequentialGroup()
												.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(button, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)))
										.addGap(2))
									.addGroup(gl_panel.createSequentialGroup()
										.addComponent(txtEANCode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addContainerGap()))))
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
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
									.addComponent(cbFSK, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(cbProdCountry, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(dpReleaseDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(spProductionYear, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
								.addComponent(lblDauerInMin)
								.addComponent(lblAnzahl)
								.addComponent(lblLagerort))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
									.addComponent(spDuration, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(spCountDVD, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
									.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(button, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)))
							.addContainerGap(14, Short.MAX_VALUE))
				);
				panel.setLayout(gl_panel);
			}
			
			JXTable tbDVD = new JXTable();
			splitPane.setLeftComponent(tbDVD);
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
			
			JButton btnAdddvd = new JButton("+");
			
			JButton btnDeleteDVD = new JButton("-");
			GroupLayout gl_buttonPane = new GroupLayout(buttonPane);
			gl_buttonPane.setHorizontalGroup(
				gl_buttonPane.createParallelGroup(Alignment.LEADING)
					.addGroup(Alignment.TRAILING, gl_buttonPane.createSequentialGroup()
						.addComponent(btnAdddvd, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnDeleteDVD, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 349, Short.MAX_VALUE)
						.addComponent(okButton)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(cancelButton))
			);
			gl_buttonPane.setVerticalGroup(
				gl_buttonPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_buttonPane.createSequentialGroup()
						.addGap(5)
						.addGroup(gl_buttonPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(cancelButton)
							.addComponent(okButton)
							.addComponent(btnAdddvd)
							.addComponent(btnDeleteDVD))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
			);
			buttonPane.setLayout(gl_buttonPane);
		}
	}
}
