import java.util.Hashtable;

public class Move{

   private int s1;
   private int s2;
   private int score;
   
   public Move(int a, int b){
      s1 = a;
      s2 = b;
   }
   
   public Move(char[] m){
      Hashtable<Character, Integer> dict = new Hashtable<Character, Integer>();
      dict.put('a', 1); dict.put('b', 2); dict.put('c', 3); dict.put('d', 4); 
      dict.put('e', 5); dict.put('f', 6); dict.put('g', 7); dict.put('h', 8);
      
      int x_1 = dict.get(m[0]);
      int y_1 = dict.get(m[1]);
      int x_2 = dict.get(m[2]);
      int y_2 = dict.get(m[3]);
   
      s1 = y_1 * 16 + x_1;
      s2 = y_2 * 16 + x_2;
      
   }
   
   public void setScore(int s){
      score = s;
   }
   
   public int getScore(){
      return score;
   }
   
   public int s1(){
      return s1;
   }
   public int s2(){
      return s2;
   }
   
   public String toString(){
      String print = new String();
      
      print = "Moved " + s1 + " to " + s2;
      
      return print;
   }
}