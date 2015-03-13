import java.awt.*;
import javax.swing.*;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;


public class SquarePanel extends JPanel{

   public static SquarePanel prevClicked = null;
   private Color color;
   
   public SquarePanel(){
      addMouseListener( new MouseListener(){
         public void mouseClicked(MouseEvent e){
            if(prevClicked == null){
               color = getBackground();
               setBackground(Color.GREEN);
               prevClicked = (SquarePanel)e.getSource();
            }else{
               prevClicked.resetBackground();
               prevClicked = null;
            }
         }
         
         public void mouseExited(MouseEvent e){}
         public void mouseEntered(MouseEvent e){}
         public void mouseReleased(MouseEvent e){}
         public void mousePressed(MouseEvent e){}
      });
   }
   
   public void resetBackground(){
      setBackground(color);
   }
}
   
   //public void mousePressed(MouseEvent e){
      
   