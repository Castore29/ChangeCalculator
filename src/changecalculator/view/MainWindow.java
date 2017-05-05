
package changecalculator.view;

import changecalculator.controller.CalculatorController;
import changecalculator.controller.CalculatorException;
import changecalculator.model.CalculatorModel;
import changecalculator.model.CalculatorModelListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class MainWindow extends JFrame implements CalculatorModelListener {
    
    private final CalculatorController controller;
    private final CalculatorModel model;

    private final JTextArea resultTextField;
    
    public MainWindow() {
 
        model = new CalculatorModel(this);
        controller = new CalculatorController(model);
        
        
        JLabel amountToPayLabel = new JLabel("Amount to pay:");
        JTextField amountToPayTextField = new JTextField();
        JLabel currencyLabel1 = new JLabel("HUF");

        JLabel amountPaidLabel = new JLabel("Amount paid:");
        JTextField amountPaidTextField = new JTextField();
        JLabel currencyLabel2 = new JLabel("HUF");
        
        JLabel resultLabel = new JLabel("Result:");
        resultTextField = new JTextArea();
        resultTextField.setEditable(false);
        
        JButton calculateButton = new JButton("Calculate");
        calculateButton.addActionListener((ActionEvent e) -> {
            try {
                controller.calculate(amountToPayTextField.getText(),
                                amountPaidTextField.getText());
            } catch (CalculatorException ex) {
                showError(ex.getMessage());
            }    
        });

        GroupLayout layout = new GroupLayout(this.getContentPane());
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
        layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(amountToPayLabel)
                    .addComponent(amountPaidLabel)
                    .addComponent(calculateButton)
                    .addComponent(resultLabel)
                    .addComponent(resultTextField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(amountToPayTextField)
                    .addComponent(amountPaidTextField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(currencyLabel1)
                    .addComponent(currencyLabel2))

        );
        layout.setVerticalGroup(
           layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(amountToPayLabel)
                    .addComponent(amountToPayTextField)
                    .addComponent(currencyLabel1))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(amountPaidLabel)
                    .addComponent(amountPaidTextField)
                    .addComponent(currencyLabel2))
                .addComponent(calculateButton)
                .addComponent(resultLabel)
                .addComponent(resultTextField)
        );

        setTitle("Change Calculator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 400);
        setResizable(true);
        setLocationRelativeTo(null);

        getContentPane().setLayout(layout);
        
    }
    
    public static void showError(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void onStateChanged() {
        resultTextField.setText(controller.getResults());
    }
}
