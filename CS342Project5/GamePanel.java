package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable, KeyListener {
	public static int WIDTH = 640;
	public static int HEIGHT = 960;
	private Thread thread;
	private boolean running;
	private BufferedImage image;
	private Graphics2D g;
	private int FPS = 60;
	private double averageFPS;
	
	private boolean gameStart = false;
	
	//mouse manager
	private MouseManager mm;

	// next piece display
	private Rectangle nextPieceRect;
	// start button
	private Rectangle startButton;
	// quit button
	private Rectangle quitButton;
	// about button
	private Rectangle aboutButton;

	// help button
	private Rectangle helpButton;
	private Grid playGrid;

	public GamePanel() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		requestFocus();
	}

	public void addNotify() {
		super.addNotify();
		if (thread == null) {
			thread = new Thread(this);
			thread.start();
		}
		addKeyListener(this);
	}

	public void run() {
		running = true;

		image = new BufferedImage(WIDTH, HEIGHT, 1);
		g = ((Graphics2D) image.getGraphics());

		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		g.setFont(new Font("Impact", 1, 20));

		long totalTime = 0L;

		int frameCount = 0;
		int maxFrameCount = 60;

		long targetTime = 1000 / FPS;

		init();

		while (running) {
			long startTime = System.nanoTime();

			update();
			render();
			draw();

			long URDTimeMillis = (System.nanoTime() - startTime) / 1000000L;

			long waitTime = targetTime - URDTimeMillis;
			try {
				Thread.sleep(waitTime);
			} catch (Exception localException) {
			}
			totalTime += System.nanoTime() - startTime;
			frameCount++;
			if (frameCount == maxFrameCount) {
				averageFPS = (1000.0D / (totalTime / frameCount / 1000000L));
				frameCount = 0;
				totalTime = 0L;
			}
		}
	}

	private void init() {

		playGrid = new Grid();
		mm = new MouseManager();
		
		nextPieceRect = new Rectangle(480, 120, 120, 120);
		startButton   = new Rectangle( 40, 20, 110, 40);
		quitButton    = new Rectangle(190, 20, 110, 40);
		aboutButton   = new Rectangle(340, 20, 110, 40);
		helpButton    = new Rectangle(490, 20, 110, 40);
	}

	private void update() {
		
		//update the mouse
		mm.update();
		
	}

	private void render() {

		// draw background
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		
		//foreground stuff
		g.setColor(Color.white);
		
		// grid
		playGrid.render(g);

		// next piece
		g.drawString("Next Piece", nextPieceRect.x, nextPieceRect.y - 10);
		g.drawRect(nextPieceRect.x, nextPieceRect.y, nextPieceRect.width, nextPieceRect.height);

		
		// start button
		if(startButton.contains(mm.mouse)){
			g.setColor(Color.white);
			g.fillRect(startButton.x, startButton.y, startButton.width, startButton.height);
			
			g.setColor(Color.black);
			g.drawString("Start", startButton.x + 25, startButton.y + 28);
		}else{
			g.setColor(Color.gray.brighter());
			g.fillRect(startButton.x, startButton.y, startButton.width, startButton.height);
			g.setColor(Color.black);
			g.drawString("Start", startButton.x + 25, startButton.y + 28);
		}
		// quit button
		if(quitButton.contains(mm.mouse)){
			g.setColor(Color.white);
			g.fillRect(quitButton.x, quitButton.y, quitButton.width, quitButton.height);
			g.setColor(Color.black);
			g.drawString("Quit", quitButton.x + 30, quitButton.y + 28);
			
			//quit the program
			if(mm.pressed){
				System.exit(1);
			}
			
		}else{
			g.setColor(Color.gray.brighter());
			g.fillRect(quitButton.x, quitButton.y, quitButton.width, quitButton.height);
			g.setColor(Color.black);
			g.drawString("Quit", quitButton.x + 30, quitButton.y + 28);
		}
		// about button
		if(aboutButton.contains(mm.mouse)){
			g.setColor(Color.white);
			g.fillRect(aboutButton.x, aboutButton.y, aboutButton.width, aboutButton.height);
			g.setColor(Color.black);
			g.drawString("About", aboutButton.x + 22, aboutButton.y + 28);
			
		}else{
			g.setColor(Color.gray.brighter());
			g.fillRect(aboutButton.x, aboutButton.y, aboutButton.width, aboutButton.height);
			g.setColor(Color.black);
			g.drawString("About", aboutButton.x + 22, aboutButton.y + 28);
		}
		// help button
		if(helpButton.contains(mm.mouse)){
			g.setColor(Color.white);
			g.fillRect(helpButton.x, helpButton.y, helpButton.width, helpButton.height);
			g.setColor(Color.black);
			g.drawString("Help", helpButton.x + 30, helpButton.y + 28);
		}else{
			g.setColor(Color.gray.brighter());
			g.fillRect(helpButton.x, helpButton.y, helpButton.width, helpButton.height);
			g.setColor(Color.black);
			g.drawString("Help", helpButton.x + 30, helpButton.y + 28);
		}


		
		//Score
		g.setColor(Color.white);
		g.drawString("Score", 190, 910);
	}

	private void draw() {
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0, null);
		g2.dispose();
	}

	public void keyTyped(KeyEvent key) {
	}

	public void keyPressed(KeyEvent key) {

	}

	public void keyReleased(KeyEvent key) {

	}
	
}
