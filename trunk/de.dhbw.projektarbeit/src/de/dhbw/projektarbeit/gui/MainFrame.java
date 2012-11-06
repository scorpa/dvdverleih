package de.dhbw.projektarbeit.gui;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.JTabbedPane;


public class MainFrame extends javax.swing.JFrame {
	private MenuBar menuBar;
	private TopPanel topPanel;
	private JPanel CardPanel, panelMain;
	

	public MainFrame() {
		super();
		setMinimumSize(new Dimension(1024, 768));
		setSize(new Dimension(800, 600));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("DVD Verwaltung");
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
			
			JPanel panelMain = new JPanel();
			panelMain.setLayout(new CardLayout(0, 0));
			GroupLayout groupLayout = new GroupLayout(getContentPane());
			groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(topPanel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(panelMain, GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE))
						.addGap(0))
			);
			groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(groupLayout.createSequentialGroup()
						.addComponent(topPanel_1, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panelMain, GroupLayout.DEFAULT_SIZE, 521, Short.MAX_VALUE))
			);
			
			JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			panelMain.add(tabbedPane, "name_1351026973258259000");
			
			
			
			DVDCard dvdCard = new DVDCard();
			tabbedPane.addTab("DVD Liste", null, dvdCard, null);
			getContentPane().setLayout(groupLayout);
			this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void cardLayoutDVD(){
		CardLayout card = (CardLayout) panelMain.getLayout();
		card.show(panelMain, "custom_1350565561472");
	}
}
