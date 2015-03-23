import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Application extends JFrame{
	
 	BoardPanel board = new BoardPanel();
   SidePanel sidePanel = new SidePanel();
   EngineInterface eInterface;   
	public Application(){
   
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS)); 
		getContentPane().add(board, "board");
      getContentPane().add(sidePanel, "sidepanel");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      setSize(700,500);
		pack();
		setVisible(true);
      
      eInterface = new EngineInterface(board, "engines/stockfish");
      new Thread(eInterface).start();
		
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Application();
			}
		});
	}

}