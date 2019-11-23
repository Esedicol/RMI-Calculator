package frames;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * @author Emmanuel Sedicol
 */
public class ServerFrame extends JPanel  {
	public JTextArea message;
	public JFrame frame; 
	
	// Simple server frame to display server messages 
	public ServerFrame() {
		frame =  new JFrame("Server Frame");
		frame.setBounds(300, 100, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		message = new JTextArea();
		message.setBounds(0, 0, 600, 430);
		frame.getContentPane().add(message);

		JButton exit = new JButton("EXIT");
		exit.setBounds(0, 430, 250, 50);
		frame.getContentPane().add(exit);
		exit.addActionListener(e -> System.exit(1)); 

		JButton clear = new JButton("CLEAR");
		clear.setBounds(250, 430, 250, 50);
		frame.getContentPane().add(clear);
		clear.addActionListener(e -> message.setText(""));
		frame.setVisible(true);
	}

	// Methdod to append text to text area
    public void displayOperations(String s){
        message.append(s + '\n');
    }

}