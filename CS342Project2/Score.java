import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.io.*;

public class Score
{
  int score;
  int mines;
  
  //constructor for score keeping
  public Score(){
    
    score = 0;
    mines = 10 ;
    
  }
  
  // to update score
  public void updateScore(){
    
    score = score++;
    
  }
  
  // returns the current score in game
  public int getScore(){
    
    return score;
  }
  
  // updates the mine info
  public void updateMine(){
    
    mines--;
    
  }
  // updates the mine info
  public void AddMine(){
    
    mines++;
    
  }
  
  // returns the current mines found
  public int getMine(){
    
    return mines;
  }
  
  
  
}