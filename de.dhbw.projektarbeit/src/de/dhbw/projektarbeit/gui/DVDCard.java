package de.dhbw.projektarbeit.gui;

import javax.swing.JPanel;
import org.jdesktop.swingx.JXTable;
import java.awt.CardLayout;
import javax.swing.JSplitPane;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;

public class DVDCard extends JPanel {

	/**
	 * Create the panel.
	 */
	public DVDCard() {
		setLayout(new CardLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setDividerSize(4);
		add(splitPane, "name_1350571039219144000");
		
		JXTable tbDVD = new JXTable();
		splitPane.setLeftComponent(tbDVD);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		splitPane.setRightComponent(panel);
		
		JPanel panel_1 = new JPanel();
		
		JLabel lblEANCode = new JLabel("EAN-Barcode");
		lblEANCode.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		
		JLabel lblProduktionsjahr = new JLabel("Produktionsjahr");
		lblProduktionsjahr.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		
		JLabel lblOriginaltitel = new JLabel("Originaltitel");
		lblOriginaltitel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGap(0, 655, Short.MAX_VALUE)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblEANCode)
						.addComponent(lblProduktionsjahr)
						.addComponent(lblOriginaltitel))
					.addContainerGap(539, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGap(0, 92, Short.MAX_VALUE)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblEANCode)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblProduktionsjahr)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblOriginaltitel)
					.addContainerGap(23, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		JPanel panel_2 = new JPanel();
		
		JLabel lblFilmtitel = new JLabel("Filmtitel");
		lblFilmtitel.setFont(new Font("Lucida Grande", Font.PLAIN, 26));
		
		JLabel lblProduzent = new JLabel("Produzent");
		lblProduzent.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		JLabel lblErscheinungsdatum = new JLabel("Erscheinungsdatum");
		lblErscheinungsdatum.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		JLabel lblDauer = new JLabel("Dauer");
		lblDauer.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		
		JLabel lblGenre = new JLabel("Genre");
		lblGenre.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		
		JLabel lblRegisseur = new JLabel("Regisseur");
		lblRegisseur.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		
		JLabel lblFSK = new JLabel("FSK");
		lblFSK.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGap(0, 784, Short.MAX_VALUE)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(lblFilmtitel)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(lblProduzent)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblErscheinungsdatum))
						.addComponent(lblDauer)
						.addComponent(lblGenre)
						.addComponent(lblRegisseur)
						.addComponent(lblFSK))
					.addContainerGap(487, Short.MAX_VALUE))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGap(0, 184, Short.MAX_VALUE)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblFilmtitel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblProduzent)
						.addComponent(lblErscheinungsdatum))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblDauer)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblGenre)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblRegisseur)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblFSK)
					.addContainerGap(11, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGap(0, 796, Short.MAX_VALUE)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(135)
							.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 655, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 784, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 314, Short.MAX_VALUE)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		splitPane.setDividerLocation(200);

	}

}
