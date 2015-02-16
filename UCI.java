import java.util.*;

public class UCI{
   
   public static void UCILoop(){
      Scanner parser, input = new Scanner(System.in);
      String curLine, current;
      Board b;
      Move[] moves = new Move[264];
      
      curLine = input.next();
      
      if(!curLine.equals("uci"))
         throw new InputMismatchException("This engine only uses the UCI protocol.");
         
      System.out.println("id name Chess Engine");
      System.out.println("id author Jesenia Garcia-Rovetta and Devin Wieker");
      System.out.println("uciok");
      
      curLine = input.next();
      
      while(!curLine.equals("isready")){}
      
      System.out.println("readyok");
      
      curLine = input.nextLine();
      
      while(!curLine.equals("quit\n")){
         parser = new Scanner(curLine);
         current = parser.next();
         if(current.equals("position")){
            current = parser.next();
            if(current.equals("startpos")){
               b = new Board(Board.STARTFEN);
            }
            else if(current.equals("fen")){
               b = new Board(parser.next());
            }
            else{
               throw new InputMismatchException("Must say either fen or startpos");
            }
               
            while(parser.hasNext()){
               current = parser.next();
               if(!current.equals("moves")){
                  b.movePiece(new Move(current));
               }
            }
            curLine = input.nextLine();
            if(current.equals("go")){
               b.genMoves(moves);
               System.out.println("bestmove " + moves[0]);
               curLine = input.nextLine();
            }
        }
                                      
     }
            
   }
}