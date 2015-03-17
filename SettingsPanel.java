import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class SettingsPanel extends JPanel{
   
   JButton backButton;
   JSlider searchDepthSlider;
   

   public SettingsPanel(){
      setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
      
      backButton = new JButton("Back");   
      add(backButton);
      
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
                  if (value == 0) {
                     System.out.println("0");
                  } 
                                }
            });
      
      
      
   }
}
