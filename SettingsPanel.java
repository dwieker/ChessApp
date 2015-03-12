import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class SettingsPanel extends JPanel{
   
   JButton backButton;

   public SettingsPanel(){
   
      backButton = new JButton("Back");   
      setBackground(Color.BLUE);
      add(backButton);
      
      backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				((CardLayout)((JPanel)getParent()).getLayout()).show(((JPanel)getParent()), "mainMenu");
			}
		});
   }
}
