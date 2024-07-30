import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe extends JFrame implements ActionListener {
    private Board board;
    private AI ai;
    private JButton[][] buttons;
    private boolean playerTurn;

    public TicTacToe() {
        board = new Board();
        ai = new AI();
        buttons = new JButton[3][3];
        playerTurn = true;

        setLayout(new GridLayout(3, 3));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].addActionListener(this);
                add(buttons[i][j]);
            }
        }

        setTitle("Tic-Tac-Toe");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton buttonClicked = (JButton) e.getSource();
        if (playerTurn) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (buttons[i][j] == buttonClicked && buttons[i][j].getText().equals("")) {
                        buttons[i][j].setText("X");
                        board.placeMove(i, j, 'X');
                        playerTurn = false;
                        if (checkGameStatus()) return;
                        aiMove();
                    }
                }
            }
        }
    }

    private void aiMove() {
        int[] move = ai.findBestMove(board);
        board.placeMove(move[0], move[1], 'O');
        buttons[move[0]][move[1]].setText("O");
        playerTurn = true;
        checkGameStatus();
    }

    private boolean checkGameStatus() {
        char winner = board.checkWin();
        if (winner == 'X') {
            JOptionPane.showMessageDialog(this, "You win!");
            resetBoard();
            return true;
        } else if (winner == 'O') {
            JOptionPane.showMessageDialog(this, "AI wins!");
            resetBoard();
            return true;
        } else if (board.isFull()) {
            JOptionPane.showMessageDialog(this, "It's a draw!");
            resetBoard();
            return true;
        }
        return false;
    }

    private void resetBoard() {
        board = new Board();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        playerTurn = true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TicTacToe::new);
    }
}
