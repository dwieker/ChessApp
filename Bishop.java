public class Bishop extends Piece{

   public Bishop(char color, int pos){
      super(color, pos, 330);
      super.setPosBonus(new int[] 
        {-20, -10,-10,-10,-10,-10,-10,-20,   0,0,0,0,0,0,0,0,
            -10,10,0,0,0,0,10,-10,                 0,0,0,0,0,0,0,0,
            -10,10,10,10,10,10,10,-10,        0,0,0,0,0,0,0,0,
            -10,0,10,10,10,10,0,-10,             0,0,0,0,0,0,0,0,
            -10,5,5,10,10,5,5,-10,               0,0,0,0,0,0,0,0,
            -10,0,5,10,10,5,0,-10,               0,0,0,0,0,0,0,0,
            -10,0,0,0,0,0,0,-10,                 0,0,0,0,0,0,0,0,
            -20,-10,-10,-10,-10,-10,-10,-20});
   }
   
   public int genMoves(Board board, Move[] moves, int n){
      return rayCheck(board, moves, n, diagonals);
   }
   
   public char getLetter(){
      if (color == 'w') return 'B';
      else return 'b';
   }
        
}



