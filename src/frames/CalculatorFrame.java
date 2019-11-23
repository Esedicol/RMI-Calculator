package frames;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.server.ServerNotActiveException;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import main.Client;
import main.Server;

public class CalculatorFrame implements ActionListener {
	public static JTextField inputBox;
	public static JTextArea message;

	public static Client cl = new Client();
	public static ServerFrame sf;
	public static Server sv;
	public static boolean connected = Client.connect();
	Stack<String> stack = new Stack<String>();
	String operator;

	public static void main(String[] args) {
		if(connected == true) {
			createWindow();
			message.append(" Succesfully connected to the Sever");
			message.append(inputBox.getText());
		} else {
			message.append(" Failed to connect to the Sever");
		}
	}

	private static void createWindow() {          
		JFrame frame = new JFrame("RMI Calculator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		createUI(frame);
		frame.setSize(300, 300);            
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	private static void createUI(JFrame frame) {
		JPanel panel = new JPanel();
		CalculatorFrame calculator = new CalculatorFrame();
		GridBagLayout layout = new GridBagLayout();          
		GridBagConstraints gbc = new GridBagConstraints();
		panel.setLayout(layout);

		inputBox = new JTextField(10); 
		inputBox.setEditable(false);

		JButton button0 = new JButton("0");JButton button1 = new JButton("1");
		JButton button2 = new JButton("2");JButton button3 = new JButton("3");
		JButton button4 = new JButton("4");JButton button5 = new JButton("5");
		JButton button6 = new JButton("6");JButton button7 = new JButton("7");
		JButton button8 = new JButton("8");JButton button9 = new JButton("9");

		JButton buttonPlus = new JButton("+");JButton buttonMinus = new JButton("-");
		JButton buttonDivide = new JButton("/");JButton buttonMultiply = new JButton("x");
		JButton buttonClear = new JButton("C");JButton buttonDot = new JButton(".");
		JButton buttonEquals = new JButton("=");

		button1.addActionListener(calculator);button2.addActionListener(calculator);
		button3.addActionListener(calculator);button4.addActionListener(calculator);
		button5.addActionListener(calculator);button6.addActionListener(calculator);
		button7.addActionListener(calculator);button8.addActionListener(calculator);
		button9.addActionListener(calculator);button0.addActionListener(calculator);

		buttonPlus.addActionListener(calculator);buttonMinus.addActionListener(calculator);
		buttonDivide.addActionListener(calculator);buttonMultiply.addActionListener(calculator);
		buttonClear.addActionListener(calculator);buttonDot.addActionListener(calculator);
		buttonEquals.addActionListener(calculator);

		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0; gbc.gridy = 1; panel.add(button1, gbc);        
		gbc.gridx = 1; gbc.gridy = 1; panel.add(button2, gbc);
		gbc.gridx = 2; gbc.gridy = 1; panel.add(button3, gbc);
		gbc.gridx = 3; gbc.gridy = 1; panel.add(buttonPlus, gbc);
		gbc.gridx = 0; gbc.gridy = 2; panel.add(button4, gbc);
		gbc.gridx = 1; gbc.gridy = 2; panel.add(button5, gbc);
		gbc.gridx = 2; gbc.gridy = 2; panel.add(button6, gbc);
		gbc.gridx = 3; gbc.gridy = 2; panel.add(buttonMinus, gbc);
		gbc.gridx = 0; gbc.gridy = 3; panel.add(button7, gbc);
		gbc.gridx = 1; gbc.gridy = 3; panel.add(button8, gbc);
		gbc.gridx = 2; gbc.gridy = 3; panel.add(button9, gbc);
		gbc.gridx = 3; gbc.gridy = 3; panel.add(buttonDivide, gbc);
		gbc.gridx = 0; gbc.gridy = 4; panel.add(buttonDot, gbc);
		gbc.gridx = 1; gbc.gridy = 4; panel.add(button0, gbc);
		gbc.gridx = 2; gbc.gridy = 4; panel.add(buttonMultiply, gbc);
		gbc.gridx = 3; gbc.gridy = 4; panel.add(buttonEquals, gbc);
		gbc.gridwidth = 3;

		gbc.gridx = 0; gbc.gridy = 0; panel.add(inputBox, gbc);        
		gbc.gridx = 3; gbc.gridy = 0; panel.add(buttonClear, gbc);


		frame.getContentPane().add(panel, BorderLayout.NORTH); 

		message = new JTextArea();
		frame.getContentPane().add(message);
	}   

	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (command.charAt(0) == 'C') {                      
			inputBox.setText("");
		}
		else if (command.charAt(0) == '=') {                    
			ArrayList<String> values = calculate(inputBox.getText());

			int val1 = Integer.parseInt(values.get(0));
			int val2 = Integer.parseInt(values.get(1));

			if(operator == "+") {
				int ans = cl.calculate("add", val1, val2);
				message(ans, val1, val2, "Add");
			} else if(operator == "-") {
				int ans = cl.calculate("subtract", val1, val2);
				message(ans, val1, val2, "Subtract");
			} else if(operator == "x") {
				int ans = cl.calculate("multiply", val1, val2);
				message(ans, val1, val2, "Multiply");
			} else if(operator == "/") {
				int ans = cl.calculate("divide", val1, val2);
				message(ans, val1, val2, "Division");
			}
		}
		else { 
			if(isOperator(command)) {
				operator = command;
			}
			inputBox.setText(inputBox.getText() + command);
		}
	}

	public void message(int ans, int v1, int v2, String op) {
		inputBox.setText(Integer.toString(ans));
		message.setText(" Operand 1: " + v1 
				+ "\n Opernad 2: " + v2
				+ "\n Operation: " + op
				+ "\n [FROM SERVER] Total: " 
				+ ans);
	}

	public ArrayList<String> calculate(String s) {
		ArrayList<String> elements = new ArrayList<String>();

		// delete white spaces
		s = s.replaceAll(" ", "");
		char[] arr = s.toCharArray();

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {

			if (arr[i] >= '0' && arr[i] <= '9') {
				sb.append(arr[i]);

				if (i == arr.length - 1) {
					stack.push(sb.toString());
				}
			} else {
				if (sb.length() > 0) {
					stack.push(sb.toString());
					sb = new StringBuilder();
				}
			}	
		}
		while (!stack.isEmpty()) {
			String elem = stack.pop();
			elements.add(0, elem);
		}

		return elements;
	}


	public boolean isOperator(String s) {
		if(s == "+" || s == "-" || s == "x" || s == "/") {
			return true;
		} else {
			return false;
		}
	}

} 