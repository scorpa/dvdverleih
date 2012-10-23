package de.dhbw.projektarbeit.gui.Dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import javax.swing.border.TitledBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.GridBagLayout;
import de.dhbw.projektarbeit.gui.CustomerCard;
import java.awt.GridBagConstraints;
import de.dhbw.projektarbeit.gui.DVDCard;
import java.awt.Font;

public class ReturnDVD extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtFilterCustomer;
	private JTextField txtFilterDVD;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ReturnDVD dialog = new ReturnDVD();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ReturnDVD() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new CardLayout(0, 0));
		{
			JPanel firstPanel = new JPanel();
			firstPanel.setBorder(new TitledBorder(null, "Bitte waehlen Sie einen Kunden aus", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(firstPanel, "name_1350994801972738000");
			JLabel lblFilter = new JLabel("Filter");
			
			txtFilterCustomer = new JTextField();
			txtFilterCustomer.setText("Filter");
			txtFilterCustomer.setColumns(10);
			
			JPanel panel_1 = new JPanel();
			GroupLayout gl_firstPanel = new GroupLayout(firstPanel);
			gl_firstPanel.setHorizontalGroup(
				gl_firstPanel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_firstPanel.createSequentialGroup()
						.addComponent(lblFilter)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(txtFilterCustomer, GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
						.addContainerGap())
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)
			);
			gl_firstPanel.setVerticalGroup(
				gl_firstPanel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_firstPanel.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_firstPanel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblFilter)
							.addComponent(txtFilterCustomer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE))
			);
			GridBagLayout gbl_panel_1 = new GridBagLayout();
			gbl_panel_1.columnWidths = new int[]{0, 0};
			gbl_panel_1.rowHeights = new int[]{0, 0};
			gbl_panel_1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
			gbl_panel_1.rowWeights = new double[]{1.0, Double.MIN_VALUE};
			panel_1.setLayout(gbl_panel_1);
			
			CustomerCard customerCard = new CustomerCard();
			GridBagConstraints gbc_customerCard = new GridBagConstraints();
			gbc_customerCard.fill = GridBagConstraints.BOTH;
			gbc_customerCard.gridx = 0;
			gbc_customerCard.gridy = 0;
			panel_1.add(customerCard, gbc_customerCard);
			firstPanel.setLayout(gl_firstPanel);
		}
		
		JPanel secondPanel = new JPanel();
		secondPanel.setBorder(new TitledBorder(null, "Bitte waehlen Sie eine DVD aus", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPanel.add(secondPanel, "name_1350995044591721000");
		
		JLabel lblFilter = new JLabel("Filter");
		
		txtFilterDVD = new JTextField();
		txtFilterDVD.setText("Filter");
		txtFilterDVD.setColumns(10);
		
		JPanel panel = new JPanel();
		GroupLayout gl_secondPanel = new GroupLayout(secondPanel);
		gl_secondPanel.setHorizontalGroup(
			gl_secondPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_secondPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblFilter)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtFilterDVD, GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
					.addContainerGap())
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)
		);
		gl_secondPanel.setVerticalGroup(
			gl_secondPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_secondPanel.createSequentialGroup()
					.addGroup(gl_secondPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFilter)
						.addComponent(txtFilterDVD, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE))
		);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0};
		gbl_panel.rowHeights = new int[]{0, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		DVDCard dvdCard = new DVDCard();
		GridBagConstraints gbc_dvdCard = new GridBagConstraints();
		gbc_dvdCard.fill = GridBagConstraints.BOTH;
		gbc_dvdCard.gridx = 0;
		gbc_dvdCard.gridy = 0;
		panel.add(dvdCard, gbc_dvdCard);
		secondPanel.setLayout(gl_secondPanel);
		
		JPanel thirdPanel = new JPanel();
		thirdPanel.setBorder(new TitledBorder(null, "Zusammenfassung", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPanel.add(thirdPanel, "name_1350995345343463000");
		
		JLabel lblCustomer = new JLabel("Customer");
		
		JLabel lblDvd = new JLabel("DVD");
		
		JLabel lblRentdate = new JLabel("RentDate");
		
		JLabel lblReturndate = new JLabel("ReturnDate");
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		GroupLayout gl_thirdPanel = new GroupLayout(thirdPanel);
		gl_thirdPanel.setHorizontalGroup(
			gl_thirdPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_thirdPanel.createSequentialGroup()
					.addGroup(gl_thirdPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblCustomer)
						.addComponent(lblDvd)
						.addGroup(gl_thirdPanel.createSequentialGroup()
							.addComponent(lblRentdate)
							.addGap(12)
							.addComponent(lblReturndate))
						.addComponent(lblPrice))
					.addContainerGap(289, Short.MAX_VALUE))
		);
		gl_thirdPanel.setVerticalGroup(
			gl_thirdPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_thirdPanel.createSequentialGroup()
					.addComponent(lblCustomer)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblDvd)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_thirdPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRentdate)
						.addComponent(lblReturndate))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblPrice)
					.addContainerGap(101, Short.MAX_VALUE))
		);
		thirdPanel.setLayout(gl_thirdPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			JButton btnBack = new JButton("Zur\u00FCck");
			btnBack.setActionCommand("OK");
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
