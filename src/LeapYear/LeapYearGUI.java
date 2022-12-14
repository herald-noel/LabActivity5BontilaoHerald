package LeapYear;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LeapYearGUI extends JFrame {
    private JPanel panel1;
    private JTextField tfYear;
    private JButton btnCheckYear;

    public LeapYearGUI() {
        btnCheckYear.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int year = Integer.parseInt(tfYear.getText());
                    isLeapYear(year);
                } catch (NumberFormatException a) {
                    JOptionPane.showMessageDialog(panel1, "Invalid input. Try again.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    tfYear.setText("");
                }
            }
        });
    }
    public static void main(String[] args) {
        LeapYearGUI app = new LeapYearGUI();
        app.setContentPane(app.panel1);
        app.setSize(500,300);
        app.setDefaultCloseOperation(EXIT_ON_CLOSE);
        app.setTitle("Leap Year Checker");
        app.setLocationRelativeTo(null); // Centers the window upon running
        app.setVisible(true);
    }

    public void isLeapYear(int year) {
        if ((year % 4 == 0 && year % 100 != 0 ) || (year % 400 == 0)) {
            JOptionPane.showMessageDialog(panel1, "Leap year");
            return;
        }
        JOptionPane.showMessageDialog(panel1, "Not a leap year");
    }
}
