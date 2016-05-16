/*****************************************************************
|COMPILED BY HENVY PATEL & DANIEL PULLEY                         |
|PROJECT 2: MINESWEEPER IN JAVA                                  |
|DESCRIPTION : THIS IS THE ADAPTED VERSION OF THE DEMO           |
|              GRIDLAYOUT THAT WAS PROVIDED IN THE CLASS         |
|            * This is the setup of the game                     |
|            * Class : MineSweeper - Contains the actual layout  |   
|                      of the game which includes, the grid      |
|                      for the game and other relevent functions.|                                  
|                                                                |
|/***************************************************************/    
/* THE PROGRAM DOES NOT HAVE IMPLEMENTATION FOR TOP TEN PLAYER   //
*****************************************************************/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
import javax.swing.Timer;

public class MineSweeper extends JFrame implements ActionListener{
  
  // variables for the mines
  private square[][] grid ;
  // randomization takes place here
  private Random rand = new Random();

  // JPanels for adding the displaying info about mines and timer
  JPanel j1;
  JPanel s1;
  
  // making conatainer for the adding the panels
  private Container container;
  private GridLayout grid1;
  // make scores
  private int pscore;
  private int mines;
  // labels for score
  private JLabel stext;
  private JButton reset;
  private boolean gameover;

  final JLabel tText; // time text
  Timer countUpTimer;
  public int currentTime = 0;
   

  // constructor for the MineSweeper
  public MineSweeper()
   {
      super( "MINESWEEPER CS342" );
      // intializing the elements of the 
      j1 = new JPanel();
      s1 = new JPanel();
      grid = new square[10][10];
      grid1 = new GridLayout( 10, 10 );
      gameover = false;
      //Adding the grid to the container
      container = getContentPane();
      j1.setLayout(new BoxLayout(j1,BoxLayout.PAGE_AXIS));
      j1.setLayout(grid1);
      // creates a menu bar here
      createMenuBar();  
      // intializing mines
      mines = 10;
      // adding the mines info to the container
      stext = new JLabel("Mines to find : " + mines+ "     ");
      s1.setLayout(new BoxLayout(s1,BoxLayout.LINE_AXIS));
      s1.add(stext);
      countUpTimer = new Timer(1000, new CountUpTimerListener());
      countUpTimer.start();
      // adding the reset button to the container
      reset = new JButton("  RESET " );
      // adding the action listner to the reset button
      reset.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent event){
            dispose();
             MineSweeper resetGame = new MineSweeper();
             resetGame.setVisible(true); 
         }
      });     
      s1.add(Box.createRigidArea(new Dimension(5,0)));  
      //s1.setLayout(new BoxLayout(s1,BoxLayout.PAGE_AXIS));
      s1.add(reset);
      s1.add(Box.createRigidArea(new Dimension(10,0)));  
      JLabel timeBox = new JLabel("Time: "); 
      tText = new JLabel(String.valueOf(currentTime));
      s1.add(timeBox);
      s1.add(tText);
     
      // intializing score
      pscore = 0;
      // to check make the grid 
      initGrid();
      isNear();
      setImage();
      dInt();
      
      // adds the two panels togather
      container.add(j1,BorderLayout.CENTER);
     container.add(s1,BorderLayout.PAGE_START);
   
      // display the grid here
      setSize( 300, 300 );
      setVisible( true );
     
         
  }
  
  // set intial Button image
   public void setImage(){
    for(int i = 0; i < 10; i++)
           for(int j = 0; j < 10; j++){
                 grid[i][j].setBimage();
    }
  }
// createMenuBar Function - This function creates the Game Menu  
 public void createMenuBar()
 {
  // Initialize a new MenuBar
  JMenuBar menuBar = new JMenuBar();
  // Our First Menu will be called Game
  JMenu gDrop = new JMenu("Game"); 
  // Our Second Menu will be called Help
  JMenu hDrop = new JMenu("Help"); 
  // The game drop down menu will have 3 items( Rest, Top Ten, and Exit) 
  JMenuItem rMenuItem = new JMenuItem("Reset");
  // Key event, if R is pressed the game will reset itself
  rMenuItem.setMnemonic(KeyEvent.VK_R);
  rMenuItem.addActionListener(new ActionListener(){
   @Override
   public void actionPerformed(ActionEvent event){
    dispose();
    MineSweeper resetGame = new MineSweeper();
    resetGame.setVisible(true); 
   }
  });
  
  // Display the top ten scores of the game
  JMenuItem tMenuItem = new JMenuItem("Top ten");
  // if T key is pressed then display topTen
  tMenuItem.setMnemonic(KeyEvent.VK_T);
  tMenuItem.addActionListener(new ActionListener(){
   @Override
   public void actionPerformed(ActionEvent event){
     JOptionPane.showMessageDialog( null,"TOP TEN PLAYERS NOT IMPLEMENTED");
    
   } 
  });
  // Exit the program
  JMenuItem eMenuItem = new JMenuItem("eXit"); 
  // If X key is pressed then the game is exited
  eMenuItem.setMnemonic(KeyEvent.VK_X);
  eMenuItem.addActionListener(new ActionListener(){
   @Override
   public void actionPerformed(ActionEvent event){
    System.exit(0);
   }
  });

  // Help Menu
  JMenuItem hMenuItem = new JMenuItem("Help");
  hMenuItem.setMnemonic(KeyEvent.VK_H);
  hMenuItem.addActionListener(new ActionListener(){
   @Override
   public void actionPerformed(ActionEvent event){
    instruct();
   }
  });
  JMenuItem aMenuItem = new JMenuItem("About");
  aMenuItem.setMnemonic(KeyEvent.VK_A);
  aMenuItem.addActionListener(new ActionListener(){
   @Override
   public void actionPerformed(ActionEvent event){
     JOptionPane.showMessageDialog( null,"Daniel Pulley: Current undergrad student\n"+" studying computer science at UIC.\n"+
               "Henvy Patel: Computer Science student @ UIC.\n" );
   }
  });

  // adding the menu's here
  gDrop.add(rMenuItem);
  gDrop.add(tMenuItem);
  gDrop.add(eMenuItem);
  hDrop.add(hMenuItem);
  hDrop.add(aMenuItem);
  menuBar.add(gDrop);
  menuBar.add(hDrop);
  setJMenuBar(menuBar);
 
 }

 
 // New WIndow with the Help info from the Help Menu
 public void instruct()
 {
 
  JOptionPane.showMessageDialog( null,"Game Drop-Down Menu: Includes (Reset, TopTen, and Exit Options)\n" + 
                                "Reset restarts the game. Top ten displays a list top ten scores and eXit exits the program\n"
                                + "To play the game first select a random place to start of the grid\n "+ 
                                "You will then see a number on a square. These number indicate the number of\n" +
                                "mines surrounding that current mine you chose\n"+
                                  "Right click on a square to mark it with a flag. If you left click a mine the game is over");
  
 }
 
 
 
  /******all the methods for the function begin here*******/
 

 // intializies the grid values here
  public void initGrid()
  {
   // Initializing new Grid    
         System.out.println("Initializing New Squares");
         for(int i = 0; i < 10; i++)
           for(int j = 0; j < 10; j++){
                 grid[i][j] = new square();
                 grid[i][j].x = i;
                 grid[i][j].y = j;
                 grid[i][j].addMouseListener (new MouseClickHandler() );
                 j1.add( grid[i][j]);
         }
         // sets the mine here
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
    grid[random1][random2].near = 99;
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
 
// displays the grid on the backend
  public void dInt(){
    
    System.out.println("---------------------------------------------------------------------------------");
    for(int i = 0; i < grid.length; i++){
      for(int j = 0; j < grid[i].length; j++){
                 System.out.print(grid[i][j].getNearVal()+ "  |" );
    }
    System.out.println("  ");
    }
    System.out.println("---------------------------------------------------------------------------------");
  }
  

  
  // cntFlag keeps the count of the opened squares on the grid
  public int cntFlag()
  {
        int cnt = 0;
        for(int i = 0; i < grid.length; i++){
          for(int j = 0; j < grid[i].length; j++){
           if(grid[i][j].ret_flag() == "D"){
             cnt++;
           }
         }
       }
       return cnt;
  }
  

 // Shows all the mine , when the user explodes bomb
 public void showAllmines(){
   Icon c = new ImageIcon("button_bomb_blown.gif");
   for(int i = 0; i < grid.length; i++){
      for(int j = 0; j < grid[i].length; j++){
         if(grid[i][j].mineHere() == true){
                grid[i][j].setIcon(c); 
         }
      }
   }
 }
 
 // recursively displays all the file tiles with the '0' mines near by
 public void show_adj(int i, int j){
   
   // check the current condition
   if(grid[i][j].isMine == true){
     return;
   }
   if(grid[i][j].mineHere() != true && grid[i][j].getNearVal() > 0 && grid[i][j].getNearVal() < 99){
       grid[i][j].changeImage();
     }

   if(grid[i][j].mineHere() != true && grid[i][j].getNearVal() == 0){
     // change the current image
    // System.out.println("i = " + i + "  j = " + j);
       grid[i][j].changeImage();
      
       // check for out of bounds condition
       if(i > 0 && grid[i-1][j].ret_flag() != "D"){
         //System.out.println("checkingtop");
         show_adj(i-1,j);
       } // top
       if(i > 0 && i < 9 && j < 9 ){
         //System.out.println("bottom");
         show_adj(i+1,j);
       } // bottom
       if(j > 0 && i < 9 && grid[i][j-1].ret_flag() != "D"){
         //System.out.println("left");
         show_adj(i,j-1);
       } // left
       if(j < 9 && i > 0 && i< 9){
        // System.out.println("left");
         //System.out.println("i = " + i + "  j = " + j);
         show_adj(i,j+1);
       } // right
       if(i > 0 && j > 0 && grid[i-1][j-1].ret_flag() != "D"){
         show_adj(i-1,j-1);
       }// top-left
       if(i>0 && j < 9) {
         show_adj(i-1,j+1);
       } // top-right
      if(i< 9 && j > 0 && grid[i+1][j-1].ret_flag() != "D"){
        show_adj(i+1,j-1);
      } // bottom-left---------------------
      if(i < 9 && j < 9) {
        show_adj(i+1,j+1);
      } // bottom-right-----------------
       
   }
 }
 
 // Checks the states of the square
 public void result(int a, square s){
   if(gameover == true){
     // replaces the game here
     dispose();
             MineSweeper resetGame = new MineSweeper();
             resetGame.setVisible(true); 
     
   }
   // double check if mine
   if(a >= 99){
     if(s.isMine == true){
       countUpTimer.stop();
       //show all mine
       showAllmines();
       JOptionPane.showMessageDialog( this,
        "You Just blew a bomb!!! GAME OVER " + "  Your score  :" + pscore);
       gameover = true;
     }  
   }
   else{
   
     // gets the location on grid
     int x1 = s.ret_x();
     int y1 = s.ret_y();
     show_adj(x1,y1);
      pscore = cntFlag();
     //System.out.print("YOUR CURRENT SCORE = "+ pscore);
     if(cntFlag() == 90){
       showAllmines();
       JOptionPane.showMessageDialog( this,
        "YOU WIN!!! " + " Your Score : ");
       gameover = true;
     }
   
   }
 }
  public void actionPerformed (ActionEvent e)
  {
   String s = "Default Button Event";

   JOptionPane.showMessageDialog (null, s);
  }
  public int getMines(){
    
    return mines;
    
  }
  
  /*ACtion handler for the reset */
 // private class MouseClickHandler extends MouseAdapter
  
/*Mouse handler for the grid values*/
 private class MouseClickHandler extends MouseAdapter
{
 public void mouseClicked (MouseEvent e)
   {
   Icon c1 = new ImageIcon("button_flag.gif");
   Icon c2 = new ImageIcon("button_question_pressed.gif");
   square temp = (square) e.getSource();
   // all the right click instruction
   if (SwingUtilities.isLeftMouseButton(e)){
     if(temp.rclick == 1 || temp.rclick == 2){
       return;
     }
     // getting the source button
     temp.lclick++;
     // check if it a bomb
     int val = temp.getNearVal();
      result(val,temp);
   }
   else if (SwingUtilities.isRightMouseButton(e)){
     temp.rclick++;
     if(temp.rval() == 2){
        // update right click
         temp.setIcon(c2);
         mines++;
         if(mines <= 0){
           mines++;
         }
         
         stext.setText("MINES TO FIND : " +getMines() + "                ");
     }
     if(temp.rval() == 1){
     // update right click
       mines--;
       
       if(mines < 0){
          JOptionPane.showMessageDialog( null,"CAN MARK ONLY 10 MINES!!");
       }
       else{
         temp.setIcon(c1);
       stext.setText("MINES TO FIND : " + getMines()+ "    ");
       }
      }
     if(temp.rval()== 3){
       temp.rclick = 0;
       Icon x  = new ImageIcon("button_pressed.gif");
       temp.setIcon(x);
       
     }
   }
   else if (SwingUtilities.isMiddleMouseButton(e)){
     String s;
        s = "Please use left or right Mouse Button only!!";   
         JOptionPane.showMessageDialog (null, s);
    }
   }

}

class CountUpTimerListener implements ActionListener{
 public void actionPerformed(ActionEvent e){
  if(++currentTime > 0){
   tText.setText(String.valueOf(currentTime));   
  }else{
   tText.setText("Out of Time!");
   countUpTimer.stop();
  }
 }
}

}
