package four;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GameBoard extends JPanel {
    private static final int NUM_OF_COLUMNS = 7;
    private static final int NUM_OF_ROWS = 6;
    private boolean gameOver = false;
    private PlayerTurn currentTurn = PlayerTurn.X;
    private final Color backgroundColor = Color.LIGHT_GRAY;
    private final Color winColor = Color.CYAN;
    private final JButton[][] buttons = new JButton[NUM_OF_COLUMNS][NUM_OF_ROWS];


    GameBoard() {
        createBoardButtons(this);
        setLayout(new GridLayout(NUM_OF_ROWS, NUM_OF_COLUMNS));
        setVisible(true);
    }

    private void createBoardButtons(JPanel panel) {
        for (int row = NUM_OF_ROWS; row > 0; row--) {
            for (int column = 0; column < NUM_OF_COLUMNS; column++) {
                char columnName = (char) (65 + column);
                String buttonName = "" + columnName + row;
                JButton button = new JButton(buttonName);
                button.setText(" ");
                button.setOpaque(true);
                button.setName("Button" + buttonName);
                button.setFocusPainted(false);
                button.setBackground(backgroundColor);

                button.addActionListener(gameButtonActionListener());
                buttons[column][row - 1] = button;

                //place Button on board(window)
                panel.add(button);
            }
        }

    }

    private ActionListener gameButtonActionListener() {
        return e -> {
            if (!gameOver) {
                JButton button = (JButton) e.getSource();
                String currentButton = button.getName().substring(button.getName().length() - 2);
                int column = currentButton.charAt(0) - 'A';

                for (int i = 0; i < buttons[column].length; i++) {
                    JButton nextButton = buttons[column][i];
                    if (nextButton.getText().equals(" ")) {
                        button = nextButton;
                        break;
                    }
                }

                if (!button.getText().equals(" ")) {
                    return;
                }

                Font font = new Font("Futura", Font.BOLD, 20);
                button.setFont(font);
                button.setText(currentTurn.name());

                currentTurn = currentTurn == PlayerTurn.X ? PlayerTurn.O : PlayerTurn.X;
                gameOver = winConditions(button);
            }
        };
    }

    private boolean winConditions(JButton currentSpace) {
        return winHorizontally(currentSpace) || winVertically(currentSpace) || winDiagonally(currentSpace);
    }

    private boolean winHorizontally(JButton currentSpace) {
        boolean gameWin = false;
        java.util.List<JButton> winningButtons = null;
        String buttonName = currentSpace.getName().substring(currentSpace.getName().length() - 2);
        int row = Integer.parseInt(buttonName.substring(1)) - 1;
        PlayerTurn player = PlayerTurn.valueOf(currentSpace.getText());

        //3 = center column
        if (buttons[3][row].getText().equals(player.name())) {
            winningButtons = new ArrayList<>();
            winningButtons.add(buttons[3][row]);
            for (int i = 1; i < 4; i++) {
                if (buttons[3 - i][row].getText().equals(player.name())) {
                    winningButtons.add(buttons[3 - i][row]);
                } else {
                    break;
                }
            }

            for (int i = 1; i < 4; i++) {
                if (buttons[3 + i][row].getText().equals(player.name())) {
                    winningButtons.add(buttons[3 + i][row]);
                } else {
                    break;
                }
            }
            gameWin = winningButtons.size() >= 4;
        }
        if (gameWin) {
            lightWinningButtons(winningButtons);
        }

        return gameWin;
    }

    private boolean winVertically(JButton currentSpace) {
        boolean gameWin = false;
        java.util.List<JButton> winningButtons = null;
        String buttonName = currentSpace.getName().substring(currentSpace.getName().length() - 2);
        int column = buttonName.charAt(0) - 'A';
        PlayerTurn player = PlayerTurn.valueOf(currentSpace.getText());

        if (buttons[column][2].getText().equals(player.name())
            && buttons[column][3].getText().equals(player.name())) {
            winningButtons = new ArrayList<>();
            winningButtons.add(buttons[column][2]);
            winningButtons.add(buttons[column][3]);

            if (buttons[column][4].getText().equals(player.name())) {
                winningButtons.add(buttons[column][4]);
                if (buttons[column][5].getText().equals(player.name())) {
                    winningButtons.add(buttons[column][5]);
                }
            }
            if (buttons[column][1].getText().equals(player.name())) {
                winningButtons.add(buttons[column][1]);
                if (buttons[column][0].getText().equals(player.name())) {
                    winningButtons.add(buttons[column][0]);
                }
            }
            gameWin = winningButtons.size() >= 4;
        }
        if (gameWin) {
            lightWinningButtons(winningButtons);
        }

        return gameWin;
    }

    private boolean winDiagonally(JButton currentSpace) {
        boolean gameWin;
        PlayerTurn player = PlayerTurn.valueOf(currentSpace.getText());
        String buttonName = currentSpace.getName().substring(currentSpace.getName().length() - 2);
        int column = buttonName.charAt(0) - 'A';
        int row = Integer.parseInt(buttonName.substring(1)) - 1;
        java.util.List<JButton> winningDiagonallyUpwards = new ArrayList<>();
        java.util.List<JButton> winningDiagonallyDownwards = new ArrayList<>();

        winningDiagonallyUpwards.add(currentSpace);
        winningDiagonallyDownwards.add(currentSpace);

        //check going in the upwards direction

        //going up right
        for (int i = 1; i < 7; i++) {
            if ((column + i < NUM_OF_COLUMNS && row + i < NUM_OF_ROWS) && buttons[column + i][row + i].getText().equals(player.name())) {
                winningDiagonallyUpwards.add(buttons[column + i][row + i]);
            } else {
                break;
            }
        }
        //going down left

        for (int i = 1; i < 7; i++) {
            if ((column - i >= 0 && row - i >= 0) && buttons[column - i][row - i].getText().equals(player.name())) {
                winningDiagonallyUpwards.add(buttons[column - i][row - i]);
            } else {
                break;
            }
        }

        //check going in the \ direction

        //going up left
        for (int i = 1; i < 7; i++) {
            if ((column - i >= 0 && row + i < NUM_OF_ROWS) && buttons[column - i][row + i].getText().equals(player.name())) {
                winningDiagonallyDownwards.add(buttons[column - i][row + i]);
            } else {
                break;
            }
        }

        //going down right
        for (int i = 1; i < 7; i++) {
            if ((column + i < NUM_OF_COLUMNS && row - i >= 0) && buttons[column + i][row - i].getText().equals(player.name())) {
                winningDiagonallyDownwards.add(buttons[column + i][row - i]);
            } else {
                break;
            }
        }

        gameWin = winningDiagonallyUpwards.size() >= 4 || winningDiagonallyDownwards.size() >= 4;

        if (gameWin) {
            if (winningDiagonallyUpwards.size() >= 4) {
                lightWinningButtons(winningDiagonallyUpwards);
            }
            if (winningDiagonallyDownwards.size() >= 4) {
                lightWinningButtons(winningDiagonallyDownwards);
            }
        }

        return gameWin;
    }

    private void lightWinningButtons(List<JButton> buttonList) {
        buttonList.forEach(button -> button.setBackground(winColor));
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public JButton[][] getButtons() {
        return buttons;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public void setCurrentTurn(PlayerTurn currentTurn) {
        this.currentTurn = currentTurn;
    }
}
