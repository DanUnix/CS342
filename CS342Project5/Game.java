package game;

import javax.swing.JFrame;

public class Game {
	
	private static MouseManager mm = new MouseManager();

	public static void main(String[] args) {
		JFrame window = new JFrame("First Game");
		window.setDefaultCloseOperation(3);
		window.setResizable(false);
		window.setLocation(300, 50);

		window.addMouseListener(mm);
		window.addMouseMotionListener(mm);

		window.setContentPane(new GamePanel());

		window.pack();
		window.setVisible(true);
	}
}
