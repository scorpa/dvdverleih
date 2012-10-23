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
import de.dhbw.projektarbeit.gui.CustomerCard;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.CardLayout;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import de.dhbw.projektarbeit.gui.DVDCard;

public class RentDVD extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtFilter;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RentDVD dialog = new RentDVD();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RentDVD() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new CardLayout(0, 0));
		
		JPanel fistPanel = new JPanel();
		fistPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Bitte waehlen Sie einen Kunden aus", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPanel.add(fistPanel, "name_2173742051695");
		
		JLabel lblFilter = new JLabel("Filter");
		
		txtFilter = new JTextField();
		txtFilter.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		GroupLayout gl_fistPanel = new GroupLayout(fistPanel);
		gl_fistPanel.setHorizontalGroup(
			gl_fistPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_fistPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblFilter)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtFilter, GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE))
				.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
		);
		gl_fistPanel.setVerticalGroup(
			gl_fistPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_fistPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_fistPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFilter)
						.addComponent(txtFilter, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE))
		);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		CustomerCard CustomerCard = new CustomerCard();
		GridBagConstraints gbc_CustomerCard = new GridBagConstraints();
		gbc_CustomerCard.fill = GridBagConstraints.BOTH;
		gbc_CustomerCard.gridx = 0;
		gbc_CustomerCard.gridy = 0;
		panel_1.add(CustomerCard, gbc_CustomerCard);
		fistPanel.setLayout(gl_fistPanel);
		
		JPanel secondPanel = new JPanel();
		secondPanel.setBorder(new TitledBorder(null, "Bitte waehlen Sie eine DVD aus", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPanel.add(secondPanel, "name_2777963286704");
		
		JLabel label = new JLabel("Filter");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JPanel panel = new JPanel();
		GroupLayout gl_secondPanel = new GroupLayout(secondPanel);
		gl_secondPanel.setHorizontalGroup(
			gl_secondPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_secondPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(label)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField, GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
					.addContainerGap())
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
		);
		gl_secondPanel.setVerticalGroup(
			gl_secondPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_secondPanel.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_secondPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
					.addContainerGap())
		);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0};
		gbl_panel.rowHeights = new int[]{0, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		DVDCard card = new DVDCard();
		GridBagConstraints gbc_card = new GridBagConstraints();
		gbc_card.fill = GridBagConstraints.BOTH;
		gbc_card.gridx = 0;
		gbc_card.gridy = 0;
		panel.add(card, gbc_card);
		secondPanel.setLayout(gl_secondPanel);
		
		JPanel thirdPanel = new JPanel();
		thirdPanel.setBorder(new TitledBorder(null, "Zusammenfassung", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPanel.add(thirdPanel, "name_1350994462186887000");
		
		JLabel lblCustomer = new JLabel("Customer");
		
		JLabel lblDvd = new JLabel("DVD");
		
		JLabel lblDate = new JLabel("Date");
		GroupLayout gl_thirdPanel = new GroupLayout(thirdPanel);
		gl_thirdPanel.setHorizontalGroup(
			gl_thirdPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_thirdPanel.createSequentialGroup()
					.addGroup(gl_thirdPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblCustomer)
						.addComponent(lblDvd)
						.addComponent(lblDate))
					.addContainerGap(367, Short.MAX_VALUE))
		);
		gl_thirdPanel.setVerticalGroup(
			gl_thirdPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_thirdPanel.createSequentialGroup()
					.addComponent(lblCustomer)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblDvd)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblDate)
					.addContainerGap(129, Short.MAX_VALUE))
		);
		thirdPanel.setLayout(gl_thirdPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			JButton btnBack = new JButton("Zur\u00FCck");
			btnBack.setVisible(false);
			buttonPane.add(btnBack);
			{
				JButton btnNext = new JButton("Weiter");
				btnNext.setActionCommand("OK");
				buttonPane.add(btnNext);
				getRootPane().setDefaultButton(btnNext);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
