public class Piece{

   static final int PAWN = 1;
   static final int KNIGHT = 2;
   static final int BISHOP = 3;
   static final int ROOK = 4;
   static final int QUEEN = 5;
   static final int KING = 6;
   static final int pawn = -1;
   static final int knight = -2;
   static final int bishop = -3;
   static final int rook = -4;
   static final int queen = -5;
   static final int king = -6;
   private static int[] kingDeltas = {-1, 15, 16, 17, 1, -15, -16, -17};
   private static int[] knightDeltas =  {31, 33, 18, -14, -31, -33, 14, -18}; 
   private static int[] rows = {1, -1, 16, -16};  
   private static int[] diagonals = {15, -15, 17, -17};
   
   private int type;
   private int pos; // location in the squares array
   private char letter;
   private int value;
     
   
   public Piece(int type, int pos){
      this.type = type;
      this.pos = pos;
      
      if(type == PAWN){letter = 'P'; value = 100;}
      else if(type == KNIGHT){letter = 'N';value = 320;}
      else if(type == BISHOP){letter = 'B';value = 330;}
      else if(type == ROOK){letter = 'R';value = 500;}
      else if(type == QUEEN){letter = 'Q';value = 900;}
      else if(type == KING){letter = 'K';value = 10000;}
      else if(type == pawn){letter = 'p';value = 100;}
      else if(type == knight){letter = 'n';value = 320;}
      else if(type == bishop){letter = 'b';value = 330;}
      else if(type == rook){letter = 'r';value = 500;}
      else if(type == queen){letter = 'q';value = 900;}
      else if(type == king){letter = 'k';value = 10000;}
   }
   
   public Piece(char type, int pos){
      this.pos = pos;
      this.letter = type;
      
      if(type == 'k'){this.type = king; value = 100000;}
      if(type == 'q'){this.type = queen; value = 900;}
      if(type == 'r'){this.type = rook; value = 500;}
      if(type == 'b'){this.type = bishop; value = 330;}
      if(type == 'n'){this.type = knight; value = 320;}
      if(type == 'p'){this.type = pawn; value = 100;}
      if(type == 'K'){this.type = KING; value = 100000;}
      if(type == 'Q'){this.type = QUEEN; value = 900;}
      if(type == 'R'){this.type = ROOK; value = 500;}
      if(type == 'B'){this.type = BISHOP; value = 330;}
      if(type == 'N'){this.type = KNIGHT; value = 320;}
      if(type == 'P'){this.type = PAWN; value = 100;}
       
   }
   
   public int getType(){
      return type;
   }
   
   public void setType(int t, char l, int v){
	   type = t;
	   letter = l;
	   value = v;
   }
   
   public void setPos(int p){
      pos = p;
   }
   
   public int getPos(){
      return pos;
   }
   
   public char getLetter(){
      return letter;
   }
   
   public int move(Board board, Move[] moves, int n){
      
      switch(type){
         case PAWN:
            return wPawnMoves(board, moves, n);
         case pawn:
            return bPawnMoves(board, moves, n);
         case KNIGHT:
         case knight:
            return knightMoves(board, moves, n);
         case BISHOP:
         case bishop:
            return checkDiagonals(board, moves, n);
         case ROOK:
         case rook:
            return checkRows(board, moves, n);
         case QUEEN:
         case queen:
            n = checkRows(board, moves, n);
            return checkDiagonals(board, moves, n);
         case KING:
            return wKingMoves(board, moves, n);
         case king:
            return bKingMoves(board, moves, n);
         default:
            return n;
            
     }
   }
   
   private int checkRows(Board board, Move[] moves, int n){
      Piece temp;
      int delta;
            
      for(int i = 0; i < 4; i++){
         delta = rows[i];
         for(int j = pos + delta; (j&0x88) == 0; j += delta){
            temp = board.checkSquare(j);
            if(temp == null) moves[n++] = new Move(pos, j);
            else if(temp.getType()*type < 0){
               moves[n++] = new Move(pos, j);
               break;
            }
            else 
               break;
         }
      }
      
      return n;
   }
   
   private int checkDiagonals(Board board, Move[] moves, int n){
      Piece temp;
      int delta;
            
      for(int i = 0; i < 4; i++){
         delta = diagonals[i];
         for(int j = pos + delta; (j&0x88) == 0; j += delta){
            temp = board.checkSquare(j);
            if(temp == null) moves[n++] = new Move(pos, j);
            else if(temp.getType()*type < 0){
               moves[n++] = new Move(pos, j);
               break;
            }
            else 
               break;
         }
      }
      
      return n;
   }

         
   private int wKingMoves(Board board, Move[] moves, int n){
      Piece temp;
      
      for(int i = 0; i < 8; i++){
         int move = pos + kingDeltas[i];
         if((move&0x88) == 0 && (board.checkSquare(move) == null || board.checkSquare(move).getType()*type < 0)) moves[n++] = new Move(pos, move);
      }
      if(board.whiteCastle(1) && board.checkSquare(1) == null && board.checkSquare(2) == null && board.checkSquare(3) == null && board.checkSquare(0) != null && board.checkSquare(0).getType() == Piece.ROOK){
         moves[n++] = new Move(pos,2);
      }
      if(board.whiteCastle(0) && board.checkSquare(Piece.QUEEN) == null && board.checkSquare(Piece.KING) == null && board.checkSquare(7)!= null && board.checkSquare(7).getType() == Piece.ROOK){
         moves[n++] = new Move(pos, Piece.KING);
      }
      return n;
   }
   
   private int bKingMoves(Board board, Move[] moves, int n){
      Piece temp;
      
      for(int i = 0; i < 8; i++){
         int move = pos + kingDeltas[i];
         if((move&0x88) == 0 && (board.checkSquare(move) == null || board.checkSquare(move).getType()*type < 0)) moves[n++] = new Move(pos, move);
      }
      if(board.blackCastle(1) && board.checkSquare(113) == null && board.checkSquare(114) == null && board.checkSquare(115) == null && board.checkSquare(112) != null && board.checkSquare(112).getType() == Piece.rook){
         moves[n++] = new Move(pos,114);
      }
      if(board.blackCastle(0) && board.checkSquare(117) == null && board.checkSquare(118) == null && board.checkSquare(119)!= null && board.checkSquare(119).getType() == Piece.rook){
         moves[n++] = new Move(pos,118);
      }
      return n;
   }
   
   private int knightMoves(Board board, Move[] moves, int n){
      
      for(int i = 0; i < 8; i++){
         int move = pos + knightDeltas[i];
         if((move&0x88) == 0 && (board.checkSquare(move) == null || board.checkSquare(move).getType()*type < 0)) moves[n++] = new Move(pos, move);
      }
      
      return n;
   }
   
   private int wPawnMoves(Board board, Move[] moves, int n){
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
   
   private int bPawnMoves(Board board, Move[] moves, int n){
      
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
}