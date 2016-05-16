// COMPILED BY : DANIEL PULLEY & HENVY PATEL
// Title: square.java
// Description: CLASS : square - These individual squares act as the buttons on the grid which have action listener 
//                               implemented in it to work with left and right clicks.

import java.util.*;
import javax.swing.*;

public class square extends JButton
{
 // isMine is a boolean lets 
 public boolean isMine;
 // Flag that contains right click options
 public String flag;
 //for keeping count of the clicks pressed on the button 
 public int rclick;
 public int lclick;
 // integer indicating how many mines are near an object instance
 public int near;
 // to save the location in the grid
 public int x;
 public int y;
 
 
 public square()
 {
   this.x = 0;
   this.y = 0;
  
  // Initialize isMine to false
  this.isMine = false;   
  // Initialize the clicks on the buttons
  this.rclick = 0;
  this.lclick = 0;
  this.flag = " ";
  // intialize near to 0 
  this.near = 0;
  
 }

 // randomBoolean function: generates and returns a random boolean value
 public boolean randomBoolean()
 {
  Random r = new Random();
  return r.nextBoolean(); 
 }

 // setFlag : sets string given to the flag for recursion
 public void setFlag(String n)
 {
  this.flag = n;
 }
 //returns the flag value in the the button
 public String ret_flag(){
   return this.flag;
 }
 
 //returns true if mine present
 public boolean mineHere(){
   
   if(isMine == true){
     return true;
   }
      return false ;
 }

 // returne number of mines near by
 public int getNearVal(){
   
   return near;
 }
 
 //return row loaction on the 
 public int ret_x(){
   
   return x;
 }
 
 // returns the column location on grid
 public int ret_y(){
   
   return y;
 }
 
 // intial Button image
 public void setBimage(){
   Icon x = new ImageIcon("button_normal.gif");
   setIcon(x);
   
 }
 
 // changes the image of  button based on the mines near by
 public void changeImage(){
   Icon x = new ImageIcon("button_pressed.gif");
   Icon c1 = new ImageIcon("1.gif");
   Icon c2 = new ImageIcon("2.gif");
   Icon c3 = new ImageIcon("3.gif");
   Icon c4 = new ImageIcon("4.gif");
   Icon c5 = new ImageIcon("5.gif");
   Icon c6 = new ImageIcon("6.gif");
   Icon c7 = new ImageIcon("7.gif");
   Icon c8 = new ImageIcon("8.gif");
   if(getNearVal() == 0){
      setIcon(x);
   
      setFlag("D");
   }
   if(getNearVal() == 1){
     
     setIcon(c1);
     setFlag("D");
   }
   if(getNearVal() == 2){
     setIcon(c2);
     setFlag("D");
   }
   if(getNearVal() == 3){
     setIcon(c3);
     setFlag("D");
   }
   if(getNearVal() == 4){
     setIcon(c4);
     setFlag("D");
   }
   if(getNearVal() == 5){
     setIcon(c5);
     setFlag("D");
   }
   if(getNearVal() == 6){
     setIcon(c6);
     setFlag("D");
   }
   if(getNearVal() == 7){
     setIcon(c7);
   }
   if(getNearVal() == 8){
     setIcon(c8);
     setFlag("D");
   }
 }
 //return right cnt on that button
 public int rval(){
   return rclick;
 }
 
 //return left cnt on that button
 public int lval(){
  return lclick;
 }
 
}// end on class Square
