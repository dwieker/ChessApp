public class Queen extends Piece{

 
   
   public Queen(char color, int pos){
      super(color, pos, 900);
      super.setPosBonus(new int[] 
         {-20,-10,-10,-5,-5,-10,-10,-20,    0,0,0,0,0,0,0,0,
            -10,0,0,0,0,0,0,-10,    0,0,0,0,0,0,0,0,
            -10,5,5,5,5,5,5,-10,    0,0,0,0,0,0,0,0,
            -5,0,5,5,5,5,0,-5,    0,0,0,0,0,0,0,0,
            -5,0,5,5,5,5,0,-5,    0,0,0,0,0,0,0,0,
            -10,0,5,5,5,5,0,-10,    0,0,0,0,0,0,0,0,
            -10,0,0,0,0,0,0,-10,    0,0,0,0,0,0,0,0,
            -20,-10,-10,-5,-5,-10,-10,-20});
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