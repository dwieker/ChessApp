public class Bishop extends Piece{

   public Bishop(char color, int pos){
      super(color, pos, 330);
   }
   
   public int genMoves(Board board, Move[] moves, int n){
      return rayCheck(board, moves, n, diagonals);
   }
   
   public char getLetter(){
      if (color == 'w') return 'B';
      else return 'b';
   }
        
}



