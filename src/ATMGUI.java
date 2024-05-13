import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATMGUI extends JFrame {
    JTextField amountField;
    JTextArea messageArea;
    JLabel amountLabel;
    JButton withdrawButton, depositButton, checkBalanceButton;


    BankAccount account;

    ATMGUI(BankAccount account) {
        this.account = account;

        setTitle("ATM Machine");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        amountLabel = new JLabel("Enter Amount:");
        amountLabel.setBounds(50,50,150,30);
        amountField = new JTextField(10);
        amountField.setBounds(200,50,200,30);
        add(amountLabel);
        add(amountField);
        add(topPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1));
        withdrawButton = new JButton("Withdraw");
        withdrawButton.setBounds(100,150,100,30);
        depositButton = new JButton("Deposit");
        depositButton.setBounds(300,150,100,30);
        checkBalanceButton = new JButton("Check Balance");
        checkBalanceButton.setBounds(150,200,200,30);
        add(withdrawButton);
        add(depositButton);
        add(checkBalanceButton);
        add(buttonPanel, BorderLayout.CENTER);

        messageArea = new JTextArea(10, 30);
        messageArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(messageArea);
        add(scrollPane, BorderLayout.SOUTH);

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withdraw();
            }
        });

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deposit();
            }
        });

        checkBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkBalance();
            }
        });

        setVisible(true);
    }

    private void withdraw() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            if (amount <= 0) {
                showMessage("Invalid amount.");
                return;
            }
            boolean success = account.withdraw(amount);
            if (success) {
                showMessage("Withdrawal successful. New balance: Rs." + account.getBalance());
            } else {
                showMessage("Insufficient Balance.");
            }
        } catch (NumberFormatException ex) {
            showMessage("Invalid amount.");
        }
    }

    private void deposit() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            if (amount <= 0) {
                showMessage("Invalid amount.");
                return;
            }
            account.deposit(amount);
            showMessage("Deposit successful. New balance: Rs." + account.getBalance());
        } catch (NumberFormatException ex) {
            showMessage("Invalid amount.");
        }
    }

    private void checkBalance() {
        showMessage("Current Balance: Rs." + account.getBalance());
    }

    private void showMessage(String message) {
        messageArea.append(message + "\n");
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount();
        account.setBalance(2000); // Set initial balance

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ATMGUI(account);
            }
        });
    }
}

class BankAccount {
    private double balance;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public void deposit(double amount) {
        balance += amount;
    }
}



