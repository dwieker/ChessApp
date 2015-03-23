import java.io.*;
import java.util.*;

public class EngineInterface implements Runnable{
   public static BufferedReader in;
   public static BufferedWriter out;
   private BoardPanel board;
   
   
   public EngineInterface(BoardPanel board, String enginePath)
   {
      this.board = board;
      try
      {
         Process p = new ProcessBuilder(enginePath).start();
         in = new BufferedReader( new InputStreamReader(p.getInputStream()) );
         out = new BufferedWriter( new OutputStreamWriter(p.getOutputStream()));

      }
      catch (IOException e)
      {
         System.out.println("Error loading engine.");
      }
      
         
   } 
    
   public void pipe(String msg)
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
   
   public void run()
   {     
      
      pipe("uci");
      flushInput();
            
      pipe("isready");
      flushInput();
      
      //pipe("position startpos");
      //pipe("go depth 1");
      

      
     
      
      
      
        
   
   }
   
   public void print(String msg)
   {
      System.out.println(msg);
   }
   
   public void flushInput()
   {
      try
      {
         while(in.ready())
         {
            print(in.readLine());  
         }
      }              
      catch(IOException e)
      {
      }

         
            
   }
    


}