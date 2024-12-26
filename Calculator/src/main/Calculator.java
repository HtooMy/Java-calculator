package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

// implemented ActionListener because i wanna to reuse it across multiple components
public class Calculator implements ActionListener{

	JFrame frame;
	JTextField textField;
	JButton[] numberButtons = new JButton[10];
	JButton[] functionButtons = new JButton[8];
	JButton addButton, subButton, mulButton, divButton;
	JButton decButton, equButton, delButton, clrButton;
	JPanel centerPanel;
	JPanel bottomPanel;
	
	// The numbers to be calculated and its result
	double num1 = 0, num2 = 0, result = 0;
	
	char operator; // User choice of operator
	
	public Calculator() {
		
		frame = new JFrame("Calculator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(420, 550);
		frame.setLayout(new BorderLayout());
		frame.setLocationRelativeTo(null);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setPreferredSize(new Dimension(50, 50));
		frame.add(textField, BorderLayout.NORTH);
		
		// Operation buttons initation
		addButton = new JButton("+");
		subButton = new JButton("-");
		mulButton = new JButton("*");
		divButton = new JButton("/");
		decButton = new JButton(".");
		equButton = new JButton("=");
		delButton = new JButton("Delete");
		clrButton = new JButton("Clear");
		
		// adding opertion button into arraylist
		functionButtons[0] = addButton;
		functionButtons[1] = subButton;
		functionButtons[2] = mulButton;
		functionButtons[3] = divButton;
		functionButtons[4] = decButton;
		functionButtons[5] = equButton;
		functionButtons[6] = delButton;
		functionButtons[7] = clrButton;
		
		for(int i = 0; i < 8; i++) {
			functionButtons[i].addActionListener(this);
		}
		
		// number buttons initiation
		for(int i = 0; i < 10; i++) {
			numberButtons[i] = new JButton(String.valueOf(i));
			numberButtons[i].addActionListener(this);
		}
		
		// Adding number buttons and operation buttons into the center panel
		centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(4, 4));
		centerPanel.add(numberButtons[1]);
		centerPanel.add(numberButtons[2]);
		centerPanel.add(numberButtons[3]);
		centerPanel.add(addButton);
		centerPanel.add(numberButtons[4]);
		centerPanel.add(numberButtons[5]);
		centerPanel.add(numberButtons[6]);
		centerPanel.add(subButton);
		centerPanel.add(numberButtons[7]);
		centerPanel.add(numberButtons[8]);
		centerPanel.add(numberButtons[9]);
		centerPanel.add(mulButton);
		centerPanel.add(decButton);
		centerPanel.add(numberButtons[0]);
		centerPanel.add(equButton);
		centerPanel.add(divButton);
		frame.add(centerPanel, BorderLayout.CENTER);
		
		bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(1, 2));
		bottomPanel.add(delButton);
		bottomPanel.add(clrButton);
		frame.add(bottomPanel, BorderLayout.SOUTH);
		
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		Calculator main = new Calculator();
	}

	@Override 
	public void actionPerformed(ActionEvent e) {
		
		for(int i = 0; i < 10; i++) {
			if(e.getSource() == numberButtons[i]) {
				textField.setText(textField.getText().concat(String.valueOf(i)));
			}
		}
		
		if(e.getSource() == decButton) {
			textField.setText(textField.getText().concat("."));
		}
		
		if(e.getSource() == addButton) {
			num1 = Double.parseDouble(textField.getText());
			operator = '+';
			textField.setText("");
		}
		
		if(e.getSource() == subButton) {
			num1 = Double.parseDouble(textField.getText());
			operator = '-';
			textField.setText("");
		}
		
		if(e.getSource() == mulButton) {
			num1 = Double.parseDouble(textField.getText());
			operator = '*';
			textField.setText("");
		}
		
		if(e.getSource() == divButton) {
			num1 = Double.parseDouble(textField.getText());
			operator = '/';
			textField.setText("");
		}
		
		if(e.getSource() == equButton) {
			num2 = Double.parseDouble(textField.getText());
			
			switch(operator) {
				case '+':
					result = num1 + num2;
					break;
				case '-':
					result = num1 - num2;
					break;
				case '*':
					result = num1 * num2;
					break;
				case '/':
					result = num1 / num2;
					break;
			}
			
			textField.setText(String.valueOf(result));
		}
		
		if(e.getSource() == delButton) {
			String string = textField.getText();
			
			// Check if the string is not empty to avoid errors
			if (!string.isEmpty()) { 
		        textField.setText(string.substring(0, string.length() - 1));
		    }
		}
		
		if(e.getSource() == clrButton) {
			textField.setText("");
		}
	}
}
