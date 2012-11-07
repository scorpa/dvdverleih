package de.dhbw.projektarbeit.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;

import de.dhbw.projektarbeit.gui.Dialogs.EditDVD;
import de.dhbw.projektarbeit.gui.Dialogs.NewDVD;

public class TopPanel extends JPanel {
	private MainFrame mf;

	/**
	 * Standardkonstruktor
	 */
	public TopPanel() {
		setWindow();
	}

	/**
	 * Konstruktor für den Aufruf aus dem MainFrame heraus mit Übergabe der
	 * Klassenparameter
	 * 
	 * @param mf
	 *            --> Klassenparameter
	 */
	public TopPanel(MainFrame mf) {
		this.mf = mf;
		setWindow();
	}

	private void setWindow() {
		// TODO Auto-generated method stub

		setSize(new Dimension(800, 25));

		JButton btnNeueDvd = new JButton("Neue DVD");
		btnNeueDvd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					btnNeueDvdActionPerformed(e);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		JButton btnDvdBearbeiten = new JButton("DVD bearbeiten");
		btnDvdBearbeiten.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDvdBearbeitenActionPerformed(e);
			}
		});
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addComponent(btnNeueDvd, GroupLayout.PREFERRED_SIZE,
								99, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnDvdBearbeiten,
								GroupLayout.PREFERRED_SIZE, 134,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap(561, Short.MAX_VALUE)));
		groupLayout
				.setVerticalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																btnNeueDvd)
														.addComponent(
																btnDvdBearbeiten))
										.addContainerGap(
												GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));
		setLayout(groupLayout);
	}

	/**
	 * ActionListener zum Aufruf des Dialogs um eine neue DVD zu erstellen
	 * 
	 * @param evt
	 *            Clickevent
	 * @throws Exception
	 */
	private void btnNeueDvdActionPerformed(java.awt.event.ActionEvent e)
			throws Exception {
		// Dialog NewDVD aufrufen
		NewDVD dialog = new NewDVD(mf);
		dialog.setVisible(true);

	}

	/**
	 * ActionListener zum Aufruf des Dialogs um eine neue DVD zu erstellen
	 * 
	 * @param evt
	 *            Clickevent
	 */
	private void btnDvdBearbeitenActionPerformed(java.awt.event.ActionEvent e) {
		// Dialog EditDVD aufrufen
		EditDVD dialog = null;
		try {
			dialog = new EditDVD(mf);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		dialog.setVisible(true);

	}

	/**
	 * ActionListener zum Aufruf des Dialogs um eine neue DVD zu erstellen
	 * 
	 * @param evt
	 *            Clickevent
	 * @throws Exception
	 */
	private void btnDvdVerleihActionPerformed(java.awt.event.ActionEvent e)
			throws Exception {
		// Dialog NewDVD aufrufen
		NewDVD dialog = new NewDVD();
		dialog.setVisible(true);

	}

	/**
	 * ActionListener zum Aufruf des Dialogs um eine neue DVD zu erstellen
	 * 
	 * @param evt
	 *            Clickevent
	 * @throws Exception
	 */
	private void btnDvdZurueckActionPerformed(java.awt.event.ActionEvent e)
			throws Exception {
		// Dialog NewDVD aufrufen
		NewDVD dialog = new NewDVD();
		dialog.setVisible(true);

	}
}
