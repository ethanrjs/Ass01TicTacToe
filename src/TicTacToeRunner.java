import javax.swing.JFrame;

public class TicTacToeRunner {
    public static void main(String[] args) {
        TicTacToeFrame gameFrame = new TicTacToeFrame();
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.pack();
        gameFrame.setVisible(true);
    }
}
