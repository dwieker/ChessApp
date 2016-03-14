import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Log extends JFrame{

   public static JTextArea textArea;

   public Log()
   {
      super("Log");
      setSize(600, 300); 
      
      textArea = new JTextArea();
      JScrollPane scroll = new JScrollPane (textArea, 
                            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
                            JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

      add(scroll);
      
   }
   
   public static void addText(String txt)
   {
      textArea.append(txt + '\n');
   }
   



}