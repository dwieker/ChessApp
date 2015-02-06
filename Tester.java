import java.util.*;

public class Tester{
   public static void main(String args[]){
      FENtest();
      perftTest();
   }
   
   public static void FENtest(){
      Board board;
      String test, test1;
      
      test = "rnbqkbnr/pppppppp/8/8/8/3P4/PPP1PPPP/RNBQKBNR b KQkq - 0 1";
      board = new Board(test);
      if(!test.equals(board.printFEN())) System.out.println("normal start fail");
      
      test = "rnbqkbnr/pppppppp/8/8/8/3P4/PPP1PPPP/RNBQKBNR b KQk - 0 1";
      board = new Board(test);
      if(!test.equals(board.printFEN())) System.out.println("castling fail 1");
      
      test = "rnbqkbnr/pppppppp/8/8/8/3P4/PPP1PPPP/RNBQKBNR b KQq - 0 1";
      board = new Board(test);
      if(!test.equals(board.printFEN())) System.out.println("castling fail 2");
      
      test = "rnbqkbnr/pppppppp/8/8/8/3P4/PPP1PPPP/RNBQKBNR b Kkq - 0 1";
      board = new Board(test);
      if(!test.equals(board.printFEN())) System.out.println("castling fail 3");
      
      test = "rnbqkbnr/pppppppp/8/8/8/3P4/PPP1PPPP/RNBQKBNR b kq - 0 1";
      board = new Board(test);
      if(!test.equals(board.printFEN())) System.out.println("castling fail 4");
      
      test = "rnbqkbnr/pppppppp/8/8/8/3P4/PPP1PPPP/RNBQKBNR b - - 0 1";
      board = new Board(test);
      if(!test.equals(board.printFEN())) System.out.println("castling fail 5");
      
      test = "5q2/8/1K6/8/4N3/8/1k6/7R w - - 0 1";
      board = new Board(test);
      if(!test.equals(board.printFEN())) System.out.println("very few pieces 1");
      
      test = "8/1k3b2/8/8/1b2P3/8/5K2/8 w - - 0 1";
      board = new Board(test);
      if(!test.equals(board.printFEN())) System.out.println("very few pieces 2");
      
      test = "R2K3R/8/8/8/8/8/8/r2k3r w KQkq - 0 1";
      board = new Board(test);
      if(!test.equals(board.printFEN())) System.out.println("very few pieces 3");
      
      test = "R2K3R/8/8/8/8/8/8/r2k3r w KQkq - 0 1";
      board = new Board(test);
      if(!test.equals(board.printFEN())) System.out.println("very few pieces 4");
      
   }
   
   public static void perftTest(){
   }
   
}