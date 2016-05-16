package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Grid {

	public static ArrayList<Square> grid;
	private int squareSize = 40;
	private int xPos = 40;
	private int yPos = 80;
	
	public Grid(){
		
		grid = new ArrayList<Square>();
		
		for(int y=0; y < 20; y++){
			for(int x=0; x < 10; x++){
				
				grid.add(new Square( xPos+(x*squareSize), yPos+(y*squareSize) ));
				
			}//inner for
		}//outer for
	
	}//end constructor
	
	
	public void update(){
		
		
	}
	
	public void render(Graphics2D g){
		
		//draw border
		g.setColor(Color.white);
		g.drawRect(xPos, yPos, squareSize*10, squareSize*20);
		
		
		//draw all the squares
		for(Square square: grid){
			square.render(g);
		}
	}
}
