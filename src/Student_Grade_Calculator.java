import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Student_Grade_Calculator extends JFrame {
    JTextField[] subjectFields;
    JButton calculateButton;
    JLabel totalMarksLabel;
    JLabel averagePercentageLabel;
    JLabel gradeLabel;

    public Student_Grade_Calculator() {
        setTitle("Grade Calculator");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(0, 2));

        JLabel[] subjectLabels = new JLabel[5];
        subjectFields = new JTextField[5];
        for (int i = 0; i < 5; i++) {
            subjectLabels[i] = new JLabel("Subject " + (i + 1) + " Marks:");
            subjectFields[i] = new JTextField();
            add(subjectLabels[i]);
            add(subjectFields[i]);
        }

        calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculate();
            }
        });

        totalMarksLabel = new JLabel("Total Marks: ");
        totalMarksLabel.setForeground(Color.BLUE);
        averagePercentageLabel = new JLabel("Average Percentage: ");
        averagePercentageLabel.setForeground(Color.BLUE);
        gradeLabel = new JLabel("Grade: ");
        gradeLabel.setForeground(Color.BLUE);


        add(calculateButton);
        add(totalMarksLabel);
        add(averagePercentageLabel);
        add(gradeLabel);

        setVisible(true);
    }

    private void calculate() {
        int totalMarks = 0;
        int subjectsCount = 0;
        for (JTextField field : subjectFields) {
            try {
                int marks = Integer.parseInt(field.getText());
                totalMarks += marks;
                subjectsCount++;
            } catch (NumberFormatException e)
            {
            }
        }

        if (subjectsCount == 0) {
            JOptionPane.showMessageDialog(this, "Please enter marks for at least one subject.");
            return;
        }

        double averagePercentage = ((double) totalMarks / (subjectsCount * 100)) * 100;

        totalMarksLabel.setText("Total Marks: " + totalMarks);
        averagePercentageLabel.setText("Average Percentage: " + String.format("%.2f", averagePercentage) + "%");

        String grade;
        if (averagePercentage >= 90) {
            grade = "A";
        } else if (averagePercentage >= 80) {
            grade = "B";
        } else if (averagePercentage >= 70) {
            grade = "C";
        } else if (averagePercentage >= 60) {
            grade = "D";
        } else {
            grade = "F";
        }

        gradeLabel.setText("Grade: " + grade);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Student_Grade_Calculator();
            }
        });
    }
}


