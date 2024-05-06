package Projektmanagement;

import javax.swing.*;

public class UserInterface {
	
	public void createWindow() {
		
			
		JFrame frame = new JFrame();
		frame.setSize(800, 800);
		frame.setTitle("Projekt Management");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel inputPanel = new JPanel();
		JPanel tablePanel = new JPanel();
		
		JLabel inputTitle = new JLabel("Formular");
		JLabel firstname = new JLabel("firstname:");
		
		JTextField fnInput = new JTextField();
		fnInput.setSize(100, 10);
		
		
		JLabel tableTitle = new JLabel("Übersicht");
		
		
		inputPanel.add(inputTitle);
		inputPanel.add(firstname);
		inputPanel.add(fnInput);
		tablePanel.add(tableTitle);
		
		JSplitPane sl = new JSplitPane(SwingConstants.HORIZONTAL, inputPanel, tablePanel);
		
		frame.add(sl);
		
		frame.setVisible(true);
	}

}
