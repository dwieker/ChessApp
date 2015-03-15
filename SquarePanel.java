import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;


public class SquarePanel extends JPanel{

   private Image img = null;
   private Color defaultColor;
   
   
   public SquarePanel(){
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
   
   public void setColor(Color color){
      defaultColor = color;
   }
   
   public Color getColor(){
      return defaultColor;
   }
  
   public void paint( Graphics g ) {
      super.paint( g );
      g.drawImage(img,  0 , 0 , getWidth() , getHeight() , null);
   
   }
   
   public void resetBackground()
   {
      setBackground(defaultColor);
   }
   
   

   
   
}
   
   
      
