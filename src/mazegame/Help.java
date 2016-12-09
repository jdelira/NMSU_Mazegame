//Help.java file
//lazy way to make help instruction :P
package mazegame;

import com.googlecode.lanterna.terminal.Terminal;
import static com.mycompany.mavenproject1.Main.currentLine;
import static com.mycompany.mavenproject1.Main.length;
import static com.mycompany.mavenproject1.Main.screen;

public class Help extends Main
{
  public static boolean MainHelp() throws InterruptedException
  {
      boolean wantsToPlay = false;
      boolean keepHelp = true;
      String opt1 = "play";
      String opt2 = "maze";
      String response1 = "yes";
      String response2 = "no";
      screen.clear();
     int furtherDownLine2 = 10;
     String helpOption = "";
     int endOfCut = 0;
     int countBy100 = 1;
     int countoff = 4;
     int startOfCut = 0;
     int newLine = 3;
     String helpMe = "Maze Runner is a strategy based game where the goal is to find the exit within a randomize maze.Within this maze there are many different obstacle that can both benefit or harm the player, and this help file will explain both how to play the Maze Runner game, and what each obstacle does within the map. To learn more about how to play the game, please type 'play'. To learn about the obstacles within the maze, type 'maze'.";
     String playHelp = ("\nTo play the game, at the main menu, type the word 'game' and then type in what size and difficulty you would like." +
           "\nOnce the map has been generated, the player can be moved around the map by using either \"WASD\" or \"8462\"." +
           "\n\n\"W\" or \"8\" will move the player up" + 
           "\n\"A\" or \"4\" will move the player left" +
           "\n\"D\" or \"6\" will move the player right" +
           "\n\"S\" or \"2\" will move the player down." +
           "\n\nTyping \"0\" or \"Q\" will display the players live total." +
           "\n\nTyping \"Exit\" \"Quit\" or \"Concede\" will exit a game in progress, by removing all of the players lives.\n");
     String mazeHelp = ("\nThere are many different obstacles within the maze, and here is a detailed description of what you can expect" +
           "\nto find and discover within the maze." +  
              
           "\n\n\"X\" - This is the player on the map. The player has a visual range of 1 room around them" +
           "\nThis visual range will display to the player what is surrounding them as they progress throughout the maze." + 
           "\nThis allows the player to adjust their movements depending on what is around them. The player starts with 5 lives." +
           
           "\n\n\"N\" - These are enemies that will hunt down the player as the player moves around the maze." +
           "\nIn the event an enemy catches a player, the player will lose a life, and the enemy will then be teleported" +
           "\nto a random location away from the player. Beware that if a player is reduced to 0 lives, they will lose the game." +
           
           "\n\n\"1\" - This obstacle is a '1-up', which will grant the player an additonal life if they move onto it." + 
           "\nThese are removed once found." +
           
           "\n\n\"*\" - This obstacle is a bomb, which when the player moves onto it, will remove a life. If the player is ever" + 
           "\nreduced to 0 lives. They will lose the game. The bombs are not removed once triggered." +
           
           "\n\n\"T\" - This obstacle is a teleportation device, that will teleport the player to a random location within the maze." + 
           "\nThese devices can be reused and are not removed once triggered, each use will teleport to a different random location." +
           "\nBeware, a teleportation device can teleport you onto any room within the maze including another a bomb, an enemy," +
           "\nor even a another teleportation device." +
          
           "\n\n\"F\" - This obstacle is a freeze trap will freeze the player, allowing the enemy to have a free move" +
           "\nand advance closer to the player. These devices are removed once triggered." +
           
           "\n\n\"S\" - This obstacle is a speed boost device, that will allow the player to have an additional move" + 
           "\nwithout the enemy being able to follow. These devices are removed once triggered, and are reusable." +
          
           "\n\n\"H\" - This obstacle is a hint, and once the player lands on it, the exit to the maze is revealed." +
           "\nEach map will only have 1 hint, which will be randomly placed within the maze. The hint is removed once discovered." +
          
           "\n\n\"E\" - This is the exit to the maze. Once the player advances to this room, the game is completed.\n");
     
     
     
     
     
     for(int i = 0; i < helpMe.length(); i++)
     {
         if(i == (100 * countBy100) - countoff)
         {
             endOfCut = (100 * countBy100) - countoff;
             screen.putString(1,newLine, helpMe.substring(startOfCut,endOfCut), Terminal.Color.WHITE, Terminal.Color.BLACK);
             screen.refresh();
             startOfCut = endOfCut;
             newLine = newLine + 1;
             countBy100++;
             countoff++;
         }
  
     }
     startOfCut = endOfCut;
     screen.putString(1,newLine, helpMe.substring(startOfCut,helpMe.length()), Terminal.Color.WHITE, Terminal.Color.BLACK);
     screen.refresh();
     
     
     currentLine = 10;
     StringBuilder sb5 = new StringBuilder();
     screen.clear();
     countoff = 4;
     while(keepHelp)
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
                     sb5.append(key.getCharacter());
                     response = sb5.toString().toLowerCase();
                     screen.putString(furtherDownLine2,currentLine, key.getCharacter() + "",Terminal.Color.WHITE, Terminal.Color.BLACK);
                     furtherDownLine2++;
                     screen.refresh();
                     break;
                 case Enter:
                     screen.clear();
                     if(response.equals(opt1))
                     {
                            newLine = 1;
                            startOfCut = 0;
                            countBy100 = 1;
                            for(int i = 0; i < playHelp.length(); i++)
                            {
                                if(i == (100 * countBy100) - countoff)
                                {
                                    endOfCut = (100 * countBy100) - countoff;
                                    screen.putString(1,newLine, playHelp.substring(startOfCut,endOfCut), Terminal.Color.WHITE, Terminal.Color.BLACK);
                                    screen.refresh();
                                    startOfCut = endOfCut;
                                    newLine = newLine + 1;
                                    countBy100++;
                                }

                            }
                      
                            startOfCut = endOfCut;
                            screen.putString(1,newLine, playHelp.substring(startOfCut,playHelp.length()), Terminal.Color.WHITE, Terminal.Color.BLACK);
                            screen.refresh();
                            
                            newLine = newLine + 4;
                            Thread.sleep(1000);
                            screen.putString(5,newLine, "So, wanna play? Please type yes or no.", Terminal.Color.WHITE, Terminal.Color.BLACK);
                            screen.refresh();
                            newLine++;
                            sb5 = new StringBuilder();
                            currentLine++;
                            furtherDownLine2 = 10;
                            while(keepHelp)
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
                                        sb5.append(key.getCharacter());
                                        response = sb5.toString().toLowerCase();
   
                                        screen.putString(furtherDownLine2,currentLine, key.getCharacter() + "",Terminal.Color.WHITE, Terminal.Color.BLACK);
                                        furtherDownLine2++;
                                        screen.refresh();
                                        break;
                                    case Enter:
                                        if(response.equals(response1))
                                        {
                                            wantsToPlay = true;
                                            screen.clear();
                                            return wantsToPlay;
                                        }
                                        else if(response.equals(response2))
                                        {
                                            wantsToPlay = false;
                                            screen.clear();
                                            return wantsToPlay;
                                        }
                                    default:
                                        screen.clear();
                                        currentLine = 10;
                                        furtherDownLine2 = 10;
                                        screen.putString(furtherDownLine2,currentLine, "Invalid response", Terminal.Color.RED, Terminal.Color.BLACK);
                                        screen.refresh();
                                        Thread.sleep(1000);
                                        screen.clear();
                                        furtherDownLine2 = 10;
                                        currentLine = 10;
                                        screen.putString(furtherDownLine2,currentLine, "To play maze game, type 'yes' or 'no'", Terminal.Color.WHITE, Terminal.Color.BLACK);
                                        screen.refresh();
                                        currentLine++;
                                        furtherDownLine2 = 10;
                                        sb5 = new StringBuilder();
                                }
                            
                            
                            }
                     }
                     else if(response.equals(opt2))
                     {
                            newLine = 1;
                            startOfCut = 0;
                            countBy100 = 1;
                            for(int i = 0; i < mazeHelp.length(); i++)
                            {
                                if(i == (100 * countBy100) - countoff)
                                {
                                    endOfCut = (100 * countBy100) - countoff;
                                    screen.putString(1,newLine, mazeHelp.substring(startOfCut,endOfCut), Terminal.Color.WHITE, Terminal.Color.BLACK);
                                    screen.refresh();
                                    startOfCut = endOfCut;
                                    newLine = newLine + 1;
                                    countBy100++;
                                }

                            }
                        
                            startOfCut = endOfCut;
                            screen.putString(1,newLine, mazeHelp.substring(startOfCut,mazeHelp.length()), Terminal.Color.WHITE, Terminal.Color.BLACK);
                            screen.refresh();
                            
                            newLine = newLine + 4;
                            Thread.sleep(1000);
                            screen.putString(5,newLine, "So, wanna play? Please type yes or no.", Terminal.Color.WHITE, Terminal.Color.BLACK);
                            screen.refresh();
                            newLine++;
                            sb5 = new StringBuilder();
                        
                            while(keepHelp)
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
                                        sb5.append(key.getCharacter());
                                        response = sb5.toString().toLowerCase();
                                        screen.putString(furtherDownLine2,newLine, key.getCharacter() + "",Terminal.Color.WHITE, Terminal.Color.BLACK);
                                        screen.refresh();
                                        furtherDownLine2++;
                                        break;
                                    case Enter:
                                        if(response.equals(response1))
                                        {
                                            wantsToPlay = true;
                                            screen.clear();
                                            return wantsToPlay;
                                        }
                                        else if(response.equals(response2))
                                        {
                                            wantsToPlay = false;
                                            screen.clear();
                                            return wantsToPlay;
                                        }
                                    default:
                                        screen.clear();
                                        currentLine = 10;
                                        furtherDownLine2 = 10;
                                        screen.putString(furtherDownLine2,currentLine, "Invalid response", Terminal.Color.RED, Terminal.Color.BLACK);
                                        screen.refresh();
                                        Thread.sleep(1000);
                                        screen.clear();
                                        furtherDownLine2 = 10;
                                        currentLine = 10;
                                        screen.putString(furtherDownLine2,currentLine, "To play maze game, type 'yes' or 'no'", Terminal.Color.WHITE, Terminal.Color.BLACK);
                                        screen.refresh();
                                        currentLine++;
                                        furtherDownLine2 = 10;
                                        sb5 = new StringBuilder();
                                }
                            
                            }
                            
                            
                            
                           break;
                     }
                 default:
                     screen.clear();
                     currentLine = 10;
                     furtherDownLine2 = 10;
                     screen.putString(furtherDownLine2,currentLine, "Invalid response", Terminal.Color.RED, Terminal.Color.BLACK);
                     screen.refresh();
                     Thread.sleep(1000);
                     screen.clear();
                     furtherDownLine2 = 10;
                     currentLine = 10;
                     screen.putString(furtherDownLine2,currentLine, "To learn about how to 'play', type 'play'. To learn how the maze works, type 'maze'.", Terminal.Color.WHITE, Terminal.Color.BLACK);
                     screen.refresh();
                     //EXIT
         
     }
    

               
    
   }
     return wantsToPlay;
}
}
           
     