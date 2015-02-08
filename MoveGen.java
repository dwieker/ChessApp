import java.lang.Math;

public class MoveGen{
   
   private static int[] kingDeltas = {-1, 15, 16, 17, 1, -15, -16, -17};

   private static int[] knightDeltas =  {31, 33, 18, -14, -31, -33, 14, -18};
   
   private static int[] rows = {1, -1, 16, -16};
   
   private static int[] diagonals = {15, -15, 17, -17};
   
   public static int checkRowsDiagonals(Board board, Piece piece, Move[] moves, int n, char x){
      Piece temp;
      int pos = piece.getPos(), delta;
      int[] directions;
      
      if(x == 'r') directions = rows;
      else directions = diagonals;
      
      for(int i = 0; i < 4; i++){
         delta = directions[i];
         for(int j = pos + delta; (j&0x88) == 0; j += delta){
            temp = board.checkSquare(j);
            if(temp == null) moves[n++] = new Move(pos, j);
            else if(temp.getType()*piece.getType() < 0){
               moves[n++] = new Move(pos, j);
               break;
            }
            else 
               break;
         }
      }
      
      return n;
   }
      
  /* public static int checkRows(Board board, Piece piece, Move[] moves, int n){
      Piece temp;
      int pos = piece.getPos();
      for(int i = pos + 1; (i&0x88) == 0; i++){
         temp = board.checkSquare(i);
         if(temp == null) moves[n++] = new Move(pos, i);
         else if(temp.getType()*piece.getType() < 0){
            moves[n++] = new Move(pos, i);
            break;
         }
         else 
            break;
      }
      for(int i = pos - 1; (i&0x88) == 0; i--){
         temp = board.checkSquare(i);
         if(temp == null) moves[n++] = new Move(pos, i);
          else if(temp.getType()*piece.getType() < 0){
            moves[n++] = new Move(pos, i);
            break;
         }
         else 
            break;
      }
      for(int i = pos + 16; (i&0x88) == 0; i += 16){
         temp = board.checkSquare(i);
         if(temp == null) moves[n++] = new Move(pos, i);
          else if(temp.getType()*piece.getType() < 0){
            moves[n++] = new Move(pos, i);
            break;
         }
         else 
            break;
      }
      for(int i = pos - 16; (i&0x88) == 0; i -= 16){
         temp = board.checkSquare(i);
         if(temp == null) moves[n++] = new Move(pos, i);
         else if(temp.getType()*piece.getType() < 0){
            moves[n++] = new Move(pos, i);
            break;
         }
         else 
            break;
      }
      
      return n;
         
   }
   
   public static int checkDiagonals(Board board, Piece piece, Move[] moves, int n){
      Piece temp;
      int pos = piece.getPos();
      for(int i = pos + 17; (i&0x88) == 0; i += 17){
         temp = board.checkSquare(i);
         if(temp == null) moves[n++] = new Move(pos, i);
         else if(temp.getType()*piece.getType() < 0){
            moves[n++] = new Move(pos, i);
            break;
         }
         else 
            break;
      }
      for(int i = pos + 15; (i&0x88) == 0; i += 15){
         temp = board.checkSquare(i);
         if(temp == null) moves[n++] = new Move(pos, i);
         else if(temp.getType()*piece.getType() < 0){
            moves[n++] = new Move(pos, i);
            break;
         }
         else 
            break;
      }
      for(int i = pos - 15; (i&0x88) == 0; i -=15){
         temp = board.checkSquare(i);
         if(temp == null) moves[n++] = new Move(pos, i);
         else if(temp.getType()*piece.getType() < 0){
            moves[n++] = new Move(pos, i);
            break;
         }
         else 
            break;
      }
      for(int i = pos - 17; (i&0x88) == 0; i -= 17){
         temp = board.checkSquare(i);
         if(temp == null) moves[n++] = new Move(pos, i);
         else if(temp.getType()*piece.getType() < 0){
            moves[n++] = new Move(pos, i);
            break;
         }
         else 
            break;
      }
      
      return n;
   }*/
   
   public static int wKingMoves(Board board, Piece king, Move[] moves, int n){
      Piece temp;
      int pos = king.getPos();
      for(int i = 0; i < 8; i++){
         int move = pos + kingDeltas[i];
         if((move&0x88) == 0 && (board.checkSquare(move) == null || board.checkSquare(move).getType()*king.getType() < 0)) moves[n++] = new Move(pos, move);
      }
      if(board.whiteCastle(1) && board.checkSquare(1) == null && board.checkSquare(2) == null && board.checkSquare(3) == null && board.checkSquare(0) != null && board.checkSquare(0).getType() == Piece.ROOK){
         moves[n++] = new Move(pos,2);
      }
      if(board.whiteCastle(0) && board.checkSquare(Piece.QUEEN) == null && board.checkSquare(Piece.KING) == null && board.checkSquare(7)!= null && board.checkSquare(7).getType() == Piece.ROOK){
         moves[n++] = new Move(pos, Piece.KING);
      }
      return n;
   }
   
   public static int bKingMoves(Board board, Piece king, Move[] moves, int n){
      Piece temp;
      int pos = king.getPos();
      for(int i = 0; i < 8; i++){
         int move = pos + kingDeltas[i];
         if((move&0x88) == 0 && (board.checkSquare(move) == null || board.checkSquare(move).getType()*king.getType() < 0)) moves[n++] = new Move(pos, move);
      }
      if(board.blackCastle(1) && board.checkSquare(113) == null && board.checkSquare(114) == null && board.checkSquare(115) == null && board.checkSquare(112) != null && board.checkSquare(112).getType() == Piece.rook){
         moves[n++] = new Move(pos,114);
      }
      if(board.blackCastle(0) && board.checkSquare(117) == null && board.checkSquare(118) == null && board.checkSquare(119)!= null && board.checkSquare(119).getType() == Piece.rook){
         moves[n++] = new Move(pos,118);
      }
      return n;
   }
   
   public static int knightMoves(Board board, Piece knight, Move[] moves, int n){
      int pos = knight.getPos();
      for(int i = 0; i < 8; i++){
         int move = pos + knightDeltas[i];
         if((move&0x88) == 0 && (board.checkSquare(move) == null || board.checkSquare(move).getType()*knight.getType() < 0)) moves[n++] = new Move(pos, move);
      }
      
      return n;
   }
   
   public static int wPawnMoves(Board board, Piece piece, Move[] moves, int n){
      int pos = piece.getPos();
      Piece temp;
      
      if( (((pos+16)&0x88) == 0) && board.checkSquare(pos + 16) == null){
         moves[n++] = new Move(pos, pos + 16);
         if(pos < 24 && pos > 15 && board.checkSquare(pos + 32) == null){
            moves[n++] = new Move(pos, pos + 32);
         }
      }
      if(((pos+17)&0x88) == 0 && (board.checkSquare(pos + 17) != null && board.checkSquare(pos + 17).getType() < 0 || pos + 17 == board.enPassant())) moves[n++] = new Move(pos, pos + 17);
      if(((pos+15)&0x88) == 0 && (board.checkSquare(pos + 15) != null && board.checkSquare(pos + 15).getType() < 0 || pos + 15 == board.enPassant())) moves[n++] = new Move(pos, pos + 15);
      
      return n;
   }
   
   public static int bPawnMoves(Board board, Piece piece, Move[] moves, int n){
      int pos = piece.getPos();
      
      if(((pos-16)&0x88) == 0 && board.checkSquare(pos - 16) == null){
         moves[n++] = new Move(pos, pos - 16);
         if(pos > 95 && pos < 104 && board.checkSquare(pos - 32) == null){
            moves[n++] = new Move(pos, pos - 32);
         }
      }
      if(((pos-17)&0x88) == 0 && (board.checkSquare(pos - 17) != null && board.checkSquare(pos - 17).getType() > 0 || pos - 17 == board.enPassant())) moves[n++] = new Move(pos, pos - 17);
      if(((pos-15)&0x88) == 0 && (board.checkSquare(pos - 15) != null && board.checkSquare(pos - 15).getType() > 0 || pos - 15 == board.enPassant())) moves[n++] = new Move(pos, pos - 15);
      
      return n;
   }
   
   public static boolean isKingChecked(Board board, Piece king){
         
      int c = (int) -Math.signum(king.getType());  
         
       //check right
      for(int i = king.pos + 1; (i&0x88) == 0; i++){
         if(board.checkSquare(i) == null) 
            continue;
         if(board.checkSquare(i).getType() == Piece.ROOK*c || board.checkSquare(i).getType() == Piece.QUEEN*c || (board.checkSquare(i).getType() == Piece.KING*c && i - 1 == king.pos)){
            return true;
         }
         break;
      }
       
      for(int i = king.pos - 1; (i&0x88) == 0; i--){ //check left
         if(board.checkSquare(i) == null) 
            continue;
         if(board.checkSquare(i).getType() == Piece.ROOK*c || board.checkSquare(i).getType() == Piece.QUEEN*c || (board.checkSquare(i).getType() == Piece.KING*c && i + 1 == king.pos)){
            return true;
         }
         break;
      }
       
      for(int i = king.pos + 16; (i&0x88) == 0; i+= 16){ //check up
         if(board.checkSquare(i) == null) 
            continue;
         if(board.checkSquare(i).getType() == Piece.ROOK*c || board.checkSquare(i).getType() == Piece.QUEEN*c || (board.checkSquare(i).getType() == Piece.KING*c && i - 16 == king.pos)){
            return true;
         }
         break;
      }
       
      for(int i = king.pos - 16; (i&0x88) == 0; i-= 16){ //check down
         if(board.checkSquare(i) == null) 
            continue;
         if(board.checkSquare(i).getType() == Piece.ROOK*c || board.checkSquare(i).getType() == Piece.QUEEN*c || (board.checkSquare(i).getType() == Piece.KING*c && i + 16 == king.pos)){
            return true;
         }
         break;
      }
       
      for(int i = king.pos + 17; (i&0x88) == 0; i+= 17){ //check up right
         if(board.checkSquare(i) == null) 
            continue;
         if(board.checkSquare(i).getType() == 3*c || board.checkSquare(i).getType() == Piece.QUEEN*c || (board.checkSquare(i).getType() == Piece.KING*c && i - 17 == king.pos)){
            return true;
         }
         break;
      }
       
      for(int i = king.pos + 15; (i&0x88) == 0; i+= 15){//check up left
         if(board.checkSquare(i) == null) 
            continue;
         if(board.checkSquare(i).getType() == 3*c || board.checkSquare(i).getType() == Piece.QUEEN*c || (board.checkSquare(i).getType() == Piece.KING*c && i - 15 == king.pos)){
            return true;
         }
         break;
      }
       
      for(int i = king.pos - 17; (i&0x88) == 0; i-= 17){ //check down left
         if(board.checkSquare(i) == null) 
            continue;
         if(board.checkSquare(i).getType() == 3*c || board.checkSquare(i).getType() == Piece.QUEEN*c || (board.checkSquare(i).getType() == Piece.KING*c && i + 17 == king.pos)){
            return true;
         }
         break;
      }
      for(int i = king.pos - 15; (i&0x88) == 0; i-= 15){ //check down right
         if(board.checkSquare(i) == null) 
            continue;
         if(board.checkSquare(i).getType() == 3*c || board.checkSquare(i).getType() == Piece.QUEEN*c || (board.checkSquare(i).getType() == Piece.KING*c && i + 15 == king.pos)){
            return true;
         }
         break;
      }
       
       //check for knights
      int pos;
      for(int i = 0; i < 8; i++){
         pos = king.pos + knightDeltas[i];
         if( (pos&0x88) == 0){
            if(board.checkSquare(pos) != null && board.checkSquare(pos).getType() == Piece.KNIGHT*c)
               return true;     
         }
      }
       
       
       //check for pawns
       
      if(1==2){}
       
      if( ((king.pos + 17*-c)&0x88) == 0 && board.checkSquare(king.pos + 17*-c) != null && board.checkSquare(king.pos + 17*-c).getType() == Piece.PAWN*c) 
         return true;   
      if( ((king.pos + 15*-c)&0x88) == 0 && board.checkSquare(king.pos + 15*-c) != null && board.checkSquare(king.pos + 15*-c).getType() == Piece.PAWN*c) 
         return true;   
       
       
       
      return false;
    
   }

   
   
       
      
      
}
