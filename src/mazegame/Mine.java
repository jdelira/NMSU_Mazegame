package mazegame;
//Mine.java creation file
//to be used to create the mines the player 
//will progress through
import com.googlecode.lanterna.terminal.Terminal;
import com.mycompany.mavenproject1.Maze;
import com.mycompany.mavenproject1.Enemy;
import java.util.Random;


public class Mine extends Main
{
  private static String mineMap[][];
  private static int mineCount = 0, mineRow, mineColumn;
  private String exit, mineEffect, blank = " ";
  private static Random random = new Random();
  private static String difficulty;
  
  public static void diffCheck(int x)
  {
    mineRow = Maze.getMazeRow();
    mineColumn = Maze.getMazeColumn();

    switch(x)
    {
        case 1:
            mineCount = (int)(mineRow * mineColumn * .1667);
            difficulty = "easy";
            Mine easy = new Mine(mineRow,mineColumn); 
            break;
        case 2:
            mineCount = (int)(mineRow * mineColumn * .25);
            difficulty = "medium";
            Mine medium = new Mine(mineRow,mineColumn); 
            break;
            
        case 3:
            mineCount = (int)(mineRow * mineColumn * .3334);
            difficulty = "hard";
            Mine hard = new Mine(mineRow,mineColumn);
            break;
    }
  }
  
  public Mine(int Row, int Column)
  {
    mineMap = new String[Row][Column];
    for(int i = 0; i < Row; i++)
      for(int j = 0; j < Column; j++)
        mineMap[i][j] = blank;
    populateMineMap();    
  }
  
  public static void printMineMap() throws InterruptedException //prints the maze
   {
      
      int mineCount = 1;
      for(int i = Maze.leftSideOfGame; i < Maze.rightSideOfGame; i++)
      {
         screen.putString(i,Maze.topSideOfGame,"__", Terminal.Color.GREEN, Terminal.Color.BLACK);
         screen.putString(i,Maze.botSideOfGame,"__", Terminal.Color.GREEN, Terminal.Color.BLACK);
         screen.refresh();
      }
           
      for (int rowcount = 0; rowcount + Maze.topSideOfGame + 1 <= Maze.botSideOfGame; rowcount++)
      {
          screen.putString(Maze.leftSideOfGame,rowcount + Maze.topSideOfGame + 1, "|",Terminal.Color.WHITE, Terminal.Color.BLACK);
          screen.putString(Maze.rightSideOfGame,rowcount + Maze.topSideOfGame + 1, "|",Terminal.Color.WHITE, Terminal.Color.BLACK);
          screen.refresh();

         for (int columncount = 0; columncount < mineMap[rowcount].length; columncount++)            
         {
           if(Enemy.getEnemyMap(rowcount, columncount).equals("N"))
           {
               if(columncount == 0)
               {
                   screen.putString(1 + Maze.leftSideOfGame, 1 + rowcount + Maze.topSideOfGame,Enemy.getEnemyMap(rowcount, columncount), Terminal.Color.GREEN, Terminal.Color.BLACK);
                   screen.refresh();
                   mineCount = 0;
                   
               }
               else
               {
                   screen.putString(columncount + Maze.leftSideOfGame + 1 + mineCount, 1 + rowcount + Maze.topSideOfGame,Enemy.getEnemyMap(rowcount, columncount), Terminal.Color.GREEN, Terminal.Color.BLACK);
                   screen.refresh();

               }
           }
           else
           {
             if(columncount == 0)
             {
                 screen.putString(1 + Maze.leftSideOfGame, 1 + rowcount + Maze.topSideOfGame,mineMap[rowcount][columncount], Terminal.Color.GREEN, Terminal.Color.BLACK);
                 screen.refresh();
   
                 mineCount = 0;
             }
             else
             {
                 screen.putString(columncount + Maze.leftSideOfGame + 1 + mineCount, 1 + rowcount + Maze.topSideOfGame,mineMap[rowcount][columncount], Terminal.Color.GREEN, Terminal.Color.BLACK);
                 screen.refresh();
  
             }
           }
           mineCount++;
         }
         mineCount = 1;
      }
   }
   
   public void populateMineMap()
   {
     int X, Y, side, mineTotal = 0;
     boolean hintPlaced = false;
     String Exit = "E", Hint = "H";
     
     X = random.nextInt(mineRow);
     Y = random.nextInt(mineColumn);
     
     side = random.nextInt(2);
     
     if(side == 1)
       mineMap[mineRow-1][Y] = Exit;
     else
       mineMap[X][mineColumn-1] = Exit;
     
     X = random.nextInt(mineRow);
     Y = random.nextInt(mineColumn);
     
     while(hintPlaced == false)
     { 
       if(!mineMap[X][Y].equals(Exit) && !(X == 0 && Y == 0))
       {
         mineMap[X][Y] = Hint;
         mineTotal++;
         hintPlaced = true;
       }
       else
       {
         X = random.nextInt(mineRow);
         Y = random.nextInt(mineColumn);
       }
     }
     
     while(mineTotal < mineCount)
     {
       if(mineMap[X][Y] == blank && !(X == 0 && Y == 0))
       {
         Effect(X, Y);
         mineTotal++;
       }
       else
       {
         X = random.nextInt(mineRow);
         Y = random.nextInt(mineColumn);
       }
     }
   }
   
   
   public static String getMineMap(int X, int Y)
   {
     return mineMap[X][Y];
   }
   
   public static void setMineMap(int X, int Y, String info)
   {
     mineMap[X][Y] = info;
   }
   
   public static int getMineCount()
   {
     return mineCount;
   }
   
   public static void Effect(int X, int Y)
   {
     String Frozen = "F", Teleport = "T", Speed = "S", Death = "*", Life = "1";
     
     int weight = random.nextInt(1000);
     
     if(difficulty.equals("easy"))
     {
       if(weight < 300)
         mineMap[X][Y] = Life;
       if(weight > 300 && weight < 500)
         mineMap[X][Y] = Speed;
       if(weight > 500 && weight < 700)
         mineMap[X][Y] = Teleport;
       if(weight > 700 && weight < 850)
         mineMap[X][Y] = Frozen;
       if(weight > 850)
         mineMap[X][Y] = Death;
     }
     if(difficulty.equals("medium"))
     {
       if(weight < 250)
         mineMap[X][Y] = Life;
       if(weight > 250 && weight < 450)
         mineMap[X][Y] = Speed;
       if(weight > 450 && weight < 650)
         mineMap[X][Y] = Teleport;
       if(weight > 650 && weight < 800)
         mineMap[X][Y] = Frozen;
       if(weight > 800)
         mineMap[X][Y] = Death;
     }
     if(difficulty.equals("hard"))
     {
       if(weight < 100)
         mineMap[X][Y] = Life;
       if(weight > 100 && weight < 250)
         mineMap[X][Y] = Speed;
       if(weight > 250 && weight < 400)
         mineMap[X][Y] = Teleport;
       if(weight > 400 && weight < 650)
         mineMap[X][Y] = Frozen;
       if(weight > 650)
         mineMap[X][Y] = Death;
     }    
   }
}