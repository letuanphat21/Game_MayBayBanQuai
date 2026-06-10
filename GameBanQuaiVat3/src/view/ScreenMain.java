package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class ScreenMain extends JFrame {

	public ScreenMain() {
		setTitle("Game Bắn quái vật");
		setSize(800, 600);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

}