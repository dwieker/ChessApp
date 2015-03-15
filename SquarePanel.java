import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;


public class SquarePanel extends JPanel{

   private Image img = null;
   private Color defaultColor;
   private int row;
   private int col;
   
   
   public SquarePanel(int row, int col){
      this.row = row;
      this.col = col;
      
      addMouseListener( 
            new MouseListener(){
               public void mouseClicked(MouseEvent e){ 
                  //System.out.print(color);
                  ((BoardPanel)getParent()).handleMouseClick((SquarePanel)e.getSource());
                 
               }
            
               public void mouseExited(MouseEvent e){}
               public void mouseEntered(MouseEvent e){}
               public void mouseReleased(MouseEvent e){}
               public void mousePressed(MouseEvent e){}
            
            });
   }
     
   public void setImage(Image img){
      
      //System.out.println(img);
      this.img = img;
   }
   
   public Image getImage(){
      return img;
   }
   
   public void setDefaultColor(Color color){
      defaultColor = color;
      setBackground(color);
   }
   
   public Color getColor(){
      return defaultColor;
   }
  
   public void paint( Graphics g ) {
      super.paint( g );
      g.drawImage(img,  0 , 0 , getWidth() , getHeight() , null);
   
   }
   
   public void resetBackground(){
      setBackground(defaultColor);
   }
   
   public int row(){
      return row;
   }
   
   public int col(){
      return col;
   }
   
   public String toString(){   
      return "" + (char)(col + 97) + Integer.toString(row + 1);
   } 
   
      
 
}
   
   
      
