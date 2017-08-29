#include <stdio.h>

char gridChar(int i) {
    switch(i) {
        case -1:
            return 'X';
        case 0:
            return ' ';
        case 1:
            return 'O';
    }
}

void draw(int b[9]) {
    printf(" Saida: \n");
    printf(" %c | %c | %c\n",gridChar(b[0]),gridChar(b[1]),gridChar(b[2]));
    printf("---+---+---\n");
    printf(" %c | %c | %c\n",gridChar(b[3]),gridChar(b[4]),gridChar(b[5]));
    printf("---+---+---\n");
    printf(" %c | %c | %c\n",gridChar(b[6]),gridChar(b[7]),gridChar(b[8]));
}

int win(const int board[9]) {
    //retorna 0 se não ganhou
    unsigned wins[8][3] = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int i;
    for(i = 0; i < 8; ++i) {
        if(board[wins[i][0]] != 0 &&
           board[wins[i][0]] == board[wins[i][1]] &&
           board[wins[i][0]] == board[wins[i][2]])
            return board[wins[i][2]];
    }
    return 0;
}

int minimax(int board[9], int player) {
    //turno do jogador
    int winner = win(board);
    if(winner != 0) return winner*player;

    int move = -1;
    int score = -2;//descarta movimentos que geram perda do jogo
    int i;
    for(i = 0; i < 9; ++i) {
        if(board[i] == 0) {
            board[i] = player;
            int thisScore = -minimax(board, player*-1);
            if(thisScore > score) {
                score = thisScore;
                move = i;
            }//escolha a jogada pior pro jogador
            board[i] = 0;
        }
    }
    if(move == -1) return 0;
    return score;
}

void computerMove(int board[9]) {
    int move = -1;
    int score = -2;
    int i;
    for(i = 0; i < 9; ++i) {
        if(board[i] == 0) {
            board[i] = 1;
            int tempScore = -minimax(board, -1);
            board[i] = 0;
            if(tempScore > score) {
                score = tempScore;
                move = i;
            }
        }
    }
    board[move] = 1;
}

void playerMove(int board[9]) {
    int move = 0;
do {
start:

printf("\n-----Instrucoes------\n");
    printf(" 0 | 1 | 2\n");
    printf("---+---+---\n");
    printf(" 3 | 4 | 5\n");
    printf("---+---+---\n");
    printf(" 6 | 7 | 8\n");
printf("---------------------------\n");
printf("Digite um numero de 0 a 8: ");
scanf("%d", &move);
if(board[move] != 0) {
printf("Nope, ja esta ocupado");
goto start;
}
printf("\n");
} while (move >= 9 || move < 0 || board[move] != 0);
board[move] = -1;
}

int main() {
    int board[9] = {0,0,0,0,0,0,0,0,0};
    printf("Maquina: O, Voce: X\nJogar primeiro(1) ou segundo(2)nd? ");
    int player=0;
    scanf("%d",&player);
    printf("\n");
    unsigned turn;
    for(turn = 0; turn < 9 && win(board) == 0; ++turn) {
        if((turn+player) % 2 == 0)
            computerMove(board);
        else {
            draw(board);
            playerMove(board);
        }
    }
    switch(win(board)) {
        case 0:
	    draw(board);
            printf("Empataram.\n");
	    
            break;
        case 1:
            draw(board);
            printf("Voce perdeu :(\n");
            break;
        case -1:
	    draw(board);
            printf("Voce ganhou :)\n");
            break;
    }
}
