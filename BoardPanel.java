import java.awt.*;
import javax.swing.*;

public class BoardPanel extends JPanel{

   public BoardPanel(){
      setBackground(Color.BLACK);
      setPreferredSize( new Dimension(500, 500) );
      setLayout(new GridLayout(8,8) );
      
      for(int i = 0; i < 64; i++){
      
         SquarePanel square = new SquarePanel();
         if(i%8%2 == 0 ^ i/8%2 == 0)
            square.setBackground(Color.BLACK);
         else
            square.setBackground(Color.WHITE);
            

         add(square);
      }
   }
}
