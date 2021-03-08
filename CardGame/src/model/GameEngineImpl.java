package model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import model.interfaces.PlayingCard.Suit;
import model.interfaces.PlayingCard.Value;
import view.interfaces.GameEngineCallback;

public class GameEngineImpl implements GameEngine {
	private Player[] players;
	private GameEngineCallback[] gecbs;
	private int currentGameEngineCallback;
	private int currentPlayers;
	private HashMap<Player, String> playerScore;
	private static final int MAX_PLAYERS = 100;
	private int check;
	private Deque<PlayingCard> currentDeck;

	public GameEngineImpl() {
		this.currentPlayers = 0;
		this.currentGameEngineCallback = 0;
		this.players = new Player[this.MAX_PLAYERS];
		this.gecbs = new GameEngineCallback[2];
		this.playerScore = new HashMap<Player, String>();
		this.check = 0;
		this.currentDeck = this.getShuffledHalfDeck();
	}

	// --------------------------------------------------------Player------------------------------------------------------------
	@Override
	public void dealPlayer(Player player, int delay) throws IllegalArgumentException {

		try {

			helper(1, player, delay);
			System.out.println();

		} catch (NoSuchElementException e) {
			System.out.print("qqqqq");
			this.currentDeck = getShuffledHalfDeck();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	private void helper(int id, Player player, int delay) throws InterruptedException {
		System.out.println(this.currentDeck);
		int score = 0;
		while (score < GameEngine.BUST_LEVEL) {
			PlayingCard dealtCard = this.currentDeck.removeFirst();
			if ((score + dealtCard.getScore()) < GameEngine.BUST_LEVEL) {
				if (id == 1) {
					if (score > 0) {
						TimeUnit.MILLISECONDS.sleep(delay);
					}
					this.gecbs[0].nextCard(player, dealtCard, this);
				} else if (id == 2) {
					TimeUnit.MILLISECONDS.sleep(delay);
					this.gecbs[0].nextHouseCard(dealtCard, this);
				}
			} else {

				if ((score + dealtCard.getScore()) > GameEngine.BUST_LEVEL) {
					if (id == 1) {
						TimeUnit.MILLISECONDS.sleep(delay);
						this.gecbs[0].bustCard(player, dealtCard, this);
					} else if (id == 2) {
						this.gecbs[0].houseBustCard(dealtCard, this);
					}

				}
				this.playerScore.put(player, "yep");
				int x = 0;
				for (Player p : players) {
					if (x < this.currentPlayers) {

						if (this.playerScore.get(p) != null) {
							if (this.playerScore.get(p).equalsIgnoreCase("yep")) {
								this.check++;
							} else {
								this.check--;
							}
						} else {
							this.check--;
						}

					}
					x++;
				}
				if (id == 1) {
					player.setResult(score);
					this.gecbs[0].result(player, player.getResult(), this);
					if (this.check == this.currentPlayers) {
						dealHouse(1000);
					}
				} else if (id == 2) {
					for (Player pl : players) {
						if (pl != null) {
							applyWinLoss(pl, score);
						}
					}
					this.gecbs[0].houseResult(score, this);

					for (Player pl : players) {
						if (pl != null) {

							pl.resetBet();

						}
					}
				}
			}
			score = score + dealtCard.getScore();
		}
		System.out.println();

	}
	// private helper

	@Override
	public void dealHouse(int delay) throws IllegalArgumentException {
		try {

			helper(2, null, delay);
			System.out.println(getAllPlayers());
		} catch (NoSuchElementException e) {
			getShuffledHalfDeck();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void applyWinLoss(Player player, int houseResult) {
		if (player.getResult() > houseResult) {
			player.setPoints(player.getPoints() + player.getBet());
		} else if (player.getResult() < houseResult) {
			player.setPoints(player.getPoints() - player.getBet());
		}
	}

	@Override
	public void addPlayer(Player player) {
		boolean check = true;
		int x = 0;
		while (x < this.currentPlayers) {
			if (this.players[x].getPlayerId().equals(player.getPlayerId())) {
				this.players[x] = new SimplePlayer(player.getPlayerId(), player.getPlayerName(), player.getPoints());
				check = false;
			}
			x++;
		}
		if (this.currentPlayers == 0 || check) {
			this.players[this.currentPlayers] = player;
			this.currentPlayers++;
		}
	}

	@Override
	public Player getPlayer(String id) {
		Player thisPlayer = null;
		if (id != null) {
			for (Player player : players) {
				if (player.getPlayerId().equalsIgnoreCase(id)) {
					thisPlayer = player;
				}
			}
		}
		return thisPlayer;
	}

	@Override
	public boolean removePlayer(Player player) {
		boolean removedPlayer = false;
		Player[] newP = new Player[this.currentPlayers];
		int x = 0;
		for (Player playerr : players) {
			if (playerr != null && playerr.equals(player)) {
				this.currentPlayers--;
				removedPlayer = true;
			} else if (playerr != null && !playerr.equals(player)) {
				newP[x] = playerr;

				x++;
			}
		}
		this.players = newP;
		return removedPlayer;
	}

	@Override
	public boolean placeBet(Player player, int bet) {
		boolean betPlaced = false;
		player.setBet(bet);
		if (player.setBet(bet)) {
			betPlaced = true;
		}
		return betPlaced;
	}

	@Override
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
		this.gecbs[this.currentGameEngineCallback] = gameEngineCallback;
		this.currentGameEngineCallback++;
	}

	@Override
	public boolean removeGameEngineCallback(GameEngineCallback gameEngineCallback) {
		boolean exsited = false;
		for (GameEngineCallback gecb : gecbs) {
			if (gecb.equals(gameEngineCallback)) {
				exsited = true;
				gecb = this.gecbs[this.currentGameEngineCallback--];
				this.gecbs = new GameEngineCallback[this.currentGameEngineCallback];
			}
		}
		return exsited;
	}

	@Override
	public Collection<Player> getAllPlayers() {
		ArrayList<Player> playerCol = new ArrayList<Player>();
		for (Player player : players) {
			if (player != null) {
				playerCol.add(player);
			}
		}
		Collections.sort(playerCol);

		return playerCol;
	}

	@Override
	public Deque<PlayingCard> getShuffledHalfDeck() {
		ArrayList<PlayingCard> getShuffledHalfDeck = new ArrayList<PlayingCard>();
		int score = 0;
		for (Value value : Value.values()) {
			for (Suit suit : Suit.values()) {
				if (value.equals(Value.EIGHT)) {
					score = 8;
				} else if (value.equals(Value.NINE)) {
					score = 9;
				} else if (value.equals(Value.TEN) || value.equals(Value.JACK) || value.equals(Value.QUEEN)
						|| value.equals(Value.KING)) {
					score = 10;
				} else if (value.equals(Value.ACE)) {
					score = 11;
				}
				getShuffledHalfDeck.add(new PlayingCardImpl(suit, value, score));
			}
		}
		Collections.shuffle(getShuffledHalfDeck);
		Deque<PlayingCard> deque = new ArrayDeque<PlayingCard>(getShuffledHalfDeck);
		return deque;

	}

}
