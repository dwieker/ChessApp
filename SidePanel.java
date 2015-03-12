import java.awt.*;
import javax.swing.*;

public class SidePanel extends JPanel{
   
   MainMenuPanel mainMenu;
	SettingsPanel settings;
   AboutPanel about;
	CardLayout cl;
   
   public SidePanel(){
      
      mainMenu = new MainMenuPanel();
      settings = new SettingsPanel();
      about = new AboutPanel();
	   cl = new CardLayout(); 
      
      setLayout(cl);  
      
      add(settings, "settings");
		add(about, "about");
      add(mainMenu, "mainMenu");
      
      
      cl.show(this, "mainMenu");
   }
}