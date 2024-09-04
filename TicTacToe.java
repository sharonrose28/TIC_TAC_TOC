import java.util.Scanner;

public class TicTacToe {
    private static final int SIZE = 3;
    private static char[][] board = new char[SIZE][SIZE];
    private static char currentPlayer = 'X';
    private static boolean isGameOver = false;

    public static void main(String[] args) {
        initializeBoard();
        displayBoard();

        while (!isGameOver) {
            processMove();
            displayBoard();
            checkGameStatus();
            if (!isGameOver) {
                togglePlayer();
            }
        }

        announceOutcome();
    }

    private static void initializeBoard() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                board[row][col] = ' ';
            }
        }
    }

    private static void displayBoard() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                System.out.print(board[row][col]);
                if (col < SIZE - 1) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
            if (row < SIZE - 1) {
                printSeparator();
            }
        }
    }

    private static void printSeparator() {
        System.out.println("---------");
    }

    private static void processMove() {
        Scanner scanner = new Scanner(System.in);
        int row, col;
        do {
            System.out.print("Player " + currentPlayer + ", enter your row (0, 1, 2) and column (0, 1, 2): ");
            row = scanner.nextInt();
            col = scanner.nextInt();
        } while (!isValidMove(row, col));
        board[row][col] = currentPlayer;
    }

    private static boolean isValidMove(int row, int col) {
        return row >= 0 && row < SIZE && col >= 0 && col < SIZE && board[row][col] == ' ';
    }

    private static void checkGameStatus() {
        if (isWinningCondition() || isBoardFull()) {
            isGameOver = true;
        }
    }

    private static boolean isWinningCondition() {
        return checkRows() || checkColumns() || checkDiagonals();
    }

    private static boolean checkRows() {
        for (int row = 0; row < SIZE; row++) {
            if (isLineWinning(board[row][0], board[row][1], board[row][2])) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkColumns() {
        for (int col = 0; col < SIZE; col++) {
            if (isLineWinning(board[0][col], board[1][col], board[2][col])) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkDiagonals() {
        return isLineWinning(board[0][0], board[1][1], board[2][2]) ||
               isLineWinning(board[0][2], board[1][1], board[2][0]);
    }

    private static boolean isLineWinning(char a, char b, char c) {
        return a == currentPlayer && a == b && a == c;
    }

    private static boolean isBoardFull() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    private static void togglePlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    private static void announceOutcome() {
        if (isGameOver) {
            if (isWinningCondition()) {
                System.out.println("Player " + ((currentPlayer == 'X') ? 'X' : 'O') + " wins!");
            } else {
                System.out.println("It's a draw!");
            }
        }
    }
}

