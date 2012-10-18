package de.dhbw.projektarbeit.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;

import de.dhbw.projektarbeit.gui.Dialogs.NewCustomer;
import de.dhbw.projektarbeit.gui.Dialogs.NewDVD;

public class TopPanel extends JPanel {
	public TopPanel() {
		setSize(new Dimension(800, 25));
		
		JButton btnNeuerKunde = new JButton("Neuer Kunde");
		btnNeuerKunde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnNeuerKundeActionPerformed(arg0);
			}
		});
		
		JButton btnNeueDvd = new JButton("Neue DVD");
		btnNeueDvd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNeueDvdActionPerformed(e);
			}
		});
		
		JButton btnKundeBearbeiten = new JButton("Kunde bearbeiten");
		btnKundeBearbeiten.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnKundeBearbeitenActionPerformed(e);
			}
		});
		
		JButton btnDvdBearbeiten = new JButton("DVD bearbeiten");
		btnDvdBearbeiten.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDvdBearbeitenActionPerformed(e);
			}
		});
		
		JButton btnDvdVerleihen = new JButton("DVD verleihen");
		btnDvdVerleihen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDvdVerleihActionPerformed(e);
			}
		});
		
		JButton btnDvdZurueck = new JButton("DVD zur\u00FCck");
		btnDvdZurueck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDvdZurueckActionPerformed(e);
			}
		});
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnNeuerKunde, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnKundeBearbeiten, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNeueDvd, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnDvdBearbeiten, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnDvdVerleihen, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnDvdZurueck, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(36, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNeuerKunde)
						.addComponent(btnKundeBearbeiten)
						.addComponent(btnNeueDvd)
						.addComponent(btnDvdBearbeiten)
						.addComponent(btnDvdVerleihen)
						.addComponent(btnDvdZurueck))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}
	
	/**
	 * ActionListener zum Aufruf des Dialogs um neuen Kunden zu erstellen
	 * 
	 * @param evt
	 *            Clickevent
	 */
	private void btnNeuerKundeActionPerformed(
			java.awt.event.ActionEvent arg0) {
		// Dialog NewCustomer aufrufen
		NewCustomer dialog = new NewCustomer();
		dialog.setVisible(true);
		
	}
	
	/**
	 * ActionListener zum Aufruf des Dialogs um eine neue DVD zu erstellen
	 * 
	 * @param evt
	 *            Clickevent
	 */
	private void btnNeueDvdActionPerformed(
			java.awt.event.ActionEvent e) {
		// Dialog NewDVD aufrufen
		NewDVD dialog = new NewDVD();
		dialog.setVisible(true);
		
	}
	
	/**
	 * ActionListener zum Aufruf des Dialogs um eine neue DVD zu erstellen
	 * 
	 * @param evt
	 *            Clickevent
	 */
	private void btnKundeBearbeitenActionPerformed(
			java.awt.event.ActionEvent e) {
		// Dialog NewDVD aufrufen
		NewDVD dialog = new NewDVD();
		dialog.setVisible(true);
		
	}
	
	/**
	 * ActionListener zum Aufruf des Dialogs um eine neue DVD zu erstellen
	 * 
	 * @param evt
	 *            Clickevent
	 */
	private void btnDvdBearbeitenActionPerformed(
			java.awt.event.ActionEvent e) {
		// Dialog NewDVD aufrufen
		NewDVD dialog = new NewDVD();
		dialog.setVisible(true);
		
	}
	
	/**
	 * ActionListener zum Aufruf des Dialogs um eine neue DVD zu erstellen
	 * 
	 * @param evt
	 *            Clickevent
	 */
	private void btnDvdVerleihActionPerformed(
			java.awt.event.ActionEvent e) {
		// Dialog NewDVD aufrufen
		NewDVD dialog = new NewDVD();
		dialog.setVisible(true);
		
	}
	
	/**
	 * ActionListener zum Aufruf des Dialogs um eine neue DVD zu erstellen
	 * 
	 * @param evt
	 *            Clickevent
	 */
	private void btnDvdZurueckActionPerformed(
			java.awt.event.ActionEvent e) {
		// Dialog NewDVD aufrufen
		NewDVD dialog = new NewDVD();
		dialog.setVisible(true);
		
	}
}
