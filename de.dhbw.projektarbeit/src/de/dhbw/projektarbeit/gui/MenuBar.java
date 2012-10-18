package de.dhbw.projektarbeit.gui;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar {
	public MenuBar() {
		
		JMenu mnDatei = new JMenu("Datei");
		add(mnDatei);
		
		JMenuItem mntmBeenden = new JMenuItem("Beenden");
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
		mnKunde.add(mntmNeuerKunde);
		
		JMenuItem mntmKundeBearbeiten = new JMenuItem("Kunde bearbeiten");
		mnKunde.add(mntmKundeBearbeiten);
		
		JMenu mnDvd = new JMenu("DVD");
		mnDialoge.add(mnDvd);
		
		JMenuItem mntmNeueDvd = new JMenuItem("Neue DVD");
		mnDvd.add(mntmNeueDvd);
		
		JMenuItem mntmDvdBearbeiten = new JMenuItem("DVD bearbeiten");
		mnDvd.add(mntmDvdBearbeiten);
		
		JMenuItem mntmVerleihpreis = new JMenuItem("Verleihpreis");
		mnDvd.add(mntmVerleihpreis);
		
		JMenu mnAngestellter = new JMenu("Angestellter");
		mnDialoge.add(mnAngestellter);
		
		JMenuItem mntmNeuerAngestellter = new JMenuItem("Neuer Angestellter");
		mnAngestellter.add(mntmNeuerAngestellter);
		
		JMenuItem mntmAngestelltenBearbeiten = new JMenuItem("Angestellten bearbeiten");
		mnAngestellter.add(mntmAngestelltenBearbeiten);
		
		JMenu mnGenre = new JMenu("Genre");
		mnDialoge.add(mnGenre);
		
		JMenuItem mntmNeueGenre = new JMenuItem("Neue Genre");
		mnGenre.add(mntmNeueGenre);
		
		JMenuItem mntmGenreBearbeiten = new JMenuItem("Genre bearbeiten");
		mnGenre.add(mntmGenreBearbeiten);
		
		JMenu mnRegisseur = new JMenu("Regisseur");
		mnDialoge.add(mnRegisseur);
		
		JMenuItem mntmNeuerRegissuer = new JMenuItem("Neuer Regissuer");
		mnRegisseur.add(mntmNeuerRegissuer);
		
		JMenuItem mntmRegisseurBearbeiten = new JMenuItem("Regisseur bearbeiten");
		mnRegisseur.add(mntmRegisseurBearbeiten);
		
		JMenu mnProduzent = new JMenu("Produzent");
		mnDialoge.add(mnProduzent);
		
		JMenuItem mntmNeuerProduzent = new JMenuItem("Neuer Produzent");
		mnProduzent.add(mntmNeuerProduzent);
		
		JMenuItem mntmProduzentBearbeiten = new JMenuItem("Produzent bearbeiten");
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

}
