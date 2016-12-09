//Maze.java creation file
//to be used to create the maze the player 
//will progress through
package mazegame;
import com.googlecode.lanterna.terminal.Terminal;
import java.util.Random;

public class Maze extends Main
{
  private static int rows, columns, enemies, length, width;
  private String blank = "?", player = "X", enemy = "E";
  private static String maze[][];
  private static Random random = new Random();
  public static int topSideOfGame, botSideOfGame, rightSideOfGame, leftSideOfGame, averageDistance;
  
  //MAZE Constructor
  public Maze(int Row, int Column)
  {
       rows = Row;
       columns = Column;
       maze = new String[Row][Column];
       
       for(int i = 0; i < rows; i++)
       {
         for(int j = 0; j < columns; j++)
         {
           if(i == 0 && j == 0)
              maze[i][j] = player;
           else
             maze[i][j] = blank;
         }
       }
  }
  
   public static int getMazeRow()
   {
     return rows;
   } 
   
   public static int getMazeColumn()
   {
     return columns;
   }
   
   public static void setMaze(int X, int Y, String info)
   {
       maze[X][Y] = info;
   }
   
   public static String getMaze(int X, int Y)
   {
 
     return maze[X][Y];
   }
  
  
  public static void sizeCheck(int x, int y)
  {
    //indication that map will not be custom
    if(y == 0){
        switch(x){
            case 1:
                Maze small = new Maze(10,10);
                break;
            case 2:
                Maze medium = new Maze(15,15);
                break;
            case 3:
                Maze large = new Maze(20,20);
                break;
        }
    }
    else{
        Maze custom = new Maze(x,y);
    }  
  }
  
    
  public static void printMaze() throws InterruptedException //prints the maze
   {
      int count = 1;
      rightSideOfGame = (Main.termColumns/2) + getMazeColumn();
      leftSideOfGame  = Math.abs((Main.termColumns/2) - getMazeColumn());  
      topSideOfGame = 1;
      botSideOfGame = topSideOfGame + getMazeRow();
      
      
     
      
      screen.clear();
      //TOP & BOT BOUNDARY PRINT
 
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
          
          
          
          //columns currently counted are less than the number of required rows per column
          for (int columncount = 0; columncount < maze[rowcount].length; columncount++)            
          {
            
            int X = Player.getPlayerX();
            int Y = Player.getPlayerY();
           
 
            if((rowcount == (X - 1)) && (columncount == Y) && ((X - 1) > -1))
            {
         
              if(Enemy.getEnemyMap(X-1,Y).equals("N"))
              {
                if(columncount == 0)
                {

                 screen.putString(leftSideOfGame + 1,1 + topSideOfGame + X-1,  Enemy.getEnemyMap(X-1,Y),Terminal.Color.WHITE, Terminal.Color.BLACK);
                 screen.refresh();

                 count = 0;
                }
                else
                {
     
                    screen.putString(leftSideOfGame + 1 + Y + count,1 + topSideOfGame + X-1,  Enemy.getEnemyMap(X-1,Y),Terminal.Color.WHITE, Terminal.Color.BLACK);
                    screen.refresh(); 
                }
              }
              else
              {
                if(columncount == 0)
                {
    
                   screen.putString(leftSideOfGame + 1,1 + topSideOfGame + X-1, Mine.getMineMap(X-1,Y),Terminal.Color.WHITE, Terminal.Color.BLACK);
                   screen.refresh(); 

                   count = 0;
                }
                else
                {

                    screen.putString(leftSideOfGame + 1 + Y + count,1 + topSideOfGame + X-1, Mine.getMineMap(X-1,Y),Terminal.Color.WHITE, Terminal.Color.BLACK);
                    screen.refresh();

                }

              }
            }
            else if(rowcount == (X + 1) && columncount == Y && (X + 1) < maze.length)
            {
          
              if(Enemy.getEnemyMap(X+1,Y).equals("N"))
              {
                if(columncount == 0)
                {
 
                   screen.putString(1 + leftSideOfGame,1 + topSideOfGame + X+1,  Enemy.getEnemyMap(X+1,Y),Terminal.Color.WHITE, Terminal.Color.BLACK);
                   screen.refresh();

                   count = 0;
                }
                else
                {
  
                    screen.putString(leftSideOfGame + 1 + Y + count ,1 + topSideOfGame + X+1,  Enemy.getEnemyMap(X+1,Y),Terminal.Color.WHITE, Terminal.Color.BLACK);
                    screen.refresh();

                }

              }
              else
              {
                 if(columncount == 0)
                 {
   
                     screen.putString(1 + leftSideOfGame,1 + topSideOfGame + X + 1,  Mine.getMineMap(X+1,Y),Terminal.Color.WHITE, Terminal.Color.BLACK);
                     screen.refresh();

                     count = 0;
                     
                 }
                 else
                 {
    
                     screen.putString(leftSideOfGame + 1 + Y + count,1 + topSideOfGame + X+1,  Mine.getMineMap(X+1,Y),Terminal.Color.WHITE, Terminal.Color.BLACK);
                     screen.refresh();

                 }

              }
            }
            else if(rowcount == X && columncount == (Y - 1) && (Y - 1) > -1)
            {

              if(Enemy.getEnemyMap(X,Y-1).equals("N"))
              {
                if(columncount == 0)
                {
      
                   screen.putString(leftSideOfGame + 1, 1 + topSideOfGame + X,  Enemy.getEnemyMap(X,Y-1),Terminal.Color.WHITE, Terminal.Color.BLACK);
                   screen.refresh(); 

                   count = 0;
                }
                else
                {
    
                   screen.putString(leftSideOfGame + 1 + Y-1 + count , 1 + topSideOfGame + X,  Enemy.getEnemyMap(X,Y-1),Terminal.Color.WHITE, Terminal.Color.BLACK);
                   screen.refresh(); 

                }

              }
              else
              {
                if(columncount == 0)
                {

                    screen.putString(leftSideOfGame + 1,1 + topSideOfGame + X,  Mine.getMineMap(X,Y-1),Terminal.Color.WHITE, Terminal.Color.BLACK);
                    screen.refresh();

                    count = 0;
                }
                else
                {

                    screen.putString(leftSideOfGame + 1 + Y-1 + count,1 + topSideOfGame + X,  Mine.getMineMap(X,Y-1),Terminal.Color.WHITE, Terminal.Color.BLACK);
                    screen.refresh();

                }

              }
            }
            else if(rowcount == X && columncount == (Y + 1) && (Y + 1) < maze[rowcount].length)
            {
      
              if(Enemy.getEnemyMap(X,Y+1).equals("N"))
              {
                if(columncount == 0)
                {

                    screen.putString(leftSideOfGame + 1,1 + topSideOfGame + X, Enemy.getEnemyMap(X,Y+1),Terminal.Color.WHITE, Terminal.Color.BLACK);
                    screen.refresh(); 

                    count = 0;
                }
                else
                {

                    screen.putString(1 + leftSideOfGame + Y+1 + count,1 + topSideOfGame + X, Enemy.getEnemyMap(X,Y+1),Terminal.Color.WHITE, Terminal.Color.BLACK);
                    screen.refresh(); 

                }
     
              }
              else
              {
                  if(columncount == 0)
                  {

                      screen.putString(leftSideOfGame + 1,1 + topSideOfGame + X, Mine.getMineMap(X,Y+1),Terminal.Color.WHITE, Terminal.Color.BLACK);
                      screen.refresh();

                      count = 0;
                  }
                  else
                  {

                      screen.putString(1 + leftSideOfGame + Y+1 + count ,1 + topSideOfGame + X, Mine.getMineMap(X,Y+1),Terminal.Color.WHITE, Terminal.Color.BLACK);
                      screen.refresh();

                  }
              }
            }
            else if(!maze[rowcount][columncount].equals("?") && Enemy.getEnemyMap(rowcount,columncount).equals("N"))
            {
                if(columncount == 0)
                {
                    screen.putString(leftSideOfGame + 1, 1 + rowcount + topSideOfGame,Enemy.getEnemyMap(rowcount, columncount), Terminal.Color.WHITE, Terminal.Color.BLACK);
                    screen.refresh();

                    count = 0;
                }
                else
                {

                    screen.putString(leftSideOfGame + 1 + columncount + count, 1 + rowcount + topSideOfGame,Enemy.getEnemyMap(rowcount, columncount), Terminal.Color.WHITE, Terminal.Color.BLACK);
                    screen.refresh();

                }
            }
            else
            {
               
                if(columncount == 0)
                {
                    if(maze[rowcount][columncount].equals("X"))
                    {
                        screen.putString(leftSideOfGame + 1, 1 + rowcount + topSideOfGame,maze[rowcount][columncount], Terminal.Color.GREEN, Terminal.Color.BLACK);
                        screen.refresh();
                        count = 0;
                    }
                    else
                    {
                        screen.putString(leftSideOfGame + 1, 1 + rowcount + topSideOfGame,maze[rowcount][columncount], Terminal.Color.RED, Terminal.Color.BLACK);
                        screen.refresh();
                        count = 0;
                    }
                }
                else
                {
                    if(maze[rowcount][columncount].equals("X"))
                    {
                        screen.putString(1 + leftSideOfGame  + columncount + count, 1 + rowcount + topSideOfGame,maze[rowcount][columncount], Terminal.Color.GREEN, Terminal.Color.BLACK);
                        screen.refresh();
                    }
                    else
                    {
                        screen.putString(1 + leftSideOfGame  + columncount + count, 1 + rowcount + topSideOfGame,maze[rowcount][columncount], Terminal.Color.RED, Terminal.Color.BLACK);
                        screen.refresh();
                    }
                }
            } 
            count++;
          }
          count = 1;
      }    
  }
}
    
