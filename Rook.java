public class Rook extends Piece{

   public Rook(char color, int pos){
      super(color, pos, 550);
   }
             
   public int genMoves(Board board, Move[] moves, int n){
      return super.rayCheck(board, moves, n, rows);
   }
   
   public char getLetter(){
      if (color == 'w') return 'R';
      else return 'r';
   }

}


