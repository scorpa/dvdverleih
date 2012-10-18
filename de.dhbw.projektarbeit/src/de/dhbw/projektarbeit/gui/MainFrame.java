package de.dhbw.projektarbeit.gui;

import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;


public class MainFrame extends javax.swing.JFrame {
	private MenuBar menuBar;
	private TopPanel topPanel;
	private JPanel CardPanel;
	

	public MainFrame() {
		super();
		setMinimumSize(new Dimension(800, 600));
		setSize(new Dimension(800, 600));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("DVD Verleih");
		try {
			UIManager.setLookAndFeel(UIManager
					.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {

		}
		initGUI();
	}

	private void initGUI() {
		try {
			
			{
				menuBar =  new MenuBar();
				topPanel = new TopPanel();
			}
			{
				this.setSize(800, 600);
				this.setJMenuBar(menuBar);
				
			}
			
			TopPanel topPanel_1 = new TopPanel();
			
			JPanel panel = new JPanel();
			panel.setLayout(new CardLayout(0, 0));
			
			
			
			DVDCard dvdCard = new DVDCard();
			panel.add(dvdCard, "name_1350566741846857000");
			GroupLayout groupLayout = new GroupLayout(getContentPane());
			groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(topPanel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(panel, GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE))
						.addGap(0))
			);
			groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(groupLayout.createSequentialGroup()
						.addComponent(topPanel_1, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 521, Short.MAX_VALUE))
			);
			
			CustomerCard customerCard = new CustomerCard();
			panel.add(customerCard, "name_1350572100037540000");
			getContentPane().setLayout(groupLayout);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
