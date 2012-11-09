package de.dhbw.projektarbeit.gui.Dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.GridBagLayout;
import javax.swing.JSplitPane;
import java.awt.GridBagConstraints;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import org.jdesktop.swingx.JXTable;

import de.dhbw.projektarbeit.db.mysql.MysqlAccess;
import de.dhbw.projektarbeit.db.request.Check;
import de.dhbw.projektarbeit.db.request.Delete;
import de.dhbw.projektarbeit.db.request.Filling;
import de.dhbw.projektarbeit.db.request.Update;
import de.dhbw.projektarbeit.gui.MainFrame;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;

/**
 * Klasse, die das Ändern des Autors erlaubt
 * 
 * @author Brunner
 * 
 */
public class EditAuthor extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JButton cancelButton, btnUpdate, btnDelete;
	private JTable tbAuthor;
	private Integer selectedID;
	private Update update;
	private boolean vorhanden = false;
	private Check check;
	private String firstName, lastName;

	/**
	 * Standardkonstrutor
	 */
	public EditAuthor() {
		setWindow();
	}

	/**
	 * Standardkonsturktor
	 */
	private void setWindow() {

		// Spaltenüberschriften
		String[] columnNames = { "ID", "Vorname", "Nachname" };

		Filling fill = new Filling();

		Object[][] authorData = null;
		try {
			authorData = fill.getTable("author");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		;

		DefaultTableModel model = new DefaultTableModel(authorData, columnNames);

		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setAlwaysOnTop(true);
		setResizable(false);
		setTitle("Autor bearbeiten");
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
				JLabel label = new JLabel("Vorname");
				txtFirstName = new JTextField();
				txtFirstName.setEnabled(false);
				txtFirstName.setColumns(22);
				txtLastName = new JTextField();
				txtLastName.setEnabled(false);
				txtLastName.setColumns(22);
				JLabel label_1 = new JLabel("Nachname");
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
										.addContainerGap(
												GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));
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
										.addContainerGap(42, Short.MAX_VALUE)));
				panel.setLayout(gl_panel);
			}

			JScrollPane scrollPane = new JScrollPane();
			splitPane.setLeftComponent(scrollPane);

			tbAuthor = new JTableNotEditable(model, columnNames);
			tbAuthor.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					tbAuthorMouseClicked(e);
				}
			});
			scrollPane.setViewportView(tbAuthor);
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

			btnDelete = new JButton("L\u00F6schen");
			btnDelete.setEnabled(false);
			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						btnDeleteActionPerformed(e);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});

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
			gl_buttonPane.setHorizontalGroup(gl_buttonPane.createParallelGroup(
					Alignment.LEADING).addGroup(
					Alignment.TRAILING,
					gl_buttonPane
							.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnDelete,
									GroupLayout.PREFERRED_SIZE, 96,
									GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(btnUpdate,
									GroupLayout.PREFERRED_SIZE, 103,
									GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 314,
									Short.MAX_VALUE).addComponent(cancelButton)
							.addContainerGap()));
			gl_buttonPane
					.setVerticalGroup(gl_buttonPane
							.createParallelGroup(Alignment.LEADING)
							.addGroup(
									Alignment.TRAILING,
									gl_buttonPane
											.createSequentialGroup()
											.addContainerGap(
													GroupLayout.DEFAULT_SIZE,
													Short.MAX_VALUE)
											.addGroup(
													gl_buttonPane
															.createParallelGroup(
																	Alignment.LEADING)
															.addGroup(
																	gl_buttonPane
																			.createSequentialGroup()
																			.addGap(1)
																			.addComponent(
																					btnDelete))
															.addGroup(
																	gl_buttonPane
																			.createSequentialGroup()
																			.addGap(1)
																			.addComponent(
																					btnUpdate))
															.addComponent(
																	cancelButton))
											.addContainerGap()));
			buttonPane.setLayout(gl_buttonPane);
		}
	}

	/**
	 * Bei Klick auf die Tabelle wird die angeklickte Zeile ausgelesen
	 * 
	 * @param e
	 *            --> Eventhandling
	 */
	private void tbAuthorMouseClicked(MouseEvent e) {
		// Daten aus Table lesen
		try {
			selectedID = (Integer) tbAuthor.getValueAt(
					tbAuthor.getSelectedRow(), 0);
			txtFirstName.setText((String) tbAuthor.getValueAt(
					tbAuthor.getSelectedRow(), 1));
			txtLastName.setText((String) tbAuthor.getValueAt(
					tbAuthor.getSelectedRow(), 2));
			if (selectedID >= 0) {
				btnUpdate.setEnabled(true);
				btnDelete.setEnabled(true);
				txtFirstName.setEnabled(true);
				txtLastName.setEditable(true);
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
		firstName = txtFirstName.getText();
		lastName = txtLastName.getText();

		// Wenn Leerzeichen im Vornamen eingegeben wurden, werden diese gelöscht
		while (firstName.indexOf(" ") == 0) {
			firstName = firstName.substring(1);
		}

		// Wenn Leerzeichen im Nachnamen eingegeben wurden, werden diese
		// gelöscht
		while (lastName.indexOf(" ") == 0) {
			lastName = lastName.substring(1);
		}
		updateAuthor(selectedID, firstName, lastName, "author");

	}

	/**
	 * Die Updatefunktion wird mit den übergebenen Parametern aufgerufen
	 * 
	 * @param id
	 *            --> ID des Eintrags auf der DB
	 * @param firstname
	 *            --> Vorname des Kameramanns
	 * @param lastname
	 *            --> Nachname des Kammeramanns
	 * @param form
	 *            --> Tabelle, in der auf der DB die Daten liegen
	 * @throws Exception
	 *             --> Exceptionhandling
	 */
	private void updateAuthor(int id, String firstname, String lastname,
			String form) throws Exception {
		// Update des Produzenten
		try {
			MysqlAccess mysql = new MysqlAccess();
			check = new Check("dvd_verleih", mysql.getConnection());
			// Check durchführen, ob Name des Autors schon vorhanden
			vorhanden = check.check("author", "Author_ID", firstname, lastname);

			if (vorhanden == false) {
				// con = mysql.getConnection();
				// Aufruf der Updatefunktion mit der speziellen Weitergabe des
				// Tabellenfelds Production_ID
				update = new Update("dvd_verleih", mysql.getConnection());
				update.updateEdits(id, firstname, lastname, form, "Author_ID");
				tbAuthor.setValueAt(txtFirstName.getText(),
						tbAuthor.getSelectedRow(), 1);
				tbAuthor.setValueAt(txtLastName.getText(),
						tbAuthor.getSelectedRow(), 2);
			} else {
				JOptionPane.showMessageDialog(this, "Der Autor \"" + firstName
						+ " " + lastName + "\" ist bereits vorhanden!",
						"Autor bearbeiten", JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e2) {
			e2.printStackTrace();
			throw new Exception(
					"Bei der Uebertragung der Parameter ist ein Fehler aufgetreten! Fehlercode: 002");
		}
	}

	/**
	 * Löschen-Button gedrückt
	 * 
	 * @param e
	 *            --> Eventhandling
	 * @throws Exception
	 *             --> Exceptionhandling
	 */
	private void btnDeleteActionPerformed(ActionEvent e) throws Exception {
		try {
			this.setVisible(false);
			MysqlAccess mysql = new MysqlAccess();
			// Aufruf der Deletemethode
			Delete delete = new Delete("dvd_verleih", mysql.getConnection());
			delete.deleteEdits(selectedID, txtFirstName.getText(),
					txtLastName.getText(), "author", "Author_ID");

			try {
				this.dispose();
				// Neues, leeres Erstellungsfenster instantiieren
				EditAuthor dialog = new EditAuthor();
				dialog.setVisible(true);

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
