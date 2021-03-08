package view.GUI;

import java.awt.GridLayout;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controller.AddPlayerController;
import model.interfaces.GameEngine;
import view.Frame;

public class Menu extends JMenuBar {
	Table tb;
	DropDown dd;
	Frame frame;
	private JMenuItem[] menuItems = { new JMenuItem("Add player"), new JMenuItem("Set bet"),
			new JMenuItem("Remove player"), new JMenuItem("Deal player") };

	public Menu(Table tb, DropDown dd, Frame frame, GameEngine gameEngine) {

		this.tb = tb;
		this.dd = dd;
		JMenu jm = new JMenu("Edit");
		this.frame = frame;
		int x = 0;
		for (JMenuItem menuItem : menuItems) {
			if (x == 0) {
				menuItem.addActionListener(new AddPlayerController(this.tb, "add", gameEngine, this.frame, 0));

			} else if (x == 1) {
				menuItem.addActionListener(new AddPlayerController(this.tb, "bet", gameEngine, this.frame, 1));
				menuItem.setEnabled(false);
			} else if (x == 2) {
				menuItem.addActionListener(new AddPlayerController(this.tb, "remove", gameEngine, this.frame, 1));
			} else if (x == 3) {
				menuItem.addActionListener(new AddPlayerController(this.tb, "deal", gameEngine, this.frame, 1));
				menuItem.setEnabled(false);
			}
			x++;
			jm.add(menuItem);
		}
		jm.setLayout(new GridLayout());
		this.add(jm);
	}

	public void disableItem(boolean addedPlayer, int item) {
		menuItems[item].setEnabled(addedPlayer);
	}
}
