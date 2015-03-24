import java.io.*;
import java.util.*;

public class EngineInterface{
   
   public static BufferedReader in;
   public static BufferedWriter out;
   public static BoardPanel boardPanel;
   
   
   public EngineInterface(BoardPanel board)
   {
      this.boardPanel = board;    
   } 
    
   public static void pipe(String msg)
   {
      try
      {
         print(msg);
         out.write(msg +"\n");
         out.flush();
      }
      catch(IOException e)
      {
      }
          
   }
      
   public static void print(String msg)
   {
      System.out.println(msg);
   }
   
      
   public static void handleIDandOptions()
   {
      String response;
      
      try
      {
         while(!(response = in.readLine()).equals("uciok"))
         {
            //process the input!
            print(response);
         }
      }
      catch (IOException e){}
      finally
      {
         return;
      }
       
   }
   
   public static void loadEngine(String enginePath)
   {
      try
      {
         Process p = new ProcessBuilder(enginePath).start();
         in = new BufferedReader( new InputStreamReader(p.getInputStream()) );
         out = new BufferedWriter( new OutputStreamWriter(p.getOutputStream()));


        pipe("uci");
        handleIDandOptions();           
        pipe("isready");
        print(in.readLine());
        
      }
      catch (IOException e)
      {
         System.out.println("Error loading engine.");
      }
   }
   
   
   public static void engineMove()
   {
      if(out == null || in == null) return;
      
      
      EngineInterface.pipe("position fen " + boardPanel.board.toString());
      EngineInterface.pipe("go depth " + SettingsMenu.settings.getInt("depth", 1));
      
      String response;
      try
      {
      
         do{
            response = in.readLine();
            System.out.println(response);
         }while(!response.split(" ")[0].equals("bestmove"));
         
         response = response.split(" ")[1];
         SquarePanel a = boardPanel.squares[Character.getNumericValue(response.charAt(1)) - 1][response.charAt(0) - 'a'];
         boardPanel.setActiveSquare(a);
         SquarePanel b = boardPanel.squares[Character.getNumericValue(response.charAt(3)) - 1][response.charAt(2) - 'a'];
      
         try
         {
            boardPanel.movePiece(a,b);
         }
         catch (Exception e)
         {
            System.out.println("engine fucked up");
         }
                  
      }
      catch(IOException e)
      {
      
      
      }
      
   
   }

    


}