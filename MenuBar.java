
import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBar extends JMenuBar
{
   public MenuBar()
   {
      JMenu menu = new JMenu("Game");
      JMenuItem menuItem = new JMenuItem("New Game");
      menuItem.addActionListener(
            new ActionListener() {
               public void actionPerformed(ActionEvent arg0) {
                  EngineInterface.pipe("ucinewgame");
                  EngineInterface.boardPanel.setBoard();
               }
            });
      menu.add(menuItem);
      add(menu);
      
      menu = new JMenu("Engine"); 
      menuItem = new JMenuItem("Load New Engine");
      menuItem.addActionListener(
            new ActionListener() {
               public void actionPerformed(ActionEvent arg0) {
                  JFileChooser jfc = new JFileChooser("/Users/devinwieker/Desktop/ProgrammingProjects/ChessApp/engines/");
                  jfc.showOpenDialog(null);
                  File file = jfc.getSelectedFile();
                  EngineInterface.loadEngine(file.getAbsolutePath());      
                                         
               
               }
            });
      menu.add(menuItem);
      add(menu);
   
       
      
      
      
      add(new SettingsMenu());
   }
   


}