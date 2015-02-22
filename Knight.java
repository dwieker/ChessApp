public class Knight extends Piece{
   


    public Knight(char color, int pos){
      super(color, pos, 320);
      super.setPosBonus(new int[] 
            {-50,-40,-30,-30,-30,-30,-40,-50,       0,0,0,0,0,0,0,0,
            -40,-20,0,5,5,0,-20,-40,               0,0,0,0,0,0,0,0,
            -30,5,10,15,15,10,5,-30,                 0,0,0,0,0,0,0,0,
            -30,0,15,20,20,15,0,-30,                 0,0,0,0,0,0,0,0,
            -30,5,15,20,20,15,5,-30,                 0,0,0,0,0,0,0,0,
            -30,0,10,10,10,10,0,-30,                 0,0,0,0,0,0,0,0,
            -50,-20,0,0,0,0,-20,-50,                 0,0,0,0,0,0,0,0,
            -50,-40,-30,-30,-30,-30,-40,-50});
      
    }
    
    public int genMoves(Board board, Move[] moves, int n){     
      for(int i = 0; i < 8; i++){
         int move = pos + knightDeltas[i];
         if((move&0x88) == 0 && (board.checkSquare(move) == null || board.checkSquare(move).color != color)) moves[n++] = new Move(pos, move);
      }  
      return n;
    }
    
    public char getLetter(){
      if (color == 'w') return 'N';
      else return 'n';
   }

   


}