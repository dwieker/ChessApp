public abstract class Piece{


   public static final int[] kingDeltas = {-1, 15, 16, 17, 1, -15, -16, -17};
   public static final int[] knightDeltas =  {31, 33, 18, -14, -31, -33, 14, -18}; 
   public static final int[] rows = {1, -1, 16, -16};  
   public static final int[] diagonals = {15, -15, 17, -17};
   
   public char color;
   public int pos; // location in the squares array
   public int value;
     
   
   public Piece(char color, int pos, int value){
      this.color = color;
      this.pos = pos;
      this.value = value;
   }
   
        
   public abstract int genMoves(Board board, Move[] moves, int n);
   
   public int rayCheck(Board board, Move[] moves, int n, int[] deltas){
      Piece temp;
      int delta;
            
      for(int i = 0; i < 4; i++){
         delta = deltas[i];
         for(int j = pos + delta; (j&0x88) == 0; j += delta){
            temp = board.checkSquare(j);
            if(temp == null) moves[n++] = new Move(pos, j);
            else if(temp.color != color){
               moves[n++] = new Move(pos, j);
               break;
            }
            else 
               break;
         }
      }
      
      return n;
   }
   
   public void setPos(int n){
      pos = n;
   }
   
   public int getPos(){
      return pos;
   }
   
   public abstract char getLetter();
      
       
}  