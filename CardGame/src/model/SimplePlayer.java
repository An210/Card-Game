package model;

import model.interfaces.GameEngine;
import model.interfaces.Player;

public class SimplePlayer implements Player {
	private String id;
	private int initialPoints;
	private String name;
	private int bet;
	private int result;

	public SimplePlayer(String id, String name, int initialPoints) {
		this.id = id;
		this.name = name;
		this.initialPoints = initialPoints;
		this.bet = 0;
		this.result = 0;
	}

	@Override
	public String getPlayerName() {
		return this.name;
	}

	@Override
	public void setPlayerName(String playerName) {
		this.name = playerName;

	}

	@Override
	public int getPoints() {
		return this.initialPoints;
	}

	@Override
	public void setPoints(int points) {
		this.initialPoints = points;
	}

	@Override
	public String getPlayerId() {
		return this.id;
	}

	@Override
	public boolean setBet(int bet) {
		boolean checkBet = false;
		if (bet > 0 && getPoints() >= bet) {
			this.bet = bet;
			checkBet = true;
		}
		return checkBet;
	}

	@Override
	public int getBet() {
		return this.bet;
	}

	@Override
	public void resetBet() {
		this.bet = 0;

	}

	@Override
	public int getResult() {
		return this.result;
	}

	@Override
	public void setResult(int result) {
		this.result = result;
	}

	@Override
	public boolean equals(Player player) {
		boolean checkEquals = false;
		if (getPlayerId().equalsIgnoreCase(player.getPlayerId())) {
			checkEquals = true;
		}
		return checkEquals;
	}

	@Override
	public int hashCode() {
		return this.id.hashCode();
	}

	@Override
	public boolean equals(Object player) {
		if (player instanceof SimplePlayer) {
			{
				SimplePlayer p = (SimplePlayer) player;
				return (this.id.equals(p.id));
			}
		}
		return true;
	}

	@Override
	public int compareTo(Player player) {
		int res = this.id.compareTo(player.getPlayerId());
		return res;
	}

	public String toString() {
		return String.format("Player: %s", this.name);
	};

}
