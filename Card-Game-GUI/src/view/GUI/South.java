package view.GUI;

import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class South extends JPanel {
	private JLabel j;

	public South(North north) {
		this.j = new JLabel();
		Border border = BorderFactory.createTitledBorder("Status");
		this.setBorder(border);
		add(this.j);
	}

	public void updateStatus(String status) {
		j.setText(status);
	}
}
