import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainMenuPanel extends JPanel{
   JButton settingsButton;
	JButton aboutButton;


   public MainMenuPanel(){
   
      settingsButton = new JButton("Switch to settings");
      aboutButton = new JButton("Switch to about screen");
      setLayout(new GridLayout(2,1));
 		add(settingsButton);
		add(aboutButton);
      
      
		settingsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				((CardLayout)((JPanel)getParent()).getLayout()).show(((JPanel)getParent()), "settings");
			}
		});
      
      aboutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				((CardLayout)((JPanel)getParent()).getLayout()).show(((JPanel)getParent()), "about");
			}
		});
   }
}