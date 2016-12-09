package mazeGame;
import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.TerminalSize;

public class Main 
{
    public static Screen screen;
    public static Key key;
    public static TerminalSize term;
    public static String response, response1 = "yes", response2 = "exit", response3 = "game", response4 = "help", response5 = "no";
    public static int currentLine, length, termColumns, termRows;
    public static void main(String... args) throws InterruptedException{
         
         int furtherDownLine;
         int[] mapDefinitions = new int[3];
         boolean r1Result = true, r2Result = false, r3Result = true, keepRunning = true, keepRunning1 = true,playAgain = false, letsPlay = false;
         screen = TerminalFacade.createScreen(); 
         TerminalSize term = screen.getTerminalSize(); //use getColumns and getRows to find column and row value of screen
         StringBuilder sb = new StringBuilder();    //holds string value for normal key value(s)
         termColumns = term.getColumns();
         termRows = term.getRows();

    while(!playAgain)
    {
         screen.startScreen();
         screen.putString(25, 10, "Welcome to Maze Runner:", Terminal.Color.WHITE, Terminal.Color.BLACK);
         screen.putString(25, 11, "->To find out what Maze Runner is all about, type 'help'", Terminal.Color.WHITE, Terminal.Color.BLACK);
         screen.putString(25, 12, "->To Play a new game, type 'game'", Terminal.Color.WHITE, Terminal.Color.BLACK);
         screen.putString(25, 13, "->To exit this program, type 'exit'", Terminal.Color.WHITE, Terminal.Color.BLACK);
         screen.refresh(); currentLine = 14; length = 25;
         while(keepRunning && !r2Result && r1Result)
         {
             key = screen.readInput();
             
             //Keep screen up until input is received
             while(key == null)
             {
                 key = screen.readInput();
             }
           
             
            //Discover input
             switch(key.getKind())
             {
                 //SCREEN CLOSE
                 case Escape:
                     screen.stopScreen();
                     System.exit(0);
                     break;
                 case NormalKey:
                     sb.append(key.getCharacter());
                     response = sb.toString().toLowerCase();
                     screen.putString(length++,currentLine, key.getCharacter() + "",Terminal.Color.WHITE, Terminal.Color.BLACK);
                     screen.refresh();
                     //EXIT
                     if(response.equals(response2))
                     {
                         currentLine++;
                         r2Result = true;
                         screen.clear();
                         currentLine = 10;
                         screen.putString(length,currentLine, "Until next time...", Terminal.Color.GREEN, Terminal.Color.BLACK);
                         screen.refresh();
                         screen.stopScreen();
                         System.exit(0);
                         break;
                         
                     }
                     //HELP
                     else if(response.equals(response4))
                     {
                      
                         //print help content by calling Help.java
                         currentLine++;
                         screen.putString(length,currentLine, "Accessing Help file...", Terminal.Color.WHITE, Terminal.Color.BLACK);
                         screen.refresh();
                         Thread.sleep(400);
                         screen.clear();
                         letsPlay = Help.MainHelp();
                         screen.refresh();
                         sb = new StringBuilder();
                         if(letsPlay == false)
                         {
                            screen.clear();
                            currentLine = 10;
                            screen.putString(length,currentLine, "Until next time...", Terminal.Color.GREEN, Terminal.Color.BLACK);
                            screen.refresh();
                            screen.stopScreen();
                            System.exit(0);
                         }
                         else
                         {
                             break;
                         }
                         
                         
                     }
                     //GAME
                     else if(response.equals(response3))
                     {
                         Thread.sleep(400);
                         furtherDownLine = length + 14;
                         screen.clear();
                         currentLine = 10;
                         screen.putString(length,currentLine, "Acessing Game",Terminal.Color.WHITE, Terminal.Color.BLACK); 
               
                         screen.refresh();
                         Thread.sleep(100);
                         screen.putString(furtherDownLine,currentLine, ".",Terminal.Color.WHITE, Terminal.Color.BLACK);
                         screen.refresh();
                         furtherDownLine++;
                         Thread.sleep(100);
                         screen.putString(furtherDownLine,currentLine, ".",Terminal.Color.WHITE, Terminal.Color.BLACK);
                         screen.refresh();
                         furtherDownLine++;
                         Thread.sleep(100);
                         screen.putString(furtherDownLine,currentLine, ".",Terminal.Color.WHITE, Terminal.Color.BLACK);
                         screen.refresh();
                         Thread.sleep(100);
                         
                         mapDefinitions = Game.main2();
       
                         //non-custom game
                         if(mapDefinitions[2] == 0)
                         {
                 
                            //pick for non-custom
                            Maze.sizeCheck(mapDefinitions[0],0);
                            Mine.diffCheck(mapDefinitions[1]);  
                            Enemy.diffCheck(mapDefinitions[1]);
                            Maze.printMaze();
                            Player.moveCheck();
                         }
                         //custom game
                         else
                         {
                            Maze.sizeCheck(mapDefinitions[2],mapDefinitions[3]);
                            Mine.diffCheck(mapDefinitions[1]);
                            Enemy.diffCheck(mapDefinitions[1]);
                            if(mapDefinitions[2] > 21 || mapDefinitions[3] > 21)
                            {
                                screen.clear();
                                screen.putString(10,10, "Please increase the size of your screen to accomodate your game", Terminal.Color.WHITE, Terminal.Color.BLACK);
                                screen.refresh();
                                Thread.sleep(5000);
                                screen.clear();
                                screen.refresh();
                                term = screen.getTerminalSize();
                                termColumns = term.getColumns();
                                termRows = term.getRows();
                            }
                            Maze.printMaze();
                            screen.refresh();
                            Player.moveCheck();
   
                         }
                        
                         screen.clear();
                         currentLine = 10;
                         screen.putString(length,Maze.topSideOfGame - 2, "Here is the full maze!", Terminal.Color.WHITE, Terminal.Color.BLACK);
                         screen.refresh();
                         Thread.sleep(400);
                         Mine.printMineMap();
                         screen.refresh();
                         Thread.sleep(300);
                         Player.resetLives();
                         screen.putString(length,Maze.botSideOfGame + 2, "Would you like to play again? Please type yes or no.", Terminal.Color.WHITE, Terminal.Color.BLACK);
                         screen.refresh();
                         sb = new StringBuilder();
                         while(keepRunning1)
                        {
                            key = screen.readInput();

                            //Keep screen up until input is received
                            while(key == null)
                            {
                                key = screen.readInput();
                            }

                           //Discover input
                            switch(key.getKind())
                            {
                                case NormalKey:
                                    sb.append(key.getCharacter());
                                    response = sb.toString().toLowerCase();
                                    if(response.length() > 3)
                                    {
                                        screen.clear();
                                        currentLine = 10;
                                        screen.putString(length,currentLine, "Invalid response", Terminal.Color.RED, Terminal.Color.BLACK);
                                        screen.refresh();
                                        Thread.sleep(1000);
                                        screen.clear();
                                        currentLine = 10;
                                        screen.putString(length,currentLine, "Would you like to play again? Please type yes or no.", Terminal.Color.WHITE, Terminal.Color.BLACK);
                                        screen.refresh();
                                        sb = new StringBuilder();
                                        currentLine++;
                                    }
                                    else if(response.equals(response1))
                                    {
                                        playAgain = true;
                                        screen.clear();
                                    }
                                    else if(response.equals(response5))
                                    {
              
                                        screen.stopScreen();
                                        System.exit(0);
                                    }
                                    break;
                                default:
                                    screen.clear();
                                    currentLine = 10;
                                    screen.putString(length,currentLine, "Invalid response", Terminal.Color.RED, Terminal.Color.BLACK);
                                    screen.refresh();
                                    Thread.sleep(1000);
                                    screen.clear();
                                    currentLine = 10;
                                    screen.putString(length,currentLine, "Would you like to play again? Please type yes or no.", Terminal.Color.WHITE, Terminal.Color.BLACK);
                                    screen.refresh();
                                    sb = new StringBuilder();
                                    currentLine++;
                            }
                            if(playAgain == true)
                            {
                                break;
                            }
                        }
                         if(playAgain == true)
                         {
                             break;
                         }
                      }
                     else if(response.length() > 4)
                     {
                            screen.clear();
                            currentLine = 10;
                            screen.putString(length,currentLine, "Invalid response", Terminal.Color.RED, Terminal.Color.BLACK);
                            screen.refresh();
                            Thread.sleep(1000);
                            screen.clear();
                            currentLine = 10;
                            screen.putString(25, currentLine, "->To find out what Maze Runner is all about, type 'help'", Terminal.Color.WHITE, Terminal.Color.BLACK);
                            currentLine++;
                            screen.putString(25, currentLine, "->To Play a new game, type 'game'", Terminal.Color.WHITE, Terminal.Color.BLACK);
                            currentLine++;
                            screen.putString(25, currentLine, "->To exit this program, type 'exit'", Terminal.Color.WHITE, Terminal.Color.BLACK);
                            currentLine++;
                            screen.refresh();
                            sb = new StringBuilder();
                     }
                     break;   
                 default:
                     screen.clear();
                     currentLine = 10;
                     screen.putString(length,currentLine, "Invalid response", Terminal.Color.RED, Terminal.Color.BLACK);
                     screen.refresh();
                     Thread.sleep(1000);
                     screen.clear();
                     currentLine = 10;
                     screen.putString(25, currentLine, "->To find out what Maze Runner is all about, type 'help'", Terminal.Color.WHITE, Terminal.Color.BLACK);
                     currentLine++;
                     screen.putString(25, currentLine, "->To Play a new game, type 'game'", Terminal.Color.WHITE, Terminal.Color.BLACK);
                     currentLine++;
                     screen.putString(25, currentLine, "->To exit this program, type 'exit'", Terminal.Color.WHITE, Terminal.Color.BLACK);
                     currentLine++;
                     screen.refresh();
                     sb = new StringBuilder();
                     
             }
             if(letsPlay == true)
             {
                 letsPlay = false;
                 break;
                 
             }
             if(playAgain == true)
             {
                 break;
             }
         }
         
         if(playAgain == true)
         {
             playAgain = false;
         }
    }
  }
}
