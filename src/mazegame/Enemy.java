//Enemy.java used to create and handle enemies
//within the maze
package mazegame;
import com.googlecode.lanterna.terminal.Terminal;
import static com.mycompany.mazeGame.Main.screen;
import static com.mycompany.mazeGame.Maze.botSideOfGame;
import static com.mycompany.mazeGame.Maze.leftSideOfGame;
import static com.mycompany.mazeGame.Maze.rightSideOfGame;
import static com.mycompany.mazeGame.Maze.topSideOfGame;
import java.util.*;

public class Enemy extends Main
{
   private int enemyX, enemyY; 
   private static int enemies;
   private String name;
   static int enemyRow; 
   static int enemyColumn;
   private static String enemyMap[][];
   
   static Random random = new Random();
   
   public static Enemy one;
   public static Enemy two;
   public static Enemy three;
   public static Enemy four;
   public static Enemy five;
   
   
   //ENEMY CONSTRUCTOR
   public Enemy(String Name)
   {
      
      int X = random.nextInt(getEnemyRow());
      int Y = random.nextInt(getEnemyColumn());
      boolean notOrigin = true;
 
          
          
      while(notOrigin == true)
      {
        if(!(X == 0 && Y == 0) && (X < getEnemyRow()) && (Y < getEnemyColumn()))
        {
          this.enemyX = X;
          this.enemyY = Y;
  
          notOrigin = false;
        }
        else
        {
          X = random.nextInt(getEnemyRow());
          Y = random.nextInt(getEnemyColumn());
        }
      }
     name = Name;
    }
    
    public static int getEnemyRow()
    {
      return Maze.getMazeRow();
    }
    
    public static int getEnemyColumn()
    {
      return Maze.getMazeColumn();
    }
    
    public int getEnemyTotal()
    {
      return enemies;
    }
                 
    public int getEnemyX()
    {
      return enemyX;
    }
    
    public int getEnemyY()
    {
      return enemyY;
    }
    
    public String getName()
    {
      return name;
    }
    
    public void setEnemyX(int X)
    {
       enemyX = X;
    }
    
    public void setEnemyY(int Y)
    {
      enemyY = Y;
    }
    
    public static String getEnemyMap(int X, int Y)
    {
      return enemyMap[X][Y];
    }
    
    public static void setEnemyMap(int X, int Y, String info)
    {
       enemyMap[X][Y] = info;
    }
    
    public static void enemyTeleport(Enemy name)
    {
      int X = random.nextInt(getEnemyRow());
      int Y = random.nextInt(getEnemyColumn());
         
      while(name.enemyX == X && name.enemyY == Y)
          X = random.nextInt(getEnemyRow());
         
      setEnemyMap(name.enemyX, name.enemyY, "~");
      name.enemyX = X;
      name.enemyY = Y;
      setEnemyMap(X, Y, "N");
    }
    
    
    public static void makeEnemyMap(int num)
    {
      enemyMap = new String[getEnemyRow()][getEnemyColumn()];
      
      for(int i = 0; i < getEnemyRow(); i++)
      {
        for(int j = 0; j < getEnemyColumn(); j++)
        {
          if(num == 2)
          {
            if((i == one.enemyX && j == one.enemyY) || (i == two.enemyX && j == two.enemyY))
              enemyMap[i][j] = "N";
            else
              enemyMap[i][j] = "~";
          }
          else if(num == 3)
          {
            if((i == one.enemyX && j == one.enemyY) || (i == two.enemyX && j == two.enemyY) || (i == three.enemyX && j == three.enemyY))
              enemyMap[i][j] = "N";
            else
              enemyMap[i][j] = "~";
          }
          else if(num == 5)
          {
            if((i == one.enemyX && j == one.enemyY) || (i == two.enemyX && j == two.enemyY) || (i == three.enemyX && j == three.enemyY)
              || (i == four.enemyX && j == four.enemyY) || (i == five.enemyX && j == five.enemyY))
              enemyMap[i][j] = "N";
            else
              enemyMap[i][j] = "~";
          }
        }
      }
    }
 
    public static void diffCheck(int x)
    {
        
      switch(x)
      {
          case 1: 
              enemies = 2;
              one = new Enemy("one");
              two = new Enemy("two");
              makeEnemyMap(enemies);
              break;
              
          case 2:
              enemies = 3;
              one = new Enemy("one");
              two = new Enemy("two");
              three = new Enemy("three");
              makeEnemyMap(enemies);
              break;
              
          case 3:
              enemies = 5;
              one = new Enemy("one");
              two = new Enemy("two");
              three = new Enemy("three");
              four = new Enemy("four");
              five = new Enemy("five");
              makeEnemyMap(enemies);
              break;        
      }
    }
    
    public static void printEnemyMap()
    {
      screen.stopScreen();
      for(int j = leftSideOfGame; j < rightSideOfGame; j++)
      {
         screen.putString(j,topSideOfGame, "__",Terminal.Color.WHITE, Terminal.Color.BLACK);
         screen.putString(j,botSideOfGame, "__",Terminal.Color.WHITE, Terminal.Color.BLACK);
         screen.refresh();
         
      }
      //LEFT & RIGHT BOUNDARY PRINT
      for(int rowcount = 0; rowcount + topSideOfGame + 1 <= botSideOfGame; rowcount++)
      {
          screen.putString(leftSideOfGame,rowcount + topSideOfGame + 1, "|",Terminal.Color.WHITE, Terminal.Color.BLACK);
          screen.putString(rightSideOfGame,rowcount + topSideOfGame + 1, "|",Terminal.Color.WHITE, Terminal.Color.BLACK);
          screen.refresh();
      
      }
      screen.startScreen();
    }
    
    public static boolean enemyCheck()
    {
      int mazeX = 0, mazeY = 0;
      
      for(int i = 1; i <= enemies; i++)
      {
        if(i == 1)
        {
          mazeX = one.enemyX;
          mazeY = one.enemyY;
            
          if((Maze.getMaze(mazeX, mazeY).equals("X")))
          {
            enemyTeleport(one);
            return true;
          }
        }
        else if(i == 2)
        {
          mazeX = two.enemyX;
          mazeY = two.enemyY;
          
          if((Maze.getMaze(mazeX, mazeY).equals("X")))
          {
            enemyTeleport(two);
            return true;
          }
        }
        else if(i == 3)
        {
          mazeX = three.enemyX;
          mazeY = three.enemyY;
          
          if((Maze.getMaze(mazeX, mazeY).equals("X")))
          {
            enemyTeleport(three);
            return true;
          }
        }
        else if(i == 4)
        {
          mazeX = four.enemyX;
          mazeY = four.enemyY;
          
          if((Maze.getMaze(mazeX, mazeY).equals("X")))
          {
            enemyTeleport(four);
            return true;
          }
        }
        else if(i == 5)
        {
          mazeX = five.enemyX;
          mazeY = five.enemyY;
          
          if((Maze.getMaze(mazeX, mazeY).equals("X")))
          {
            enemyTeleport(five);
            return true;
          }
        }
      }
     return false;
    }
    
    public static void enemyMove()
    {
      int playerX = Player.getPlayerX(); 
      int playerY = Player.getPlayerY();
      int hunt;
      
      for(int i = 1; i <= enemies; i++)
      {
        if(i == 1)
        {
          hunt = random.nextInt(2);
          
          if(hunt == 0)
          {
            if(one.getEnemyX() > playerX && one.getEnemyX()-1 >= 0)
              one.setEnemyX(one.getEnemyX()-1);
            else if(one.enemyX < playerX && one.getEnemyX()+1 < getEnemyRow())
              one.setEnemyX(one.getEnemyX()+1);
            else if(one.getEnemyX() == playerX)
            {
              if(one.getEnemyY() > playerY && one.getEnemyY()-1 >= 0)
                one.setEnemyY(one.getEnemyY()-1);
              else if(one.getEnemyY() < playerY && one.getEnemyY()+1 < getEnemyColumn())
                one.setEnemyY(one.getEnemyY()+1);
            }
          }
          else if(hunt == 1)
          {
            if(one.getEnemyY() > playerY && one.getEnemyY()-1 >= 0)
              one.setEnemyY(one.getEnemyY()-1);
            else if(one.enemyY < playerY && one.getEnemyY()+1 < getEnemyColumn())
              one.setEnemyY(one.getEnemyY()+1);
            else if(one.getEnemyY() == playerY)
            {
              if(one.getEnemyX() > playerX && one.getEnemyX()-1 >= 0)
                one.setEnemyX(one.getEnemyX()-1);
              else if(one.getEnemyX() < playerX && one.getEnemyX()+1 < getEnemyRow())
                one.setEnemyX(one.getEnemyX()+1);
            }
          }
        }
        if(i == 2)
        {
          hunt = random.nextInt(2);
          
          if(hunt == 0)
          {
            if(two.getEnemyX() > playerX && two.getEnemyX()-1 >= 0)
              two.setEnemyX(two.getEnemyX()-1);
            else if(two.enemyX < playerX && two.getEnemyX()+1 < getEnemyRow())
              two.setEnemyX(two.getEnemyX()+1);
            else if(two.getEnemyX() == playerX)
            {
              if(two.getEnemyY() > playerY && two.getEnemyY()-1 >= 0)
                two.setEnemyY(two.getEnemyY()-1);
              else if(two.getEnemyY() < playerY && two.getEnemyY()+1 < getEnemyColumn())
                two.setEnemyY(two.getEnemyY()+1);
            }
          }
          else if(hunt == 1)
          {
            if(two.getEnemyY() > playerY && two.getEnemyY()-1 >= 0)
              two.setEnemyY(two.getEnemyY()-1);
            else if(two.enemyY < playerY && two.getEnemyY()+1 < getEnemyColumn())
              two.setEnemyY(two.getEnemyY()+1);
            else if(two.getEnemyY() == playerY)
            {
              if(two.getEnemyX() > playerX && two.getEnemyX()-1 >= 0)
                two.setEnemyX(two.getEnemyX()-1);
              else if(two.getEnemyX() < playerX && two.getEnemyX()+1 < getEnemyRow())
                two.setEnemyX(two.getEnemyX()+1);
            }
          }
        }
        if(i == 3)
        {
          hunt = random.nextInt(2);
          
          if(hunt == 0)
          {
            if(three.getEnemyX() > playerX && three.getEnemyX()-1 >= 0)
              three.setEnemyX(three.getEnemyX()-1);
            else if(three.enemyX < playerX && three.getEnemyX()+1 < getEnemyRow())
              three.setEnemyX(three.getEnemyX()+1);
            else if(three.getEnemyX() == playerX)
            {
              if(three.getEnemyY() > playerY && three.getEnemyY()-1 >= 0)
                three.setEnemyY(three.getEnemyY()-1);
              else if(three.getEnemyY() < playerY && three.getEnemyY()+1 < getEnemyColumn())
                three.setEnemyY(three.getEnemyY()+1);
            }
          }
          else if(hunt == 1)
          {
            if(three.getEnemyY() > playerY && three.getEnemyY()-1 >= 0)
              three.setEnemyY(three.getEnemyY()-1);
            else if(three.enemyY < playerY && three.getEnemyY()+1 < getEnemyColumn())
              three.setEnemyY(three.getEnemyY()+1);
            else if(three.getEnemyY() == playerY)
            {
              if(three.getEnemyX() > playerX && three.getEnemyX()-1 >= 0)
                three.setEnemyX(three.getEnemyX()-1);
              else if(three.getEnemyX() < playerX && three.getEnemyX()+1 < getEnemyRow())
                three.setEnemyX(three.getEnemyX()+1);
            }
          }
        }
        if(i == 4)
        {
          hunt = random.nextInt(2);
          
          if(hunt == 0)
          {
            if(four.getEnemyX() > playerX && four.getEnemyX()-1 >= 0)
              four.setEnemyX(four.getEnemyX()-1);
            else if(four.enemyX < playerX && four.getEnemyX()+1 < getEnemyRow())
              four.setEnemyX(four.getEnemyX()+1);
            else if(four.getEnemyX() == playerX)
            {
              if(four.getEnemyY() > playerY && four.getEnemyY()-1 >= 0)
                four.setEnemyY(four.getEnemyY()-1);
              else if(four.getEnemyY() < playerY && four.getEnemyY()+1 < getEnemyColumn())
                four.setEnemyY(four.getEnemyY()+1);
            }
          }
          else if(hunt == 1)
          {
            if(four.getEnemyY() > playerY && four.getEnemyY()-1 >= 0)
              four.setEnemyY(four.getEnemyY()-1);
            else if(four.enemyY < playerY && four.getEnemyY()+1 < getEnemyColumn())
              four.setEnemyY(four.getEnemyY()+1);
            else if(four.getEnemyY() == playerY)
            {
              if(four.getEnemyX() > playerX && four.getEnemyX()-1 >= 0)
                four.setEnemyX(four.getEnemyX()-1);
              else if(four.getEnemyX() < playerX && four.getEnemyX()+1 < getEnemyRow())
                four.setEnemyX(four.getEnemyX()+1);
            }
          }
        }
        if(i == 5)
        {
          hunt = random.nextInt(2);
          
          if(hunt == 0)
          {
            if(five.getEnemyX() > playerX && five.getEnemyX()-1 >= 0)
              five.setEnemyX(five.getEnemyX()-1);
            else if(five.enemyX < playerX && five.getEnemyX()+1 < getEnemyRow())
              five.setEnemyX(five.getEnemyX()+1);
            else if(five.getEnemyX() == playerX)
            {
              if(five.getEnemyY() > playerY && five.getEnemyY()-1 != 0)
                five.setEnemyY(five.getEnemyY()-1);
              else if(five.getEnemyY() < playerY && five.getEnemyY()+1 < getEnemyColumn())
                five.setEnemyY(five.getEnemyY()+1);
            }
          }
          else if(hunt == 1)
          {
            if(five.getEnemyY() > playerY && five.getEnemyY()-1 >= 0)
              five.setEnemyY(five.getEnemyY()-1);
            else if(five.enemyY < playerY && five.getEnemyY()+1 < getEnemyColumn())
              five.setEnemyY(five.getEnemyY()+1);
            else if(five.getEnemyY() == playerY)
            {
              if(five.getEnemyX() > playerX && five.getEnemyX()-1 >= 0)
                five.setEnemyX(five.getEnemyX()-1);
              else if(five.getEnemyX() < playerX && five.getEnemyX()+1 < getEnemyRow())
                five.setEnemyX(five.getEnemyX()+1);
            }
          }
        }
      }
      makeEnemyMap(enemies);
    }
}
