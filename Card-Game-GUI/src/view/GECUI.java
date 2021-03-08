package view;

import javax.swing.SwingUtilities;

import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.interfaces.GameEngineCallback;

public class GECUI implements GameEngineCallback {
	private Frame frame;
	private int firstTime;

	public GECUI(Frame frame) {
		this.frame = frame;
		this.firstTime = 0;
	}

	@Override
	public void nextCard(Player player, PlayingCard card, GameEngine engine) {

		helper(player, card, engine, 1);

	}

	private void helper(Player player, PlayingCard card, GameEngine engine, int cardType) {
		class MyRunnable implements Runnable {
			private Frame frame;
			private PlayingCard card;
			private Player player;
			private int cardType;

			public MyRunnable(Frame frame, PlayingCard card, int cardType, Player player) {
				this.frame = frame;
				this.card = card;
				this.cardType = cardType;
				this.player = player;
			}

			@Override

			public void run() {
				this.frame.getTable().updateTable(engine, this.frame);
				if (this.cardType == 1) {
					this.frame.getPlayerStatus().setPreviousPoint(player, this.player.getPoints());
					this.frame.getTable().getPreviousPoint(this.frame.getPlayerStatus().getPreviousPoint(player));

				}
				if (cardType == 1) {
					this.frame.getPlayerStatus().setBeingDealt(player, "yep");
				} else if (cardType == 2)
					this.frame.getPlayerStatus().setBeingDealt(player, "no");
				this.frame.getCP().getPlayer(this.player);
				this.frame.getCP().addCard();
				this.frame.getCP().updateCardPanel(player, this.card, cardType);
				if (this.player.equals(this.frame.getPlayerStatus().getSelectedPlayer())) {
					this.frame.getCP().repaint();
				}
			}
		}
		Runnable r = new MyRunnable(this.frame, card, cardType, player);
		SwingUtilities.invokeLater(r);

	}

	@Override
	public void bustCard(Player player, PlayingCard card, GameEngine engine) {
		helper(player, card, engine, 2);
	}

	@Override
	public void result(Player player, int result, GameEngine engine) {
		// TODO Auto-generated method stub

	}

//------------------------------------------------------------------------------------------House-------------------------------------------------
	@Override
	public void nextHouseCard(PlayingCard card, GameEngine engine) {
		helper1(card, engine, 1);
//		class MyRunnable implements Runnable {
//			private Frame frame;
//			private PlayingCard card;
//			private Player player;
//			private int firstTime;
//
//			public MyRunnable(Frame frame, PlayingCard card, Player player, int firstTime) {
//				this.frame = frame;
//				this.card = card;
//				this.firstTime = firstTime;
//				this.player = player;
//			}
//
//			@Override
//
//			public void run() {
//
//				if (this.firstTime == 0) {
//					this.frame.getCP().reset(this.player, this.frame);
//				}
//				this.frame.getCP().getPlayer(this.player);
//				this.frame.getCP().addCard();
//				this.frame.getCP().updateCardPanel(player, this.card, 1);
//				this.frame.getCP().repaint();
//				this.firstTime++;
//			}
//
//		}
//		Runnable r = new MyRunnable(this.frame, card, new SimplePlayer("0", "House", 0), this.firstTime);
//		this.firstTime++;
//		SwingUtilities.invokeLater(r);
	}

	public void helper1(PlayingCard card, GameEngine engine, int id) {
		
		
		class MyRunnable implements Runnable {
			private Frame frame;
			private PlayingCard card;
			private Player player;
			private int firstTime;

			public MyRunnable(Frame frame, PlayingCard card, Player player, int firstTime) {
				this.frame = frame;
				this.card = card;
				this.firstTime = firstTime;
				this.player = player;
			}

			@Override

			public void run() {
				if (this.firstTime == 0) {
					this.frame.getCP().reset(this.player, this.frame);
				}
				this.frame.getCP().getPlayer(this.player);
				this.frame.getCP().addCard();

				this.frame.getCP().updateCardPanel(player, this.card, id);
				if (id == 2)
					this.frame.getTable().updateTable(engine, this.frame);
				this.frame.getCP().repaint();
				this.firstTime++;

			}

		}
		Runnable r = new MyRunnable(this.frame, card, new SimplePlayer("0", "House", 0), this.firstTime);
		this.firstTime++;
		SwingUtilities.invokeLater(r);
		

	}

	@Override
	public void houseBustCard(PlayingCard card, GameEngine engine) {
		helper1(card, engine, 2);
//		class MyRunnable implements Runnable {
//			private Frame frame;
//			private PlayingCard card;
//			private Player player;
//			private int firstTime;
//
//			public MyRunnable(Frame frame, PlayingCard card, Player player, int firstTime) {
//				this.frame = frame;
//				this.card = card;
//				this.firstTime = firstTime;
//				this.player = player;
//			}
//
//			@Override
//
//			public void run() {
//				if (this.firstTime == 0) {
//					this.frame.getCP().reset(this.player, this.frame);
//				}
//				this.frame.getCP().getPlayer(this.player);
//				this.frame.getCP().addCard();
//				this.frame.getCP().updateCardPanel(player, this.card, 2);
//				this.frame.getTable().updateTable(engine, this.frame);
//				this.frame.getCP().repaint();
//				this.firstTime++;
//
//			}
//
//		}
//		Runnable r = new MyRunnable(this.frame, card, new SimplePlayer("0", "House", 0), this.firstTime);
//		this.firstTime++;
//		SwingUtilities.invokeLater(r);

	}

	@Override
	public void houseResult(int result, GameEngine engine) {
		// TODO Auto-generated method stub

	}

}
