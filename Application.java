import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Application extends JFrame{
	
   //JFrame frame = new JFrame("Devin-Jesenia Chess Application!");
	//JPanel superPanel = new JPanel();
	BoardPanel board = new BoardPanel();
   SidePanel sidePanel = new SidePanel();
   
	public Application(){
   
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS)); 
		getContentPane().add(board, "board");
      getContentPane().add(sidePanel, "sidepanel");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      setSize(700,500);
		pack();
		setVisible(true);
		
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