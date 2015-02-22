public class AI{

   public static int eval(Board b){
      int material = 0;
      int posScore = 0;
      int score;
      
      for(Piece p : b.pieces){
         material += p.value;
         posScore += p.getPosBonus();
         
      }
      
      score = material + posScore;
      return b.curPlayer() == 'w' ? score : -score;
   }
   
   public static int alphaBeta(Board board, int alpha, int beta, int depthleft){
      if( depthleft == 0 ) return eval(board);  //quiesce( alpha, beta );
           
      Move[] moves = new Move[264];
      int n = board.genMoves(moves);
      int score;
      
      for (int i = 0; i < n; ++i)  {
         score = -alphaBeta(board,  -beta, -alpha, depthleft - 1 );
         if( score >= beta ){
            System.out.println("beta cutoff. depthleft = " + depthleft);
            return beta;
         }   //  fail hard beta-cutoff
         if( score > alpha )
            alpha = score; // alpha acts like max in MiniMax
      }
      System.out.println("returning alpha. depthleft = " + depthleft);
      return alpha;
   }
   
   public static Move calculateMove(Board board, int depth){
      Move[] moves = new Move[264];
      int n = board.genMoves(moves);
      
      int highestScore = -1000000000, curScore;
      Move bestMove = moves[0];
      MoveHistory mh;
      
      for(int i = 0; i < n; i++){
         mh = board.movePiece(moves[i]);
         curScore = alphaBeta(board, -10000000, 10000000, depth);
         if(curScore > highestScore){
            highestScore = curScore;
            bestMove = moves[i];
         }
         System.out.println(moves[i] + " " + curScore);
         board.unmovePiece(mh);
      }
      
      return bestMove;
   }
      
      
   
   

}