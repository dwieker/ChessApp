import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.prefs.Preferences;


public class SettingsPanel extends JPanel{
   
   JButton backButton;
   
   JSlider searchDepthSlider;
   
   
   
   public static Preferences settings;
   

   public SettingsPanel(){
      settings = Preferences.userRoot().node("Settings");
   
      setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
      
  
      backButton = new JButton("Back");   
      add(backButton);
      
      
      add(new JLabel("Max Search Depth"));
      searchDepthSlider = new JSlider(JSlider.HORIZONTAL, 0, 5, 3);
      searchDepthSlider.setMajorTickSpacing(1);
      searchDepthSlider.setPaintTicks(true);
      searchDepthSlider.setPaintLabels(true);
      searchDepthSlider.setSnapToTicks(true);
      add(searchDepthSlider);
      
      setBackground(Color.WHITE);
      
      
      backButton.addActionListener(
            new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent arg0) {
                  ((CardLayout)((JPanel)getParent()).getLayout()).show(((JPanel)getParent()), "mainMenu");
               }
            });
      
      searchDepthSlider.addChangeListener(
            new ChangeListener() {
               public void stateChanged(ChangeEvent event) {
                  int value = searchDepthSlider.getValue();
                  settings.putInt("depth", value);
                 
                                }
            });
      
      
      
   }
   
 
}
