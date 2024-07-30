public class AI {
    public int[] findBestMove(Board board) {
        int bestVal = Integer.MIN_VALUE;
        int[] bestMove = new int[2];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getBoard()[i][j] == ' ') {
                    board.getBoard()[i][j] = 'O';
                    int moveVal = minimax(board, 0, false, Integer.MIN_VALUE, Integer.MAX_VALUE);
                    board.getBoard()[i][j] = ' ';
                    if (moveVal > bestVal) {
                        bestMove[0] = i;
                        bestMove[1] = j;
                        bestVal = moveVal;
                    }
                }
            }
        }

        return bestMove;
    }

    private int minimax(Board board, int depth, boolean isMax, int alpha, int beta) {
        char winner = board.checkWin();
        if (winner == 'O') {
            return 10 - depth;
        } else if (winner == 'X') {
            return depth - 10;
        } else if (board.isFull()) {
            return 0;
        }

        if (isMax) {
            int best = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board.getBoard()[i][j] == ' ') {
                        board.getBoard()[i][j] = 'O';
                        best = Math.max(best, minimax(board, depth + 1, false, alpha, beta));
                        board.getBoard()[i][j] = ' ';
                        alpha = Math.max(alpha, best);
                        if (beta <= alpha) {
                            return best;
                        }
                    }
                }
            }
            return best;
        } else {
            int best = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board.getBoard()[i][j] == ' ') {
                        board.getBoard()[i][j] = 'X';
                        best = Math.min(best, minimax(board, depth + 1, true, alpha, beta));
                        board.getBoard()[i][j] = ' ';
                        beta = Math.min(beta, best);
                        if (beta <= alpha) {
                            return best;
                        }
                    }
                }
            }
            return best;
        }
    }
}

