package game;

import java.awt.Color;
import java.awt.Graphics2D;

public class Square {

	private int size = 40;
	
	private int xPos;
	private int yPos;
	
	private boolean isEmpty = true;
	
	public Square(int xPos, int yPos){
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	
	public void update(){
		
		
	}
	
	public void render(Graphics2D g){
		
		if(isEmpty){
			//inside
			g.setColor(Color.gray);
			g.fillRect(xPos, yPos, size, size);
			
			//border
			g.setColor(Color.white);
			g.drawRect(xPos, yPos, size, size);
		}
		
	}
	
	
	public boolean isEmpty(){
		return isEmpty;
	}
	
}
