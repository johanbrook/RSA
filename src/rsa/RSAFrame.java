package rsa;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JOptionPane;


public class RSAFrame extends JFrame {
	
	private final int WIDTH = 400;
	private final int HEIGHT = 300;

	private JTextArea 	resultArea;
	private JTextArea 	input;
	private JButton		decrypt;
	private JButton		encrypt;
	
	private RSA tool;
	
	public RSAFrame(){
		super("Johans RSA");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setResizable(false);
				
		setLayout(new GridLayout(3, 1));
		
		String bitLength = JOptionPane.showInputDialog(this, "Please provide a bit length");
		
		if(bitLength == null){
			System.exit(0);
		}
		else{
			try{
				tool = new RSA(Integer.parseInt(bitLength));
			}
			catch(NumberFormatException e){
				JOptionPane.showMessageDialog(this, "Please provide a legal integer!");
			}
		}
		
		
		JLabel pubKey = new JLabel(tool.getPublicKey().toString());
		
		Font resultFont = new Font("Monaco", Font.PLAIN, 13);
		
		resultArea = new JTextArea(8, 20);
		input = new JTextArea(8, 20);
		resultArea.setFont(resultFont);
		input.setFont(resultFont);
		resultArea.setMargin(new Insets(5, 5, 5, 5));
		input.setMargin(new Insets(5, 5, 5, 5));
	
		
		decrypt = new JButton("Decrypt");
		encrypt = new JButton("Encrypt");
		
		decrypt.addActionListener(new ButtonListener());
		encrypt.addActionListener(new ButtonListener());
		
		
		JPanel buttonPane = new JPanel();
		buttonPane.add(encrypt);
		buttonPane.add(decrypt);
		buttonPane.add(pubKey);
		
		add(input);
		add(buttonPane);
		add(resultArea);
		
		pack();
	}
	
	
	private class ButtonListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e){
			String result;
			
			if(e.getSource() == encrypt){
				result = tool.encrypt(input.getText().trim());
			}else{
				result = tool.decrypt(input.getText().trim());
			}
			
			resultArea.setText(result);
			
		}
	}
	
	
}
