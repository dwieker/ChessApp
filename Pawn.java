public class Pawn extends Piece{

   public Pawn(char color, int pos){
      super(color, pos, 100);
   }
   
   public int genMoves(Board board, Move[] moves, int n){
      Piece temp;  
      
      if(color == 'w'){      
         if( (((pos+16)&0x88) == 0) && board.checkSquare(pos + 16) == null){
            moves[n++] = new Move(pos, pos + 16);
            if(pos < 24 && pos > 15 && board.checkSquare(pos + 32) == null){
               moves[n++] = new Move(pos, pos + 32);
            }
         }
         if(((pos+17)&0x88) == 0 && (board.checkSquare(pos + 17) != null && board.checkSquare(pos + 17).color != color || pos + 17 == board.enPassant())) moves[n++] = new Move(pos, pos + 17);
         if(((pos+15)&0x88) == 0 && (board.checkSquare(pos + 15) != null && board.checkSquare(pos + 15).color != color || pos + 15 == board.enPassant())) moves[n++] = new Move(pos, pos + 15);
      }
      else{
      
       if(((pos-16)&0x88) == 0 && board.checkSquare(pos - 16) == null){
         moves[n++] = new Move(pos, pos - 16);
         if(pos > 95 && pos < 104 && board.checkSquare(pos - 32) == null){
            moves[n++] = new Move(pos, pos - 32);
         }
        }
        if(((pos-17)&0x88) == 0 && (board.checkSquare(pos - 17) != null && board.checkSquare(pos - 17).color != color || pos - 17 == board.enPassant())) moves[n++] = new Move(pos, pos - 17);
        if(((pos-15)&0x88) == 0 && (board.checkSquare(pos - 15) != null && board.checkSquare(pos - 15).color != color || pos - 15 == board.enPassant())) moves[n++] = new Move(pos, pos - 15);   
      
      }
      return n;
   }
   
   public char getLetter(){
      if (color == 'w') return 'P';
      else return 'p';
   }


}