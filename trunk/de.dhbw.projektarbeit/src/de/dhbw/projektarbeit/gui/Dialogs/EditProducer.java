package de.dhbw.projektarbeit.gui.Dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import org.jdesktop.swingx.JXTable;

public class EditProducer extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JButton okButton;
	private JButton cancelButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EditProducer dialog = new EditProducer();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EditProducer() {
		setResizable(false);
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Produzenten bearbeiten");
		setBounds(100, 100, 610, 430);
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
			splitPane.setDividerSize(4);
			splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
			GridBagConstraints gbc_splitPane = new GridBagConstraints();
			gbc_splitPane.fill = GridBagConstraints.BOTH;
			gbc_splitPane.gridx = 0;
			gbc_splitPane.gridy = 0;
			contentPanel.add(splitPane, gbc_splitPane);
			{
				JPanel panel = new JPanel();
				panel.setBorder(new EmptyBorder(5, 5, 5, 5));
				splitPane.setRightComponent(panel);
				textField = new JTextField();
				textField.setText("FirstName");
				textField.setColumns(22);
				JLabel label = new JLabel("Vorname");
				JLabel label_1 = new JLabel("Nachname");
				textField_1 = new JTextField();
				textField_1.setText("LastName");
				textField_1.setColumns(22);
				GroupLayout gl_panel = new GroupLayout(panel);
				gl_panel.setHorizontalGroup(
					gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label))
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_1))
							.addContainerGap())
				);
				gl_panel.setVerticalGroup(
					gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(label)
								.addComponent(label_1))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addContainerGap(132, Short.MAX_VALUE))
				);
				panel.setLayout(gl_panel);
			}
			
			JXTable tbProduction = new JXTable();
			splitPane.setLeftComponent(tbProduction);
			splitPane.setDividerLocation(280);
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
			
			JButton btnAddProduction = new JButton("+");
			
			JButton btnDeleteProduction = new JButton("-");
			GroupLayout gl_buttonPane = new GroupLayout(buttonPane);
			gl_buttonPane.setHorizontalGroup(
				gl_buttonPane.createParallelGroup(Alignment.LEADING)
					.addGroup(Alignment.TRAILING, gl_buttonPane.createSequentialGroup()
						.addComponent(btnAddProduction, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnDeleteProduction, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 282, Short.MAX_VALUE)
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
							.addComponent(btnAddProduction)
							.addComponent(btnDeleteProduction))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
			);
			buttonPane.setLayout(gl_buttonPane);
		}
	}
}
