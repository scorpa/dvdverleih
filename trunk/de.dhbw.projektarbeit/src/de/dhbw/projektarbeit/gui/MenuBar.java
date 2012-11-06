package de.dhbw.projektarbeit.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import de.dhbw.projektarbeit.gui.Dialogs.EditAuthor;
import de.dhbw.projektarbeit.gui.Dialogs.EditCamera;
import de.dhbw.projektarbeit.gui.Dialogs.EditProducer;
import de.dhbw.projektarbeit.gui.Dialogs.EditRegisseur;
import de.dhbw.projektarbeit.gui.Dialogs.NewAuthor;
import de.dhbw.projektarbeit.gui.Dialogs.NewCamera;
import de.dhbw.projektarbeit.gui.Dialogs.NewDVD;
import de.dhbw.projektarbeit.gui.Dialogs.NewProducer;
import de.dhbw.projektarbeit.gui.Dialogs.NewRegisseur;

public class MenuBar extends JMenuBar {
	public MenuBar() {

		JMenu mnDatei = new JMenu("Datei");
		add(mnDatei);

		JMenuItem mntmBeenden = new JMenuItem("Beenden");
		mntmBeenden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Button "Beenden" gedrückt
				mntmBeendenActionPerformed(arg0);
			}
		});
		mnDatei.add(mntmBeenden);

		JMenu mnDialoge = new JMenu("Dialoge");
		add(mnDialoge);

		JMenu mnDvd = new JMenu("DVD");
		mnDialoge.add(mnDvd);

		JMenuItem mntmNeueDvd = new JMenuItem("Neue DVD");
		mntmNeueDvd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Button "Neue DVD" gedrückt
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

		JMenu mnRegisseur = new JMenu("Regisseur");
		mnDialoge.add(mnRegisseur);

		JMenuItem mntmNeuerRegissuer = new JMenuItem("Neuer Regisseur");
		mntmNeuerRegissuer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Button "Neuer Regisseur" gedrückt
				mntmNeuerRegissuerActionPerformed(e);
			}
		});
		mnRegisseur.add(mntmNeuerRegissuer);

		JMenuItem mntmRegisseurBearbeiten = new JMenuItem(
				"Regisseur bearbeiten");
		mntmRegisseurBearbeiten.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Button "Regisseur bearbeiten" gedrückt
				mntmRegisseurBearbeitenActionPerformed(arg0);
			}
		});
		mnRegisseur.add(mntmRegisseurBearbeiten);

		JMenu mnProduzent = new JMenu("Produzent");
		mnDialoge.add(mnProduzent);

		JMenuItem mntmNeuerProduzent = new JMenuItem("Neuer Produzent");
		mntmNeuerProduzent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Button "Neuer Produzent" gedrückt
				mntmNeuerProduzentActionPerformed(e);
			}
		});
		mnProduzent.add(mntmNeuerProduzent);

		JMenuItem mntmProduzentBearbeiten = new JMenuItem(
				"Produzent bearbeiten");
		mntmProduzentBearbeiten.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Button "Produzent bearbeiten" gedrückt
				mntmProduzentBearbeitenActionPerformed(e);
			}
		});
		mnProduzent.add(mntmProduzentBearbeiten);

		JMenu mnKamera = new JMenu("Kamera");
		mnDialoge.add(mnKamera);

		JMenuItem mntmNeueKamera = new JMenuItem("Neuer Kamermann");
		mntmNeueKamera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Button "Neuer Kameramann" gedrückt
				mntmNeueKameraActionPerformed(e);
			}
		});
		mnKamera.add(mntmNeueKamera);

		JMenuItem mntmKameraBearbeiten = new JMenuItem("Kameramann bearbeiten");
		mntmKameraBearbeiten.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Button "Kameramann bearbeiten" gedrückt
				mntmKameraBearbeitenActionPerformed(e);
			}
		});
		mnKamera.add(mntmKameraBearbeiten);

		JMenu mnAutor = new JMenu("Autor");
		mnDialoge.add(mnAutor);

		JMenuItem mntmNeuerAutor = new JMenuItem("Neuer Autor");
		mntmNeuerAutor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Button "Neuer Autor" gedrückt
				mntmNeuerAutorActionPerformed(e);
			}
		});
		mnAutor.add(mntmNeuerAutor);

		JMenuItem mntmAutorBearbeiten = new JMenuItem("Autor bearbeiten");
		mntmAutorBearbeiten.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Button "Autor bearbeiten" gedrückt
				mntmAutorBearbeitenActionPerformed(e);
			}
		});
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
	 * Aufruf aus dem Kontextmenü Dialoge -> Regisseur --> Regisseur bearbeiten
	 * @param arg0 --> Eventhandling
	 */
	private void mntmRegisseurBearbeitenActionPerformed(ActionEvent arg0) {
		EditRegisseur er = new EditRegisseur();
		er.setVisible(true);
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
	
	/**
	 * Aufruf aus dem Kontextmenü Dialoge -> Produzent --> Produzent bearbeiten
	 * @param e --> Eventhandling
	 */
	private void mntmProduzentBearbeitenActionPerformed(ActionEvent e) {
		EditProducer ep = new EditProducer();
		ep.setVisible(true);
	}
	
	/**
	 * Aufruf aus dem Kontextmenü Dialoge -> Kamera --> Neuer Kameramann anlegen
	 * @param e --> Eventhandling
	 */
	private void mntmNeueKameraActionPerformed(ActionEvent e) {
		NewCamera nc = new NewCamera();
		nc.setVisible(true);
	}
	
	/**
	 * Aufruf aus dem Kontextmenü Dialoge -> Kamera --> Kameramann bearbeiten
	 * @param e --> Eventhandling
	 */
	private void mntmKameraBearbeitenActionPerformed(ActionEvent e) {
		EditCamera ec = new EditCamera();
		ec.setVisible(true);
	}
	
	/**
	 * Aufruf aus dem Kontextmenü Dialoge -> Autor --> Neuer Autor anlegen
	 * @param e --> Eventhandling
	 */
	private void mntmNeuerAutorActionPerformed(ActionEvent e) {
		NewAuthor na = new NewAuthor();
		na.setVisible(true);
	}
	
	/**
	 * Aufruf aus dem Kontextmenü Dialoge -> Autor --> Autor bearbeiten
	 * @param e --> Eventhandling
	 */
	private void mntmAutorBearbeitenActionPerformed(ActionEvent e) {
		EditAuthor ea = new EditAuthor();
		ea.setVisible(true);
	}
}
