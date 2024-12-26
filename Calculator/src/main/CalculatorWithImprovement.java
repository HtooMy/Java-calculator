package main;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CalculatorWithImprovement implements ActionListener {

	private JFrame frame;
	private JTextField textField;
	private JPanel buttonPanel;

	private final String[] buttonLabels = { 
			"7", "8", "9", "/", 
			"4", "5", "6", "*", 
			"1", "2", "3", "-", 
			".", "0", "=","+" 
	};

	private double num1 = 0, num2 = 0;
	private String operator = "";

	public CalculatorWithImprovement() {
		frame = new JFrame("Calculator With Improvement");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 400);
		frame.setLayout(new BorderLayout());
		frame.setLocationRelativeTo(null);

		// Text field
		textField = new JTextField();
		textField.setEditable(false);
		frame.add(textField, BorderLayout.NORTH);

		// Button panel
		buttonPanel = new JPanel(new GridLayout(4, 4, 5, 5));
		for (String label : buttonLabels) {
			JButton button = new JButton(label);
			button.addActionListener(this);
			buttonPanel.add(button);
		}
		frame.add(buttonPanel);

		// South Panel
		JPanel southPanel = new JPanel();
		southPanel.setLayout(new GridLayout(0, 2));
		
		// Clear btn
		JButton clrBtn = new JButton("Clear");
		clrBtn.addActionListener(e -> textField.setText(""));
		
		// Delete btn
		JButton delBtn = new JButton("Delete");
		delBtn.addActionListener(e -> {
			String text = textField.getText();
			if(!text.isEmpty()) {
				textField.setText(text.substring(0, text.length()-1));
			}
		});
		
		southPanel.add(clrBtn);
		southPanel.add(delBtn);
		frame.add(southPanel, BorderLayout.SOUTH);

		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new CalculatorWithImprovement();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();

		// \d is a java regex (regular expression) for 0-9
		if (command.matches("\\d") || command.equals(".")) {
			textField.setText(textField.getText() + command);
		} else if (command.equals("+") || command.equals("-") || command.equals("*") || command.equals("/")) {
			operator = command;
			num1 = Double.parseDouble(textField.getText());
			textField.setText("");
		} else if (command.equals("=")) {
		    num2 = Double.parseDouble(textField.getText());
			double result = switch (operator) {
			   case "+" -> num1 + num2;
			   case "-" -> num1 - num2;
			   case "*" -> num1 * num2;
			   case "/" -> num1 / num2;
			   default -> 0;
			};
			
			textField.setText(String.valueOf(result));
		}
	}

}
