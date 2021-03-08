package controller;

import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JTable;

import view.Frame;
import view.GUI.Table;

public class NewHeight implements PropertyChangeListener {
	private Table tb;
	private Frame frame;
	private JTable j;

	public NewHeight(Frame frame, Table tb, JTable j) {
		this.tb = tb;
		this.frame = frame;
		this.j = j;
	}
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		int x = this.tb.getJ().getRowHeight() * this.tb.getJ().getRowCount();
		int y = frame.getHeight() / 3;
		int i = frame.getHeight() / 4;
		int chosen = 0;
		if ((x*10) < y) {
			chosen = i;
		} else {
			chosen = y;
		}
		this.tb.setMaximumSize(new Dimension(frame.getWidth(), chosen));
		this.tb.setPreferredSize(new Dimension(frame.getWidth(), chosen));
	}

}
