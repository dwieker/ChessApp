import java.util.*;

public class Board{
   static final int WHITE = 1;
   static final int BLACK = -1;
   static final String start_pos = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
   
   private int cur_player;
   private int move_count;
   private boolean[] w_c_rights = new boolean[2]; //first index is king side, second queen side
   private boolean[] b_c_rights = new boolean[2];
   private int enPassantSq = -1;
   private Piece[] squares;
   private LinkedList<Piece> pieces;
   private Piece wKing;
   private Piece bKing;
      
   public Board(String FEN){
      squares = new Piece[128];
      pieces = new LinkedList<Piece>();
      Piece temp;

      //initialize castle rights to false
      w_c_rights[0] = false;
      w_c_rights[1] = false;
      b_c_rights[0] = false;
      b_c_rights[1] = false;
      
      
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
               if(FEN.charAt(i) == 'K') w_c_rights[0] = true;
               else if(FEN.charAt(i) == 'Q') w_c_rights[1] = true;
               else if(FEN.charAt(i) == 'k') b_c_rights[0] = true;
               else if(FEN.charAt(i) == 'q') b_c_rights[1] = true;
               i++;
            }
            
            break;
         }
         
         else{
            temp = new Piece(FEN.charAt(i), row*16 + col);
            if(temp.letter = "K") wKing = temp;
            else if(temp.letter = "k") bKing = temp;
            else pieces.add(temp);
            
            squares[row*16 + col] = temp;
            col++;
         }
         
         i++;
        
      }
    
   }
   
   
   public MoveHistory movePiece(Move m){
   
    MoveHistory mh = new MoveHistory(m, b_c_rights, w_c_rights, enPassantSq);

    if (squares[m.s1()] != null){
        move_history.setCaptured(squares[s2]);   
        pieces.remove(squares[s2]);
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
      if(b_c_rights[0] || b_c_rights[1] || b_c_rights[0] || b_c_rights[1]){
         if(b_c_rights[0]) System.out.print("K");
         if(b_c_rights[1]) System.out.print("Q");
         if(b_c_rights[0]) System.out.print("k");
         if(b_c_rights[1]) System.out.print("q");   
      }
      else
         System.out.print("-");
         
      //add space
      System.out.print(" ");
     
     //to be fixed later...
      System.out.print( "- 0 1" );
   }

   
}