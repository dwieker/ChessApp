package ChessEngine;

public class MoveHistory{
   private Move move;
   private boolean[] b_castle_rights = new boolean[2];
   private boolean[] w_castle_rights = new boolean[2];
   private int did_castle = -1; //0-queen side white, 1-king side white, 2-queen side black, 3-king side black
   private Piece captured;
   private boolean pawnPromo;
   private int enPassantSq;
   
   public MoveHistory(Move m, boolean[] b, boolean[] w, int enPassantSq){
      move = m;
      b_castle_rights[0] = b[0];
      b_castle_rights[1] = b[1];
      w_castle_rights[0] = w[0];
      w_castle_rights[1] = w[1];
      this.enPassantSq = enPassantSq;
      pawnPromo = false;
      
   }
   
   public Move getMove()
   {
      return move;
   }
   
   public boolean[] get_b_rights()
   {
      return b_castle_rights;
   }
   
   public boolean[] get_w_rights()
   {
      return w_castle_rights;
   }
   
   public int didCastle()
   {
      return did_castle;
   }
   public Piece getCaptured()
   {
      return captured;
   }
   
   public boolean getPromo()
   {
      return pawnPromo;
   }
   
   public void setCaptured(Piece p){
      captured = p;
   }
   
   public void setPromo(){
	   pawnPromo = true;
   }
   
   public void castled(int c){
	   did_castle = c;
   }
   
   public int getEnPassant(){
      return enPassantSq;
   }
}