public class MoveGen{



// ================== PIECE MOVES FUNCTIONS ====================== //
int check_rows(Board board, Piece piece, int n){
    for(int i = piece.pos + 1; !(i&0x88); i++){ //check right
        if(board.squares[i] == NULL){
            assert(!(i&0x88));
            piece.moves[n] = i;
            n+=1;
        }
        else if(board.squares[i]->value*piece.value < 0){
            assert(!(i&0x88));
            piece.moves[n] = i;
            n+=1;
            break;
        }
        else{
            break;
        }
    }
    for(int i = piece.pos - 1; !(i&0x88); i--){ //check left
        if(board.squares[i] == NULL){
            assert(!(i&0x88));
            piece.moves[n] = i;
            n+=1;
        }
        else if(board.squares[i]->value*piece.value < 0){
            assert(!(i&0x88));
            piece.moves[n] = i;
            n+=1;
            break;
        }
        else{
            break;
        }
    }
    for(int i = piece.pos + 16; !(i&0x88); i+= 16){ //check up
        if(board.squares[i] == NULL){
            assert(!(i&0x88));
            piece.moves[n] = i;
            n+=1;
        }
        else if(board.squares[i]->value*piece.value < 0){
            assert(!(i&0x88));
            piece.moves[n] = i;
            n+=1;
            break;
        }
        else{
            break;
        }
    }
    for(int i = piece.pos - 16; !(i&0x88); i-= 16){ //check down
        if(board.squares[i] == NULL){
            assert(!(i&0x88));
            piece.moves[n] = i;
            n+=1;
        }
        else if(board.squares[i]->value*piece.value < 0){
            assert(!(i&0x88));
            piece.moves[n] = i;
            n+=1;
            break;
        }
        else{
            break;
        }
    }
    return n;
}

int check_diagonals(Board board, Piece piece, int n){
    for(int i = piece.pos + 17; !(i&0x88); i+=17){ //check up-right
        if(board.squares[i] == NULL){
            assert(!(i&0x88));
            piece.moves[n] = i;
            n+=1;
        }
        else if(board.squares[i]->value*piece.value < 0){
            assert(!(i&0x88));
            piece.moves[n] = i;
            n+=1;
            break;
        }
        else{
            break;
        }
    }
    for(int i = piece.pos + 15; !(i&0x88); i+=15){ //check up-left
        if(board.squares[i] == NULL){
            assert(!(i&0x88));
            piece.moves[n] = i;
            n+=1;
        }
        else if(board.squares[i]->value*piece.value < 0){
            assert(!(i&0x88));
            piece.moves[n] = i;
            n+=1;
            break;
        }
        else{
            break;
        }
    }
    for(int i = piece.pos - 15; !(i&0x88); i-= 15){ //check down-right
        if(board.squares[i] == NULL){
            assert(!(i&0x88));
            piece.moves[n] = i;
            n+=1;
        }
        else if(board.squares[i]->value*piece.value < 0){
            assert(!(i&0x88));
            piece.moves[n] = i;
            n+=1;
            break;
        }
        else{
            break;
        }
    }
    for(int i = piece.pos - 17; !(i&0x88); i-= 17){ //check down-left
        if(board.squares[i] == NULL){
            assert(!(i&0x88));
            piece.moves[n] = i;
            n+=1;
        }
        else if(board.squares[i]->value*piece.value < 0){
            assert(!(i&0x88));
            piece.moves[n] = i;
            n+=1;
            break;
        }
        else{
            break;
        }
    }
    return n;
}

int set_rook_moves(Board board, Piece rook){
    int n = check_rows(board, rook, 0);
    return n;
}
int set_bishop_moves(Board board, Piece bishop){
    int n = check_diagonals(board, bishop, 0);
    return n;
}
int set_queen_moves(Board board, Piece queen){
    int n = check_rows(board, queen, 0);
    n = check_diagonals(board, queen, n);
    return n;
}
int set_king_moves(Board board, Piece king, bool print){
    int n = 0;
    for(int i = 0; i < 8; i++){
        int pos = king.pos + king_deltas[i];
        if( !(pos & 0x88) ){
            if(board.squares[pos] == NULL || board.squares[pos]->value * king.value < 0){
                king.moves[n] = pos;
                n++;
            }
        }
    }
    
    if(king.value > 0){
        if(board.castling[0] && !board.squares[1] && !board.squares[2] && !board.squares[3] && board.squares[0] && board.squares[0]->value == 4){
            king.moves[n] = 2;
            n++;
        }
        if(board.castling[1] && !board.squares[5] && !board.squares[6] && board.squares[7] && board.squares[7]->value == 4){
            king.moves[n] = 6;
            n++;
        }
    }else {
        if(board.castling[2] && !board.squares[113] && !board.squares[114] && !board.squares[115] && board.squares[112] && board.squares[112]->value == -4){
            king.moves[n] = 114;
            n++;
        }
        if(board.castling[3] && !board.squares[117] && !board.squares[118] && board.squares[119] && board.squares[119]->value == -4){
            king.moves[n] = 118;
            n++;
            
        }
    }
    if(print){
        std::cout << king.letter << " KING POSSIBLE MOVES: ";
        for(int i = 0; i < n; i++){
            std::cout << king.pos << "-> " << king.moves[i];
            
        }
        
        
    }
    return n;
}

}