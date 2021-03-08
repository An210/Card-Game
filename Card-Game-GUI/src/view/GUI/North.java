package view.GUI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

import controller.AddPlayerController;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.Frame;
import view.model.PlayerState;

public class North extends JToolBar {

	private South south;
	private Table tb;
	private Frame frame;
	private DropDown dropDown;
	private PlayerState playerState;
	private Menu mn;

	public final static String SELECTED_TITLE = "SELECTED_COLOUR";

	public North(Table tb, Frame frame, GameEngine gameEngine) {

		this.tb = tb;
		this.frame = frame;
		JToolBar north = new JToolBar();
		this.dropDown = new DropDown(this.frame);
		this.mn = new Menu(this.tb, dropDown, this.frame, gameEngine);
		this.south = south;

		north.add(this.mn);
		north.add(dropDown);

		add(north);
	}

	public DropDown getDropDown() {
		return this.dropDown;
	}
	
	public Menu getMenu() {
		return this.mn;
	}

}
