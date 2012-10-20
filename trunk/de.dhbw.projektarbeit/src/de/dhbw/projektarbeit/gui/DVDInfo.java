package de.dhbw.projektarbeit.gui;

import javax.swing.JPanel;
import javax.swing.JSplitPane;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class DVDInfo extends JPanel {

	/**
	 * Create the panel.
	 */
	public DVDInfo() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setDividerSize(4);
		GridBagConstraints gbc_splitPane = new GridBagConstraints();
		gbc_splitPane.fill = GridBagConstraints.BOTH;
		gbc_splitPane.gridx = 0;
		gbc_splitPane.gridy = 0;
		add(splitPane, gbc_splitPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		splitPane.setRightComponent(panel);
		
		JPanel panel_1 = new JPanel();
		
		JLabel label = new JLabel("EAN-Barcode");
		label.setFont(new Font("Dialog", Font.PLAIN, 14));
		
		JLabel label_1 = new JLabel("Produktionsjahr");
		label_1.setFont(new Font("Dialog", Font.PLAIN, 14));
		
		JLabel label_2 = new JLabel("Originaltitel");
		label_2.setFont(new Font("Dialog", Font.PLAIN, 14));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGap(0, 453, Short.MAX_VALUE)
				.addGap(0, 453, Short.MAX_VALUE)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(label)
						.addComponent(label_1)
						.addComponent(label_2))
					.addContainerGap(194, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGap(0, 92, Short.MAX_VALUE)
				.addGap(0, 92, Short.MAX_VALUE)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(label)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(label_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(label_2)
					.addContainerGap(12, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		JPanel panel_2 = new JPanel();
		
		JLabel label_3 = new JLabel("Filmtitel");
		label_3.setFont(new Font("Dialog", Font.PLAIN, 26));
		
		JLabel label_4 = new JLabel("Produzent");
		label_4.setFont(new Font("Dialog", Font.PLAIN, 20));
		
		JLabel label_5 = new JLabel("Erscheinungsdatum");
		label_5.setFont(new Font("Dialog", Font.PLAIN, 20));
		
		JLabel label_6 = new JLabel("Dauer");
		label_6.setFont(new Font("Dialog", Font.PLAIN, 16));
		
		JLabel label_7 = new JLabel("Genre");
		label_7.setFont(new Font("Dialog", Font.PLAIN, 16));
		
		JLabel label_8 = new JLabel("Regisseur");
		label_8.setFont(new Font("Dialog", Font.PLAIN, 16));
		
		JLabel label_9 = new JLabel("FSK");
		label_9.setFont(new Font("Dialog", Font.PLAIN, 16));
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGap(0, 578, Short.MAX_VALUE)
				.addGap(0, 578, Short.MAX_VALUE)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(label_3)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(label_4)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(label_5))
						.addComponent(label_6)
						.addComponent(label_7)
						.addComponent(label_8)
						.addComponent(label_9))
					.addContainerGap(154, Short.MAX_VALUE))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGap(0, 184, Short.MAX_VALUE)
				.addGap(0, 184, Short.MAX_VALUE)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(label_3)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_4)
						.addComponent(label_5))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(label_6)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(label_7)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(label_8)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(label_9)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGap(0, 598, Short.MAX_VALUE)
				.addGap(0, 598, Short.MAX_VALUE)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(135)
							.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 128, Short.MAX_VALUE)
				.addGap(0, 253, Short.MAX_VALUE)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		DVDCard DVDCard = new DVDCard();
		splitPane.setLeftComponent(DVDCard);
		splitPane.setDividerLocation(200);

	}
}
