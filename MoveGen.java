public class MoveGen{
   
   private static int[] kingDeltas = {-1, 15, 16, 17, 1, -15, -16, -17};

   private static int[] knightDeltas =  {31, 33, 18, -14, -31, -33, 14, -18};
      
   public static int checkRows(Board board, Piece piece, Move[] moves, int n){
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
   }
   
   public static int wKingMoves(Board board, Piece king, Move[] moves, int n){
      Piece temp;
      int pos = king.getPos();
      for(int i = 0; i < 8; i++){
         int move = pos + kingDeltas[i];
         if((move&0x88) == 0 && (board.checkSquare(move) == null || board.checkSquare(move).getType()*king.getType() < 0)) moves[n++] = new Move(pos, move);
      }
      if(board.whiteCastle(0) && board.checkSquare(1) == null && board.checkSquare(2) == null && board.checkSquare(3) == null && board.checkSquare(0) != null && board.checkSquare(0).getType() == Piece.ROOK){
         moves[n++] = new Move(pos,2);
      }
      if(board.whiteCastle(1) && board.checkSquare(5) == null && board.checkSquare(6) == null && board.checkSquare(7)!= null && board.checkSquare(7).getType() == Piece.ROOK){
         moves[n++] = new Move(pos, 6);
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
      if(board.blackCastle(1) && board.checkSquare(113) == null && board.checkSquare(114) == null && board.checkSquare(115) == null && board.checkSquare(112) != null && board.checkSquare(112).getType() == 4){
         moves[n++] = new Move(pos,114);
      }
      if(board.blackCastle(0) && board.checkSquare(117) == null && board.checkSquare(118) == null && board.checkSquare(119)!= null && board.checkSquare(119).getType() == -4){
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
      
      if(board.checkSquare(pos + 16) == null){
         moves[n++] = new Move(pos, pos + 16);
         if(pos < 24 && pos > 15 && board.checkSquare(pos + 32) == null){
            moves[n++] = new Move(pos, pos + 32);
         }
      }
      if(board.checkSquare(pos + 17) != null || pos + 17 == board.enPassant()) moves[n++] = new Move(pos, pos + 17);
      if(board.checkSquare(pos + 15) != null || pos + 15 == board.enPassant()) moves[n++] = new Move(pos, pos + 15);
      
      return n;
   }
   
   public static int bPawnMoves(Board board, Piece piece, Move[] moves, int n){
      int pos = piece.getPos();
      
      if(board.checkSquare(pos - 16) == null){
         moves[n++] = new Move(pos, pos - 16);
         if(pos > 95 && pos < 104 && board.checkSquare(pos - 32) == null){
            moves[n++] = new Move(pos, pos - 32);
         }
      }
      if(board.checkSquare(pos - 17) != null || pos - 17 == board.enPassant()) moves[n++] = new Move(pos, pos - 17);
      if(board.checkSquare(pos - 15) != null || pos - 15 == board.enPassant()) moves[n++] = new Move(pos, pos - 15);
      
      return n;
   }
   
       
      
      
}
