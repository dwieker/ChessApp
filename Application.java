import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Application extends JFrame{
	
   BoardPanel board;
   MenuBar menuBar;
   EngineInterface eInterface;
      
   public Application(){
        
      eInterface = new EngineInterface();
      board = new BoardPanel(eInterface);
      eInterface.attatchBoard(board);
      menuBar = new MenuBar(eInterface);
      
     
      getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS)); 
      getContentPane().add(board, "board");
      setJMenuBar(menuBar);
      
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      setSize(700,500);
      pack();
      setVisible(true);
          
          		
   }

   public static void main(String[] args) {
      SwingUtilities.invokeLater(
            new Runnable() {
               @Override
               public void run() {
                  new Application();
               }
            });
   }
   
     

}