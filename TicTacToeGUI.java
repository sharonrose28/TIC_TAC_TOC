import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGUI extends JFrame {
    private static final int SIZE = 3;
    private JButton[][] buttons = new JButton[SIZE][SIZE];
    private char currentPlayer = 'X';
    private boolean isGameOver = false;

    public TicTacToeGUI() {
        setTitle("Tic-Tac-Toe");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(SIZE, SIZE));

        initializeBoard();

        setVisible(true);
    }

    private void initializeBoard() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                buttons[row][col] = new JButton(" ");
                buttons[row][col].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[row][col].setFocusPainted(false);
                buttons[row][col].addActionListener(new ButtonClickListener(row, col));
                add(buttons[row][col]);
            }
        }
    }

    private void processMove(int row, int col) {
        if (isValidMove(row, col)) {
            buttons[row][col].setText(String.valueOf(currentPlayer));
            if (checkGameStatus()) {
                announceOutcome();
            } else {
                togglePlayer();
            }
        }
    }

    private boolean isValidMove(int row, int col) {
        return buttons[row][col].getText().equals(" ") && !isGameOver;
    }

    private boolean checkGameStatus() {
        if (isWinningCondition() || isBoardFull()) {
            isGameOver = true;
            return true;
        }
        return false;
    }

    private boolean isWinningCondition() {
        return checkRows() || checkColumns() || checkDiagonals();
    }

    private boolean checkRows() {
        for (int row = 0; row < SIZE; row++) {
            if (isLineWinning(buttons[row][0].getText().charAt(0),
                              buttons[row][1].getText().charAt(0),
                              buttons[row][2].getText().charAt(0))) {
                return true;
            }
        }
        return false;
    }

    private boolean checkColumns() {
        for (int col = 0; col < SIZE; col++) {
            if (isLineWinning(buttons[0][col].getText().charAt(0),
                              buttons[1][col].getText().charAt(0),
                              buttons[2][col].getText().charAt(0))) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonals() {
        return isLineWinning(buttons[0][0].getText().charAt(0),
                             buttons[1][1].getText().charAt(0),
                             buttons[2][2].getText().charAt(0)) ||
               isLineWinning(buttons[0][2].getText().charAt(0),
                             buttons[1][1].getText().charAt(0),
                             buttons[2][0].getText().charAt(0));
    }

    private boolean isLineWinning(char a, char b, char c) {
        return a == currentPlayer && a == b && a == c && a != ' ';
    }

    private boolean isBoardFull() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (buttons[row][col].getText().equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }

    private void togglePlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    private void announceOutcome() {
        String message;
        if (isWinningCondition()) {
            message = "Player " + currentPlayer + " wins!";
        } else {
            message = "It's a draw!";
        }
        JOptionPane.showMessageDialog(this, message);
        resetBoard();
    }

    private void resetBoard() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                buttons[row][col].setText(" ");
            }
        }
        currentPlayer = 'X';
        isGameOver = false;
    }

    private class ButtonClickListener implements ActionListener {
        private int row, col;

        public ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            processMove(row, col);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TicTacToeGUI());
    }
}
