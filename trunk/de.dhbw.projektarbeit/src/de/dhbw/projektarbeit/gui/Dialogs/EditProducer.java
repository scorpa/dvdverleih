package de.dhbw.projektarbeit.gui.Dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JSplitPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import org.jdesktop.swingx.JXTable;

import de.dhbw.projektarbeit.db.request.Filling;
import de.dhbw.projektarbeit.db.request.Update;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JScrollPane;

public class EditProducer extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JButton btnUpdate;
	private JButton cancelButton;
	private int selectedID;
	private Update update;
	private Connection con;
	private JScrollPane scrollPane;
	private JTable tbProduction;

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
	 * Standardkonstruktor
	 */
	public EditProducer() {
		setWindow();
	}

	/**
	 * Methode zur Dialogerstellung
	 */
	private void setWindow() {

		// Spaltenüberschriften
		String[] columnNames = { "ID", "Vorname", "Nachname" };

		Filling fill = new Filling();

		Object[][] productionData = null;
		try {
			productionData = fill.getTable("production");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		;

		DefaultTableModel model = new DefaultTableModel(productionData,
				columnNames);
		setResizable(false);
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Produzenten bearbeiten");
		setBounds(100, 100, 610, 430);
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
				txtFirstName = new JTextField();
				txtFirstName.setText("FirstName");
				txtFirstName.setColumns(22);
				JLabel label = new JLabel("Vorname");
				JLabel label_1 = new JLabel("Nachname");
				txtLastName = new JTextField();
				txtLastName.setText("LastName");
				txtLastName.setColumns(22);
				GroupLayout gl_panel = new GroupLayout(panel);
				gl_panel.setHorizontalGroup(gl_panel
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_panel.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_panel.createParallelGroup(
														Alignment.LEADING)
														.addComponent(
																txtFirstName,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(label))
										.addGap(18)
										.addGroup(
												gl_panel.createParallelGroup(
														Alignment.LEADING)
														.addComponent(
																txtLastName,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(label_1))
										.addContainerGap()));
				gl_panel.setVerticalGroup(gl_panel
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_panel.createSequentialGroup()
										.addGroup(
												gl_panel.createParallelGroup(
														Alignment.BASELINE)
														.addComponent(label)
														.addComponent(label_1))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_panel.createParallelGroup(
														Alignment.BASELINE)
														.addComponent(
																txtFirstName,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																txtLastName,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addContainerGap(132, Short.MAX_VALUE)));
				panel.setLayout(gl_panel);
			}

			scrollPane = new JScrollPane();
			splitPane.setLeftComponent(scrollPane);

			tbProduction = new JTableNotEditable(model, columnNames);
			tbProduction.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					tbProductionMouseClicked(e);
				}
			});
			tbProduction.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tbProduction.setModel(model);
			scrollPane.setViewportView(tbProduction);
			splitPane.setDividerLocation(280);
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
			}

			JButton btnDelete = new JButton("L\u00F6schen");

			btnUpdate = new JButton("Speichern");
			btnUpdate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						btnUpdateActionPerformed(e);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			btnUpdate.setEnabled(false);
			GroupLayout gl_buttonPane = new GroupLayout(buttonPane);
			gl_buttonPane.setHorizontalGroup(
				gl_buttonPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_buttonPane.createSequentialGroup()
						.addContainerGap()
						.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnUpdate, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 314, Short.MAX_VALUE)
						.addComponent(cancelButton)
						.addContainerGap())
			);
			gl_buttonPane.setVerticalGroup(
				gl_buttonPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_buttonPane.createSequentialGroup()
						.addGap(5)
						.addGroup(gl_buttonPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_buttonPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnUpdate)
								.addComponent(cancelButton))
							.addComponent(btnDelete))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
			);
			buttonPane.setLayout(gl_buttonPane);
		}
	}

	/**
	 * Bei Klick auf die Tabelle wird die angeklickte Zeile ausgelesen
	 * 
	 * @param e
	 *            --> Eventhandling
	 */
	protected void tbProductionMouseClicked(MouseEvent e) {
		// Daten aus Table lesen
		try {
			selectedID = (Integer) tbProduction.getValueAt(
					tbProduction.getSelectedRow(), 0);
			txtFirstName.setText((String) tbProduction.getValueAt(
					tbProduction.getSelectedRow(), 1));
			txtLastName.setText((String) tbProduction.getValueAt(
					tbProduction.getSelectedRow(), 2));
			if (selectedID > 0) {
				btnUpdate.setEnabled(true);
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		}

	}

	/**
	 * Übergabemethode der Werte aus der ausgelesenen Zeile an den Updateaufruf
	 * 
	 * @param e
	 *            --> Eventhandling
	 * @throws Exception
	 *             --> Exceptionhandling
	 */
	protected void btnUpdateActionPerformed(ActionEvent e) throws Exception {
		updateProduction(selectedID, txtFirstName.getText(),
				txtLastName.getText(), "production");

	}

	/**
	 * Die Updatefunktion wird mit den übergebenen Parametern aufgerufen
	 * 
	 * @param id
	 *            --> ID des Eintrags auf der DB
	 * @param firstname
	 *            --> Vorname des Produzenten
	 * @param lastname
	 *            --> Nachname des Produzenten
	 * @param form
	 *            --> Tabelle, in der auf der DB die Daten liegen
	 * @throws Exception
	 *             --> Exceptionhandling
	 */
	private void updateProduction(int id, String firstname, String lastname,
			String form) throws Exception {
		// Update des Produzenten
		try {
			try {
				con = DriverManager
						.getConnection("jdbc:mysql://localhost/dvd_verleih?user=root");
			} catch (SQLException e) {
				// Verbindung zum SQL Server fehlgeschlagen. Fehlercode 005
				e.printStackTrace();
				throw new Exception(
						"Verbindung zum SQL Server fehlgeschlagen. Fehlercode 005");
			}
			// Aufruf der Updatefunktion mit der speziellen Weitergabe des
			// Tabellenfelds Production_ID
			update = new Update("dvd_verleih", con);
			update.updateEdits(id, firstname, lastname, form, "Production_ID");
			tbProduction.setValueAt(txtFirstName.getText(),
					tbProduction.getSelectedRow(), 1);
			tbProduction.setValueAt(txtLastName.getText(),
					tbProduction.getSelectedRow(), 2);

		} catch (Exception e2) {
			e2.printStackTrace();
			throw new Exception(
					"Bei der Uebertragung der Parameter ist ein Fehler aufgetreten! Fehlercode: 002");
		}
	}

}