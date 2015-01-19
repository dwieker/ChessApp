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
   
   int type;
   int pos; // location in the squares array
   char letter;
   int value;
  
   
   public Piece(int type, int pos){
      this.type = type;
      this.pos = pos;
      
      if(type == PAWN){letter = 'P';value = 100;}
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
}