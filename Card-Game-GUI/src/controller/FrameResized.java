package controller;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

import javax.swing.JTable;

import view.Frame;
import view.GUI.Table;

public class FrameResized extends ComponentAdapter implements WindowStateListener {
	private Table tb;
	private Frame frame;
	private JTable j;

	public FrameResized(Frame frame, Table tb, JTable j) {
		this.tb = tb;
		this.frame = frame;
		this.j = j;
	}

	public void componentResized(ComponentEvent evt) {
		helper();
	}

	@Override
	public void windowStateChanged(WindowEvent e) {
		helper();
	}

	private void helper() {
		int x = this.tb.getJ().getRowHeight() * this.tb.getJ().getRowCount();
		int y = frame.getHeight() / 3;
		int i = frame.getHeight() / 4;
		int chosen = 0;
		if (x < y) {
			chosen = i;
		} else {
			chosen = y;
		}
		this.tb.setMaximumSize(new Dimension(frame.getWidth(), chosen));
		this.tb.setPreferredSize(new Dimension(frame.getWidth(), chosen));

	}
}
