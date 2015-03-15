public class AI{

   public static int nodes = 0;

   
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
      if( depthleft == 0 ){
         nodes++;
         return eval(board);  //quiesce( alpha, beta );
      }
           
      Move[] moves = new Move[264];
      int n = board.genMoves(moves);
      int score;
      MoveHistory mh;
      
      for (int i = 0; i < n; ++i)  {
         mh = board.movePiece(moves[i]);
         
         //check if king is in check. If so, undo move
         if(board.curPlayer == 'b' ? ((King)(board.wKing)).isChecked(board) : ((King)(board.bKing)).isChecked(board)){
            board.unmovePiece(mh);
            continue;
         } 
         
         
         score = -alphaBeta(board,  -beta, -alpha, depthleft - 1 );
         moves[i].score = score;
         
         board.unmovePiece(mh);
         if( score >= beta ){
            //System.out.println("beta cutoff. depthleft = " + depthleft);
            return beta;
         }   
         if( score > alpha )
            alpha = score; // alpha acts like max in MiniMax
      }
      //System.out.println("returning alpha. depthleft = " + depthleft);
      return alpha;
   }
   
   public static Move calculateMove(Board board, int maxDepth){
      Move[] moves = new Move[264];
      int n = board.genMoves(moves);
      
      int highestScore = -1000000000, curScore;
      Move bestMove = moves[0];
      MoveHistory mh;
      
      for(int depth = 0; depth <= maxDepth; depth++){
         System.out.println(depth);
         for(int i = 0; i < n; i++){
            System.out.println("Move: " + moves[i]);
            
            mh = board.movePiece(moves[i]);
            
            //check if king is in check
            if(board.curPlayer == 'b' ? ((King)(board.wKing)).isChecked(board) : ((King)(board.bKing)).isChecked(board)){
               board.unmovePiece(mh);
               moves[i].score = -1000000000;
               continue;
            } 
            
            curScore = -alphaBeta(board, -10000000, 10000000, depth);
            moves[i].score = curScore;
            
            if(curScore > highestScore){
               highestScore = curScore;
               bestMove = moves[i];
            }
            System.out.println(moves[i] + " " + curScore);
            board.unmovePiece(mh);
         }
         bubbleSortMoves(moves, n);
         System.out.println("depth: " + depth + " , nodes: " + nodes);
         nodes = 0;
         highestScore = -10000000;
         
         
      }
      
      
      return bestMove;
   }
   
   
   
   public static void bubbleSortMoves(Move[] moves, int n){
      Move temp;
      for(int i=0; i < n-1; i++){
         for(int j=1; j < n-i; j++){
            if(moves[j-1].score < moves[j].score){
               temp=moves[j-1];
               moves[j-1] = moves[j];
               moves[j] = temp;
            }
         }
      }
   } 
      
      

   
}