public class MoveHistory{
   private Move move;
   private boolean[] b_castle_rights;
   private boolean[] w_castle_rights;
   private int did_castle; //0-queen side white, 1-king side white, 2-queen side black, 3-king side black
   private Piece captured;
   private Piece pawn_promotion;
   private Move last_move;
   
   public MoveHistory(Move m, boolean[] b, boolean[] w, int did, Piece c, Piece p, Move l){
      move = m;
      b_castle_rights[0] = b[0];
      b_castle_rights[1] = b[1];
      w_castle_rights[0] = w[0];
      w_castle_rights[1] = w[1];
      did_castle = did;
      captured = c;
      pawn_promotion = p;
      last_move = l;
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
   
}