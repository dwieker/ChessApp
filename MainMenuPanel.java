import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainMenuPanel extends JPanel{
   JButton settingsButton;
	JButton aboutButton;
   JButton newGameButton;


   public MainMenuPanel(){
   
      settingsButton = new JButton("Settings");
      aboutButton = new JButton("About");
      newGameButton = new JButton("New Game!!!!!!");
      setLayout(new GridLayout(2,1));
 		add(settingsButton);
		add(aboutButton);
      add(newGameButton);
      
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
      
      newGameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				EngineInterface.pipe("ucinewgame");
            EngineInterface.board.resetBoard();
			}
		});

   }
}