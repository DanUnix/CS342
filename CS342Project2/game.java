// Author: Daniel Pulley
// Title: game.java
// Description: This class contains the game logic to minesweeper. To varies to Initializing the grid which in this case is a 2D-Array of square objects


import java.util.*;
import javax.swing.*;
import java.io.*;
import java.awt.*;

public class game
{
		// initialize a new 2D-Array of squares
        private square[][] grid = new square[10][10];
		private Random rand = new Random();         	
		public game()
		{
			initGrid();
			displayMines();
			isNear();
			gridTest();
			
		}

		// initGrid Function - This Function creates a 2d array of square objects  that will be used for the game
		public void initGrid()
		{
			// Initializing new Grid    
        	System.out.println("Initializing New Squares");
        	for(int i = 0; i < grid.length; i++)
            	for(int j = 0; j < grid[i].length; j++)
                	grid[i][j] = new square();
			setMines();	
		}
		
		// setMines Function - This function is to randomly place 10 mines on the grid of squares
		public void setMines()
		{
			int random1,random2; 
			for(int i = 0; i < grid.length; i++){
				random1 = rand.nextInt(8) + 1;
				random2 = rand.nextInt(8) + 1;;
				grid[random1][random2].isMine = true;	
			}
		}

			
		// isNear Function - This function takes the current grid position and checks the 8 positions that surround it for Mines
		// If one of those spots is a mine then the integer near is the square object is incremented marking how many mines are 
		// around the current square
		public void isNear()
		{	
			for(int i = 0; i < grid.length; i++){
				for(int j = 0; j < grid[i].length; j++){
					
					// Above the current square
					if(i > 0){
						if(grid[i-1][j].isMine == true)
							grid[i][j].near++;	
					}
					// Below the current square
					if(i < 9){
						if(grid[i+1][j].isMine == true)
							grid[i][j].near++;
					}
					// Left to current square
					if(j > 0){
						if(grid[i][j-1].isMine == true)
							grid[i][j].near++;
					}
					// Right to current square
					if(j < 9){
						if(grid[i][j+1].isMine == true)
							grid[i][j].near++;
					}
					// Upper Right to current square
					if(i < 9 && j < 9){
						if(grid[i+1][j+1].isMine == true)
							grid[i][j].near++;			
					}
					// Upper left to current square
					if(i < 9 && j > 0){
						if(grid[i+1][j-1].isMine == true)
							grid[i][j].near++;
					}
					// Bottom right to current square
					if(i > 0 && j < 9){
						if(grid[i-1][j+1].isMine == true)
							grid[i][j].near++;
					}
					// Bottom left to current square
					if(i > 0 && j > 0){
						if(grid[i-1][j-1].isMine == true)
							grid[i][j].near++;
					}
				}
			}	
		}		
	
		// Sanity Checkers

		// Display the mines in the grid
		public void displayMines()
		{
			// Display grid isMine elements as sanity check
        	System.out.println("Printing out isMine Values");
        	for(int i = 0; i < grid.length; i++)
            	for(int j = 0; j < grid[i].length; j++)
                	System.out.println("Grid["+i+"]"+"["+j+"]: "+grid[i][j].isMine);

		}
		
		public void gridTest()
		{
			// take a random point on the grid and display it in a grid form
			// Above square = grid[i][j-1]
			// Below square = grid[i][j+1]
			// Left square = grid[i-1][j]
			// Right square = grid[i+1][j]
			// Upper Left square = grid[i-1][j-1]
			// Upper Right square = grid[i+1][j-1]
			// Bottom left square = grid[i-1][j+1]
			// Bottom Right square = grid[i+1][j+1]
			// Using grid[5][5] as the center 
			System.out.println("\n Text Display of Grid[5][5]" + "\n" + "--------------------------");
			System.out.println(grid[5-1][5-1].isMine + "|" + grid[5][5-1].isMine + "|" + grid[5+1][5-1].isMine);
			System.out.println("-----------------");
			System.out.println(grid[5-1][5].isMine + "|" + grid[5][5].isMine + "|" + grid[5+1][5].isMine);
			System.out.println("-----------------");
			System.out.println(grid[5-1][5+1].isMine + "|" + grid[5][5+1].isMine + "|" + grid[5+1][5+1].isMine + "\n");
			
			// Sanity Check isNear values
			
			System.out.println("\n Text Display of Grid[5][5]" + "\n" + "--------------------------");
			System.out.println(grid[5-1][5-1].near + "|" + grid[5][5-1].near + "|" + grid[5+1][5-1].near);
			System.out.println("-----------------");
			System.out.println(grid[5-1][5].near + "|" + grid[5][5].near + "|" + grid[5+1][5].near);
			System.out.println("-----------------");
			System.out.println(grid[5-1][5+1].near + "|" + grid[5][5+1].near + "|" + grid[5+1][5+1].near + "\n");
			
		}
	
		public void flagTest()
		{
			// Flag Test
        	System.out.println("Set all Square Flags to M");
        	for(int i = 0; i < grid.length; i++)
            	for(int j = 0; j < grid[i].length; j++)
                	grid[i][j].setFlag("M");

		}
		
		
		
		public void displayFlags()
		{
			// Display grid isMine elements as sanity check
	        System.out.println("Printing out isMine Values");
        	for(int i = 0; i < grid.length; i++)
            	for(int j = 0; j < grid[i].length; j++)
                	System.out.println("Grid["+i+"]"+"["+j+"]: "+grid[i][j].isMine);

		}
}

