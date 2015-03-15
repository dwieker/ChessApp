import javax.swing.*; 
import java.io.*; 
import java.awt.*; 
import java.awt.image.*; 
import javax.imageio.*; 


public class PaintImage extends JPanel 
{ 
  
  public PaintImage () 
  { 
    super(); 
    ImageIcon image = new ImageIcon("rook.png");
    JLabel label = new JLabel();
    label.setIcon(image);
    add( label );         
   
  } 

  public static void main(String [] args) 
  { 
    JFrame f = new JFrame("Window"); 
    f.add(new PaintImage()); 
    f.setVisible(true); 
  } 
}