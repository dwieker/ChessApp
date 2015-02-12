public class King extends Piece{
   public King(char color, int pos){
      super(color, pos, 100000000);
   }
   
  public int genMoves(Board board, Move[] moves, int n){
      Piece temp;
      
      
      for(int i = 0; i < 8; i++){
         int move = pos + kingDeltas[i];
         if((move&0x88) == 0 && (board.checkSquare(move) == null || board.checkSquare(move).color != color)) moves[n++] = new Move(pos, move);
      }
      
      if(color == 'w'){
         if(board.whiteCastle(1) && board.checkSquare(1) == null && board.checkSquare(2) == null && board.checkSquare(3) == null && board.checkSquare(0) != null && board.checkSquare(0).getClass() == Rook.class){
            moves[n++] = new Move(pos,2);
         }
         if(board.whiteCastle(0) && board.checkSquare(5) == null && board.checkSquare(6) == null && board.checkSquare(7)!= null && board.checkSquare(7).getClass() == Rook.class){
            moves[n++] = new Move(pos, 6);
         }
      }
      else{
         if(board.blackCastle(1) && board.checkSquare(113) == null && board.checkSquare(114) == null && board.checkSquare(115) == null && board.checkSquare(112) != null && board.checkSquare(112).getClass() == Rook.class)
            moves[n++] = new Move(pos,114);
         if(board.blackCastle(0) && board.checkSquare(117) == null && board.checkSquare(118) == null && board.checkSquare(119)!= null && board.checkSquare(119).getClass() == Rook.class)
            moves[n++] = new Move(pos,118);   
      }
      return n;
   }
   
   public boolean isChecked(Board board){
             
      Piece temp;
      int delta;
        
      //check rows      
      for(int i = 0; i < 4; i++){
         delta = rows[i];
         for(int j = pos + delta; (j&0x88) == 0; j += delta){
            temp = board.checkSquare(j);
            
            if(temp == null) 
               continue;
             
            if(temp.color != color && (temp.getClass() == Rook.class || temp.getClass() == Queen.class || (temp.getClass() == King.class && i - 1 == pos))) 
               return true;
         
            break; 
           
         }
      }
      
      //check diags      
      for(int i = 0; i < 4; i++){
         delta = diagonals[i];
         for(int j = pos + delta; (j&0x88) == 0; j += delta){
            temp = board.checkSquare(j);
            if(temp == null) 
               continue;
             
            if(temp.color != color && (temp.getClass() == Bishop.class || temp.getClass() == Queen.class || (temp.getClass() == King.class && i - 17 == pos)))
               return true;
         
            break; 
           
         }
      }
      
  
      //check for knights
      for(int i = 0; i < 8; i++){
         delta = pos + knightDeltas[i];
         if( (delta&0x88) == 0){
            temp = board.checkSquare(delta);
            if(temp != null && temp.getClass() == Knight.class && temp.color != color)
               return true;     
         }
      }
       
       
      //check for pawns
      int c;
      if(color == 'w') c = -1;
      else c = 1; 
      
      if( ((pos - 17*c)&0x88) == 0 && board.checkSquare(pos - 17*c) != null && board.checkSquare(pos - 17*c).getClass() == Pawn.class &&  board.checkSquare(pos - 17*c).color != color)
         return true;   
      if( ((pos - 15*c)&0x88) == 0 && board.checkSquare(pos - 15*c) != null && board.checkSquare(pos - 15*c).getClass() == Pawn.class &&  board.checkSquare(pos - 15*c).color != color)
         return true;   
             
      return false;
    
   }    
   
   public char getLetter(){
      if (color == 'w') return 'K';
      else return 'k';
   }


}