import java.awt.*;
import javax.swing.*;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import ChessEngine.*;

public class BoardPanel extends JPanel{

   SquarePanel[][] squares = new SquarePanel[8][8];
   SquarePanel activeSquare = null;
   Board b;
   
   
   public BoardPanel(){
      b = new Board();
      b.setup(Board.STARTFEN);
      setPreferredSize( new Dimension(500, 500) );
      setLayout(new GridLayout(8,8));
      
      for(int i = 0; i < 8; i++){
         for(int j = 0; j < 8; j++){
         
            SquarePanel square = new SquarePanel();
            squares[i][j] = square;
            
            if(i%2 == 0 ^ j%2 == 0)
            {
               square.setColor(Color.GRAY);
               square.setBackground(Color.GRAY);
            }
            else
            {
               square.setColor(Color.WHITE);
               square.setBackground(Color.WHITE);     
            }
               
            
            Piece p = b.checkSquare(7 - i, 7 - j);
            
            try{
               File file = new File("PieceImages/" + p.color + p.getClass().getSimpleName() + ".png");
               Image img =  ImageIO.read(file);
               System.out.println("PieceImages/" + p.color + p.getClass().getSimpleName() + ".png");
               square.setImage(img);
            }
            catch(IOException e){
               //do nothing
            }
            catch(NullPointerException e){
               //efsdf
            }
            
            add(square);
         }
         
      }
   }
   
   public void handleMouseClick(SquarePanel sq)
   {
      if(activeSquare == null)
      {
         setActiveSquare(sq);
      }
      else
      {
         activeSquare.resetBackground();
         movePiece(activeSquare, sq);
         activeSquare = null;
      }
   }
   
   public void movePiece(SquarePanel a, SquarePanel b)
   {
           
      b.setImage(a.getImage());
      b.repaint();
      a.setImage(null);
             
   }
   
   public void setActiveSquare(SquarePanel sq)
   {
      sq.setBackground(Color.YELLOW);
      activeSquare = sq;
   }
}
