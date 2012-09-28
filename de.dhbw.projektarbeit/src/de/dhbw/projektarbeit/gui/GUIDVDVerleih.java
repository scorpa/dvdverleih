package de.dhbw.projektarbeit.gui;
import java.awt.Frame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class GUIDVDVerleih extends javax.swing.JFrame {
	private JMenuBar jMenuBar1;
	private JMenuItem MenuItemDatabase;
	private JMenuItem MenuItemClose;
	private JMenu jMenu1;

	private void initGUI() {
		{
			this.setName("mainFrame");
			this.setExtendedState(Frame.MAXIMIZED_BOTH);
			{
				jMenuBar1 = new JMenuBar();
				setJMenuBar(jMenuBar1);
				{
					jMenu1 = new JMenu();
					jMenuBar1.add(jMenu1);
					jMenu1.setText("Datei");
					{
						MenuItemDatabase = new JMenuItem();
						jMenu1.add(MenuItemDatabase);
						MenuItemDatabase.setText("Datenbank");
					}
					{
						MenuItemClose = new JMenuItem();
						jMenu1.add(MenuItemClose);
						MenuItemClose.setText("Beenden");
					}
				}
			}
		}
		{
			this.setSize(543, 326);
		}

	}

}
