
package view.GUI;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Collection;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.Frame;

public class Table extends JPanel {
	private JTable j;
	private DefaultTableModel model;
	private JScrollPane jsp;
	private int firstHeight;
	private int compare;

	public Table(Frame frame) {
		this.j = new JTable(
				new DefaultTableModel(null, new Object[] { "Id", "Name", "Points", "Bet", "Result", "Win/Loss" }));

		this.model = (DefaultTableModel) this.j.getModel();
		this.jsp = new JScrollPane(this.j);
		this.firstHeight = this.j.getRowHeight() * 5;
		Dimension firstSize = new Dimension(frame.getWidth(), firstHeight);
		Border blackline = BorderFactory.createTitledBorder("Summary Panel");

		this.setMaximumSize(new Dimension(frame.getWidth(), frame.getHeight() / 3));

		this.setPreferredSize(firstSize);
		this.setLayout(new GridLayout());
		this.setBorder(blackline);
		this.j.setEnabled(false);
		this.add(jsp);

	}

	public JTable getJ() {
		return this.j;
	}

	public int getH() {
		return this.firstHeight;
	}

	public void updateTable(GameEngine gameEngine, Frame frame) {
		model.setRowCount(0);
		Collection<Player> players = gameEngine.getAllPlayers();
		String result = "";

		for (Player player : players) {

			int newPoint = player.getPoints();
		
			if (player != null) {
				if (compare == newPoint) {
					result = "Drawn";
				} else if (compare > newPoint) {
					result = "Loose";
				} else {
					result = "Win";
				}
			}
			this.model.addRow(new Object[] { player.getPlayerId(), player.getPlayerName(), player.getPoints(),
					player.getBet(), player.getResult(), result });

		}

		int x = this.j.getRowHeight() * this.j.getRowCount();
		int y = frame.getHeight() / 3;
		int i = frame.getHeight() / 4;
		int chosen = 0;
		if ((x * 10) < y) {
			chosen = i;
		} else {
			chosen = y;
		}
		this.setMaximumSize(new Dimension(frame.getWidth(), chosen));
		this.setPreferredSize(new Dimension(frame.getWidth(), chosen));
	}

	public void getPreviousPoint(int compare) {
		this.compare = compare;
	}

}
