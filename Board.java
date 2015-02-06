import java.util.*;

public class Board{
   static final int WHITE = 1;
   static final int BLACK = -1;
   static final String STARTFEN = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
   static final String POSITION4 = "r3k2r/Pppp1ppp/1b3nbN/nP6/BBP1P3/q4N2/Pp1P2PP/R2Q1RK1 w kq - 0 1";
   static final String TEST = "r3k2r/Pppp1ppp/1b3nbN/nP6/BBP1P3/q4N2/Pp1P2PP/R2Q1RK1 w kq - 0 1";
   
   private int curPlayer;
   private int move_count;
   private boolean[] wCastleRights = new boolean[2]; //first index is king side, second queen side
   private boolean[] bCastleRights = new boolean[2];
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
      wCastleRights[0] = false;
      wCastleRights[1] = false;
      bCastleRights[0] = false;
      bCastleRights[1] = false;
      
      
      //Scan FEN
      int row = 7, col = 0, i = 0;
      while(true){
         if(FEN.charAt(i) == '/' ){ row--; col = 0;}
         else if(FEN.charAt(i) > '0' && FEN.charAt(i) < '9')
            col += Character.getNumericValue(FEN.charAt(i));
         
         else if(FEN.charAt(i) == ' '){
            
            //read current player
            i++;
            if(FEN.charAt(i) == 'w') curPlayer = WHITE;
            else if(FEN.charAt(i) == 'b') curPlayer = BLACK;
              
            //Read castle rights
            i++; i++;
            while(FEN.charAt(i) != '-'  && FEN.charAt(i) != ' ' ){
               if(FEN.charAt(i) == 'K') wCastleRights[0] = true;
               else if(FEN.charAt(i) == 'Q') wCastleRights[1] = true;
               else if(FEN.charAt(i) == 'k') bCastleRights[0] = true;
               else if(FEN.charAt(i) == 'q') bCastleRights[1] = true;
               i++;
            }
            
            break;
         }
         
         else{
            temp = new Piece(FEN.charAt(i), row*16 + col);
            if(temp.letter == 'K') wKing = temp;
            else if(temp.letter == 'k') bKing = temp;
            else pieces.add(temp);
            
            squares[row*16 + col] = temp;
            col++;
         }
         
         i++;
        
      }
    
   }
   
   
   public MoveHistory movePiece(Move m){
   
      MoveHistory mh = new MoveHistory(m, bCastleRights, wCastleRights, enPassantSq);
      curPlayer *= -1;
      enPassantSq = -1;
      int taken;
      if (squares[m.s2()] != null){
         mh.setCaptured(squares[m.s2()]);   
         pieces.remove(squares[m.s2()]);
      }
      else if(Math.abs(squares[m.s1()].getType()) == Piece.PAWN){
         if((m.s2() - m.s1()) % 16 != 0){
            taken = m.s2() + curPlayer*16;
            mh.setCaptured(squares[taken]);
            pieces.remove(squares[taken]);
            squares[taken] = null;
         }
         else if(Math.abs(m.s2() - m.s1()) == 32){
            enPassantSq = m.s2() + curPlayer;
         }
      }
      if(squares[m.s1()].getType() == Piece.PAWN && m.s2() > 111){
         squares[m.s1()].setType(Piece.QUEEN, 'Q', 900);
         mh.setPromo();
      }
      else if(squares[m.s1()].getType() == Piece.pawn && m.s2() < 8){
         squares[m.s1()].setType(Piece.queen, 'q', 900);
         mh.setPromo();
      }
      if(squares[m.s1()].getType() == Piece.KING){
         wCastleRights[0] = wCastleRights[1] = false;
         if(m.s1() == 4 && m.s2() == 6){
            squares[5] = squares[7];
            squares[5].setPos(5);
            squares[7] = null;
            mh.castled(2);
         }
         else if(m.s1() == 4 && m.s2() == 2){
            squares[3] = squares[0];
            squares[3].setPos(3);
            squares[0] = null;
            mh.castled(1);
         }
      }
      else if(squares[m.s1()].getType() == Piece.king){
         bCastleRights[0] = bCastleRights[1] = false;
         if(m.s1() == 116 && m.s2() == 118){
            squares[117] = squares[119];
            squares[117].setPos(117);
            squares[119] = null;
            mh.castled(4);
         }
         else if(m.s1() == 116 && m.s2() == 114){
            squares[115] = squares[112];
            squares[115].setPos(115);
            squares[112] = null;
            mh.castled(3);
         }
      }
      else if(squares[m.s1()].getType() == Piece.ROOK || squares[m.s1()].getType() == Piece.rook)
      {
         if(m.s1() == 7) wCastleRights[0] = false;
         else if(m.s1() == 0) wCastleRights[1] = false;
         else if(m.s2() == 112) bCastleRights[1] = false;
         else if(m.s2() == 119) bCastleRights[0] = false;
      }
      
      squares[m.s2()] = squares[m.s1()];
      squares[m.s2()].setPos(m.s2());
      squares[m.s1()] = null;
      
      return mh;
   }
   
   
   void unmovePiece(MoveHistory mh){
      curPlayer *= -1;
      squares[mh.getMove().s1()] = squares[mh.getMove().s2()];  //move piece back to previous square
      squares[mh.getMove().s1()].setPos(mh.getMove().s1()); //update pieces position
      squares[mh.getMove().s2()] = null; //empty old square
      bCastleRights = mh.get_b_rights();
      wCastleRights = mh.get_w_rights();
      enPassantSq =  mh.getEnPassant();
   
   
      if(mh.getCaptured() != null){
         squares[mh.getMove().s2()] = mh.getCaptured();
         pieces.add(mh.getCaptured());
      
      }
      if(mh.getPromo()){
         if(curPlayer == WHITE)
            squares[mh.getMove().s1()].setType(Piece.PAWN, 'P', 100);
         else
            squares[mh.getMove().s1()].setType(Piece.pawn, 'p', 100);
      }
      else if(mh.did_castle() > 0) {
         if(mh.did_castle() == 1){
            wCastleRights[0] = true;
            squares[0] = squares[3];
            squares[3] = null;
            squares[0].setPos(0);
         }
         else if(mh.did_castle() == 2){
            wCastleRights[1] = true;
            squares[7] = squares[5];
            squares[5] = null;
            squares[7].setPos(7);
         }
         else if(mh.did_castle() == 3){
            bCastleRights[0] = true;
            squares[112] = squares[115];
            squares[115] = null;
            squares[112].setPos(112);
         }
         else if(mh.did_castle() == 4){
            bCastleRights[1] = true;
            squares[119] = squares[117];
            squares[117] = null;
            squares[119].setPos(119);
         }
      }
        
   }
         
   
   public String printFEN(){
      int blanks = 0;
      String print = new String("");;
      for(int i = 7; i > -1; i--){
         for(int j = 0; j < 8; j++){
            if(squares[i*16 + j] != null){
               if(blanks > 0){
                  print += blanks;
                  blanks = 0;
               }
               print += squares[i*16 + j].letter;
            }
            else blanks++;
         }
         if(blanks > 0) print += blanks;
         blanks = 0;
         if(i != 0) print += "/";
      }
      
      //add space
      print += " ";
      
      //print current player
      if(curPlayer == WHITE)  print += "w";
      else if(curPlayer == BLACK)  print += "b";
      
      //add space
      print += " ";
      
      //print castle rights  
      if(bCastleRights[0] || bCastleRights[1] || wCastleRights[0] || wCastleRights[1]){
         if(wCastleRights[0]) print += "K";
         if(wCastleRights[1]) print += "Q";
         if(bCastleRights[0]) print += "k";
         if(bCastleRights[1]) print += "q";   
      }
      else
         print += "-";
         
      //add space
      print += " ";
     
     //to be fixed later...
      print += "- 0 1" ;
      
      return print;
   }
   
   public Piece checkSquare(int s){
      return squares[s];
   }
   
   public boolean whiteCastle(int n){
      return wCastleRights[n];
   }
   
   public boolean blackCastle(int n){
      return bCastleRights[n];
   }

   public int enPassant(){
      return enPassantSq;
   }
   
   public int genMoves(Move[] moves){
      int n = 0, type;
      
      for(Piece p: pieces){
         if((type = p.getType()) * curPlayer > 0){
            System.out.print(type + "   ");
            if(Math.abs(type) == Piece.KNIGHT){
               n = MoveGen.knightMoves(this, p, moves, n);
               System.out.println(n);
            }
            else if(Math.abs(type) == Piece.BISHOP){
               n = MoveGen.checkDiagonals(this, p, moves, n);
               System.out.println(n);
            }
            else if(Math.abs(type) == Piece.ROOK){
               n = MoveGen.checkRows(this, p, moves, n); 
               System.out.println(n);
            }
            
            else if(Math.abs(type) == Piece.QUEEN){
               n = MoveGen.checkRows(this, p, moves, n);
               n = MoveGen.checkDiagonals(this, p, moves, n);
               System.out.println(n);
            }
            else if(type == Piece.PAWN){
               n = MoveGen.wPawnMoves(this, p, moves, n);
               System.out.println(n);
            }
            
            else if(type == Piece.pawn){
               n = MoveGen.bPawnMoves(this, p, moves, n);
               System.out.println(n);
            }
         
         }
         
      
      }
      
      if(curPlayer > 0) n = MoveGen.wKingMoves(this, wKing, moves, n); 
      else n = MoveGen.bKingMoves(this, bKing, moves, n);
      
      return n;
   }
   
}