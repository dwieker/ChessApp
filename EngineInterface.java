import java.io.*;
import java.util.*;

public class EngineInterface{
   
   public static BufferedReader in;
   public static BufferedWriter out;
   public BoardPanel boardPanel;
   public Log outputLog;
   
   
   public EngineInterface(){} 
    
   public void pipe(String msg)
   {
      try
      {
         outputLog.addText(msg);
         out.write(msg +"\n");
         out.flush();
      }
      catch(IOException e)
      {
      }
      catch(NullPointerException e)
      {
         //there's no engine connected!
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
            Log.addText(response);
         }
      }
      catch (IOException e){} 
   }
   
   public void loadEngine(String enginePath)
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
         System.out.println(e);
      }
   }
   
   
   public void getEngineMove()
   {
      if(out == null || in == null) return;
      
      
      pipe("position fen " + boardPanel.board.toString());
      pipe("go depth " + SettingsMenu.settings.getInt("depth", 1));
      
      String response;
      try
      {
      
         do{
            response = in.readLine();
            Log.addText(response);
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
            Log.addText("engine fucked up");
         }
                  
      }
      catch(IOException e)
      {
      
      
      }
      
   
   }
   
   public void attatchBoard(BoardPanel board)
   {
      this.boardPanel = board;
   }
   
   public void attatchLog(Log log)
   {
      this.outputLog = log;
   }

    


}