package Projektmanagement;

import java.awt.GridLayout;
import javax.swing.*;

public class UserInterface {
	
	public void createWindow() {
		
			
		JFrame frame = new JFrame("Projekt Management");
		frame.setSize(800, 800);
		frame.setLayout(new GridLayout(8, 8));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel inputPanel = new JPanel();
		JPanel tablePanel = new JPanel();
		
		JLabel inputTitle = new JLabel("Formular", JLabel.CENTER);
		inputTitle.setLocation(400, 10);		
		JLabel firstname = new JLabel("firstname:");
		firstname.setLocation(300, 550);
		
		JTextField fnInput = new JTextField();
		fnInput.setSize(300, 300);
		fnInput.setLocation(100, 560);
		
		
		JLabel tableTitle = new JLabel("Übersicht", JLabel.CENTER);
		
		
		inputPanel.add(inputTitle);
		inputPanel.add(firstname);
		inputPanel.add(fnInput);
		tablePanel.add(tableTitle);
		
		JSplitPane sl = new JSplitPane(SwingConstants.HORIZONTAL, inputPanel, tablePanel);
		sl.setDividerLocation(400);
		
		frame.add(sl);
		
		frame.setVisible(true);
	}

}
