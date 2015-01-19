public class Tester{
   public static void main(String args[]){
      //Board b = new Board("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR b KQk - 0 1");
      Board b = new Board(Board.STARTPOS);
      b.printFEN();
      b.movePiece(new Move(99, 67));
      b.printFEN();
      b.movePiece(new Move(114, 84));
      b.printFEN();
      b.movePiece(new Move(115, 99));
      b.printFEN();
      b.movePiece(new Move(113, 118));
      b.printFEN();
      b.movePiece(new Move(116, 114));
      b.printFEN();
      
   
   
   }
}