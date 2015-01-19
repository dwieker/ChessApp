import java.util.*;

public class Board{
   static final int WHITE = 1;
   static final int BLACK = -1;
   static final String start_pos = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
   
   private int cur_player;
   private int move_count;
   private boolean[] b_castle_rights = new boolean[2]; //first index is king side, second queen side
   private boolean[] w_castle_rights = new boolean[2];
   private Move last_move;
   private Piece[] squares;
   private LinkedList<Piece> white_pieces, black_pieces;
   private Piece temp;
   
   public Board(String FEN){
      squares = new Piece[128];
      white_pieces = new LinkedList<Piece>();
      black_pieces = new LinkedList<Piece>();
     
      //fill rest of board with null pieces. value = 0
      for(int i = 0; i < 128; i++)
         squares[i] = null;
         
      //initialize castle rights to false
      w_castle_rights[0] = false;
      w_castle_rights[1] = false;
      b_castle_rights[0] = false;
      b_castle_rights[1] = false;
      
      
      //Scan FEN
      int row = 7, col = 0, i = 0;
      while(true){
         if(FEN.charAt(i) == '/' ){ row--; col = 0;}
         else if(FEN.charAt(i) > '0' && FEN.charAt(i) < '9')
            col += Character.getNumericValue(FEN.charAt(i));
        
         else if(FEN.charAt(i) == ' '){
            
            //read current player
            i++;
            if(FEN.charAt(i) == 'w') cur_player = WHITE;
            else if(FEN.charAt(i) == 'b') cur_player = BLACK;
            
            
            //Read castle rights
            i++; i++;
            while(FEN.charAt(i) != '-'  && FEN.charAt(i) != ' ' ){
               if(FEN.charAt(i) == 'K') w_castle_rights[0] = true;
               else if(FEN.charAt(i) == 'Q') w_castle_rights[1] = true;
               else if(FEN.charAt(i) == 'k') b_castle_rights[0] = true;
               else if(FEN.charAt(i) == 'q') b_castle_rights[1] = true;
               i++;
            }
            
            break;
         
         }
         
         else{
            temp = new Piece(FEN.charAt(i), row*16 + col);
            if(temp.getType() > 0) white_pieces.add(temp);
            else if(temp.getType() < 0) black_pieces.add(temp);
            squares[row*16 + col] = temp;
            col++;
         }
         
         i++;
        
      }
    
   }
   
   
   
   public void printFEN(){
      int blanks = 0;
      for(int i = 7; i > -1; i--){
         for(int j = 0; j < 8; j++){
            if(squares[i*16 + j] != null){
               if(blanks > 0){
                  System.out.print(blanks);
                  blanks = 0;
               }
               System.out.print(squares[i*16 + j].letter);
            }
            else blanks++;
         }
         if(blanks > 0) System.out.print(blanks);
         blanks = 0;
         if(i != 0) System.out.print("/");
      }
      
      //add space
      System.out.print(" ");
      
      //print current player
      if(cur_player == WHITE)  System.out.print("w");
      else if(cur_player == BLACK)  System.out.print("b");
      
      //add space
      System.out.print(" ");
      
      //print castle rights  
      if(w_castle_rights[0] || w_castle_rights[1] || b_castle_rights[0] || b_castle_rights[1]){
         if(w_castle_rights[0]) System.out.print("K");
         if(w_castle_rights[1]) System.out.print("Q");
         if(b_castle_rights[0]) System.out.print("k");
         if(b_castle_rights[1]) System.out.print("q");   
      }
      else
         System.out.print("-");
         
      //add space
      System.out.print(" ");
     
     //to be fixed later...
      System.out.print( "- 0 1" );
   }

   
}