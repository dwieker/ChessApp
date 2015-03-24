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
   
   
   public BoardPanel()
   {
      board = new Board();
      
      setPreferredSize( new Dimension(500, 500) );
      setLayout(new GridLayout(8,8));
      
      for(int i = 0; i < 8; i++)
      {
         for(int j = 0; j < 8; j++)
         {
         
            SquarePanel square = new SquarePanel(7 - i, j);
            squares[7 - i][j] = square;
            
            if(i%2 == 0 ^ j%2 == 0)
            {
               square.setDefaultColor(Color.GRAY);
            }
            else
            {
               square.setDefaultColor(Color.WHITE);     
            }
                           
            add(square);
         }
         
      }
   
      resetBoard();
   }
   
   public void handleMouseClick(SquarePanel sq)
   {
            
      //check that it's the correct person's turn
      if(activeSquare == null && sq.getImage() != null && board.checkSquare(sq.row(), sq.col()).color == board.curPlayer)
      {
         setActiveSquare(sq);
         sq.setBackground(Color.YELLOW);
         if(SettingsMenu.settings.getBoolean("showPossibleMoves", false))
         {
            for(SquarePanel s : allowedSquares)
            {
               s.setBorder(BorderFactory.createLineBorder(Color.GREEN, 5));
            }    
         }
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
            activeSquare.resetBackground();   
            for(SquarePanel p : allowedSquares)
            {
               p.resetBackground();
            }
            allowedSquares.clear();             
            activeSquare = null;
            //repaint();
            return;
         }
              
         activeSquare.resetBackground();   
         for(SquarePanel p : allowedSquares)
         {
            p.resetBackground();
         }
         allowedSquares.clear();             
         activeSquare = null;
         
         paintImmediately(0,0,getWidth(),getHeight());
         engineMove();
         
      }
      System.out.println(board);
   }
   
   public void engineMove()
   {
      EngineInterface.pipe("position fen " + board.toString());
      EngineInterface.pipe("go depth " + SettingsMenu.settings.getInt("depth", 1));
      
      String response;
      try
      {
      
         do{
            response = EngineInterface.in.readLine();
            System.out.println(response);
         }while(!response.split(" ")[0].equals("bestmove"));
         
         response = response.split(" ")[1];
         System.out.println(response);
         SquarePanel a = squares[Character.getNumericValue(response.charAt(1)) - 1][response.charAt(0) - 'a'];
         setActiveSquare(a);
         SquarePanel b = squares[Character.getNumericValue(response.charAt(3)) - 1][response.charAt(2) - 'a'];
      
         try
         {
            movePiece(a,b);
         }
         catch (InvalidMoveException e)
         {
            System.out.println("engine fucked up");
         }
         finally
         {
            allowedSquares.clear();             
            activeSquare = null;
         }
         
         
      }
      catch(IOException e)
      {
      
      }
      
      
   
   
   }
    
   
   public void movePiece(SquarePanel a, SquarePanel b) throws InvalidMoveException{
      
      if(!allowedSquares.contains(b))
      {     
         throw new InvalidMoveException();
      }
     
      
      //update internal board
      Move m = new Move(a.toString() + b.toString());
      MoveHistory mh = board.movePiece(m);
      
      //just look at mh.getEnPassant instead. math was already handled !!!!!!!!!!! FIX
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
            squares[7][3].setImage(squares[7][0].getImage());
            squares[7][0].setImage(null);
            squares[7][0].repaint();
            break;     
         case 4:
            squares[7][5].setImage(squares[7][7].getImage());
            squares[7][7].setImage(null);
            squares[7][7].repaint();
            break;    
      }
     
     //check for pawn promotion
      if(mh.getPromo())
      {
         try
         {
            char c = board.checkSquare(b.row(), b.col()).color;
            File file = new File("PieceImages/" + c + "Queen.png");
            Image img =  ImageIO.read(file);
            b.setImage(img);
            System.out.println("image changed to queen!");
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
                
      
      b.setImage(a.getImage());
      a.setImage(null);   
      b.repaint();
      a.repaint();
                                    
   }
   
   public void fillAllowedMoves()
   {
      //find legal moves
      Move[] moves = new Move[30];
      MoveHistory mh;
      SquarePanel panel;
      
      
      Piece p = board.checkSquare(activeSquare.row(), activeSquare.col());
      int n = p.genMoves(board, moves, 0);
      
            
      for(int i = 0; i < n; i++)
      {
         mh = board.movePiece(moves[i]);
         if(board.curPlayer == 'b' ? ((King)(board.wKing)).isChecked(board) : ((King)(board.bKing)).isChecked(board))
         {
            board.unmovePiece(mh);
            continue;
         }
         else
         {
            board.unmovePiece(mh);
         }
         
         panel = squares[moves[i].s2()/16][moves[i].s2()%16];
         allowedSquares.add(panel);
               
      }
   
   }
   
   public void setActiveSquare(SquarePanel sq)
   {
      activeSquare = sq;
      fillAllowedMoves(); 
   }
   
   public void resetBoard()
   {
         
      board.setup(Board.STARTFEN);
            
      for(int i = 0; i < 8; i++)
      {
         for(int j = 0; j < 8; j++)
         {
         
                           
            Piece p = board.checkSquare(7 - i, j);
            
            try
            {
               File file = new File("PieceImages/" + p.color + p.getClass().getSimpleName() + ".png");
               Image img =  ImageIO.read(file);
               squares[7 - i][j].setImage(img);
            }
            catch(IOException e){
               //do nothing
            }
            catch(NullPointerException e){
               squares[7 - i][j].setImage(null);
            }
            
         }
         
      }
      repaint();
   
   }
   
   
   private class InvalidMoveException extends Exception{}
}
