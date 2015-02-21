import java.util.Hashtable;

/**
 * This class stores the basic information about a move. It initially stores 
 * just the square the piece is moving from and the destination square.
 * Later if can also store the value of a move, to be used by the AI.
 * @author Jesenia Garcia-Rovetta
 * @author Devin Wieker
 */
public class Move{

   private int s1;
   private int s2;
   private int score;
   
   /**
    * This constructor stores the source and destination squares of this move.
    *
    * @param s1 Initial square.
    * @param s2 Destination square.
    */
   public Move(int s1, int s2){
      this.s1 = s1;
      this.s2 = s2;
   }
   
   /**
    * This constructor is to be used to interface with the user. It parses textual
    * input which the user can understand and easily input, into a representation 
    * which can be understood by the game.
    *
    * @param m The user input of the move.
    */
   public Move(String m){
      Hashtable<Character, Integer> dict = new Hashtable<Character, Integer>();
      dict.put('a', 0); dict.put('b', 1); dict.put('c', 2); dict.put('d', 3); 
      dict.put('e', 4); dict.put('f', 5); dict.put('g', 6); dict.put('h', 7);
      
      int x_1 = dict.get(m.charAt(0));
      int y_1 = Character.getNumericValue(m.charAt(1)) - 1;
      int x_2 = dict.get(m.charAt(2));
      int y_2 = Character.getNumericValue(m.charAt(3)) - 1;
   
      s1 = y_1 * 16 + x_1;
      s2 = y_2 * 16 + x_2;
      
   }
   
   /**
   * Sets the value of the board state after this move.
   *
   * @param s The value of the move.
   */
   public void setScore(int s){
      score = s;
   }
   
   /**
   * Retrieves the value of the board state after this move.
   *
   * @return s The value of the move.
   */
   public int getScore(){
      return score;
   }
   
   /**
   * Retrieves the square which the piece is moving from.
   *
   * @return The inital square.
   */
   public int s1(){
      return s1;
   }
   
   /**
   * Retrieves the square which the piece is moving to.
   *
   * @return The destination square.
   */
   public int s2(){
      return s2;
   }
   
   /**
   * Returns a meaningful string which can be used for debugging.
   *
   * @return String representation of move.
   */
   public String toString(){
      Hashtable<Integer, Character> dict = new Hashtable<Integer, Character>();
      dict.put(1, 'a'); dict.put(2, 'b'); dict.put(3, 'c'); dict.put(4, 'd'); 
      dict.put(5, 'e'); dict.put(6, 'f'); dict.put(7, 'g'); dict.put(8, 'h');

      
      String r = dict.get(s1%16 + 1) + Integer.toString(s1/16 + 1) + dict.get(s2%16 + 1) + Integer.toString(s2/16 + 1);
      return r;      
   }
}