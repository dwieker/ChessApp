import java.awt.*;
import javax.swing.*;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;


public class SquarePanel extends JPanel{
   
   public SquarePanel(){
      addMouseListener( new MouseListener(){
         public void mouseClicked(MouseEvent e){
            setBackground(Color.GREEN);
         }
         
         public void mouseExited(MouseEvent e){}
         public void mouseEntered(MouseEvent e){}
         public void mouseReleased(MouseEvent e){}
         public void mousePressed(MouseEvent e){}
      });
   }
}
   
   //public void mousePressed(MouseEvent e){
      
   