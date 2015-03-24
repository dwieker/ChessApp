
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBar extends JMenuBar
{
   public MenuBar()
   {
      JMenu menu = new JMenu("Game");
      JMenuItem menuItem = new JMenuItem("New Game");
      menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EngineInterface.pipe("ucinewgame");
            EngineInterface.board.setBoard();
			}
		});
      
      

         

      menu.add(menuItem);
      add(menu);
      add(new SettingsMenu());
   }
   


}