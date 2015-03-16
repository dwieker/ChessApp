import java.awt.*;
import javax.swing.*;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import ChessEngine.*;
import java.util.*;

public class BoardPanel extends JPanel{

   SquarePanel[][] squares = new SquarePanel[8][8];
   SquarePanel activeSquare = null;
   ArrayList<SquarePanel> allowedSquares = new ArrayList<SquarePanel>();
   Board board;
   
   
   public BoardPanel(){
      board = new Board();
      board.setup(Board.STARTFEN);
      setPreferredSize( new Dimension(500, 500) );
      setLayout(new GridLayout(8,8));
      
      for(int i = 0; i < 8; i++){
         for(int j = 0; j < 8; j++){
         
            SquarePanel square = new SquarePanel(7 - i, j);
            squares[7 - i][j] = square;
            
            if(i%2 == 0 ^ j%2 == 0){
               square.setDefaultColor(Color.GRAY);
            }
            else{
               square.setDefaultColor(Color.WHITE);     
            }
               
            
            Piece p = board.checkSquare(7 - i, j);
            
            try{
               File file = new File("PieceImages/" + p.color + p.getClass().getSimpleName() + ".png");
               Image img =  ImageIO.read(file);
               square.setImage(img);
            }
            catch(IOException e){
               //do nothing
            }
            catch(NullPointerException e){
               //do nothing
            }
            
            add(square);
         }
         
      }
   }
   
   public void handleMouseClick(SquarePanel sq)
   {
      System.out.println(sq.toString());
      
      //check that it's the correct person's turn
      if(activeSquare == null && sq.getImage() != null && board.checkSquare(sq.row(), sq.col()).color == board.curPlayer)
      {
         setActiveSquare(sq);
         fillAllowedSquares();     
      }
      else if(activeSquare != null)
      {
          //attempt to movePiece
         try
         {
            movePiece(activeSquare, sq);
         }
         catch (InvalidMoveException e)
         {
            //do nothing
         }
      
         activeSquare.resetBackground();
         
         for(SquarePanel p : allowedSquares)
         {
            p.resetBackground();
         }
         allowedSquares.clear();
            
         
         
                 
         activeSquare = null;
      }
      System.out.println(board);
   }
   
   public void movePiece(SquarePanel a, SquarePanel b) throws InvalidMoveException
   {
      
      if(!allowedSquares.contains(b))
      {     
         throw new InvalidMoveException();
      }
     
      b.setImage(a.getImage());
      
      //update internal board
      Move m = new Move(a.toString() + b.toString());
      MoveHistory mh = board.movePiece(m);
      
      //just look at mh.getEnPassant instead. math was already handled
      Piece p = mh.getCaptured();
      if(p != null && p.getPos()/16 != b.row())
      {
         SquarePanel sq = squares[p.getPos()/16][p.getPos()%16];
         sq.setImage(null);
         sq.repaint();
      }
     
      //check for castle
      switch(mh.didCastle())
      {
         case -1:
            break;
         case 1:
            squares[0][3].setImage(squares[0][0].getImage());
            squares[0][0].setImage(null);
            squares[0][0].repaint();
            break;
         case 2:
            squares[0][5].setImage(squares[0][7].getImage());
            squares[0][7].setImage(null);
            squares[0][7].repaint();
            break;    
         case 3:
            squares[0][3].setImage(squares[0][0].getImage());
            squares[0][0].setImage(null);
            break;     
         case 4:
            squares[0][3].setImage(squares[0][0].getImage());
            squares[0][0].setImage(null);
            break;    
     }
     
     //check for pawn promotion
     if(mh.getPromo())
     {
           try
           {
               char c = board.checkSquare(7-b.row(), b.col()).color;
               File file = new File("PieceImages/" + c + "Queen.png");
               Image img =  ImageIO.read(file);
               b.setImage(img);
           }
            catch(IOException e)
            {
               System.out.println(e);
            }
            catch(NullPointerException e)
            {
               System.out.println(e);
            }
              
     }
           
         
      //update image placement
      b.repaint();
      a.setImage(null);
                 
      
          
                     
   }
   
   public void fillAllowedSquares()
   {
      //find legal moves
      Move[] moves = new Move[30];
      Piece p = board.checkSquare(activeSquare.row(), activeSquare.col());
      int n = p.genMoves(board, moves, 0);
      if(board.enPassant() > -1)
         System.out.println(" - " +board.enPassant()+ " - ");
      MoveHistory mh;
      SquarePanel panel;
      
      for(int i = 0; i < n; i++) System.out.println(moves[i]);
      
      for(int i = 0; i < n; i++)
      {
         mh = board.movePiece(moves[i]);
         if(board.curPlayer == 'b' ? ((King)(board.wKing)).isChecked(board) : ((King)(board.bKing)).isChecked(board)){
            board.unmovePiece(mh);
            continue;
         }
         else
         {
            board.unmovePiece(mh);
         }
         
         if(board.enPassant() != -1) System.out.println("here2");
         panel = squares[moves[i].s2()/16][moves[i].s2()%16];
         allowedSquares.add(panel);
         panel.setBorder(BorderFactory.createLineBorder(Color.GREEN, 5));
      
      }
   
   }
   
   public void setActiveSquare(SquarePanel sq)
   {
      sq.setBackground(Color.YELLOW);
      activeSquare = sq;
   }
   
   
   private class InvalidMoveException extends Exception{}
}
