import java.util.*;
import java.lang.*;

public class Tester{

   private static String POSITION5 = "rnbqkb1r/pp1p1ppp/2p5/4P3/2B5/8/PPP1NnPP/RNBQK2R w KQkq - 0 6";
   private static final String POSITION4 = "r3k2r/Pppp1ppp/1b3nbN/nP6/BBP1P3/q4N2/Pp1P2PP/R2Q1RK1 w kq - 0 1";
   private static final String TEST = "r3k2r/Pppp1ppp/1b3nbN/nP6/BBP1P3/q4N2/Pp1P2PP/R2Q1RK1 w kq - 0 1";
   private static final String POSITION2 = "r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R w KQkq -";
   private static final String POSITION3 = "8/2p5/3p4/KP5r/1R3p1k/8/4P1P1/8 w - -"; 
   private static final String POSITION6 = "r4rk1/1pp1qppp/p1np1n2/2b1p1B1/2B1P1b1/P1NP1N2/1PP1QPPP/R4RK1 w - - 0 10";
   
   public static void main(String args[]){
      FENtest();
      
      long t1 = System.nanoTime();
      Board b = new Board(POSITION6);
      System.out.println(b.printFEN());
      System.out.println(perftTest(4,b));
      System.out.println("time: " + (System.nanoTime() - t1)/1000000000.0);
         
   }
   
   public static void FENtest(){
      Board board;
      String test;
      
      test = "rnbqkbnr/pppppppp/8/8/8/3P4/PPP1PPPP/RNBQKBNR b KQkq - 0 1";
      board = new Board(test);
      if(!test.equals(board.printFEN())){
         System.out.println("normal start fail");
         System.out.println(board.printFEN());
      }
         
      
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
   
   public static int perftTest(int depth, Board board){
      int nodes = 0;
      King temp;
      Move[] moves = new Move[264];
      int n_moves;
      MoveHistory mh;
      
      
      if(depth == 0) 
         return 1;
      
      if(board.curPlayer() == 'w') temp = (King)board.wKing();
      else temp = (King)board.bKing();
   
      
      n_moves = board.genMoves(moves);
      //System.out.println(n_moves);
      for(int i = 0; i < n_moves; i++){
         //System.out.println(moves[i]);
         mh = board.movePiece(moves[i]);
              
       
         if(temp.isChecked(board) || (mh.getCaptured() != null && mh.getCaptured().getClass() == Piece.class)){
            board.unmovePiece(mh);
            board.subtractCastle();
         }
         else if(mh.didCastle() > 0 && ( (new King(temp.color, mh.getMove().s1())).isChecked(board) ||
                  (new King(temp.color, (mh.getMove().s1() + mh.getMove().s2())/2)).isChecked(board))){
            board.unmovePiece(mh);  
            board.subtractCastle();
         }
         else{     
            nodes += perftTest(depth - 1, board);
            board.unmovePiece(mh);
         }
        
      }
      
      board.counts();
      return nodes;
           
   }
   
}