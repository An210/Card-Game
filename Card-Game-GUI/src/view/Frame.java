package view;

import java.awt.BorderLayout;
import java.awt.Dimension;



import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import controller.FrameResized;
import model.GameEngineImpl;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.GUI.CardPanel;
import view.GUI.North;
import view.GUI.South;
import view.GUI.Table;
import view.model.PlayerState;

public class Frame extends JFrame {
	private GameEngine gameEngine = new GameEngineImpl();
	private PlayerState playerState;
	private North north;
	private CardPanel cp;
	private Table tb;
	private South south;

	public Frame() {
		JPanel frame = new JPanel();
		this.cp = new CardPanel();

		setBounds(500, 100, 900, 600);
		this.tb = new Table(this);
		this.north = new North(tb, this, this.gameEngine);

		this.playerState = null;
		this.south = new South(north);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JTable listPane = new JTable();
		listPane.add(cp);
		listPane.add(tb);
		this.addComponentListener(new FrameResized(this, this.tb, this.tb.getJ()));
		this.addWindowStateListener(new FrameResized(this, this.tb, this.tb.getJ()));
		listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));
		this.setMinimumSize(new Dimension(800, 500));

		frame.setLayout(new BorderLayout(2, 2));
		frame.add(north, BorderLayout.NORTH);
		frame.add(south, BorderLayout.SOUTH);

		frame.add(listPane, BorderLayout.CENTER);

		setLayout(new BorderLayout());
		add(frame, BorderLayout.CENTER);
		setVisible(true);
		this.gameEngine.addGameEngineCallback(new GECUI(this));
	}

	public South getSouth() {
		return this.south;
	}
	
	public PlayerState getPlayerStatus() {
		return this.playerState;
	}

	public North getNorth() {
		return this.north;
	}

	public CardPanel getCP() {
		return this.cp;
	}

	public void setCardPanel(CardPanel newCP) {
		this.cp = newCP;
	}

	public void setPlayerStatus(Player selectedPlayer) {
		this.playerState = new PlayerState(selectedPlayer);
	}

	public Table getTable() {
		return this.tb;
		
	}
	
	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Frame();
			}
		});

	}

}
