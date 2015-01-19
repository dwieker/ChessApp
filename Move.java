public class Move{

   private int s1;
   private int s2;
   private int score;
   
   public Move(int a, int b){
      s1 = a;
      s2 = b;
   }
   
   public Move(char[] m){
      char[] letters = {'a', 'b', 'c', 'd', 'e', 'f', 'g' , 'h'};
      char[] digits = {'1','2','3','4','5','6','7','8'};
      int x_1 = 0, y_1 = 0, x_2 = 0, y_2 = 0;
      
      for(int i = 0; i < 8; i++){
         if(m[0] == letters[i])
            x_1 = i;
         if(m[1] == digits[i])
            y_1 = i;
         if(m[2] == letters[i])
            x_2 = i;
         if(m[3] == digits[i])
            y_2 = i;
      }
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
}