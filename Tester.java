import java.util.*;

public class Tester{
   private static String POSITION3 = "rnbqkb1r/pp1p1ppp/2p5/4P3/2B5/8/PPP1NnPP/RNBQK2R w KQkq - 0 6";
   private static final String POSITION4 = "r3k2r/Pppp1ppp/1b3nbN/nP6/BBP1P3/q4N2/Pp1P2PP/R2Q1RK1 w kq - 0 1";
   private static final String TEST = "r3k2r/Pppp1ppp/1b3nbN/nP6/BBP1P3/q4N2/Pp1P2PP/R2Q1RK1 w kq - 0 1";
   private static final String POSITION2 = "r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R w KQkq -";


   public static void main(String args[]){
      FENtest();
      Board b = new Board(POSITION2);
      System.out.println(perftTest(2,b));
      
      
    /* b = new Board(Board.TEST);
      Move[] moves = new Move[256];
      
      int n = b.genMoves(moves);
      for(int i = 0; i < n; i++){
         System.out.println(moves[i]);
      }*/
      
   }
   
   public static void FENtest(){
      Board board;
      String test;
      
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
   
   public static int perftTest(int depth, Board board){
      int nodes = 0;
      Piece temp;
      Move[] moves = new Move[264];
      int n_moves;
      MoveHistory mh;
      
      
      if(depth == 0) return 1;
      
      if(board.curPlayer() == board.WHITE) temp = board.wKing();
      else temp = board.bKing();

      
      n_moves = board.genMoves(moves);
      for(int i = 0; i < n_moves; i++) {
        mh = board.movePiece(moves[i]);
        
       //nodes += perftTest(depth - 1, board);
       //board.unmovePiece(mh);

       
        if(MoveGen.isKingChecked(board, temp))
           board.unmovePiece(mh);
           
        else{     
           nodes += perftTest(depth - 1, board);
           board.unmovePiece(mh);
        }
        
      }
      return nodes;
           
   }
   
}