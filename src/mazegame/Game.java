//Game.java file to be used
//as main for the MazeGame Project
package mazegame;
import com.googlecode.lanterna.terminal.Terminal;


public class Game extends Main
{
  
  public static int[] main2() throws InterruptedException
  {
    currentLine = 10; length = 16;
    String size, size1 = "small", size2 = "medium", size3 = "large", size4 = "custom";
    String strength, strength1 = "easy", strength2 = "medium", strength3 = "hard";
    int customRow = 0, customCol = 0, digitValue = 0;
    int[] mapSettings = new int[4];
    
    StringBuilder sb1 = new StringBuilder();
    boolean keepRunning1 = true;
    boolean keepRunning2 = true;
    boolean keepRunning3 = true;
    boolean keepRunning4 = true;
    boolean isDigit = true;
    screen.clear();
    
    screen.putString(length, currentLine, "Please choose the size of your game(Small, Medium, Large, or Custom):", Terminal.Color.WHITE, Terminal.Color.BLACK);
    screen.refresh();
    currentLine++;
    while(keepRunning1)
    {
        key = screen.readInput();
        while(key == null)
        {
            key = screen.readInput();
        }
        
        switch(key.getKind())
        {
            case NormalKey:
                sb1.append(key.getCharacter());
                response = sb1.toString().toLowerCase();
                screen.putString(length, currentLine, key.getCharacter() + "", Terminal.Color.WHITE, Terminal.Color.BLACK);
                screen.refresh();
                length++;
                if(response.length() > 6)
                {
                    screen.clear();
                    screen.putString(25, 10, "Invalid size", Terminal.Color.RED, Terminal.Color.BLACK);
                    screen.refresh();
                    Thread.sleep(1000);
                    screen.clear();
                    currentLine = 10;
                    length = 16;
                    response =  "";
                    sb1 = new StringBuilder();
                    screen.putString(length, currentLine, "Please choose the size of your game(Small, Medium, Large, or Custom):", Terminal.Color.WHITE, Terminal.Color.BLACK);
                    screen.refresh();
                    currentLine++;
                }
                else if(response.equals(size1) || response.equals(size2) || response.equals(size3) || response.equals(size4))
                {
                    size = response;
                    if(response.equals(size1))
                    {
                        mapSettings[0] = 1;
                    }
                    else if(response.equals(size2))
                    {
                        mapSettings[0] = 2;
                    }
                    else if(response.equals(size3))
                    {
                        mapSettings[0] = 3;
                    }
                    else
                    {
                        mapSettings[0] = 4;
                    }
                    
                    currentLine = 10;
                    length = 16;
                    screen.clear();
                    screen.putString(length, currentLine, "Please choose the difficulty of your game(Easy, Medium, or Hard):", Terminal.Color.WHITE, Terminal.Color.BLACK);
                    currentLine++;
                    screen.refresh();
                    sb1 = new StringBuilder();
                    
                    while(keepRunning2)
                    {
    
                        key = screen.readInput();
                        while(key == null)
                        {
                            key = screen.readInput();
                        }
                        
                        switch(key.getKind())
                        {
                            case NormalKey:
                                sb1.append(key.getCharacter());
                                response = sb1.toString().toLowerCase();
                                screen.putString(length, currentLine, key.getCharacter() + "", Terminal.Color.WHITE, Terminal.Color.BLACK);
                                screen.refresh();
                                length++;
                                
                                if(response.length() > 6)
                                {
                                    screen.clear();
                                    screen.putString(25, 10, "Invalid difficulty", Terminal.Color.RED, Terminal.Color.BLACK);
                                    screen.refresh();
                                    Thread.sleep(1000);
                                    screen.clear();
                                    currentLine = 10;
                                    length = 16;
                                    screen.putString(length, currentLine, "Please choose the difficulty of your game(Easy, Medium, or Hard):", Terminal.Color.WHITE, Terminal.Color.BLACK);
                                    screen.refresh();
                                    currentLine++;
                                    response = "";
                                    sb1 = new StringBuilder();
                                }
                                else if(response.equals(strength1) || response.equals(strength2) || response.equals(strength3))
                                {
                                    if(response.equals(strength1))
                                    {
                                        mapSettings[1] = 1;
                                    }
                                    else if(response.equals(strength2))
                                    {
                                        mapSettings[1] = 2;
                                    }
                                    else
                                    {
                                        mapSettings[1] = 3;
                                    }
                                    strength = response;
                                    if(!size.equals(size4))
                                    {
                                        screen.clear();
                                        currentLine = 10;
                                        length = 16;
                                        screen.putString(length, currentLine, "You have selected a " + size + " maze and " + strength + " difficulty, Enjoy!", Terminal.Color.WHITE, Terminal.Color.BLACK);
                                        screen.refresh();
                                        Thread.sleep(1000);
                                        screen.clear();
                                        return mapSettings;
                                    }
                                    else
                                    { 
                                        screen.clear();
                                        currentLine = 10;
                                        length = 16;
                                        screen.putString(length, currentLine, "You have selected a " + size4 + " maze and " + strength + " difficulty, Enjoy!", Terminal.Color.WHITE, Terminal.Color.BLACK);
                                        screen.refresh();
                                        Thread.sleep(1000);
                                        screen.clear();
                                        currentLine = 10;
                                        length = 16;
                                        screen.putString(length, currentLine, "Please enter the rows of your custom game:", Terminal.Color.WHITE, Terminal.Color.BLACK);
                                        screen.refresh();
                                        sb1 = new StringBuilder();
                                        currentLine++;
                                      while(keepRunning3)
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
                                                    sb1.append(key.getCharacter());
                                                    response = sb1.toString().toLowerCase();
                                                    screen.putString(length, currentLine, key.getCharacter() + "", Terminal.Color.WHITE, Terminal.Color.BLACK);
                                                    screen.refresh();
                                                    length++;
                                                    break;
                                                case Enter:
                                                        isDigit = true;
                                                        for(int i = 0; i < response.length(); i++)
                                                        {
                                                            if(!(Character.isDigit(response.charAt(i))))
                                                            {
                                                                isDigit = false;
                                                                break;
                                                            }
                                                        }

                                                        if(isDigit == true)
                                                        {
                                                            digitValue = Integer.parseInt(response);
                                                        }
                                                    
                                                            //is not a digit
                                                        if(isDigit == false)
                                                        {
                                                            currentLine = 10;
                                                            length = 16;
                                                            screen.clear();
                                                            screen.putString(length, currentLine, "Invalid row input" + response, Terminal.Color.RED, Terminal.Color.BLACK);
                                                            screen.refresh();
                                                            Thread.sleep(1000);
                                                            screen.clear();
                                                            currentLine = 10;
                                                            screen.putString(length, currentLine, "Please enter the rows of your custom game:", Terminal.Color.WHITE, Terminal.Color.BLACK);
                                                            response = "";
                                                            currentLine++;
                                                            screen.refresh();
                                                            sb1 = new StringBuilder();
                                                            break;

                                                        }
                                                        //if number entered is less than 5
                                                        else if(digitValue < 5)
                                                        {  
                                                            screen.clear();
                                                            currentLine = 10;
                                                            screen.putString(2, currentLine, "A maze can not be smaller then 5 rows, please enter a number greater than or equal to 5", Terminal.Color.WHITE, Terminal.Color.BLACK);
                                                            screen.refresh();
                                                            Thread.sleep(1000);
                                                            screen.clear();
                                                            currentLine = 10;
                                                            length = 16;
                                                            screen.putString(length, currentLine, "Please enter the rows of your custom game:", Terminal.Color.WHITE, Terminal.Color.BLACK);
                                                            currentLine++;
                                                            screen.refresh();
                                                            response = "";
                                                            sb1 = new StringBuilder();
                                                            break;
                                                        }
                                                    
                                                    
                                                    
                                                        isDigit = true;
                                                        customRow = Integer.parseInt(response);
                                                        mapSettings[2] = customRow;
                                                        length = 16;
                                                        currentLine++;
                                                        screen.putString(length, currentLine, "Please enter the columns of your custom game:", Terminal.Color.WHITE, Terminal.Color.BLACK);
                                                        screen.refresh();
                                                        sb1 = new StringBuilder();
                                                        currentLine++;
  
                                                        while(keepRunning4)
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
                                                                      
                                                                      
                                                                      sb1.append(key.getCharacter());
                                                                      response = sb1.toString().toLowerCase();
                                                                      screen.putString(length, currentLine, key.getCharacter() + "", Terminal.Color.WHITE, Terminal.Color.BLACK);
                                                                      length++;
                                                                      screen.refresh();
                                                                      break;
                                                                  case Enter:
                                                                        isDigit = true;
                                                                        for(int i = 0; i < response.length(); i++)
                                                                        {
                                                                            if(!(Character.isDigit(response.charAt(i))))
                                                                            {
                                                                                isDigit = false;
                                                                                break;
                                                                            }
                                                                        }

                                                                        if(isDigit == true)
                                                                        {
                                                                            digitValue = Integer.parseInt(response);
                                                                        }

                                                                        if(isDigit == false)
                                                                        {
                                                                            length = 16;
                                                                            currentLine = 10;
                                                                            screen.clear();
                                                                            screen.putString(length, currentLine, "Invalid column input", Terminal.Color.RED, Terminal.Color.BLACK);
                                                                            screen.refresh();
                                                                            Thread.sleep(1000);
                                                                            screen.clear();
                                                                            currentLine = 10;
                                                                            
                                                                            screen.putString(length, currentLine, "Please enter the columns of your custom game:", Terminal.Color.WHITE, Terminal.Color.BLACK);
                                                                            currentLine++;
                                                                            screen.refresh();
                                                                            response = "";
                                                                            sb1 = new StringBuilder();
                                                                            break;

                                                                        }
                                                                        else if(digitValue < 5)
                                                                        {
                                                                            currentLine = 10;
                                                                            length = 16;
                                                                            screen.clear();
                                                                            screen.putString(2, currentLine, "A maze can not be smaller then 5 columns, please enter a number greater than or equal to 5", Terminal.Color.RED, Terminal.Color.BLACK);
                                                                            screen.refresh();
                                                                            Thread.sleep(1000);
                                                                            screen.clear();
                                                                            currentLine = 10;
                                                                            screen.putString(length, currentLine, "Please enter the columns of your custom game:", Terminal.Color.WHITE, Terminal.Color.BLACK);
                                                                            currentLine++;
                                                                            screen.refresh();
                                                                            response = "";
                                                                            sb1 = new StringBuilder();
                                                                            break;
                                                                        }
                                                                          customCol = Integer.parseInt(response);
                                                                          mapSettings[3] = customCol;
                                                                          return mapSettings;
                                                                       
                                                                      
                                                                  default:
                                                                          currentLine = 10;
                                                                          length = 16;
                                                                          screen.clear();
                                                                          screen.putString(length, currentLine, "Invalid column input", Terminal.Color.RED, Terminal.Color.BLACK);
                                                                          screen.refresh();
                                                                          Thread.sleep(1000);
                                                                          screen.clear();
                                                                          currentLine = 10;
                                                                          screen.putString(length, currentLine, "Please enter the columns of your custom game:", Terminal.Color.WHITE, Terminal.Color.BLACK);
                                                                          currentLine++;
                                                                          screen.refresh();
                                                                          response = "";
                                                                          sb1 = new StringBuilder();
                                                            }
                                                        }
                                                        break;
                                                default:
                                  
                                                    currentLine = 10;
                                                    screen.clear();
                                                    screen.putString(length, currentLine, "Invalid row input", Terminal.Color.RED, Terminal.Color.BLACK);
                                                    screen.refresh();
                                                    Thread.sleep(1000);
                                                    screen.clear();
                                                    currentLine = 10;
                                                    screen.putString(length, currentLine, "Please enter the rows of your custom game:", Terminal.Color.WHITE, Terminal.Color.BLACK);
                                                    currentLine++;
                                                    screen.refresh();
                                                    response = "";
                                                    sb1 = new StringBuilder();
                                            }  
                                      }
                                         }
                                }
                                break;
                          default:
                         
                                currentLine = 10;
                                length = 16;
                                screen.clear();
                                screen.putString(length, currentLine, "Invalid difficulty", Terminal.Color.RED, Terminal.Color.BLACK);
                                screen.refresh();
                                Thread.sleep(1000);
                                screen.clear();
                                currentLine = 10;
                                screen.putString(length, currentLine, "Please choose the difficulty of your game(Easy, Medium, or Hard):", Terminal.Color.WHITE, Terminal.Color.BLACK);
                                currentLine++;
                                screen.refresh();
                                response = "";
                                sb1 = new StringBuilder();           
                       }
                    }
                }  
              break;  
       default:

            currentLine = 10;
            length = 16;
            screen.clear();
            screen.putString(length, currentLine, "Invalid size", Terminal.Color.RED, Terminal.Color.BLACK);
            screen.refresh();
            Thread.sleep(1000);
            screen.clear();
            currentLine = 10;
            screen.putString(length, currentLine, "Please choose the size of your game(Small, Medium, Large, or Custom):", Terminal.Color.WHITE, Terminal.Color.BLACK);
            currentLine++;
            screen.refresh();
            response = "";
            sb1 = new StringBuilder();     
    }
    }
    return mapSettings;
  }
}
    