public class Rook extends Piece{


   public Rook(char color, int pos){
      super(color, pos, 550);
      super.setPosBonus(new int[]
         {0,0,0,5,5,0,0,0,    0,0,0,0,0,0,0,0,
            -5,0,0,0,0,0,0,-5,    0,0,0,0,0,0,0,0,
            -5,0,0,0,0,0,0,-5,    0,0,0,0,0,0,0,0,
            -5,0,0,0,0,0,0,-5,    0,0,0,0,0,0,0,0,
            -5,0,0,0,0,0,0,-5,    0,0,0,0,0,0,0,0,
            -5,0,0,0,0,0,0,-5,    0,0,0,0,0,0,0,0,
            5,10,10,10,10,10,10,5,    0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0});
   }
             
   public int genMoves(Board board, Move[] moves, int n){
      return super.rayCheck(board, moves, n, rows);
   }
   
   public char getLetter(){
      if (color == 'w') return 'R';
      else return 'r';
   }

}


