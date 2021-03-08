package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.Frame;

import view.GUI.DropDown;
import view.GUI.Menu;
import view.GUI.Table;

public class AddPlayerController implements ActionListener {
	private GameEngine gameEngine;
	private Table tb;
	private DropDown dd;
	private Menu mn;
	private Frame frame;
	private String type;
	private Player selectedPlayer;
	private int ID;
	private int i = 0;

	public AddPlayerController(Table tb, String type, GameEngine gameEngine, Frame frame, int i) {
		this.i = i;
		this.frame = frame;
		this.ID = 1;
		this.tb = tb;
		this.type = type;
		this.gameEngine = gameEngine;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.mn = this.frame.getNorth().getMenu();
		this.dd = this.frame.getNorth().getDropDown();
		this.selectedPlayer = this.dd.selectedPlayer();

		if (this.type.equalsIgnoreCase("add")) {
			String name = JOptionPane.showInputDialog("Enter name:");
			int points = Integer.parseInt(JOptionPane.showInputDialog("Enter points:"));
			this.gameEngine.addPlayer(new SimplePlayer(String.valueOf(ID), name, points));
			if (this.gameEngine.getAllPlayers() != null) {
				this.ID++;
			}
				
				this.frame.getTable().getPreviousPoint(points);
			
			
			this.frame.getSouth().updateStatus("Added " + this.selectedPlayer.getPlayerName());
			newUpdate();
			
			// Adding player method
		} else if (this.type.equalsIgnoreCase("bet")) {

			int bet = Integer.parseInt(JOptionPane.showInputDialog("Enter bet:"));
			if (bet < 0 || bet > this.selectedPlayer.getPoints()) {
				JOptionPane.showMessageDialog(null, "Invalid bet");
			} else {
				this.frame.getPlayerStatus().setPreviousPoint(this.selectedPlayer, this.selectedPlayer.getPoints());
				this.frame.getTable()
						.getPreviousPoint(this.frame.getPlayerStatus().getPreviousPoint(this.selectedPlayer));
				this.gameEngine.placeBet(this.selectedPlayer, bet);
				this.frame.getSouth().updateStatus(
						"Bet " + this.selectedPlayer.getBet() + " " + this.selectedPlayer.getPlayerName());
				this.selectedPlayer = this.dd.selectedPlayer();
				newUpdate();
			}
			// Placing bet

		} else if (this.type.equalsIgnoreCase("remove")) {
			this.frame.getSouth().updateStatus("Removed " + this.selectedPlayer.getPlayerName());
			this.gameEngine.removePlayer(this.selectedPlayer);
			this.frame.getCP().removePlayer(this.selectedPlayer);
			newUpdate();

			// Remove player
		} else if (this.type.equalsIgnoreCase("deal")) {

			class MyRunnable implements Runnable {
				private Player selectedPlayer;
				private GameEngine gameEngine;
				private Frame frame;

				public MyRunnable(Frame frame, Player selectedPlayer, GameEngine gameEngine) {
					this.selectedPlayer = selectedPlayer;
					this.gameEngine = gameEngine;
					this.frame = frame;
				}

				public void run() {
					this.gameEngine.dealPlayer(this.selectedPlayer, 100);
				}
			}
			Runnable r = new MyRunnable(this.frame, this.selectedPlayer, this.gameEngine);
			new Thread(r).start();
			this.frame.getPlayerStatus().setBeingDealt(this.selectedPlayer, "yep");
			this.frame.getSouth().updateStatus("Card is being dealt to " + this.selectedPlayer.getPlayerName());
		}
		// New dealing thread

		if (i == 0) {
			this.dd.addController();
		}
		i++;
		// Adding controller once for DropDown

		disableCheck();
	}

	public void newUpdate() {
		this.tb.updateTable(this.gameEngine, this.frame);
		this.dd.updateDropDown(this.gameEngine);
		this.selectedPlayer = this.dd.selectedPlayer();
		this.frame.setPlayerStatus(this.selectedPlayer);
	}

	public void disableCheck() {
		if (this.frame.getPlayerStatus().added()) {
			this.mn.disableItem(true, 1);
		} else {
			this.mn.disableItem(false, 1);
		}

		if (this.frame.getPlayerStatus().checkBet()) {
			this.mn.disableItem(true, 1);
			this.mn.disableItem(true, 3);
			
			if (this.frame.getPlayerStatus().getBeingDealt(this.selectedPlayer) != null) {
				if (this.frame.getPlayerStatus().getBeingDealt(this.selectedPlayer).equalsIgnoreCase("yep")) {
					this.mn.disableItem(false, 3);
				}
			}
		} else {
			this.mn.disableItem(false, 3);
		}

	}
}
