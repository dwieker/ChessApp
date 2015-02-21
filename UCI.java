import java.util.*;
import java.io.*;

public class UCI{
   
   public static void UCILoop(){
      Scanner input = new Scanner(System.in);
      Scanner parser;
      String token, line;
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
            calculateMove(board);
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
   
   private static void calculateMove(Board board){
      Move[] moves = new Move[264];
      board.genMoves(moves);
      System.out.println("bestmove " + moves[0]);
   }
          
}