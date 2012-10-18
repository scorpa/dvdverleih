package de.dhbw.projektarbeit.gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

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
