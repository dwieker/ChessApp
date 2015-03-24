import java.io.*;
import java.util.*;

public class EngineInterface{
   
   public static BufferedReader in;
   public static BufferedWriter out;
   public static BoardPanel board;
   
   
   public EngineInterface(BoardPanel board, String enginePath)
   {
      this.board = board;
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
   
      
   public void handleIDandOptions()
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
    


}