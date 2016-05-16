package game;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;



public class MouseManager implements MouseListener,  MouseMotionListener {

	private static int mouseMovedX, mouseMovedY;
	public static Point mouse;
	public static boolean pressed;

	public static BufferedImage unpressedImage;
	public static BufferedImage pressedImage;
	
	
	public void update(){
		mouse = new Point(mouseMovedX, mouseMovedY);

	}
	
	public void render(Graphics2D g){
		

	}
	
	
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1){
			pressed = true;
		}
		
	}

	
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1)
			pressed = false;
		
		
	}

	

	public void mouseDragged(MouseEvent e) {
		mouseMovedX = e.getX();
		mouseMovedY = e.getY();
		
	}

	public void mouseMoved(MouseEvent e) {
		mouseMovedX = e.getX();
		mouseMovedY = e.getY();
		
	}

	public void mouseClicked(MouseEvent e) { }
	public void mouseEntered(MouseEvent e) { }
	public void mouseExited(MouseEvent e) { }
	
}
