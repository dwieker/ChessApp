import java.util.*;
import java.io.*;

public class UCI{
   
   public static int searchDepth = 5;
   
   public static void UCILoop(){
      Scanner input = new Scanner(System.in);
      Scanner parser;
      Board board = new Board();
      Boolean playOn = true;
      
      while(playOn){
         parser = new Scanner(input.nextLine());          
         playOn = handleCommand(parser.next(),parser,board);   
            //System.out.println(board);              
      }                           
   }
   
   private static boolean handleCommand(String token, Scanner parser, Board board){
      switch(token){
         case "quit": 
            return false;
         case "uci": 
            System.out.println("id name Chess Engine");
            System.out.println("id author Jesenia Garcia-Rovetta and Devin Wieker");
            System.out.println("uciok");
            return true;
         case "isready":
            System.out.println("readyok");
            return true;          
         case "position":
            setupState(parser, board);
            return true;
         case "go":
            handleGo(parser, board);
            return true;       
      }
      return true;
   }
   
   private static void setupState(Scanner parser, Board board){
      String token;
      while(parser.hasNext()){
         token = parser.next();
         if(token.equals("fen")) board.setup(parser.next());  
         else if(token.equals("startpos")) board.setup(Board.STARTFEN);
         else if(token.equals("moves")) continue;
         else board.movePiece(new Move(token));  
      }
   } 
   
   public static void handleGo(Scanner parser, Board board){
      while(parser.hasNext()){
         switch(parser.next()){
            case "depth": 
               searchDepth = parser.nextInt();
         }
      }
      System.out.println("bestmove " + AI.calculateMove(board, searchDepth));
      
   }
               

      
   
            
}