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

public class RentDVD extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtFilter;

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
		contentPanel.add(secondPanel, "name_2777963286704");
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
