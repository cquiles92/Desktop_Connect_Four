package four;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class ConnectFour extends JFrame {
    public ConnectFour() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 900);
        setTitle("Connect Four");
        GameBoard board = new GameBoard();
        JPanel resetPanel = createBottomPanel(board);
        add(board);
        add(resetPanel, BorderLayout.PAGE_END);
        setVisible(true);

    }

    private JPanel createBottomPanel(GameBoard gameBoard) {
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton resetButton = new JButton("Reset");
        resetButton.setName("ButtonReset");
        resetButton.addActionListener(resetButtonActionListener(gameBoard));

        bottomPanel.add(resetButton);
        bottomPanel.setVisible(true);
        return bottomPanel;
    }

    private ActionListener resetButtonActionListener(GameBoard gameBoard) {
        return e -> {
            for (JButton[] buttonArray : gameBoard.getButtons()) {
                for (JButton button : buttonArray) {
                    button.setText(" ");
                    button.setBackground(gameBoard.getBackgroundColor());
                }
            }
            gameBoard.setCurrentTurn(PlayerTurn.X);
            gameBoard.setGameOver(false);
        };
    }
}
