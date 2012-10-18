package de.dhbw.projektarbeit.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JSplitPane;
import java.awt.GridBagConstraints;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import org.jdesktop.swingx.JXTable;

public class EditRegisseur extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JButton okButton;
	private JButton cancelButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EditRegisseur dialog = new EditRegisseur();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EditRegisseur() {
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setAlwaysOnTop(true);
		setResizable(false);
		setTitle("Regisseure bearbeiten");
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
				JLabel label = new JLabel("Vorname");
				txtFirstName = new JTextField();
				txtFirstName.setText("FirstName");
				txtFirstName.setColumns(22);
				txtLastName = new JTextField();
				txtLastName.setText("LastName");
				txtLastName.setColumns(22);
				JLabel label_1 = new JLabel("Nachname");
				GroupLayout gl_panel = new GroupLayout(panel);
				gl_panel.setHorizontalGroup(
					gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(txtFirstName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label))
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(txtLastName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_1))
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);
				gl_panel.setVerticalGroup(
					gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(label)
								.addComponent(label_1))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtFirstName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtLastName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addContainerGap(42, Short.MAX_VALUE))
				);
				panel.setLayout(gl_panel);
			}
			
			JXTable tbRegisseur = new JXTable();
			splitPane.setLeftComponent(tbRegisseur);
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
			
			JButton btnAdd = new JButton("+");
			
			JButton btnDelete = new JButton("-");
			GroupLayout gl_buttonPane = new GroupLayout(buttonPane);
			gl_buttonPane.setHorizontalGroup(
				gl_buttonPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_buttonPane.createSequentialGroup()
						.addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 278, Short.MAX_VALUE)
						.addComponent(okButton)
						.addGap(5)
						.addComponent(cancelButton))
			);
			gl_buttonPane.setVerticalGroup(
				gl_buttonPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_buttonPane.createSequentialGroup()
						.addGap(5)
						.addGroup(gl_buttonPane.createParallelGroup(Alignment.LEADING)
							.addComponent(okButton)
							.addComponent(cancelButton)
							.addGroup(gl_buttonPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnAdd)
								.addComponent(btnDelete)))
						.addContainerGap())
			);
			buttonPane.setLayout(gl_buttonPane);
		}
	}
}
