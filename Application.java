import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Application{
	
   JFrame frame = new JFrame("Devin-Jesenia Chess Application!");
	JPanel superPanel = new JPanel();
	BoardPanel board = new BoardPanel();
   SidePanel sidePanel = new SidePanel();
   
	public Application(){
   
		superPanel.setLayout(new BoxLayout(superPanel, BoxLayout.X_AXIS)); 
		superPanel.add(board, "board");
      superPanel.add(sidePanel, "sidepanel");
		frame.add(superPanel);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      frame.setSize(700,500);
		frame.pack();
		frame.setVisible(true);
		
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