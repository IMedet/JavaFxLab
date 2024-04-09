import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame {

    private JTextField display;

    private double num1 = 0;
    private double num2 = 0;
    private char operation = ' ';

    public Calculator() {
        setTitle("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 400);
        setLayout(new BorderLayout());

        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.PLAIN, 20));
        add(display, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 5, 5));

        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "C", "0", "=", "+"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.PLAIN, 20));
            button.addActionListener(new ButtonClickListener());
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            switch (command) {
                case "C":
                    display.setText("");
                    num1 = 0;
                    num2 = 0;
                    operation = ' ';
                    break;
                case "+":
                case "-":
                case "*":
                case "/":
                    if (!display.getText().isEmpty()) {
                        num1 = Double.parseDouble(display.getText());
                        operation = command.charAt(0);
                        display.setText("");
                    }
                    break;
                case "=":
                    if (!display.getText().isEmpty()) {
                        num2 = Double.parseDouble(display.getText());
                        double result = calculate(num1, num2, operation);
                        display.setText(Double.toString(result));
                    }
                    break;
                default:
                    display.setText(display.getText() + command);
                    break;
            }
        }
    }

    private double calculate(double num1, double num2, char operation) {
        switch (operation) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                if (num2 != 0)
                    return num1 / num2;
                else
                    return Double.NaN; // Indicate division by zero
            default:
                return Double.NaN; // Invalid operation
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Calculator().setVisible(true);
        });
    }
}

