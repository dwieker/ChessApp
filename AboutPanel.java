import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import ChessEngine.*;


public class AboutPanel extends JPanel{
   
   JButton backButton;

   public AboutPanel(){
      
      backButton = new JButton("Back");
      setBackground(Color.GREEN);
      add(backButton);
      
      backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				((CardLayout)((JPanel)getParent()).getLayout()).show(((JPanel)getParent()), "mainMenu");
			}
		});
   }
}
