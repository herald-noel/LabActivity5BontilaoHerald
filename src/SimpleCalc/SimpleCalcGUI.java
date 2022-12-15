package SimpleCalc;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleCalcGUI extends JFrame {
    private JPanel pnlMain;
    private JTextField tfNumber1;
    private JComboBox cbOperations;
    private JButton btnCompute;
    private JTextField tfNumber2;
    private JTextField lblResult;

    public SimpleCalcGUI() {
        btnCompute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setLblResult();
            }
        });
    }

    public static void main(String[] args) {
        SimpleCalcGUI app = new SimpleCalcGUI();
        app.setContentPane(app.pnlMain);
        app.setSize(550,300);
        app.setDefaultCloseOperation(EXIT_ON_CLOSE);
        app.setTitle("Simple Calculator");
        app.setLocationRelativeTo(null); // Centers the window upon running
        app.setVisible(true);
    }
    public double toDouble (String n) {
        return Double.parseDouble(n);
    }

    public double calc(double a, double b, String op) {
        double answer = 0;
        switch (op) {
            case "+":
                answer = a + b;
                break;
            case "-":
                answer = a - b;
                break;
            case "*":
                answer = a * b;
                break;
            case "/":
                answer = a / b;
                break;
        }
        return answer;
    }

    public void setLblResult() {
        try {
            double a = toDouble(tfNumber1.getText());
            double b = toDouble(tfNumber2.getText());
            String op = (String) cbOperations.getSelectedItem();
            assert op != null;
            double answer = calc(a, b, op);
            if (Double.isInfinite(answer) || Double.isNaN(answer)) {
                throw new ArithmeticException();
            }
            // When dividing by a negative number
            if (answer == -0) {
                answer = 0;
            }
            lblResult.setText(String.format("%.2f", answer));
        } catch (NumberFormatException f) {
            JOptionPane.showMessageDialog(pnlMain, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ArithmeticException f) {
            JOptionPane.showMessageDialog(pnlMain, "Cannot divide by zero", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
