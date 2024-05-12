package project;

import java.awt.EventQueue;

import project.window.PMWindow;


/**
 * The Main class contains the entry point of the project management application.
 * It creates and displays the main window of the application.
 */
public class Main {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PMWindow frame = new PMWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
