package Projektmanagement;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.*;

public class UserInterface {
	
	public void createWindow() {
		
			
		JFrame frame = new JFrame("Projekt Management");
		frame.setSize(800, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel topPanel = new JPanel();
		JPanel bottomPanel = new JPanel();		
		
		createTopPanel(topPanel);
		createBottomPanel(bottomPanel);
		
		JSplitPane sl = new JSplitPane(SwingConstants.HORIZONTAL, topPanel, bottomPanel);
		sl.setDividerLocation(400);
			
		
		frame.add(sl);
		
		frame.setVisible(true);
	}
	
	private void createTopPanel(JPanel panel) {
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
	    gbl.setConstraints(panel, gbc);		
        panel.setLayout(gbl);
        
        
        JLabel l = null;
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(createLabel(l, "Formular"), gbc);
		
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(createLabel(l, "Firstname:"), gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(createLabel(l, "Lastname:"), gbc);
	}
	
	private void createBottomPanel(JPanel panel) {
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
	    gbl.setConstraints(panel, gbc);		
        panel.setLayout(gbl);
        JLabel l = null;
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(createLabel(l, "Hi"), gbc);
		
	}
	
	private JLabel createLabel(JLabel label, String txt) {
		label = new JLabel(txt);
		label.setPreferredSize(new Dimension(60, 20));
		return label;
	}

}
