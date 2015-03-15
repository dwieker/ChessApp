package ChessEngine;

public class Pawn extends Piece{


   

   public Pawn(char color, int pos){
      super(color, pos, 100);
      super.setPosBonus(new int[] 
         {0,  0,  0,  0,  0,  0,  0,  0,    0,0,0,0,0,0,0,0,
         5, 10, 10,-25,-25, 10, 10,  5,    0,0,0,0,0,0,0,0,
         5, -5,-10,  0,  0,-10, -5,  5,    0,0,0,0,0,0,0,0,
         0,  0,  0, 35, 35,  0,  0,  0,    0,0,0,0,0,0,0,0,
         5,  5, 10, 27, 27, 10,  5,  5,    0,0,0,0,0,0,0,0,
        10, 10, 20, 30, 30, 20, 10, 10,    0,0,0,0,0,0,0,0,
        50, 50, 50, 50, 50, 50, 50, 50,    0,0,0,0,0,0,0,0,
         0,  0,  0,  0,  0,  0,  0,  0});
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