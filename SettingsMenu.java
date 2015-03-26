import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ItemListener;
import java.util.prefs.Preferences;
import java.awt.event.ItemEvent;




public class SettingsMenu extends JMenu{
   

   JCheckBoxMenuItem allowedMovesCheckBox;
   JPopupMenu popup;
   JMenuItem depthSearchButton;
   
   
   public static Preferences settings;
   

   public SettingsMenu(){
      super("Settings");
      
  
      settings = Preferences.userRoot().node("Settings");
           
        
      //add check box
      allowedMovesCheckBox = new JCheckBoxMenuItem("Show allowed moves", settings.getBoolean("showPossibleMoves",false)); 
      allowedMovesCheckBox.addItemListener(
            new ItemListener() {
               public void itemStateChanged(ItemEvent e) {
                  if (e.getStateChange() == ItemEvent.DESELECTED) {
                     settings.putBoolean("showPossibleMoves", false);
                  }
                  else if (e.getStateChange() == ItemEvent.SELECTED) {
                     settings.putBoolean("showPossibleMoves", true);
                  }
               
               }
            });
            
                  
      //create popup object
      popup = new JPopupMenu();
      popup.setPopupSize(200,60);
      popup.setBorderPainted(true);
      
      JSlider slider = new JSlider(1,20,settings.getInt("depth", 10));
      slider.setMajorTickSpacing(2);
      slider.setSnapToTicks(true);
      slider.setPaintLabels(true);
      slider.addChangeListener(new ChangeListener() {
         public void stateChanged(ChangeEvent e)
         {      
            //System.out.println(slider.getValue());
            settings.putInt("depth", slider.getValue());
         }
      });
      
      popup.add(slider);
      
      
   
      //add button to invoke depth popup
      depthSearchButton = new JMenuItem("Set Search Depth");
      depthSearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				popup.show(getParent().getParent(), 150, 200);
			}
		});

      
      
           
      add(depthSearchButton);
      addSeparator();      
      add(allowedMovesCheckBox);
   
      
      
      
   }
   
 
}
