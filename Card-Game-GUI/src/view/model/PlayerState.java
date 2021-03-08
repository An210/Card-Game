package view.model;

import java.util.HashMap;

import model.interfaces.Player;

public class PlayerState {
	private Player selectedPlayer;
	private int score;
	private HashMap<Player, Integer> previousP;
	private HashMap<Player, String> beingDealt;

	public PlayerState(Player selectedPlayer) {
		this.selectedPlayer = selectedPlayer;
		this.score = 0;
		this.previousP = new HashMap<Player, Integer>();
		this.beingDealt = new HashMap<Player, String>();

	}

	public boolean checkBet() {
		if (this.selectedPlayer != null) {
			return this.selectedPlayer.setBet(this.selectedPlayer.getBet());
		}
		return false;
	}
	// Return true if the chosen player placed bet

	public void setPreviousPoint(Player player, int previousPoint) {
		this.previousP.put(player, previousPoint);

	}

	public int getPreviousPoint(Player player) {
		return this.previousP.get(player);
	}
	// Setter and getter of previous points from a selected player

	public boolean added() {
		if (selectedPlayer != null && !this.selectedPlayer.getPlayerId().equalsIgnoreCase("0")) {
			return true;
		} else
			return false;
	}
	// Check whether the player exist, enable menu item purposes

	public Player getSelectedPlayer() {
		return this.selectedPlayer;
	}
	// Return the player

	// ---------------------------------------------------------------------------
//	public void setScore(int score) {
//		this.score = score;
//	}
//
//	public int getScore() {
//		return this.score;
//
//	}
	// ---------------------------------------------------------------------------

	public void setBeingDealt(Player player, String yep) {
		this.beingDealt.put(player, yep);
	}

	public String getBeingDealt(Player player) {
		return this.beingDealt.get(player);

	}
	//Setter and getter of player state (being dealt -> disabling Deal item in the menu purpose
}
