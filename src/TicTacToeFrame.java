import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeFrame extends JFrame {
    private static final int ROWS = 3;
    private static final int COLS = 3;
    private TicTacToeTile[][] boardTiles = new TicTacToeTile[ROWS][COLS];
    private String currentPlayer = "X";
    private int moveCount = 0;
    private static final int MOVES_FOR_WIN = 5;
    private static final int MOVES_FOR_TIE = 7;

    public TicTacToeFrame() {
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLayout(new BorderLayout());

        JPanel boardPanel = new JPanel(new GridLayout(ROWS, COLS));
        initializeBoard(boardPanel);

        JPanel controlPanel = new JPanel();
        addQuitButton(controlPanel);

        add(boardPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null); // Center the window
        setVisible(true);
    }

    private void initializeBoard(JPanel boardPanel) {
        ActionListener actionListener = this::handleButtonAction;
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                boardTiles[row][col] = new TicTacToeTile(row, col);
                boardTiles[row][col].setFont(new Font("Arial", Font.BOLD, 40));
                boardTiles[row][col].addActionListener(actionListener);
                boardPanel.add(boardTiles[row][col]);
            }
        }
    }

    private void addQuitButton(JPanel controlPanel) {
        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(e -> System.exit(0));
        controlPanel.add(quitButton);
    }

    private void handleButtonAction(ActionEvent e) {
        TicTacToeTile tile = (TicTacToeTile) e.getSource();
        if (tile.getText().equals("") && moveCount < 9) {
            tile.setText(currentPlayer);
            tile.setEnabled(false);
            moveCount++;

            if (moveCount >= MOVES_FOR_WIN && checkWin(currentPlayer)) {
                JOptionPane.showMessageDialog(this, "Player " + currentPlayer + " wins!");
                resetBoard();
            } else if (moveCount >= MOVES_FOR_TIE && checkTie()) {
                JOptionPane.showMessageDialog(this, "It's a Tie!");
                resetBoard();
            } else {
                currentPlayer = currentPlayer.equals("X") ? "O" : "X";
            }
        }
    }

    private boolean checkWin(String player) {
        return checkRows(player) || checkColumns(player) || checkDiagonals(player);
    }

    private boolean checkRows(String player) {
        for (int row = 0; row < ROWS; row++) {
            if (checkRowCol(boardTiles[row][0].getText(), boardTiles[row][1].getText(), boardTiles[row][2].getText(), player)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkColumns(String player) {
        for (int col = 0; col < COLS; col++) {
            if (checkRowCol(boardTiles[0][col].getText(), boardTiles[1][col].getText(), boardTiles[2][col].getText(), player)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonals(String player) {
        boolean diagonal1 = checkRowCol(boardTiles[0][0].getText(), boardTiles[1][1].getText(), boardTiles[2][2].getText(), player);
        boolean diagonal2 = checkRowCol(boardTiles[0][2].getText(), boardTiles[1][1].getText(), boardTiles[2][0].getText(), player);
        return diagonal1 || diagonal2;
    }

    private boolean checkRowCol(String c1, String c2, String c3, String player) {
        return (c1.equals(player) && c2.equals(player) && c3.equals(player));
    }

    private boolean checkTie() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (boardTiles[row][col].getText().isEmpty()) {
                    return false; // If there's an empty spot, no tie yet
                }
            }
        }
        return true;
    }

    private void resetBoard() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                boardTiles[row][col].setText("");
                boardTiles[row][col].setEnabled(true);
            }
        }
        currentPlayer = "X";
        moveCount = 0;
    }

    public static void main(String[] args) {
        new TicTacToeFrame();
    }
}
