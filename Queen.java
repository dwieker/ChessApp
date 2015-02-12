public class Queen extends Piece{

   public Queen(char color, int pos){
      super(color, pos, 900);
   }
   
    public int genMoves(Board board, Move[] moves, int n){
      n = rayCheck(board, moves, n, diagonals); 
      return rayCheck(board, moves, n, rows);
   }
   
   public char getLetter(){
      if (color == 'w') return 'Q';
      else return 'q';
   }



}