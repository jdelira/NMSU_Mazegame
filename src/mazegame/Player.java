//Player class to control the player throughout the Maze
//will handle player movement, and trigger Mine effects

package mazegame;
import com.googlecode.lanterna.terminal.Terminal;
import static com.mycompany.mavenproject1.Main.key;
import static com.mycompany.mavenproject1.Main.response;
import static com.mycompany.mavenproject1.Main.screen;
import java.util.Random;



public class Player
{
   public static int lives = 5, playerX = 0, playerY = 0;
   static Random random = new Random();
   private static String numMove;
   
   
   public static void resetLives()
   {
     lives = 5;
   }
   
   public static boolean winCon(int X, int Y)
   {
          if(Mine.getMineMap(X, Y).equals("E") && (playerX == X && playerY == Y))
            return true;
          else
            return false;
   }
    
  public static boolean loseCon()
   {
     if(lives <= 0)
       return true;
     else
       return false;
   }     
   
   public static void findPlayer()
   {
     for(int i = 0; i < Maze.getMazeRow(); i++)
       for(int j = 0; j < Maze.getMazeColumn(); j++)
       {

         if(Maze.getMaze(i, j).equals("X"))
         {
   

           playerX = i;
           playerY = j;
         }
       }
   }
   
   public static int getPlayerX()
   {
   
     findPlayer();
   
     return playerX;
   }
   
   public static int getPlayerY()
   {

     findPlayer();
    
     return playerY;
   }
   
   public static void moveCheck() throws InterruptedException
   {  
      if(Maze.getMazeRow() > 22 || Maze.getMazeColumn() > 22)
      {
        screen.putString(Maze.leftSideOfGame + Maze.getMazeColumn()/4,Maze.botSideOfGame + 2,"Please enter a direction: S or 2-Down, A or 4-Left, D or 6-Right, W or 8-Up",Terminal.Color.WHITE, Terminal.Color.BLACK); 
        screen.refresh();
      }
      else
      {
        screen.putString(Maze.leftSideOfGame/2,Maze.botSideOfGame + 2,"Please enter a direction: S or 2-Down, A or 4-Left, D or 6-Right, W or 8-Up",Terminal.Color.WHITE, Terminal.Color.BLACK); 
        screen.refresh();
      }
      findPlayer();
      boolean win = winCon(playerX, playerY);
      boolean lose = loseCon();
      StringBuilder sb = new StringBuilder();    //holds string value for normal key value(s)
      while(win == false && lose == false)
      {

         key = screen.readInput();
          
            //Keep screen up until input is received
            while(key == null)
            {
               key = screen.readInput();
            }
            
            sb.append(key.getCharacter());
            response = sb.toString().toLowerCase();
                 
        //check if player is trying to make move outside of bounds
        //true if player is within bounds
        if(validMove(response))
        {
          movePlayer(response, false);
          
          win = winCon(playerX, playerY);
          lose = loseCon();
            if(Maze.getMazeRow() > 22 || Maze.getMazeColumn() > 22)
            {
              screen.putString(Maze.leftSideOfGame + Maze.getMazeColumn()/4,Maze.botSideOfGame + 2,"Please enter a direction: S or 2-Down, A or 4-Left, D or 6-Right, W or 8-Up",Terminal.Color.WHITE, Terminal.Color.BLACK); 
              screen.refresh();
            }
            else
            {
              screen.putString(Maze.leftSideOfGame/2,Maze.botSideOfGame + 2,"Please enter a direction: S or 2-Down, A or 4-Left, D or 6-Right, W or 8-Up",Terminal.Color.WHITE, Terminal.Color.BLACK); 
              screen.refresh();
            }

        } 
        else
        {
            Maze.printMaze();
            if(Maze.getMazeRow() > 22 || Maze.getMazeColumn() > 22)
            {
              screen.putString(Maze.leftSideOfGame + Maze.getMazeColumn()/4,Maze.botSideOfGame + 2,"Please enter a direction: S or 2-Down, A or 4-Left, D or 6-Right, W or 8-Up",Terminal.Color.WHITE, Terminal.Color.BLACK); 
              screen.refresh();
            }
            else
            {
              screen.putString(Maze.leftSideOfGame/2,Maze.botSideOfGame + 2,"Please enter a direction: S or 2-Down, A or 4-Left, D or 6-Right, W or 8-Up",Terminal.Color.WHITE, Terminal.Color.BLACK); 
              screen.refresh();
            }
            
        }
        sb = new StringBuilder();


      }

      if(lose == true)
      {


        screen.clear();
        screen.putString(10,10,"You lose",Terminal.Color.WHITE, Terminal.Color.BLACK);
        screen.refresh();
        Thread.sleep(2500);
      }
      else if(win == true)
      {
        screen.clear();
        screen.putString(10,10,"Congratulations, you have exited the maze and won this game!",Terminal.Color.WHITE, Terminal.Color.BLACK);
        screen.refresh();
        Thread.sleep(2500);
      }
    }
   
   public static boolean validMove(String direction) throws InterruptedException
   {
        if((direction.equals("8") || direction.equalsIgnoreCase("w")) && (playerX - 1 < 0))
        {
          screen.clear();
          screen.putString(10,10,"Invalid move",Terminal.Color.WHITE, Terminal.Color.BLACK);
          screen.refresh();
          Thread.sleep(2000);
          return false;
        }
        else if((direction.equals("6") || direction.equalsIgnoreCase("d")) && (playerY + 1 >= Maze.getMazeColumn()))
        {
          screen.clear();
          screen.putString(10,10,"Invalid move",Terminal.Color.WHITE, Terminal.Color.BLACK);
          screen.refresh();
          Thread.sleep(2000);
          return false;
        }
        else if((direction.equals("4") || direction.equalsIgnoreCase("a")) && (playerY - 1 < 0))
        {
          screen.clear();
          screen.putString(10,10,"Invalid move",Terminal.Color.WHITE, Terminal.Color.BLACK); 
          screen.refresh();
          Thread.sleep(2000);
          return false;
        }
        else if((direction.equals("2") || direction.equalsIgnoreCase("s")) && (playerX + 1 >= Maze.getMazeRow()))
        {
          screen.clear();
          screen.putString(10,10,"Invalid move",Terminal.Color.WHITE, Terminal.Color.BLACK);
          screen.refresh();
          Thread.sleep(2000);
          return false;
        }
        else
        {
          return true;
        }
   }  
   
   
   
   public static void playerEffects() throws InterruptedException
   {
     findPlayer();
     String effect = Mine.getMineMap(playerX, playerY);
     //screen.stopScreen();
     if(Enemy.enemyCheck() == true)
     {
       screen.clear();
       screen.putString(2,10,"An enemy has hit you! You have lost a life, and the enemy has been teleported to a random location!",Terminal.Color.WHITE, Terminal.Color.BLACK);
       screen.refresh();
       Thread.sleep(2500);
       lives--;
       if(lives >= 0)
       {
          screen.clear();
          screen.putString(6,10,"You have " + lives + " lives remaining!",Terminal.Color.WHITE, Terminal.Color.BLACK);
          screen.refresh();
          Thread.sleep(2500);
       }
       playerEffects();
     }
     
     else if(effect != " ")
     {
       if(effect == "*")
       {
         lives--;
         screen.clear();
         screen.putString(6,10,"You have hit a mine, and lost a life! You have " + lives + " lives remaining.",Terminal.Color.WHITE, Terminal.Color.BLACK);
         screen.refresh();
         Thread.sleep(1500);
       }
       if(effect == "T")
       {
         
         int X = random.nextInt(Maze.getMazeRow());
         int Y = random.nextInt(Maze.getMazeColumn());
         
         while(playerX == X && playerY == Y)
           X = random.nextInt(Maze.getMazeRow());
         
         Maze.setMaze(playerX, playerY, effect);
         playerX = X;
         playerY = Y;
         Maze.setMaze(playerX, playerY, "X");
         screen.clear();
         screen.putString(6,10,"You have hit a teleportation device! You have been teleported to a random location!",Terminal.Color.WHITE, Terminal.Color.BLACK);
         screen.refresh();
         Thread.sleep(2500);
         playerEffects();
    
       }           
       if(effect == "1")
       { 
         lives++;
         screen.clear();
         screen.putString(6,10,"You have found an extra life! You have " + lives + " lives remaining.",Terminal.Color.WHITE, Terminal.Color.BLACK);
         screen.refresh();
         Thread.sleep(2500);
         Mine.setMineMap(playerX, playerY, " ");
       }
       if(effect == "H")
       {
         for(int i = 0; i < Maze.getMazeRow(); i++)
           for(int j = 0; j < Maze.getMazeColumn(); j++)
              if(Mine.getMineMap(i, j) == "E")
                Maze.setMaze(i, j, "E");
         Mine.setMineMap(playerX, playerY, " ");
         screen.clear();
         screen.putString(6,10,"You have discovered a hint that reveals the exit to the maze!",Terminal.Color.WHITE, Terminal.Color.BLACK);
         screen.refresh();
         Thread.sleep(2500);

       }
       if(effect == "F")
       {
         Mine.setMineMap(playerX, playerY, " ");
         screen.clear();
         screen.putString(2,10,"You have hit a freeze mine, and been frozen in place! The enemies are advancing on your position!",Terminal.Color.BLUE, Terminal.Color.BLACK);
         screen.refresh();
         Thread.sleep(2500);
         Enemy.enemyMove();
         playerEffects();
       }  
       if(effect == "S")
       {
         screen.clear();
         screen.putString(6,10,"You have hit a speed boost, move again!",Terminal.Color.WHITE, Terminal.Color.BLACK);
         screen.refresh();
         Thread.sleep(2500);
         Mine.setMineMap(playerX, playerY, " ");
         Maze.printMaze();      
       }
     }
   }
  
   
   public static void movePlayer(String direction, boolean speed) throws InterruptedException
   { 
     boolean move = true;
     findPlayer();

     switch(direction)    //based on user input, moves the player the desired direction around the maze
      {
         case "8": case "w":
            Maze.setMaze(playerX, playerY, Mine.getMineMap(playerX, playerY));
            Maze.setMaze(playerX-1, playerY, "X");
            break;
         
         case "2": case "s":

            Maze.setMaze(playerX, playerY, Mine.getMineMap(playerX, playerY));
            Maze.setMaze(playerX+1, playerY, "X");
            break;
         
         case "4": case "a":
            Maze.setMaze(playerX, playerY, Mine.getMineMap(playerX, playerY));
            Maze.setMaze(playerX, playerY-1, "X");
            break;
         
         case "6": case "d":
            Maze.setMaze(playerX, playerY, Mine.getMineMap(playerX, playerY));
            Maze.setMaze(playerX, playerY+1, "X");
            break; 
         
         case "revealmap":
            for(int i = 0; i < Maze.getMazeRow(); i++)
              for(int j = 0; j < Maze.getMazeColumn(); j++)
              {
                Maze.setMaze(i, j, Mine.getMineMap(i, j));
                Maze.setMaze(playerX, playerY, "X");
              }
            move = false;
            break;
         
         case "upupdowndownleftrightleftrightbastart":
            lives = lives+100;
            for(int i = 0; i < Maze.getMazeRow(); i++)
              for(int j = 0; j < Maze.getMazeColumn(); j++)
              {
                Maze.setMaze(i, j, Mine.getMineMap(i, j));
                Maze.setMaze(playerX, playerY, "X");
              }
            move = false;
            break;
         
         case "0": case "q":
            screen.clear();
            screen.putString(25,10,"You have " + lives + " lives remaining!",Terminal.Color.WHITE, Terminal.Color.BLACK);
            screen.refresh();
            Thread.sleep(2500);
            move = false;
            break;
            
         case "exit": case "quit": case "concede":
            screen.clear();
            screen.putString(25,10,"You have decided to quit the game, and thus instantly lose!",Terminal.Color.WHITE, Terminal.Color.BLACK);
            screen.refresh();
            Thread.sleep(2500);
            lives = 0;
            move = false;
            break;
            
         case "revealenemies":
            Enemy.printEnemyMap();
            move = false;
            break; 
            
         default:
           move = false;
           Maze.printMaze();          
            
      }
      if(move == true && speed == false)
      {
        Enemy.enemyMove();
        playerEffects();
        Maze.printMaze();

      }
      else if(move == true && speed == true)
      {
        playerEffects();
        Maze.printMaze();
        
      }
  }  
}   