package de.dhbw.projektarbeit.gui;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import de.dhbw.projektarbeit.gui.Dialogs.NewCustomer;
import de.dhbw.projektarbeit.gui.Dialogs.NewDVD;
import de.dhbw.projektarbeit.gui.Dialogs.NewProducer;
import de.dhbw.projektarbeit.gui.Dialogs.NewRegisseur;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuBar extends JMenuBar {
	public MenuBar() {

		JMenu mnDatei = new JMenu("Datei");
		add(mnDatei);

		JMenuItem mntmBeenden = new JMenuItem("Beenden");
		mntmBeenden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mntmBeendenActionPerformed(arg0);
			}
		});
		mnDatei.add(mntmBeenden);

		JMenu mnAnsicht = new JMenu("Ansicht");
		add(mnAnsicht);

		JMenuItem mntmKundenliste = new JMenuItem("Kundenliste");
		mnAnsicht.add(mntmKundenliste);

		JMenuItem mntmDvdListe = new JMenuItem("DVD Liste");
		mnAnsicht.add(mntmDvdListe);

		JMenu mnDialoge = new JMenu("Dialoge");
		add(mnDialoge);

		JMenu mnKunde = new JMenu("Kunde");
		mnDialoge.add(mnKunde);

		JMenuItem mntmNeuerKunde = new JMenuItem("Neuer Kunde");
		mntmNeuerKunde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mntmNeuerKundeActionPerformed(e);
			}
		});
		mnKunde.add(mntmNeuerKunde);

		JMenuItem mntmKundeBearbeiten = new JMenuItem("Kunde bearbeiten");
		mnKunde.add(mntmKundeBearbeiten);

		JMenu mnDvd = new JMenu("DVD");
		mnDialoge.add(mnDvd);

		JMenuItem mntmNeueDvd = new JMenuItem("Neue DVD");
		mntmNeueDvd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					mntmNeueDvdActionPerformed(e);
				} catch (Exception e1) {
					// Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mnDvd.add(mntmNeueDvd);

		JMenuItem mntmDvdBearbeiten = new JMenuItem("DVD bearbeiten");
		mnDvd.add(mntmDvdBearbeiten);

		JMenuItem mntmVerleihpreis = new JMenuItem("Verleihpreis");
		mnDvd.add(mntmVerleihpreis);

		JMenu mnRegisseur = new JMenu("Regisseur");
		mnDialoge.add(mnRegisseur);

		JMenuItem mntmNeuerRegissuer = new JMenuItem("Neuer Regisseur");
		mntmNeuerRegissuer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mntmNeuerRegissuerActionPerformed(e);
			}
		});
		mnRegisseur.add(mntmNeuerRegissuer);

		JMenuItem mntmRegisseurBearbeiten = new JMenuItem(
				"Regisseur bearbeiten");
		mnRegisseur.add(mntmRegisseurBearbeiten);

		JMenu mnProduzent = new JMenu("Produzent");
		mnDialoge.add(mnProduzent);

		JMenuItem mntmNeuerProduzent = new JMenuItem("Neuer Produzent");
		mntmNeuerProduzent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mntmNeuerProduzentActionPerformed(e);
			}
		});
		mnProduzent.add(mntmNeuerProduzent);

		JMenuItem mntmProduzentBearbeiten = new JMenuItem(
				"Produzent bearbeiten");
		mnProduzent.add(mntmProduzentBearbeiten);

		JMenu mnKamera = new JMenu("Kamera");
		mnDialoge.add(mnKamera);

		JMenuItem mntmNeueKamera = new JMenuItem("Neue Kamera");
		mnKamera.add(mntmNeueKamera);

		JMenuItem mntmKameraBearbeiten = new JMenuItem("Kamera bearbeiten");
		mnKamera.add(mntmKameraBearbeiten);

		JMenu mnAutor = new JMenu("Autor");
		mnDialoge.add(mnAutor);

		JMenuItem mntmNeuerAutor = new JMenuItem("Neuer Autor");
		mnAutor.add(mntmNeuerAutor);

		JMenuItem mntmAutorBearbeiten = new JMenuItem("Autor bearbeiten");
		mnAutor.add(mntmAutorBearbeiten);
	}

	/**
	 * Aufruf aus dem Kontextmenü Datei --> Beenden
	 * 
	 * @param arg0
	 *            --> Eventhandling
	 */
	private void mntmBeendenActionPerformed(ActionEvent arg0) {
		System.exit(0);
	}

	/**
	 * Aufruf aus dem Kontextmenüt Dialoge -> Kunden --> Neuer Kunde anlegen
	 * 
	 * @param e
	 *            --> Eventhandling
	 */
	private void mntmNeuerKundeActionPerformed(ActionEvent e) {
		NewCustomer nc = new NewCustomer();
		nc.setVisible(true);
	}

	/**
	 * Aufruf aus dem Kontextmenü Dialoge -> DVD --> Neue DVD anlegen
	 * 
	 * @param e
	 *            --> Eventhandling
	 * @throws Exception
	 *             --> Exceptionhandling
	 */
	private void mntmNeueDvdActionPerformed(ActionEvent e) throws Exception {
		NewDVD dvd = new NewDVD();
		dvd.setVisible(true);
	}

	/**
	 * Aufruf aus dem Kontextmenü Dialoge -> Regisseur --> Neuen Regisseur
	 * anlegen
	 * 
	 * @param e
	 *            --> Eventhandling
	 */
	private void mntmNeuerRegissuerActionPerformed(ActionEvent e) {
		NewRegisseur nr = new NewRegisseur();
		nr.setVisible(true);
	}

	/**
	 * Aufruf aus dem Kontextmenü Dialoge -> Produzent --> Neuen Produzent
	 * anlegen
	 * @param e --> Eventhandling
	 */
	private void mntmNeuerProduzentActionPerformed(ActionEvent e) {
		NewProducer np = new NewProducer();
		np.setVisible(true);
	}
}
