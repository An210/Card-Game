package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.Frame;
import view.GUI.DropDown;
import view.GUI.Menu;

public class ChangeController implements ActionListener {

	Menu mn;
	DropDown dd;
	private Player selectedPlayer;
	private Frame frame;
	private GameEngine gameEngine;

	public ChangeController(DropDown dd, Frame frame, GameEngine gameEngine) {

		this.dd = dd;
		this.selectedPlayer = null;
		this.frame = frame;
		this.gameEngine = gameEngine;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		this.mn = this.frame.getNorth().getMenu();
		this.selectedPlayer = (Player) this.dd.selectedPlayer();

		// ------------------------
		this.frame.getCP().reset(this.frame.getPlayerStatus().getSelectedPlayer(), this.frame);
		// ------------------------
		this.frame.getCP().getPlayer(this.selectedPlayer);
		this.frame.getCP().repaint();

		this.frame.setPlayerStatus(this.selectedPlayer);

	}
}
