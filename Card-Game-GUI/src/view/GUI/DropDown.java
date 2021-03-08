package view.GUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.Collection;

import javax.swing.JComboBox;
import javax.swing.JList;

import controller.ChangeController;
import controller.DropDownController;
import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.Frame;
import view.model.PlayerState;

public class DropDown extends JComboBox {

	private JComboBox<Player> dropDown;
	private Frame frame;
	private Collection<Player> players;
	private GameEngine gameEngine;
	private DropDownController r;

	public DropDown(Frame frame) {
		dropDown = new JComboBox<Player>();
		dropDown.addItem(new SimplePlayer("0", "House", 0));
		dropDown.setRenderer(new DropDownController());
		this.frame = frame;
		r = new DropDownController();
		setLayout(new BorderLayout());
		add(dropDown);
	}

	public void updateDropDown(GameEngine gameEngine) {
		dropDown.removeAllItems();

		this.gameEngine = gameEngine;
		this.players = gameEngine.getAllPlayers();
		int u = 0;
		for (Player player : players) {
			dropDown.addItem(player);
			dropDown.setSelectedItem(player.getPlayerName());

		}
		dropDown.addItem(new SimplePlayer("0", "House", 0));
		
	}

	public void addController() {

		dropDown.addActionListener(new ChangeController(this, this.frame, gameEngine));

	}

	public Player selectedPlayer() {
		return (Player) dropDown.getSelectedItem();
	}

	public Component getListCellRendererComponent(JList<Player> list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {

		return list;

	}
}
