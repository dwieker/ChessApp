public class Knight extends Piece{

    public Knight(char color, int pos){
      super(color, pos, 320);
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