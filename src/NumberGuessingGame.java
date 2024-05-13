import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NumberGuessingGame extends JFrame {
    private int randomNumber;
    private int guessCount;
    private int maxAttempts = 5;

    JTextField guessField;
    JButton guessButton;
    JButton clearButton;
    JButton playAgainButton;
    JLabel resultLabel;

    public NumberGuessingGame() {
        setTitle("Number Guessing Game");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        randomNumber = (int) (Math.random() * 100) + 1;
        guessCount = 0;


        JLabel instructionLabel = new JLabel("Guess the number (1-100): You have Only 5 Attempts");
        instructionLabel.setBounds(75,50,400,30);
        guessField = new JTextField();
        guessField.setBounds(125,125,200,30);
        guessButton = new JButton("Guess");
        guessButton.setBounds(50,175,100,30);
        clearButton = new JButton("Clear");
        clearButton.setBounds(180,175,100,30);
        playAgainButton = new JButton("Play Again");
        playAgainButton.setBounds(320,175,100,30);
        resultLabel = new JLabel("");
        resultLabel.setBounds(100,175,400,30);



        guessButton.addActionListener(new GuessButtonListener());
        clearButton.addActionListener(new ClearButtonListener());
        playAgainButton.addActionListener(new PlayAgainButtonListener());

        add(instructionLabel);
        add(guessField);
        add(guessButton);
        add(clearButton);
        add(playAgainButton);
        add(resultLabel);


        setVisible(true);
    }

    private class GuessButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (guessCount >= maxAttempts) {
                resultLabel.setText("You have used all attempts! The number was " + randomNumber);
                resultLabel.setForeground(Color.red);
                guessButton.setEnabled(false);
                clearButton.setEnabled(false);
                playAgainButton.setEnabled(true);
            } else {
                try {
                    int guess = Integer.parseInt(guessField.getText());
                    guessCount++;

                    if (guess < randomNumber) {
                        resultLabel.setText("Too low! Try again.");
                        resultLabel.setForeground(Color.blue);
                    } else if (guess > randomNumber) {
                        resultLabel.setText("Too high! Try again.");
                        resultLabel.setForeground(Color.blue);
                    } else {
                        resultLabel.setText("Congratulations! You guessed the number in " + guessCount + " tries.");
                        resultLabel.setForeground(Color.ORANGE);
                        guessButton.setEnabled(false);
                        clearButton.setEnabled(false);
                        playAgainButton.setEnabled(true);
                    }
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Invalid input! Please enter a valid number.");
                    resultLabel.setForeground(Color.red);
                }
                guessField.setText("");
            }
        }
    }

    private class ClearButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            guessField.setText("");
            resultLabel.setText("");
        }
    }

    private class PlayAgainButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            randomNumber = (int) (Math.random() * 100) + 1;
            guessCount = 0;
            resultLabel.setText("");
            guessField.setText("");
            guessButton.setEnabled(true);
            clearButton.setEnabled(true);
            playAgainButton.setEnabled(false);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NumberGuessingGame());
    }
}



