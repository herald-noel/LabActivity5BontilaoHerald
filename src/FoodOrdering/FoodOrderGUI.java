package FoodOrdering;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

public class FoodOrderGUI extends JFrame {
    private JPanel pnlMain;
    private JCheckBox cPizza;
    private JRadioButton rbNone;
    private JButton btnOrder;
    private JCheckBox cBurger;
    private JCheckBox cFries;
    private JCheckBox cSoftDrinks;
    private JCheckBox cTea;
    private JCheckBox cSundae;
    private JRadioButton rb5;
    private JRadioButton rb10;
    private JRadioButton rb15;

    private final Hashtable<JCheckBox, Integer> foods;
    private final Hashtable<JRadioButton, Double> discounts;
    public FoodOrderGUI() {
        // Store all the orders in the foods hashtable
        foods = new Hashtable<>();
        foods.put(cPizza, 100);
        foods.put(cBurger, 80);
        foods.put(cFries, 65);
        foods.put(cSoftDrinks, 55);
        foods.put(cTea, 50);
        foods.put(cSundae, 40);
        // Store all the discount in the discounts hashtable
        discounts = new Hashtable<>();
        discounts.put(rbNone, 0.0);
        discounts.put(rb5, 0.05);
        discounts.put(rb10, 0.10);
        discounts.put(rb15, 0.15);
        // Set rbNone Default to true
        rbNone.setSelected(true);
        // Method when Order button is clicked
        btnOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                promptPrice();
            }
        });
    }
    public static void main(String[] args) {
        FoodOrderGUI app = new FoodOrderGUI();
        app.setContentPane(app.pnlMain);
        app.setSize(450, 550);
        app.setDefaultCloseOperation(EXIT_ON_CLOSE);
        app.setTitle("Food Ordering System");
        app.setLocationRelativeTo(null);
        app.setVisible(true);
    }
    // Method that adds the selected food
    public int sumOrder() {
        int total = 0;
        for (JCheckBox food : foods.keySet()) {
            if (food.isSelected()) {
                total += foods.get(food);
            }
        }
        return total;
    }
    // Method that returns the discount
    public Double getDiscount() {
        Double numDiscount = null;
        for (JRadioButton discount : discounts.keySet()) {
            if (discount.isSelected()) {
                numDiscount = discounts.get(discount);
                break;
            }
        }
        return numDiscount;
    }
    public void promptPrice() {
        try {
            int total = sumOrder();
            if (total == 0) {
                throw new Exception();
            }
            double discount = total * getDiscount();
            double price = total - discount;
            JOptionPane.showMessageDialog(pnlMain, String.format("The total price is Php %.2f", price));
        } catch (Exception e) {
            rbNone.setSelected(true);
            JOptionPane.showMessageDialog(pnlMain, "No orders selected. Please select at least one item from " +
                            "the menu.", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            rbNone.setSelected(true); // Set selection of rbNone to true
            for (JCheckBox food : foods.keySet()) {
                if (food.isSelected()) {
                    food.setSelected(false); // Uncheck the checkbox
                }
            }
        }
    }
}
