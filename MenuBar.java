
import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBar extends JMenuBar
{
   
   EngineInterface eInterface;
   
   public MenuBar(EngineInterface e)
   {
      
      eInterface = e;
      
      // **********   Game options ************//
      JMenu menu = new JMenu("Game");
      
      //add new game button
      JMenuItem menuItem = new JMenuItem("New Game");
      menuItem.addActionListener(
            new ActionListener() {
               public void actionPerformed(ActionEvent arg0) {
                  eInterface.pipe("ucinewgame");
                  eInterface.boardPanel.setBoard();
               }
            });
      menu.add(menuItem);
      add(menu);
      
      //*****************************************//
      
      
      // **********   Engine options ************//
      menu = new JMenu("Engine"); 
      
      //add new engine button
      menuItem = new JMenuItem("Load New Engine");
      menuItem.addActionListener(
            new ActionListener() {
               public void actionPerformed(ActionEvent arg0) {
                  JFileChooser jfc = new JFileChooser("/Users/devinwieker/Desktop/ProgrammingProjects/ChessApp/engines/");
                  jfc.showOpenDialog(null);
                  File file = jfc.getSelectedFile();
                  eInterface.loadEngine(file.getAbsolutePath());      
                                         
               
               }
            });
      menu.add(menuItem);
      add(menu);
      
      //*****************************************//
      
      
      
      //***************  View options  *******************//
      menu = new JMenu("View");
      
      //add gui-engine log window and button
      final Log commLog = new Log();
      eInterface.attatchLog(commLog);
      commLog.setSize(600, 300);        
      menuItem = new JMenuItem("Log");
      
      menuItem.addActionListener(
            new ActionListener() {
               public void actionPerformed(ActionEvent arg0) {
                       commLog.setVisible(true);
               }
            });
      menu.add(menuItem);
      add(menu);

      
   
       
      
      
      
      add(new SettingsMenu());
   }
   
     


}