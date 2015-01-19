public class MoveHistory{
   private Move move;
   private boolean[] b_castle_rights;
   private boolean[] w_castle_rights;
   private int did_castle = -1; //0-queen side white, 1-king side white, 2-queen side black, 3-king side black
   private Piece captured;
   private Piece pawn_promotion;
   private int enPassantSq;
   
   public MoveHistory(Move m, boolean[] b, boolean[] w, enPassantSq){
      move = m;
      b_castle_rights[0] = b[0];
      b_castle_rights[1] = b[1];
      w_castle_rights[0] = w[0];
      w_castle_rights[1] = w[1];
      this.enPassantSq = enPassantSq;
   }
   
   public Move get_move()
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
   
   public int did_castle()
   {
      return did_castle;
   }
   public Piece get_captured()
   {
      return captured;
   }
   
   public Piece get_promotion()
   {
      return pawn_promotion;
   }
   
   public Move get_move()
   {
      return move;
   }
   
   public Move get_last_move()
   {
      return last_move;
   }
   
   public void setCaptured(Piece p){
      captured = p;
   }
   
   public void setPromo(int s){
	   pawn_promotion = s;
   }
   
   public void castled(int c){
	   did_castle = c;
   }
   
}